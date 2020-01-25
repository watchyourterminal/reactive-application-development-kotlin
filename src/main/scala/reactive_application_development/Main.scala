package reactive_application_development

import java.util.Locale

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorSystem, Behavior}

object Main extends App {
  val system = ActorSystem(GuideSystem(), GuideSystem.toString)

  system ! GuideSystem.Start
}

case object GuideSystem {

  sealed trait Command
  case object Start extends Command

  def apply(): Behavior[Command] =
    Behaviors.setup { context =>
      val guidebook = context.spawn(Guidebook(), Guidebook.toString)
      val tourist = context.spawn(Tourist(), Tourist.toString)

      Behaviors.receiveMessage {
        case Start => {
          tourist ! Tourist.Start(Locale.getISOCountries, guidebook)
          Behaviors.same
        }
      }
    }
}
