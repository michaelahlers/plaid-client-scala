package plaid.client.models.request

import com.plaid.client.request.{ InstitutionsGetRequest => ReferenceInstitutionsGetRequest }

import scala.language.implicitConversions

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object ImplicitConversions extends ToReferenceImplicits

object ToReferenceImplicits extends ToReferenceImplicits
trait ToReferenceImplicits {
	implicit def implyInstitutionsGetRequest(v: InstitutionsGetRequest): ReferenceInstitutionsGetRequest = {
		import v._
		new ReferenceInstitutionsGetRequest(length, offset)
	}
}
