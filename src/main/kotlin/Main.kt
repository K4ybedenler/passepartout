import dev.inmo.tgbotapi.bot.ktor.telegramBot
import dev.inmo.tgbotapi.extensions.api.bot.getMe
import dev.inmo.tgbotapi.extensions.api.chat.get.getChat
import dev.inmo.tgbotapi.types.chat.ExtendedSupergroupChat
import dev.inmo.tgbotapi.extensions.api.send.reply
import dev.inmo.tgbotapi.extensions.api.send.sendMessage
import dev.inmo.tgbotapi.extensions.behaviour_builder.buildBehaviourWithLongPolling
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onCommand
import dev.inmo.tgbotapi.extensions.utils.extendedSupergroupChatOrNull
import dev.inmo.tgbotapi.extensions.utils.extendedSupergroupChatOrThrow
import dev.inmo.tgbotapi.requests.send.CopyMessage
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.chat.ExtendedChat


suspend fun main() {
    haha()
//    return haha()
}

suspend fun haha() {
    val bot = telegramBot("5896153553:AAGgTDtZU22IGsV_nSpGMXwct93d8BO1fss")
    bot.buildBehaviourWithLongPolling {
        onCommand("say") {

            // first try
            var tryNumOne = getChat(it.chat.id)
            println(tryNumOne)

            // properties of current chat
            var thisChat = getChat(it.chat).extendedSupergroupChatOrThrow()
            println("thisChat ---- $thisChat")

            // getting id of linked chat
            var idOfChannelFromGroup = thisChat?.linkedChannelChatId!!
            println("idOfChannelFromGroup ---- $idOfChannelFromGroup")

            sendMessage(idOfChannelFromGroup, "hahaha")
//            CopyMessage(idOfChannelFromGroup, idOfCurretChat,)

        }
    }.join()
}