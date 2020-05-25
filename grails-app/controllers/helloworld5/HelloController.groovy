package helloworld5

import grails.converters.JSON

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
        respond "post: $data"
    }


//    def postTest(){
////        def id = params.id
//        def data = request.JSON
//        log.info("---post param: ")
//    }
}
