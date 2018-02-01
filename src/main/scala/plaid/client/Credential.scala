package plaid.client

import plaid.client.support.syntax.shapeless.tags._
import shapeless.tag._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
case class Credential(client: Credential.Client, secret: Credential.Key, public: Option[Credential.Key])
object Credential {
	object Tags {
		trait Key
	}

	type Key = String @@ Tags.Key
	object Key {
		def apply(v: String): Key = v.tagged[Tags.Key]
	}

	object Client {
		object Tags {
			trait Id
		}

		type Id = String @@ Tags.Id
		object Id {
			def apply(v: String): Id = v.tagged[Tags.Id]
		}

	}

	case class Client(id: Client.Id)

	def apply(client: Credential.Client, secret: Credential.Key): Credential = Credential(client, secret, None)
	def apply(client: Credential.Client, secret: Credential.Key, public: Credential.Key): Credential = Credential(client, secret, Some(public))
}
