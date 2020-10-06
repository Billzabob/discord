package dissonance.model.phil

import cats.implicits._
import io.circe.generic.semiauto._
import io.circe.{Decoder, Encoder}

case class Match(gameId: Long, queueId: Int, participants: List[Participant], participantIdentities: List[ParticipantIdentity], gameDuration: Int) {
  def player(accountId: String): Participant = {
    val result = for {
      pId <- participantIdentities.find(_.player.accountId == accountId)
      ps  <- participants.find(_.participantId == pId.participantId)
    } yield ps
    result.get
  }

  def damagePercentage(accountId: String): Double = {
    val p            = player(accountId)
    val toatalDamage = participants.filter(_.teamId == p.teamId).foldMap(_.stats.totalDamageDealtToChampions)
    val playerDamage = p.stats.totalDamageDealtToChampions
    (playerDamage / toatalDamage.toDouble)
  }
}

object Match {
  implicit val matchDecoder: Decoder[Match] = deriveDecoder
  implicit val matchEncoder: Encoder[Match] = deriveEncoder
}
