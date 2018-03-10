package plaid.client.models.request

import org.scalacheck.Gen._
import org.scalacheck.ScalacheckShapeless._
import org.scalacheck._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object InstitutionsGetRequestSpec {

	object Generators {
		object InstitutionsGetRequests {
			val gen: Gen[InstitutionsGetRequest] =
				for {
					offset <- posNum[Int]
					length <- choose(0, 500)
				} yield InstitutionsGetRequest(offset, length)
		}
	}

}
