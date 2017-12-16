package plaid.syntax.client

import com.plaid.client.PlaidClient.Builder
import org.mockito.ArgumentMatchers._
import org.mockito.Mockito._
import org.scalatest._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import plaid.client.Credential
import plaid.syntax.client.ToPlaidClientBuilderOps._

import plaid.client.CredentialSpec.Generators._
import plaid.client.CredentialProviderSpec.Generators._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
class PlaidClientBuilderOpsSpec extends FlatSpec
	with Matchers
	with MockitoSugar
	with GeneratorDrivenPropertyChecks {

	def verifyCredential(credential: Credential, method: Builder => Builder): Any = {
		val builder = mock[Builder]

		when(builder.clientIdAndSecret(anyString(), anyString())).thenReturn(builder)
		when(builder.publicKey(anyString())).thenReturn(builder)

		method(builder)

		verify(builder).clientIdAndSecret(credential.client.id, credential.secret)
		credential.public match {
			case Some(public) => verify(builder).publicKey(public)
			case None => verify(builder, never()).publicKey(anyString())
		}
	}

	it must "assign credential" in {
		forAll(Credentials.gen) { credential =>
			verifyCredential(credential, _.credential(credential))
		}
	}

	it must "assign from provider" in {
		forAll(CredentialProviders.gen) { provider =>
			provider.credential match {
				case Some(credential) => verifyCredential(credential, _.credentialFrom(provider))
				case None =>
					val builder = mock[Builder]
					verify(builder, never()).clientIdAndSecret(anyString(), anyString())
					verify(builder, never()).publicKey(anyString())
			}
		}
	}

}
