import dev.inmo.tgbotapi.bot.ktor.telegramBot
import dev.inmo.tgbotapi.extensions.api.chat.get.getChat
import dev.inmo.tgbotapi.extensions.api.send.sendMessage
import dev.inmo.tgbotapi.extensions.behaviour_builder.buildBehaviourWithLongPolling
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onCommand
import dev.inmo.tgbotapi.extensions.utils.extendedSupergroupChatOrThrow

suspend fun main() {
    haha()
//    return haha()
}

suspend fun haha() {
    val bot = telegramBot("5896153553:AAGgTDtZU22IGsV_nSpGMXwct93d8BO1fss")
    bot.buildBehaviourWithLongPolling {
        onCommand("say") {

            // properties of current chat
            val thisChat = getChat(it.chat).extendedSupergroupChatOrThrow()
            println("thisChat ---- $thisChat")

            // getting id of linked chat
            val idOfChannelFromGroup = thisChat.linkedChannelChatId!!
            println("idOfChannelFromGroup ---- $idOfChannelFromGroup")

            sendMessage(idOfChannelFromGroup, "hahaha")

        }
    }.join()
}