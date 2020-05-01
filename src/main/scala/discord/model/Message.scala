package discord.model

import io.circe.Decoder
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto._
import discord.model.user._

case class Message(
    channelId: String,
    content: String,
    author: User
)

object Message {
  implicit val config: Configuration            = Configuration.default.withSnakeCaseMemberNames
  implicit val messageDecoder: Decoder[Message] = deriveConfiguredDecoder
}
