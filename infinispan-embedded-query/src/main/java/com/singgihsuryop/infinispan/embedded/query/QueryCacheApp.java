package com.singgihsuryop.infinispan.embedded.query;

import java.util.List;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.query.Search;
import org.infinispan.query.dsl.Query;
import org.infinispan.query.dsl.QueryFactory;

public class QueryCacheApp {
	

	public static void main(String args[]) throws Exception {
		System.out.println("Running QueryCache App");
		System.setProperty("java.net.preferIPv4Stack", "true");

		EmbeddedCacheManager manager = new DefaultCacheManager("infinispan-7.2-config.xml");
		Cache<String, Person> cache = manager.getCache("personCache");
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
	}
}
