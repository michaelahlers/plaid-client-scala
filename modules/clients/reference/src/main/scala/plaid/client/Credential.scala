package plaid.client

import plaid.client.Credential.Client._
import plaid.client.Credential._
import plaid.client.commons.shapeless._
import shapeless.tag._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
case class Credential(client: Client, secret: String @@ Key, public: Option[String @@ Key])
object Credential {
	trait Key
	object Key extends Tagger[Key]

	object Client {
		trait Id
		object Id extends Tagger[Id]
	}

	case class Client(id: String @@ Id)

	def apply(client: Client, secret: String @@ Key): Credential = Credential(client, secret, None)
	def apply(client: Client, secret: String @@ Key, public: String @@ Key): Credential = Credential(client, secret, Some(public))
}
