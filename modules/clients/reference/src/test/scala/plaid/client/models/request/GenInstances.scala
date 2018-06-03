package plaid.client.models.request

import org.scalacheck.Gen._

/**
 * @author <a href="mailto:michael@ahlers.consulting>Michael Ahlers</a>
 */
object GenInstances {
	val genInstitutionsGetRequest =
		for {
			offset <- posNum[Int]
			length <- choose(1, 500)
		} yield InstitutionsGetRequest(offset, length)
	val genRefInstitutionsGetRequest =
		for {
			count <- choose(1, 500)
			offset <- posNum[Int]
		} yield new com.plaid.client.request.InstitutionsGetRequest(count, offset)
}
