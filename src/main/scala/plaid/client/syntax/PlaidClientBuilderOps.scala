package plaid.client.syntax

import com.plaid.client.PlaidClient.Builder
import plaid.client.Credential.Key
import plaid.client.{ Credential, CredentialProvider }
import ToPlaidClientBuilderOps._
import shapeless.tag._

import scala.language.implicitConversions

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
final class PlaidClientBuilderOps(val builder: Builder) extends AnyVal {

	def publicKey(key: Option[String @@ Key]): Builder =
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
	implicit def implyPlaidClientBuilderOps(b: Builder): PlaidClientBuilderOps = new PlaidClientBuilderOps(b)
}

object ToPlaidClientBuilderOps extends ToPlaidClientBuilderOps
