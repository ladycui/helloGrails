package helloworld5

import com.alibaba.fastjson.JSONObject
import com.convertlab.errors.CommonErrors
import grails.gorm.transactions.Transactional

class PersonService {



    @Transactional
    def testSave(json) {
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
            def save = person.save(flush: true)
        } catch (Exception e) {
            log.error("---saving error--, ${e.message}")
//            e.printStackTrace()
//            return customFailResponse(CommonErrors.GENERAL_INTERNAL_ERROR, "inner error")
            return "catch"
        }

        log.info("--end---$person")
        return customeResponse(new JSONObject())
    }
    def customeResponse(JSONObject response) {
        response.put("status", "success")
        return response
    }

    def customFailResponse(CommonErrors error, msg = null) {
        def response = new JSONObject()
        def errorBody = new JSONObject()
        errorBody.put("code", error.code)
        errorBody.put("message", msg ?: error.description)
        response.put("error", errorBody)
        return response
    }
}
