package plaid.client

import plaid.client.support.syntax.shapeless.tags._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object CredentialSpec {

	object Generators extends Generators
	trait Generators {

		import org.scalacheck._
		import Arbitrary._
		import Gen._

		object Credentials {

			object Clients {
				import Credential.Client
				import Client.Tags._

				val gen: Gen[Client] =
					for {
						id <- identifier
					} yield {
						Client(
							id = id.tagged[Id]
						)
					}
			}

			val gen: Gen[Credential] =
				for {
					client <- Clients.gen
					secret <- arbitrary[String]
					publicO <- option(arbitrary[String])
				} yield {
					import Credential.Tags._

					Credential(
						client = client,
						secret = secret.tagged[Key],
						public = publicO.map(_.tagged[Key])
					)
				}

		}
	}

}
