package plaid.client.models.request.common

import com.plaid.client.request.common.Product._
import plaid.client.models.request.common.Product._

import scala.language.implicitConversions

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object ImplicitConversions extends FromReferenceImplicits with ToReferenceImplicits

object FromReferenceImplicits extends FromReferenceImplicits
trait FromReferenceImplicits {
	implicit def implyProduct(v: com.plaid.client.request.common.Product) =
		v match {
			case AUTH => Authentication
			case BALANCE => Balance
			case CREDIT_DETAILS => CreditDetails
			case IDENTITY => Identity
			case INCOME => Income
			case TRANSACTIONS => Transactions
		}
}

object ToReferenceImplicits extends ToReferenceImplicits
trait ToReferenceImplicits {
	implicit def implyProduct(v: Product) = {
		import com.plaid.client.request.common.Product._
		v match {
			case Authentication => AUTH
			case Balance => BALANCE
			case CreditDetails => CREDIT_DETAILS
			case Identity => IDENTITY
			case Income => INCOME
			case Transactions => TRANSACTIONS
		}
	}
}
