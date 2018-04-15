package plaid.client.models.request.common

import org.scalacheck.Arbitrary._
import org.scalacheck.ScalacheckShapeless._
import org.scalacheck._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object Generators {
	object Products {
		val gen: Gen[Product] = arbitrary[Product]
	}
}
