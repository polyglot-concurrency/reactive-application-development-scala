import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.ActorRef
import akka.actor.typed.Behavior

object Tourist {

  sealed trait Command

  final case class Guidance(code: String, description: String) extends Command

  final case class Start(codes: Seq[String], replyTo: ActorRef[Guidebook.Command]) extends Command

  def apply(): Behavior[Command] = Behaviors.receive { (ctx, msg) =>
    msg match {
      case Start(codes, replyTo) =>
        codes.foreach(replyTo ! Guidebook.Inquiry(_, ctx.self))
        Behaviors.same

      case Guidance(code, description) =>
        println(s"$code: $description")
        Behaviors.same
    }
  }

}
