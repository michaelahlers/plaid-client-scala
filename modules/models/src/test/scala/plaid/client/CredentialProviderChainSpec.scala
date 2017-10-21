package plaid.client

import org.scalacheck._
import org.scalatest._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import plaid.syntax.client.credentialProviders._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
class CredentialProviderChainSpec extends FlatSpec with Matchers
	with GeneratorDrivenPropertyChecks {

	it must "provide first available credential" in {
		forAll(Gen.listOf(Generators.CredentialProviders.gen)) { providers =>
			whenever {
				providers.nonEmpty && {
					/* Exercise having a credential at the head, only in the tail, or none at all. */
					providers.head.credential.nonEmpty ||
						(providers.head.credential.isEmpty && providers.tail.flatMap(_.credential).nonEmpty) ||
						providers.flatMap(_.credential).isEmpty
				}
			} {
				val credentials = providers.flatMap(_.credential)
				val expected = credentials.headOption
				val chain = providers.reduce(_ | _)
				chain.credential should be(expected)
			}
		}
	}

}
