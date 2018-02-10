from kafka import KafkaProducer

TOPIC_NAME = 'some-topic'
producer = KafkaProducer()

message_content = b'hello world!'
producer.send(TOPIC_NAME, message_content)
producer.flush()
