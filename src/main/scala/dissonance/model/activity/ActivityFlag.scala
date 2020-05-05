package dissonance.model.activity

import dissonance.model.BitFlag
import io.circe.Decoder

sealed trait ActivityFlag extends BitFlag with Product with Serializable

object ActivityFlag {
  case object Instance    extends ActivityFlag { val mask = 1 << 0 }
  case object Join        extends ActivityFlag { val mask = 1 << 1 }
  case object Spectate    extends ActivityFlag { val mask = 1 << 2 }
  case object JoinRequest extends ActivityFlag { val mask = 1 << 3 }
  case object Sync        extends ActivityFlag { val mask = 1 << 4 }
  case object Play        extends ActivityFlag { val mask = 1 << 5 }

  val allFlags = List(Instance, Join, Spectate, JoinRequest, Sync, Play)

  implicit val decoder: Decoder[List[ActivityFlag]] = BitFlag.decoder(allFlags)
}