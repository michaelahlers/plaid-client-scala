package plaid.client

import plaid.client.Credential.Client._
import plaid.client.Credential._
import plaid.client.CredentialProvider.Named._
import shapeless.tag._

import scala.collection.Map

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
trait CredentialProvider {
	def credential: Option[Credential]
}

object CredentialProvider {

	private case class Direct(credential: Option[Credential]) extends CredentialProvider
	def direct(c: Option[Credential]): CredentialProvider = Direct(c)
	def direct(c: Credential): CredentialProvider = direct(Some(c))

	object Named {
		trait Name
		object Name extends Tagger[Name]
		case class Names(clientId: String @@ Name, secretKey: String @@ Name, publicKey: String @@ Name)
	}

	case class Named[A <: Map[String, String]](names: Names, values: A) extends CredentialProvider {
		override def credential =
			for {
				clientId <- values.get(names.clientId)
				secretKey <- values.get(names.secretKey)
				publicKey = values.get(names.publicKey).map(Key(_))
			} yield Credential(Client(Id(clientId)), Key(secretKey), publicKey)
	}

	val systemProperties: CredentialProvider = Named(Names(Name("plaid.client.id"), Name("plaid.secret.key"), Name("plaid.public.key")), sys.props)
	val environmentVariables: CredentialProvider = Named(Names(Name("PLAID_CLIENT_ID"), Name("PLAID_SECRET_KEY"), Name("PLAID_PUBLIC_KEY")), sys.env)

}
