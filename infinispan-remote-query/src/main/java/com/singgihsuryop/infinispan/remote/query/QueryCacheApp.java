package com.singgihsuryop.infinispan.remote.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.Search;
import org.infinispan.client.hotrod.configuration.Configuration;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.client.hotrod.marshall.ProtoStreamMarshaller;
import org.infinispan.commons.util.Util;
import org.infinispan.protostream.FileDescriptorSource;
import org.infinispan.protostream.SerializationContext;
import org.infinispan.query.dsl.Query;
import org.infinispan.query.dsl.QueryFactory;
import org.infinispan.query.remote.client.ProtobufMetadataManagerConstants;

public class QueryCacheApp {
	

	public static void main(String args[]) throws Exception {
		System.out.println("Running QueryCache App");
		System.setProperty("java.net.preferIPv4Stack", "true");

		Configuration configuration = new ConfigurationBuilder().addServer().host("127.0.0.1").port(11222).marshaller(new ProtoStreamMarshaller()).build();
//		Configuration configuration = new ConfigurationBuilder()
//				.addServers("127.0.0.1:11322")
//				.addServers("127.0.0.1:11422")
//				.marshaller(new ProtoStreamMarshaller())
//				.build();
		RemoteCacheManager cacheManager = new RemoteCacheManager(configuration);
		
		RemoteCache<String, String> metadataCache = cacheManager.getCache(
	            ProtobufMetadataManagerConstants.PROTOBUF_METADATA_CACHE_NAME);
	        metadataCache.put(
	            "quickstart/person.proto",
	            Util.read(QueryCacheApp.class.getResourceAsStream("/quickstart/person.proto")));
	        
		SerializationContext serCtx = ProtoStreamMarshaller.getSerializationContext(cacheManager);
		FileDescriptorSource fileDescriptorSource = new FileDescriptorSource();
		fileDescriptorSource.addProtoFiles("quickstart/person.proto");
		serCtx.registerProtoFiles(fileDescriptorSource);
		serCtx.registerMarshaller(new PersonMarshaller());
		
		
		
		RemoteCache<String, Person> cache = cacheManager.getCache("PERSON_CACHE");
		List<Person> resultList;
		
		QueryFactory<?> queryFactory = Search.getQueryFactory(cache);
		Query query = queryFactory.from(Person.class).build();
		resultList = query.list();
		System.out.println("Find All " +resultList);
		
		query = queryFactory.from(Person.class).having("name").eq("Singgih").toBuilder().build();
		resultList = query.list();
		System.out.println("find By Name " +resultList);
		
		query = queryFactory.from(Person.class)
				.having("age").eq(25).toBuilder().build();
		resultList = query.list();
		System.out.println("find By Age " +resultList);
		
		query = queryFactory.from(Person.class)
				.not().having("age").lt(50)
				.and()
				.not().having("age").gt(60).toBuilder().build();
		
		resultList = query.list();
		System.out.println("find By Age that not less than 50 nor greater than 60 " +resultList);
		
//		
//		Set<String> setLong =  new HashSet<>();
//		setLong.add("Singgih");
//		setLong.add("Sierra");
		
		List<String> list =  new ArrayList<String>();
		list.add("Singgih1");
		list.add("Singgih2");
		
		Map<String, String> map =  new HashMap<String, String>();
		map.put("Singgih1", null);
		map.put("Singgih2", null);
		
		query = queryFactory.from(Person.class)
				.having("name").containsAny(map).toBuilder().build();
		resultList = query.list();
		System.out.println("Contains any " +resultList);
		//quickstart.Person _gen0 WHERE _gen0.name = {Singgih2=null, Singgih1=null}
		
		query = queryFactory.from(Person.class)
				.having("name").containsAll(map).toBuilder().build();
		resultList = query.list();
		System.out.println("Contains all " +resultList);
		
		//quickstart.Person _gen0 WHERE _gen0.name = {Singgih2=null, Singgih1=null}
		
	}
}
