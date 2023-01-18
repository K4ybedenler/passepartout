import dev.inmo.tgbotapi.bot.ktor.telegramBot
import dev.inmo.tgbotapi.extensions.api.chat.get.getChat
import dev.inmo.tgbotapi.extensions.api.send.copyMessage
import dev.inmo.tgbotapi.extensions.api.send.sendMessage
import dev.inmo.tgbotapi.extensions.behaviour_builder.buildBehaviourWithLongPolling
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onCommand
import dev.inmo.tgbotapi.extensions.utils.extendedSupergroupChatOrThrow
import dev.inmo.tgbotapi.requests.ForwardMessage

val bot = telegramBot("TOKEN")

suspend fun main() {
    haha()
//    return haha()
}

suspend fun haha() {
    bot.buildBehaviourWithLongPolling {
        onCommand("say") {

            // properties of current chat
            val thisChat = getChat(it.chat).extendedSupergroupChatOrThrow()
            println("thisChat ---- $thisChat")

            val ttttis = thisChat.id

            // getting id of linked chat
            val idOfChannelFromGroup = thisChat.linkedChannelChatId!!
            println("idOfChannelFromGroup ---- $idOfChannelFromGroup")

            ForwardMessage(idOfChannelFromGroup, ttttis,   )
//            copyMessage(idOfChannelFromGroup, it,  )
            sendMessage(idOfChannelFromGroup, "hahaha")

        }
    }.join()
}