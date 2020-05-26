package helloworld5

class ScheduleTestServiceJob {
    static triggers = {
//        simple name:"ScheduleTestService", startDelay: 2000 , repeatInterval: 6000000 //refresh every 6000s
        simple name:"ScheduleTestService", startDelay: 0 , repeatInterval: 10000 //refresh every 10s
    }

//    def execute(){
////    	dmhubTokenService.refreshAll()
////        log.info("==== Starting Job ====")
////        println("----job-----")
//    }
}
