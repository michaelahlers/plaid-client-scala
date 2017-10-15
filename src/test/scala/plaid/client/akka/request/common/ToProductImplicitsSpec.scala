package plaid.client.akka.request.common

import com.plaid.client.request.{ common => reference }
import org.scalatest._
import plaid.client.akka.request.common.ImplicitConversions._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
class ToProductImplicitsSpec extends WordSpec
	with Matchers with Inspectors {

	"Implicit conversion" must {

		s"return ${classOf[Product]} from ${classOf[reference.Product]}" in {
			import Product._
			import reference.Product._

			val scenarios =
				(AUTH, Authentication) ::
					(BALANCE, Balance) ::
					(CREDIT_DETAILS, CreditDetails) ::
					(IDENTITY, Identity) ::
					(INCOME, Income) ::
					(TRANSACTIONS, Transactions) ::
					Nil

			forAll(scenarios) {
				case (reference, expected) =>
					(reference: Product) should be(expected)
			}
		}

	}

}
