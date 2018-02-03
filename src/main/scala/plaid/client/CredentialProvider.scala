package plaid.client

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
trait CredentialProvider {
	def credential: Option[Credential]
}

object CredentialProvider {
	private case class Direct(credential: Option[Credential]) extends CredentialProvider
	def apply(c: Option[Credential]): CredentialProvider = Direct(c)
	def apply(c: Credential): CredentialProvider = CredentialProvider(Some(c))
}
