package plaid.client.models.request.common

import com.plaid.client.request.common.{ Product => ReferenceProduct }
import org.scalatest._
import plaid.client.models.request.common.ImplicitConversions._
import Matchers._
import Inspectors._
import com.plaid.client.request.common.Product._
import plaid.client.models.request.common.Product._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
class ImplicitConversionsSpec extends FlatSpec {

	it must s"convert from reference product" in {
		forAll(ReferenceProduct.values().toList) { sample =>
			val expected =
				sample match {
					case AUTH => Authentication
					case BALANCE => Balance
					case CREDIT_DETAILS => CreditDetails
					case IDENTITY => Identity
					case INCOME => Income
					case TRANSACTIONS => Transactions
				}

			val actual: Product = sample
			actual should be(expected)
		}
	}

	it must s"convert to reference product" in {
		forAll(Product.values) { sample =>
			val expected =
				sample match {
					case Authentication => AUTH
					case Balance => BALANCE
					case CreditDetails => CREDIT_DETAILS
					case Identity => IDENTITY
					case Income => INCOME
					case Transactions => TRANSACTIONS
				}

			val actual: ReferenceProduct = sample
			actual should be(expected)
		}
	}

}
