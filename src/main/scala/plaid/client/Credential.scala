package plaid.client

import plaid.client.Credential.Client._
import plaid.client.Credential._
import plaid.client.support.syntax.shapeless.tags._
import shapeless.tag._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
case class Credential(client: Client, secret: Key, public: Option[Key])
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

	case class Client(id: Id)

	def apply(client: Client, secret: Key): Credential = Credential(client, secret, None)
	def apply(client: Client, secret: Key, public: Key): Credential = Credential(client, secret, Some(public))
}
