package plaid.client

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
trait CredentialProvider {
	def credential: Option[Credential]
}
