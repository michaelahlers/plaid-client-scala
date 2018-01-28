package plaid.syntax.client

import plaid.client.Credential

import scala.language.implicitConversions

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
final class CredentialOps(val self: Credential) extends AnyVal {
}

trait ToCredentialOps {
	implicit def implyCredentialOps(c: Credential): CredentialOps = new CredentialOps(c)
}
