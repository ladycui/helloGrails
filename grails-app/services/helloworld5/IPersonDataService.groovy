package helloworld5

import grails.gorm.services.Query

interface IPersonDataService {
    Person findByName(String name)

    Integer findPersonAge(String name)

    Number count()

    @Query("""\
    select ${p} from ${Person p} where ${p.name} = $name
""")
    List<Person> findAllByName(String name)
}