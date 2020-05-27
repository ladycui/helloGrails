package helloworld5

class BootStrap {

    def databaseConsumerService

    def init = { servletContext ->

        log.info("==> bootstrap start==")
        databaseConsumerService.start()
    }
    def destroy = {
        databaseConsumerService.shutdown()
        log.info("==> bootstrap end==")
    }
}
