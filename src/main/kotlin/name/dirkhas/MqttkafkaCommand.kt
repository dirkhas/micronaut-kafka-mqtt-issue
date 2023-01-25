package name.dirkhas

import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.Topic
import io.micronaut.configuration.picocli.PicocliRunner
import io.micronaut.context.ApplicationContext
import io.micronaut.messaging.annotation.MessageBody

import picocli.CommandLine
import picocli.CommandLine.Command
import picocli.CommandLine.Option
import picocli.CommandLine.Parameters

@Command(name = "mqttkafka", description = ["..."],
        mixinStandardHelpOptions = true)
class MqttkafkaCommand : Runnable {

    override fun run() {
        println("Hi!")
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            PicocliRunner.run(MqttkafkaCommand::class.java, *args)
        }
    }

    @KafkaListener(groupId = "test")
    class KafkaConsumer {
        @Topic("test")
        fun onMessage(
            @KafkaKey
            key: String,
            @MessageBody
            message: String
        ) {
            println("Message received from kafka topic \"test\" with key \"$key\" and message \"$message\"")
        }
    }
}
