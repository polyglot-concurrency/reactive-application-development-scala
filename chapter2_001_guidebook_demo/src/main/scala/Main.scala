import java.util.Locale

import akka.actor.typed.ActorSystem
import Tourist.Start
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors

object Main extends App {

  sealed trait Command

  ActorSystem(Main(), "GuideSystem")

  def apply(): Behavior[Command] = Behaviors.setup { context =>
    val guidebook = context.spawn(Guidebook(), "guidebook")
    val tourist   = context.spawn(Tourist(), "tourist")

    tourist ! Start(Locale.getISOCountries, guidebook)

    Behaviors.same

  }

}
