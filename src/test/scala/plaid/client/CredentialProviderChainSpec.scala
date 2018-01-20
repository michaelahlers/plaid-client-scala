package plaid.client

import org.scalacheck.Gen._
import org.scalacheck.ScalacheckShapeless._
import org.scalatest._
import org.scalatest.prop.GeneratorDrivenPropertyChecks._
import plaid.client.CredentialProviderSpec.Generators._
import plaid.syntax.client.credentialProviders._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
class CredentialProviderChainSpec extends WordSpec with Matchers {
	"Chain" must {
		"provide first available credential" in {
			forAll(nonEmptyListOf(CredentialProviders.gen)) { providers =>
				val exemplar = providers.flatMap(_.credential).headOption
				val chain = providers.reduce(_ | _)
				chain.credential should be(exemplar)
			}
		}
	}
}
