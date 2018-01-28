package plaid.syntax.client

import plaid.client.{ CredentialProvider, CredentialProviderChain }

import scala.language.implicitConversions

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
final class CredentialProviderOps(val self: CredentialProvider) extends AnyVal {
	def |(tail: CredentialProvider): CredentialProvider = CredentialProviderChain(self, tail)
	def isEmpty: Boolean = self.credential.isEmpty
	def isDefined: Boolean = self.credential.isDefined
}

trait ToCredentialProviderOps {
	implicit def implyCredentialProviderOps(p: CredentialProvider): CredentialProviderOps = new CredentialProviderOps(p)
}
