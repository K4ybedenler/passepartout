package modules

import dev.inmo.tgbotapi.bot.ktor.telegramBot
import dev.inmo.tgbotapi.extensions.api.chat.get.getChat
import dev.inmo.tgbotapi.extensions.api.deleteMessage
import dev.inmo.tgbotapi.extensions.api.send.copyMessage
import dev.inmo.tgbotapi.extensions.api.send.sendMessage
import dev.inmo.tgbotapi.extensions.behaviour_builder.buildBehaviourWithLongPolling
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onContentMessage
import dev.inmo.tgbotapi.extensions.utils.extendedSupergroupChatOrNull
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.caption
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.from
import dev.inmo.tgbotapi.extensions.utils.shortcuts.executeAsync
import dev.inmo.tgbotapi.types.files.PhotoSize
import dev.inmo.tgbotapi.types.message.ChannelContentMessageImpl
import dev.inmo.tgbotapi.types.message.CommonGroupContentMessageImpl
import dev.inmo.tgbotapi.types.message.ConnectedFromChannelGroupContentMessageImpl
import dev.inmo.tgbotapi.types.message.content.DocumentContent
import dev.inmo.tgbotapi.types.message.content.PhotoContent
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.message.content.VideoContent


val bot = telegramBot("5896153553:AAHmgcxA2dwzW22hoYHEzDHbrA6gr5S_rtc")

suspend fun messageInGroup() {
    bot.buildBehaviourWithLongPolling {
        onContentMessage {

            val sms = it
            val smsId = sms.messageId
            val currChatId = getChat(sms.chat).id
            val currChat = getChat(sms.chat)
            val messCont = sms.content
            val userName = sms.from!!.username!!.username


            // checking Message type
            when (sms) {

                // Message came to Channel
                is ChannelContentMessageImpl -> {

                    if (!(messCont as TextContent).text.contains("1.")
                        && messCont.text.contains("#баг")
                    ) {
                        deleteMessage(currChatId, smsId)
                    }
                }


                // Message came to Group
                is CommonGroupContentMessageImpl -> {


                    // getting id of linked chat
                    val idOfChannelFromGroup = (currChat).extendedSupergroupChatOrNull()?.linkedChannelChatId

                    fun checkPermissions(): Boolean {
                        return idOfChannelFromGroup != null
                    }

                    val textPermission =
                        sms.caption!!.contains("#баг")
                                && sms.caption!!.contains("1.")
                                && sms.caption!!.contains("2.")

                    fun checkMessage(): Boolean {
                        return messCont is PhotoContent && textPermission
                                || messCont is DocumentContent && messCont.media.thumb is PhotoSize && textPermission
                                || messCont is VideoContent && textPermission
                                || sms.mediaGroupId != null && textPermission
                    }



                    if (checkPermissions() && checkMessage() && sms.mediaGroupId == null) {
                        copyMessage(idOfChannelFromGroup!!, currChatId, smsId)
                        deleteMessage(currChatId, smsId)
                        // sending username in sme channel
                        sendMessage(currChatId, "$userName")
                    } else if (checkPermissions() && sms.mediaGroupId != null && idOfChannelFromGroup != null && userName != null) {

//                        println(1234)
                        val a = messCont.createResend(idOfChannelFromGroup)
                        executeAsync(a)

                        // sending username in sme channel
                        sendMessage(currChatId, "$userName")
                        deleteMessage(currChatId, smsId)
                    }
                }


                // Message came to Group from connected Channel
                is ConnectedFromChannelGroupContentMessageImpl -> {

                    sendMessage(currChatId, "")
                }

                // other types of Messages
                else -> {

                    sendMessage(currChatId, "")
                }
            }
        }
    }.join()
}