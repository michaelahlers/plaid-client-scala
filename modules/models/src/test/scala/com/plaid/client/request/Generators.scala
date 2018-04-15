package com.plaid.client.request

import org.scalacheck.Gen
import org.scalacheck.Gen.{ choose, posNum }

/**
 * @author <a href="mailto:michael@ahlers.consulting>Michael Ahlers</a>
 */
object Generators {

	object InstitutionsGetRequests {
		val gen: Gen[InstitutionsGetRequest] =
			for {
				count <- choose(0, 500)
				offset <- posNum[Int]
			} yield new InstitutionsGetRequest(count, offset)
	}

}
