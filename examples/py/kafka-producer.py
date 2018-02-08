from kafka import KafkaProducer

producer = KafkaProducer()

producer.send('some-topic', b'hello world!')
producer.flush()
