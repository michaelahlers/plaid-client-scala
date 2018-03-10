package plaid.client

import org.scalacheck.Gen._
import org.scalacheck.ScalacheckShapeless._
import org.scalatest._
import org.scalatest.prop.GeneratorDrivenPropertyChecks._
import plaid.client.CredentialProviderSpec.Generators._
import plaid.client.syntax.credentials._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
class CredentialProviderOpsSpec extends WordSpec with Matchers {
	"Chained credentials" must {
		"provide first available" in {
			forAll(nonEmptyListOf(CredentialProviders.gen)) { providers =>
				val exemplar = providers.flatMap(_.credential).headOption
				val chain = providers.reduce(_ | _)
				chain.credential should be(exemplar)
			}
		}
	}
}
