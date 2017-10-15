package plaid.client.akka.request.common

import com.plaid.{ client => reference }
import org.scalatest._
import plaid.client.akka.request.common.ImplicitConversions._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
class ToProductImplicitsSpec extends WordSpec
	with Matchers with Inspectors
	with ReferenceComparisons {

	"Implicit conversion" must {

		s"return ${classOf[Product]} from ${classOf[reference.request.common.Product]}" in {
			forAll(reference.request.common.Product.values()) { sample =>
				verifyProduct(sample, sample)
			}
		}

	}

}
