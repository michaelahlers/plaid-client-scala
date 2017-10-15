package plaid.client.akka.request.common

import com.plaid.{ client => reference }
import org.scalatest.Matchers._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
trait ToReferenceComparisons {

	def verifyProduct(sample: Product, actual: reference.request.common.Product) = {
		import Product._
		import reference.request.common.Product._

		sample match {
			case Authentication => actual should be(AUTH)
			case Balance => actual should be(BALANCE)
			case CreditDetails => actual should be(CREDIT_DETAILS)
			case Identity => actual should be(IDENTITY)
			case Income => actual should be(INCOME)
			case Transactions => actual should be(TRANSACTIONS)
		}
	}

}

object ToReferenceComparisons extends ToReferenceComparisons
