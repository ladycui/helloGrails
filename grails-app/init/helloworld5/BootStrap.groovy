package helloworld5

class BootStrap {

    def databaseConsumerService

    def init = { servletContext ->

        log.info("==> bootstrap start==")
        databaseConsumerService.start()
    }
    def destroy = {
        log.info("==> bootstrap end==")
        databaseConsumerService.shutdown()
    }
}
