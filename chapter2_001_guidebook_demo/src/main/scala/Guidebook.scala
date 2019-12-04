import akka.actor.typed.ActorRef
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import java.util.Currency
import java.util.Locale

import Tourist.Guidance

object Guidebook {

  sealed trait Command

  final case class Inquiry(code: String, replyTo: ActorRef[Tourist.Command]) extends Command

  def describe(locale: Locale) =
    s"""In ${locale.getDisplayCountry}, ${locale.getDisplayLanguage} is spoken and the currency is the ${Currency
      .getInstance(locale)
      .getDisplayName}"""

  def apply(): Behavior[Command] = Behaviors.receive { (ctx, msg) =>
    msg match {
      case Inquiry(code, replyTo) =>
        println(s"Actor ${ctx.self.path.name} responding to inquiry about $code")
        Locale.getAvailableLocales.filter(_.getCountry == code).foreach { locale =>
          replyTo ! Guidance(code, describe(locale))
        }
        Behaviors.same
    }
  }
}
