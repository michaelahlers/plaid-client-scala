package plaid.client.akka.request.common

import com.plaid.client.request.{ common => reference }

import scala.language.implicitConversions

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object ImplicitConversions
	extends ToProductImplicits
	with ToReferenceImplicits

trait ToProductImplicits {

	implicit def implyProduct(v: reference.Product): Product = {
		import reference.Product._

		v match {
			case AUTH => Product.Authentication
			case BALANCE => Product.Balance
			case CREDIT_DETAILS => Product.CreditDetails
			case IDENTITY => Product.Identity
			case INCOME => Product.Income
			case TRANSACTIONS => Product.Transactions
		}
	}

}

trait ToReferenceImplicits {

	implicit def implyProduct(v: Product): reference.Product = {
		import reference.Product._
		v match {
			case Product.Authentication => AUTH
			case Product.Balance => BALANCE
			case Product.CreditDetails => CREDIT_DETAILS
			case Product.Identity => IDENTITY
			case Product.Income => INCOME
			case Product.Transactions => TRANSACTIONS
		}
	}

}
