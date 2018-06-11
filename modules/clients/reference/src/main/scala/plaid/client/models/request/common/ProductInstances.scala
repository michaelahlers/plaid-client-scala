package plaid.client.models.request.common

import cats._
import cats.implicits._
import com.plaid.client.request.common.Product._
import plaid.client.models.request.common.Product._

import scala.language.implicitConversions

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object ProductInstances extends ProductInstances
trait ProductInstances {
	implicit object InjectRefProduct extends Inject[Product, RefProduct] {
		override val inj = {
			case Authentication => AUTH
			case Balance => BALANCE
			case CreditDetails => CREDIT_DETAILS
			case Identity => IDENTITY
			case Income => INCOME
			case Transactions => TRANSACTIONS
		}
		override val prj = {
			case AUTH => Authentication.some
			case BALANCE => Balance.some
			case CREDIT_DETAILS => CreditDetails.some
			case IDENTITY => Identity.some
			case INCOME => Income.some
			case TRANSACTIONS => Transactions.some
		}
	}
}
