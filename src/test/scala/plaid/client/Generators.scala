package plaid.client

import ahlers.plaid.syntax.shapeless.tags._
import org.scalacheck._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object Generators {

	object Credentials {
		object Clients {
			val gen =
				for {
					id <- Gen.identifier
				} yield {
					import Credential.Client
					import Client.Tags._

					Client(
						id = id.tagged[Id]
					)
				}
		}

		val gen =
			for {
				client <- Clients.gen
				secret <- Arbitrary.arbString.arbitrary
				publicO <- Gen.option(Arbitrary.arbString.arbitrary)
			} yield {
				import Credential.Tags._

				Credential(
					client = client,
					secret = secret.tagged[Key],
					public = publicO.map(_.tagged[Key])
				)
			}
	}

	object CredentialProviders {
		case class DirectCredentialProvider(credential: Option[Credential]) extends CredentialProvider

		val gen: Gen[CredentialProvider] =
			for {
				credentialO <- Gen.option(Credentials.gen)
			} yield DirectCredentialProvider(credentialO)
	}

}
