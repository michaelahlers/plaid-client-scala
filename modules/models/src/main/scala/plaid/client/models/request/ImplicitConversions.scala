package plaid.client.models.request

import scala.language.implicitConversions

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object ImplicitConversions extends ToReferenceImplicits

object ToReferenceImplicits extends ToReferenceImplicits
trait ToReferenceImplicits {
	implicit def implyInstitutionsGetRequest(v: InstitutionsGetRequest) =
		new com.plaid.client.request.InstitutionsGetRequest(v.length, v.offset)
}
