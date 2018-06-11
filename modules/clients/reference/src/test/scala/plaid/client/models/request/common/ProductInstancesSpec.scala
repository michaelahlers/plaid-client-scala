package plaid.client.models.request.common

import org.scalatest.Inspectors._
import org.scalatest.Matchers._
import org.scalatest._
import plaid.client.models.request.common.Product._
import plaid.client.models.request.common.ProductInstances._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
class ProductInstancesSpec extends WordSpec {

	"Inject product" must {

		"apply" in {
			forAll(Product.values) { product =>
				import com.plaid.client.request.common.Product._
				InjectRefProduct.apply(product) should be {
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

		"unapply" in {
			import com.plaid.client.request.common.Product._
			forAll(values().toList) { product =>
				InjectRefProduct.unapply(product) should contain {
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

}
