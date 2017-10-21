package plaid.client.models.request.common

import org.scalatest._
import plaid.client.models.request.common.ToReferenceImplicits._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
class ToReferenceImplicitsSpec extends FlatSpec
	with Matchers with Inspectors
	with ToReferenceComparisons {

	it must s"convert ${classOf[Product]}" in {
		forAll(Product.values) { sample =>
			verifyProduct(sample, sample)
		}
	}

}
