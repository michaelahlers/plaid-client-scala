package plaid.client.models.request.common

import com.plaid.client.request.common.Product._
import com.plaid.client.request.common.{ Product => ReferenceProduct }
import org.scalatest.Inspectors._
import org.scalatest.Matchers._
import org.scalatest._
import plaid.client.models.request.common.ImplicitConversions._
import plaid.client.models.request.common.Product._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
class ImplicitConversionsSpec extends WordSpec {

	"From reference" when {

		"product" in {
			forAll(ReferenceProduct.values().toList) { product =>
				(product: Product) should be {
					product match {
						case AUTH => Authentication
						case BALANCE => Balance
						case CREDIT_DETAILS => CreditDetails
						case IDENTITY => Identity
						case INCOME => Income
						case TRANSACTIONS => Transactions
					}

				}
			}

		}
	}

	"To reference" when {

		"product" in {
			forAll(Product.values) { product =>
				(product: ReferenceProduct) should be {
					product match {
						case Authentication => AUTH
						case Balance => BALANCE
						case CreditDetails => CREDIT_DETAILS
						case Identity => IDENTITY
						case Income => INCOME
						case Transactions => TRANSACTIONS
					}
				}
			}
		}

	}

}
