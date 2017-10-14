package ahlers.plaid.product.common

import enumeratum._

/**
 * Not to be confused with Scala's [[scala.Product]] type.
 *
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object Product extends Enum[Product] {
	val values: _root_.scala.collection.immutable.IndexedSeq[_root_.ahlers.plaid.product.common.Product] = findValues
	case object Authentication extends Product
	case object Balance extends Product
	case object CreditDetails extends Product
	case object Identity extends Product
	case object Income extends Product
	case object Transactions extends Product
}

sealed trait Product extends EnumEntry
