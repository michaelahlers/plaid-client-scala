package plaid.syntax.client

import plaid.client.{ CredentialProvider, CredentialProviderChain }

import scala.language.implicitConversions

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
final case class CredentialProviderOps(self: CredentialProvider) extends AnyVal {
	def |(tail: CredentialProvider): CredentialProvider = CredentialProviderChain(self, tail)
}

trait ToCredentialProviderOps {
	implicit def implyCredentialProviderOps(p: CredentialProvider): CredentialProviderOps = CredentialProviderOps(p)
}
