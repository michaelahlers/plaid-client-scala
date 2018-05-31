package plaid.client.models.request.common

import com.plaid.client.request.common.Product._
import cats._
import org.scalatest.Inspectors._
import org.scalatest.Matchers._
import org.scalatest._
import plaid.client.models.request.common.Product._
import ProductInstances._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
class ProductInstancesSpec extends WordSpec {

	"Inject product" must {

		"apply" in {
			forAll(com.plaid.client.request.common.Product.values().toList) { product =>
				InjectProduct.apply(product) should be {
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

		"unapply" in {
			forAll(Product.values) { product =>
				InjectProduct.unapply(product) should contain {
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
