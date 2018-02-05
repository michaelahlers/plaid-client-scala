package plaid.client

import org.scalacheck.Arbitrary._
import org.scalacheck.Gen._
import org.scalacheck.ScalacheckShapeless._
import org.scalacheck._
import org.scalamock.scalatest.MockFactory
import org.scalatest.Matchers._
import org.scalatest._
import org.scalatest.prop.GeneratorDrivenPropertyChecks._
import plaid.client.CredentialSpec.Generators._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object CredentialProviderSpec {
	object Generators {
		object CredentialProviders {
			private case class DirectCredentialProvider(credential: Option[Credential]) extends CredentialProvider
			val gen: Gen[CredentialProvider] = option(Credentials.gen).map(DirectCredentialProvider)
		}
	}
}

class CredentialProviderSpec extends WordSpec with MockFactory {

	implicit val arbCredential = Arbitrary(Credentials.gen)

	"Direct" must {
		import CredentialProvider.direct
		"provide given credential" in {
			forAll { credential: Option[Credential] =>
				direct(credential).credential should be(credential)
			}
		}
	}

	"Named" must {
		import CredentialProvider.{ Named, environmentVariables, systemProperties }
		import Named._

		implicit val arbName = Arbitrary {
			for {
				clientId <- arbitrary[String]
				secretKey <- arbitrary[String]
				publicKey <- arbitrary[String]
				if clientId.trim.nonEmpty && secretKey.trim.nonEmpty && publicKey.trim.nonEmpty
				if 3 == Set(clientId, secretKey, publicKey).size
			} yield Names(Name(clientId), Name(secretKey), Name(publicKey))
		}

		"specify correct names and values" when {
			"providing system properties" in {
				systemProperties should have(
					'values(sys.props),
					'names(Names(
						Name("plaid.client.id"),
						Name("plaid.secret.key"),
						Name("plaid.public.key")
					))
				)
			}
			"providing environment variables" in {
				environmentVariables should have(
					'values(sys.env),
					'names(Names(
						Name("PLAID_CLIENT_ID"),
						Name("PLAID_SECRET_KEY"),
						Name("PLAID_PUBLIC_KEY")
					))
				)
			}
		}

		"provide equivalent credential" in {
			forAll { (names: Names, credential: Credential) =>
				val provider = Named(names, {
					val values =
						Some(names.clientId -> credential.client.id) ::
							Some(names.secretKey -> credential.secret) ::
							credential.public.map(names.publicKey -> _) ::
							Nil

					values.flatten.toMap[String, String]
				})

				provider.credential should contain(credential)
			}
		}

		"provide nothing" when {
			"missing required values" in {
				/* Using implicit arbitrary syntax always fails here; fix by providing explicit generator. */
				forAll(arbitrary[Names], option(arbitrary[String]), option(arbitrary[String]), option(arbitrary[String])) { (names, clientId, secretKey, publicKey) =>
					whenever(clientId.isEmpty || secretKey.isEmpty) {
						val provider = Named(names, {
							val values =
								clientId.map(names.clientId -> _) ::
									secretKey.map(names.secretKey -> _) ::
									publicKey.map(names.publicKey -> _) ::
									Nil

							values.flatten.toMap[String, String]
						})

						provider.credential should be(empty)
					}
				}
			}
		}

	}

}
