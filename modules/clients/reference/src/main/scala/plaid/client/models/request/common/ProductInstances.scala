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
	implicit object InjectProduct extends Inject[com.plaid.client.request.common.Product, Product] {
		override val inj = {
			case AUTH => Authentication
			case BALANCE => Balance
			case CREDIT_DETAILS => CreditDetails
			case IDENTITY => Identity
			case INCOME => Income
			case TRANSACTIONS => Transactions
		}
		override val prj = {
			case Authentication => AUTH.some
			case Balance => BALANCE.some
			case CreditDetails => CREDIT_DETAILS.some
			case Identity => IDENTITY.some
			case Income => INCOME.some
			case Transactions => TRANSACTIONS.some
		}
	}
}
