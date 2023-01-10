import dev.inmo.tgbotapi.bot.ktor.telegramBot
import dev.inmo.tgbotapi.extensions.api.bot.getMe
import dev.inmo.tgbotapi.extensions.api.send.reply
import dev.inmo.tgbotapi.extensions.behaviour_builder.buildBehaviourWithLongPolling
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onCommand

suspend fun main() {
    val bot = telegramBot("TOKEN")

    bot.buildBehaviourWithLongPolling {
//        println(getMe())

        onCommand("start") {




            reply(it, "Hi:)")
        }
    }.join()
}
