import java.sql.Timestamp


databaseChangeLog = {

    changeSet(author: "cuiyanna", id: "create table person_migration") {
        preConditions(onFail: "MARK_RAN") {
            not {
                tableExists(tableName: "person_migration")
            }
        }
        createTable(tableName: "person_migration") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }
            column(name: "version", type: "INT") {
                constraints(nullable: "false")
            }
            column(name: "name_chinese", type: "VARCHAR(64)") {
                constraints(nullable: "false")
            }
            column(name: "name_english", type: "VARCHAR(64)") {
                constraints(nullable: "false")
            }
            column(name: "age_school", type: "INT") {
                constraints(nullable: "false")
            }
            column(name: "create_time", type: "DATETIME", defaultValueDate: new Timestamp(System.currentTimeMillis()))

        }
    }
}
