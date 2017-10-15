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
				public <- Gen.option(Arbitrary.arbString.arbitrary)
			} yield {
				import Credential.Tags._

				Credential(
					client = client,
					secret = secret.tagged[Key],
					public = public.map(_.tagged[Key])
				)
			}
	}

}
