package ahlers.plaid.product.request.common

import com.plaid.client.request.{ common => plaid }
import org.scalatest._
import ImplicitConversions._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
class ToProductImplicitsSpec extends WordSpec with Matchers {

	"Implicit conversion" that {
		s"takes ${classOf[plaid.Product]}" must {
			val scenarios =
				(plaid.Product.AUTH, Product.Authentication) ::
					(plaid.Product.BALANCE, Product.Balance) ::
					(plaid.Product.CREDIT_DETAILS, Product.CreditDetails) ::
					(plaid.Product.IDENTITY, Product.Identity) ::
					(plaid.Product.INCOME, Product.Income) ::
					(plaid.Product.TRANSACTIONS, Product.Transactions) ::
					Nil

			scenarios foreach {
				case (p, expected) =>
					s"convert $p to $expected" in {
						implyProduct(p) should be(expected)
					}
			}
		}
	}

}
