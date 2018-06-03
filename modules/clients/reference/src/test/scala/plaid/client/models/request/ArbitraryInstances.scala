package plaid.client.models.request

import org.scalacheck._
import GenInstances._

/**
 * @author <a href="mailto:michael@ahlers.consulting>Michael Ahlers</a>
 */
object ArbitraryInstances {
	implicit val arbInstitutionsGetRequest = Arbitrary(genInstitutionsGetRequest)
	implicit val arbRefInstitutionsGetRequest = Arbitrary(genRefInstitutionsGetRequest)
}
