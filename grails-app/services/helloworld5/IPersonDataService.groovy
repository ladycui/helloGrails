package helloworld5

import grails.gorm.services.Query

interface IPersonDataService {
    Person findByNameChinese(String name)


    Number count()

    @Query("""\
    select ${p} from ${Person p} where ${p.nameEnglish} = $name
""")
    List<Person> findAllByName(String name)

//    @Query("""\
//    select ${p} from ${Person p} where ${p.nameEnglish} = $name and ${p.ageSchool} = $age
//""")
//    List<Person> findAllByNameAndAge(String name, Integer age)

    @Query("from $Person as person where person.nameEnglish=$englishName")
    List<Person> searchByTitle(String englishName)

    @Query("from $Person as person where person.nameEnglish=$englishName and person.ageSchool=$age")
    List<Person> searchByEnglishNameAndAge(String englishName, Integer age)
}