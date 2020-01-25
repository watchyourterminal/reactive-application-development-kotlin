package reactive_application_development

import java.util.{Currency, Locale}

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, Behavior}

case object Guidebook {
  sealed trait Command
  case class Inquiry(code: String, replyTo: ActorRef[Tourist.Command]) extends Command

  def apply(): Behavior[Command] =
    Behaviors.receive { (context, message) =>
      message match {
        case Inquiry(code, replyTo) =>
          context.log.info(s"Actor ${context.self.path.name} responding to inquiry about $code")
          Locale.getAvailableLocales.
            filter(_.getCountry == code).
            foreach { locale =>
              replyTo ! Tourist.Guidance(code, describe(locale), context.self)
            }
          Behaviors.same
      }
    }

  private def describe(locale: Locale) =
    s"""
       |In ${locale.getDisplayCountry},
       |${locale.getDisplayLanguage} is spoken and the currency
       |is the ${Currency.getInstance(locale).getDisplayName}
       |""".stripMargin
}
