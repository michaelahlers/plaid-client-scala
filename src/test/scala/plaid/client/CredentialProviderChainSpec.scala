package plaid.client

import org.scalatest._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import plaid.client.CredentialProviderSpec.Generators._
import plaid.syntax.client.credentialProviders._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
class CredentialProviderChainSpec extends WordSpec with Matchers
	with GeneratorDrivenPropertyChecks {

	"Chain" must {

		"provide first available credential" when {
			"at head" in {
				val exemplar = CredentialProviders.samples.filter(_.credential.nonEmpty).head
				val chain = exemplar | CredentialProviders.samples.take(2).reduce(_ | _)
				chain.credential should not be empty
				chain.credential should be(exemplar.credential)
			}
			"at tail" in {
				val exemplar = CredentialProviders.samples.filter(_.credential.nonEmpty).head
				val chain = CredentialProviders.samples.filter(_.credential.isEmpty).take(2).reduce(_ | _) | exemplar
				chain.credential should not be empty
				chain.credential should be(exemplar.credential)
			}
		}

		"provide no credential" when {
			"none are given" in {
				CredentialProviders.samples.filter(_.credential.isEmpty).take(2).reduce(_ | _).credential should be(empty)
			}
		}

	}

}
