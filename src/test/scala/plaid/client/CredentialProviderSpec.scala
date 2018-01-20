package plaid.client

import org.scalacheck.Gen
import org.scalacheck.Gen._
import org.scalacheck.ScalacheckShapeless._
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
