package plaid.client.akka.request.common

import com.plaid.{ client => reference }
import org.scalatest._
import plaid.client.akka.request.common.FromReferenceImplicits._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
class FromReferenceImplicitsSpec extends FlatSpec
	with Matchers with Inspectors
	with FromReferenceComparisons {

	it must s"convert ${classOf[reference.request.common.Product]}" in {
		forAll(reference.request.common.Product.values().toList) { sample =>
			verifyProduct(sample, sample)
		}
	}

}
