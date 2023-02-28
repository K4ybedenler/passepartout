package data

import dev.inmo.tgbotapi.bot.ktor.telegramBot
import dev.inmo.tgbotapi.extensions.api.chat.get.getChat
import dev.inmo.tgbotapi.extensions.api.deleteMessage
import dev.inmo.tgbotapi.extensions.api.send.copyMessage
import dev.inmo.tgbotapi.extensions.behaviour_builder.buildBehaviourWithLongPolling
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onContentMessage
import dev.inmo.tgbotapi.extensions.utils.extendedSupergroupChatOrThrow
import dev.inmo.tgbotapi.types.message.CommonGroupContentMessageImpl
import dev.inmo.tgbotapi.types.message.content.PhotoContent
import dev.inmo.tgbotapi.types.message.content.TextContent

val bot = telegramBot("5896153553:AAGgTDtZU22IGsV_nSpGMXwct93d8BO1fss")

suspend fun haha() {
    bot.buildBehaviourWithLongPolling {
        onContentMessage {


            val massassa = it
            val massassakaka = it.content
            val massID = massassa.messageId
            val thisChatID = getChat(it.chat).id


            // properties of current chat
            val thisChat = getChat(massassa.chat).extendedSupergroupChatOrThrow()


            // getting id of linked chat
            val idOfChannelFromGroup = thisChat.linkedChannelChatId


            fun checkPermissions(): Boolean {

                val A: Boolean = idOfChannelFromGroup != null && massassa is CommonGroupContentMessageImpl
                return A
            }


            if (checkPermissions() && massassakaka is TextContent && massassakaka.text.contains("") || checkPermissions() && massassakaka is PhotoContent) {
                copyMessage(idOfChannelFromGroup!!, thisChatID, massID)
                deleteMessage(thisChatID, massID)
            }


        }
    }.join()
}