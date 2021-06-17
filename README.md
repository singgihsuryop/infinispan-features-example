# infinispan-features-example
Infinispan is an open-source in-memory data grid that offers flexible deployment options and robust capabilities for storing, managing, and processing data. Infinispan provides a key/value data store that can hold all types of data, from Java objects to plain text. Infinispan distributes your data across elastically scalable clusters to guarantee high availability and fault tolerance, whether you use Infinispan as a volatile cache or a persistent data store.


**infinispan-embedded** is an example repositories which focuses on infinispan cache located or embedded in your Java Application. **infinispan-remote** is an example repositories of Java Application which integrates to separated Infinispan Server.



****_Features in this repositories_****

Distributed Execution: Extended from ExecutorService, Infinispan Distributed Execution has ability to perform tasks on an entire cluster of Infinispan nodes

Map Reduce: Adopt from Google’s original MapReduce, Infinispan Map Reduce has ability to performs filtering and sorting using a parallel, distributed algorithm on a cluster.

Eviction: control the size of the data container by removing entries when the container becomes larger than a configured threshold

Expiration: limits the amount of time entries can exist

Listener: Get notifications when changes occur to the data store or Infinispan clusters.

Persistence: Store data both in memory and durable persistent locations (File, Database).

Query: allows relational and full-text search with straightforward syntax

