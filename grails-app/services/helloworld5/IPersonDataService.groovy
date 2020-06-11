package helloworld5

import grails.gorm.services.Query

interface IPersonDataService {
    Person findByNameChinese(String name)


    Number count()

    @Query("""\
    select ${p} from ${Person p} where ${p.nameEnglish} = $name
""")
    List<Person> findAllByName(String name)
}