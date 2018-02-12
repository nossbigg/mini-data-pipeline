# mini-data-pipeline
A quick way to deploy a mini data pipeline 

## Components
- [Hadoop](http://hadoop.apache.org/): An unstructured data store
- [Kafka](https://kafka.apache.org/): A message queue
- [Spark](https://spark.apache.org/): An in-memory job runner
- [Zookeeper](https://zookeeper.apache.org/): A distributed systems coordinator
    - *A necessary Kafka dependency*

## Docker Images Used
- Hadoop: [sequenceiq](https://hub.docker.com/r/sequenceiq/spark/)
    - Comes installed with Spark binaries for experimentation
- Kafka: [wurstmeister](https://hub.docker.com/r/wurstmeister/kafka/)
- Spark: [p7hb](https://hub.docker.com/r/p7hb/docker-spark/)
- Zookeeper:  [Apache](https://hub.docker.com/_/zookeeper/)

## Ports
- Hadoop
    - 50010, 50020, 50070, 50075, 50090, 8020, 9000: HDFS
    - 10020, 19888: MapReduce
    - 8030, 8031, 8032, 8033, 8040, 8042, 8088: YARN
    - 49707, 2122: Other
- Kafka
    - 9092
- Spark
    - 8080: Spark Web UI
    - 7077: Job endpoint
- Spark (Slave)
    - 8081: Web UI
- Zookeeper
    - 2181: For clients (eg. Kafka) to connect to
    
Reference: [Apache Ambari v1.2.3 Chapter 10. Configuring Ports
](https://ambari.apache.org/1.2.3/installing-hadoop-using-ambari/content/reference_chap2.html)