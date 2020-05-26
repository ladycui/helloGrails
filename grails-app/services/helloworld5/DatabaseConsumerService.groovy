package helloworld5

import com.convertlab.kafka.KafkaConsumerManager
import org.springframework.beans.factory.annotation.Value
//import redis.clients.jedis.Jedis

class DatabaseConsumerService extends KafkaConsumerManager {

    @Value('${kafkaServer.bootstrap.servers}')
    String bootstrapServers

    @Value('${kafka.youzan_channel.topic:test_topic}')
    String topic

    @Value('${kafka.youzan_channel.groupId:test_groupid}')
    String groupId

    @Value('${kafka.youzan_channel.numConsumers:10}')
    Integer numConsumers

//    def redisService
//    def mmsFlowService

    @Override
    void processKafkaMessage(String key, Map message) {
        log.info("--- receive message is:${message}")

        def flag = "mms::flowSend:${key}"
        def notBlocked = false

    }
}
