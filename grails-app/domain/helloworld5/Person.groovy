package helloworld5

import groovy.transform.ToString

@ToString
class Person {

    static mapping = {
        table 'person_migration'
    }

    String nameChinese
    String nameEnglish
    Integer ageSchool
    Date createTime = new Date()
    Integer age
}
