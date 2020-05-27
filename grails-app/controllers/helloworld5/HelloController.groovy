package helloworld5

import grails.converters.JSON
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Value

class HelloController {
    def index() {
        render "hello world5!"
    }

    def getTest() {
        log.info("----get: $id -----")
        render $id
    }

    def getTest2() {
        def id = params.id
        log.info("----get2: $id ---")
        render "getTest2: $id"
    }

    def postTest() {
        def data = request.JSON
        log.info("---post: $data ---")
        render "post: $data"
    }

    def postTest2() {
        def id = params.id
        def data = request.JSON
        log.info("---post: id: $id, data: $data ---")
//        render "post: $data"
        response << $data
    }

    def kafkaProducerService
    @Value('${kafka.youzan_channel.topic:test_topic}')
    String topic

    def kafkaTest() {
        def data = request.JSON
        log.info("---kafaka get json: $data")
        log.info("---kafaka get json: ${data.key}")
//        public void send(String topic, Object key, Map<String, Object> data) {
        Map<String, Object> map = new HashMap<>()
        map.put("data", data)
        if (kafkaProducerService == null) {
            log.info("---service is null")
            render "service null"
        }
        kafkaProducerService.send(topic, data.key, map)
        log.info("----kafka send end")

        render "kafka: $data"
    }

    def personDataService

    def mysqlTest() {
        def queryName = "iMperson"
        def person = new Person(name: queryName, age: 10)
        person.save(flush: true)
        log.info("save person: $person")
        def queryPerson = personDataService.findByName(queryName)
        log.info("query person: $queryPerson")
        response << $queryPerson
    }


    def httpTest() {

    }
}
