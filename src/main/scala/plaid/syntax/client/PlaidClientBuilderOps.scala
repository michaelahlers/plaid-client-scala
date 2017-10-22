package plaid.syntax.client

import com.plaid.client.PlaidClient.Builder
import plaid.client.Credential.Key
import plaid.client.{ Credential, CredentialProvider }
import plaid.syntax.client.ToPlaidClientBuilderOps._

import scala.language.implicitConversions

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
case class PlaidClientBuilderOps(builder: Builder) extends AnyVal {

	def publicKey(key: Option[Key]): Builder =
		key.map(builder.publicKey)
			.getOrElse(builder)

	def credential(credential: Credential): Builder = {
		import credential._
		import client._

		builder
			.clientIdAndSecret(id, secret)
			.publicKey(public)
	}

	def credentialFrom(provider: CredentialProvider): Builder =
		provider.credential
			.map(credential)
			.getOrElse(builder)

}

trait ToPlaidClientBuilderOps {
	implicit def implyPlaidClientBuilderOps(b: Builder): PlaidClientBuilderOps = PlaidClientBuilderOps(b)
}

object ToPlaidClientBuilderOps extends ToPlaidClientBuilderOps
