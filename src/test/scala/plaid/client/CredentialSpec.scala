package plaid.client

import org.scalacheck.Arbitrary._
import org.scalacheck.ScalacheckShapeless._
import org.scalacheck._
import plaid.client.support.syntax.shapeless.tags._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object CredentialSpec {

	object Generators {
		object Credentials {

			object Clients {
				import Credential.Client

				object Ids {
					import Client.Tags._
					val gen: Gen[Client.Id] = arbitrary[String].map(_.tagged[Id])
				}

				val gen: Gen[Client] = {
					implicit val arbId = Arbitrary(Ids.gen)
					arbitrary[Client]
				}
			}

			object Keys {
				import Credential.Tags._
				val gen: Gen[Credential.Key] = arbitrary[String].map(_.tagged[Key])
			}

			val gen: Gen[Credential] = {
				implicit val arbKey = Arbitrary(Keys.gen)
				implicit val arbClient = Arbitrary(Clients.gen)
				arbitrary[Credential]
			}

		}
	}

}
