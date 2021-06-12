package com.singgihsuryop.infinispan.remote.query;

import java.util.UUID;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.Configuration;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.client.hotrod.marshall.ProtoStreamMarshaller;
import org.infinispan.commons.util.Util;
import org.infinispan.protostream.FileDescriptorSource;
import org.infinispan.protostream.SerializationContext;
import org.infinispan.protostream.annotations.ProtoSchemaBuilder;
import org.infinispan.query.remote.client.ProtobufMetadataManagerConstants;

public class PutValueToCacheApp {

	public static void main(String args[]) throws Exception {
		System.out.println("Running PutValueToCache App");
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

		Person person1 = new Person(generateId(), "Singgih1", 25);
		cache.put(person1.getId(), person1);
		System.out.println("Success put person "+person1 +" to cache");
		
		Person person1a = new Person(generateId(), "Singgih2", 25);
		cache.put(person1a.getId(), person1a);
		System.out.println("Success put person "+person1a +" to cache");
		
		Person person2 = new Person(generateId(), "Shawn", 22);
		cache.put(person2.getId(), person2);
		System.out.println("Success put person "+person2 +" to cache");
		
		Person person3 = new Person(generateId(), "Sierra", 55);
		cache.put(person3.getId(), person3);
		System.out.println("Success put person "+person3 +" to cache");
		
		Person person4 = new Person(generateId(), "Simone", 25);
		cache.put(person4.getId(), person4);
		System.out.println("Success put person "+person4 +" to cache");
		
		Person person5 = new Person(generateId(), "Susi", 25);
		cache.put(person5.getId(), person5);
		System.out.println("Success put person "+person5 +" to cache");
		
	}
	
	private static String generateId(){
		return UUID.randomUUID().toString();
	}
}
