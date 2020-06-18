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
            column(name: "is_leader", type: "BOOLEAN", defaultValueBoolean: "false") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cuiyanna", id: "create index idx_person_name_age on table person_migration") {
        preConditions(onFail: "MARK_RAN") {
            not {
                indexExists(indexName: "idx_person_name_age")
            }
        }
        createIndex(tableName: "person_migration", indexName: "idx_person_name_age", unique: "true") {
            column(name: "name_english")
            column(name: "age_school")
        }
    }
}
