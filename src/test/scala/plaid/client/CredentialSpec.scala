package plaid.client

import org.scalacheck.Arbitrary._
import org.scalacheck.ScalacheckShapeless._
import org.scalacheck._
import plaid.client.Credential.Client._
import plaid.client.Credential._
import shapeless.tag._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object CredentialSpec {

	object Generators {
		object Credentials {

			object Clients {
				import Credential.Client

				object Ids {
					val gen: Gen[String @@ Id] = arbitrary[String].map(Id(_))
				}

				val gen: Gen[Client] = {
					implicit val arbId = Arbitrary(Ids.gen)
					arbitrary[Client]
				}
			}

			object Keys {
				val gen: Gen[String @@ Key] = arbitrary[String].map(Key(_))
			}

			val gen: Gen[Credential] = {
				implicit val arbKey = Arbitrary(Keys.gen)
				implicit val arbClient = Arbitrary(Clients.gen)
				arbitrary[Credential]
			}

		}
	}

}
