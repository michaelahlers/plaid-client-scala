package plaid.client.syntax

import plaid.client.CredentialProvider
import CredentialProviderOps.Chain

import scala.language.implicitConversions

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
final class CredentialProviderOps(val self: CredentialProvider) extends AnyVal {
	def |(tail: CredentialProvider): CredentialProvider = Chain(self, tail)
	def isEmpty: Boolean = self.credential.isEmpty
	def isDefined: Boolean = self.credential.isDefined
}

object CredentialProviderOps {
	private case class Chain(head: CredentialProvider, tail: CredentialProvider) extends CredentialProvider {
		override def credential = head.credential orElse tail.credential
	}
}

trait ToCredentialProviderOps {
	implicit def implyCredentialProviderOps(p: CredentialProvider): CredentialProviderOps = new CredentialProviderOps(p)
}
