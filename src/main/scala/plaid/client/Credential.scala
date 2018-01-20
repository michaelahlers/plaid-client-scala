package plaid.client

import shapeless.tag._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object Credential {
	object Tags {
		trait Key
	}

	type Key = String @@ Tags.Key

	object Client {
		object Tags {
			trait Id
		}

		type Id = String @@ Tags.Id
	}

	case class Client(id: Client.Id)
}

case class Credential(
	client: Credential.Client,
	secret: Credential.Key,
	public: Option[Credential.Key]
)
