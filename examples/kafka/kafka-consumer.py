from kafka import KafkaConsumer

consumer = KafkaConsumer('some-topic')

# note: blocking call, will wait for a message to be published on the topic
print(next(consumer))

