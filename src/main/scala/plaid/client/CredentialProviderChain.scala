package plaid.client

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
case class CredentialProviderChain(head: CredentialProvider, tail: CredentialProvider) extends CredentialProvider {
	/* Don't evaluate this eagerly. */
	override def credential = head.credential orElse tail.credential
}
