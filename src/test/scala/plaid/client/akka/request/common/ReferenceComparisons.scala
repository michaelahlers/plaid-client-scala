package plaid.client.akka.request.common

import com.plaid.client.request.common.Product._
import com.plaid.{ client => reference }
import org.scalatest.Matchers._
import org.scalatest._
import plaid.client.akka.request.common.Product._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
trait ReferenceComparisons { self: Suite =>

	def verifyProduct(sample: reference.request.common.Product, actual: Product) =
		sample match {
			case AUTH => actual should be(Authentication)
			case BALANCE => actual should be(Balance)
			case CREDIT_DETAILS => actual should be(CreditDetails)
			case IDENTITY => actual should be(Identity)
			case INCOME => actual should be(Income)
			case TRANSACTIONS => actual should be(Transactions)
		}

}
