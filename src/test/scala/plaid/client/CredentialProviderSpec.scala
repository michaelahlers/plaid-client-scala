package plaid.client

import org.scalacheck.Gen

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object CredentialProviderSpec {

	object Generators extends Generators
	trait Generators {

		import CredentialSpec.Generators._
		import Gen._

		object CredentialProviders {
			private case class DirectCredentialProvider(credential: Option[Credential]) extends CredentialProvider

			val gen: Gen[CredentialProvider] =
				for {
					credentialO <- option(Credentials.gen)
				} yield DirectCredentialProvider(credentialO)

			def samples: Stream[CredentialProvider] =
				Stream.continually(gen).flatMap(_.sample)

			def sample(filter: CredentialProvider => Boolean): CredentialProvider =
				samples.filter(filter).head

		}

	}

}
