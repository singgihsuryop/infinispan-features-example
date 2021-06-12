package com.singgihsuryop.infinispan.remote.listener;

import org.infinispan.client.hotrod.annotation.ClientCacheEntryCreated;
import org.infinispan.client.hotrod.annotation.ClientCacheEntryModified;
import org.infinispan.client.hotrod.annotation.ClientCacheEntryRemoved;
import org.infinispan.client.hotrod.annotation.ClientListener;
import org.infinispan.client.hotrod.event.ClientCacheEntryCreatedEvent;
import org.infinispan.client.hotrod.event.ClientCacheEntryModifiedEvent;
import org.infinispan.client.hotrod.event.ClientCacheEntryRemovedEvent;

@ClientListener
public class PersonCacheListener {

	@ClientCacheEntryCreated
	public void entryCreated(ClientCacheEntryCreatedEvent<String> event) {
		System.out.printf("Entry with key %s is created", event.getKey());
	}

	@ClientCacheEntryModified
	public void entryModified(ClientCacheEntryModifiedEvent<String> event) {
		System.out.printf("Entry with key %s is modified", event.getKey());
	}

	@ClientCacheEntryRemoved
	public void entryRemoved(ClientCacheEntryRemovedEvent<String> event) {
		System.out.printf("Entry with key %s is removed", event.getKey());
	}

}
