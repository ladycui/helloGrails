package helloworld5

import com.alibaba.fastjson.JSONObject
import grails.converters.JSON
import grails.gorm.transactions.Transactional
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

    @Transactional
    def addPerson() {
        def json = request.JSON
        log.info("---addPerson request: $json")
        def person
        try {
            person = new Person(json)
            log.info("----$person")
        } catch (Exception e) {
//            e.printStackTrace()
            log.error("${e.message}")
            log.info("--------")
            person = new Person()
            person.nameChinese = "中文名"
            person.nameEnglish = "englishName"
            person.ageSchool = 10
//            person.age = 18//对象中的属性不能比表多，此处age
        }
        try {
            person.save(flush: true)
        } catch (Exception e1) {
            e1.printStackTrace()
        }
        log.info("--end---$person")
        render person.nameChinese
    }

    def personService

    def testSave() {
        def json = request.JSON
        log.info("testSave get params: $json")
        def saveResponse
        saveResponse = personService.testSave(json)
        response << saveResponse ?: "hahah"
    }

    def testGet() {
        def a = params.a
        def b = params.b
        log.info("---a: $a")
        log.info("---b: $b")
        render a
    }

    def queryPersonByEnglishName() {
        def name = params.name
        def age = params.age

        def updatePerson = Person.executeUpdate("update Person p set p.nameEnglish=:newEnglishName where p.nameEnglish=:oldName", [newEnglishName: "new english name", oldName: "english name"])
        log.info("update person : $updatePerson")

        def persons = personDataService.findAllByName(name)
        for (def person in persons) {
            log.info("--------$person")
        }
        log.info("find by name size: ${persons.size}")
        age = Integer.valueOf(age)
        persons = personDataService.findAllByNameAndAge(name, age)
        for (def person in persons) {
            log.info("***$person")
        }
        log.info("find by name and age size: ${persons.size}")

        try {
            def executeQueryPersons = Person.executeQuery("select nameChinese from Person p where p.nameChinese=:chineseName",
                    [chineseName: 'chineseName'])
            log.info("--1&&&--$executeQueryPersons")
        } catch (Exception e) {
            log.error("error happens when query1: ${e.message}")
        }

        //不能使用*
        try {
            def executeQueryPersons = Person.executeQuery("select p.* from Person p where p.nameChinese=:chineseName",
                    [chineseName: 'chineseName'])
            log.info("--2&&&--$executeQueryPersons")
        } catch (Exception e) {
            log.error("error happens when query1.1: ${e.message}")
        }
        try {
            def executeQueryPersons = Person.executeQuery("select nameChinese from Person p where p.nameChinese=:chineseName " +
                    "and ageSchool=:age",
                    [chineseName: 'chineseName', age: 10])
            log.info("--3&&&--$executeQueryPersons")
        } catch (Exception e) {
            log.error("error happens when query1.2: ${e.message}")
        }

        try {
            def query = Person.where {
                nameChinese == "chineseName"
            }
            def bart = query.findAll()
            log.info("query2 got: $bart")
        } catch (Exception e1) {
            log.error("error happens when query2: ${e1.message}")
        }
        try {
            def query = Person.where {
                nameChinese == "chineseName"
                ageSchool == 10
            }
            def bart = query.findAll()
            log.info("query2 got: $bart")
        } catch (Exception e1) {
            log.error("error happens when query2.1: ${e1.message}")
        }
        try {
            def query3 = personDataService.searchByTitle("englishName")
            log.info("#####___query3: $query3")
        } catch (Exception e) {
            log.error("error happens when query3: ${e.message}")
        }
        try {
            def query4 = personDataService.searchByEnglishNameAndAge("englishName", 12)
            log.info("#####___query3: $query4")
        } catch (Exception e) {
            log.error("error happens when query4: ${e.message}")
        }

        render persons as JSON
    }


}
