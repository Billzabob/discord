package discord

import cats.effect._
import cats.implicits._
import discord.model._

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    Discord(args.head).start(handleEvent).as(ExitCode.Success)

  def handleEvent(client: DiscordClient, event: DispatchEvent): IO[Unit] = event match {
    case DispatchEvent.MessageCreate(message) =>
      if (message.content == "ping")
        client.sendMessage("pong", message.channelId).void
      else IO.unit
    case _ =>
      IO.unit
  }
}
