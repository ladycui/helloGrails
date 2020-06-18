package helloworld5

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')

//        "/template/update/$id"(controller: "mmsTemplate", action: "update", method: "POST")
        "/get/$id"(controller:"hello", action: "getTest")
        "/get/$id"(controller:"hello", action: "getTest2", method: "GET")//上面那个就失效了
        "/post"(controller:"hello", action: "postTest", method: "POST")
        // /post?id=1
        "/post/$id"(controller:"hello", action: "postTest2", method: "POST")

        "/kafka"(controller: "hello", action: "kafkaTest", method:"POST")

        "/savePerson"(controller: "hello", action: "mysqlTest")

        "/addPerson"(controller: "hello", action: "addPerson", method: "POST")
        "/savePerson"(controller: "hello", action: "testSave", method: "POST")

        "/queryPerson"(controller: "hello", action: "queryPersonByEnglishName", method: "GET")

        "/testGet"(controller: "hello", action: "testGet", method: "GET")


    }
}
