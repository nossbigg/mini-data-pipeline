from kafka import KafkaConsumer

TOPIC_NAME = 'some-topic'

consumer = KafkaConsumer(TOPIC_NAME)

# note: blocking call, will wait for a message to be published on the topic
print(next(consumer))

