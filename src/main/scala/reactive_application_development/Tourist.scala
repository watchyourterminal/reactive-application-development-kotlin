package reactive_application_development

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, Behavior}

case object Tourist {

  sealed trait Command
  case class Guidance(code: String, description: String, replyTo: ActorRef[Guidebook.Command]) extends Command
  case class Start(codes: Seq[String], replyTo: ActorRef[Guidebook.Command]) extends Command

  def apply(): Behavior[Command] =
    Behaviors.receive { (context, message) =>
      message match {
        case Start(codes, replyTo) =>
          codes.foreach(replyTo ! Guidebook.Inquiry(_, context.self))
          Behaviors.same
        case Guidance(code, description, _) =>
          context.log.info(s"$code: $description")
          Behaviors.same
      }
    }
}
