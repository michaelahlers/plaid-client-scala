package plaid.client.models.request

import com.plaid.client.request.{ InstitutionsGetRequest => ReferenceInstitutionsGetRequest }
import org.scalatest._
import org.scalatest.prop.GeneratorDrivenPropertyChecks._
import plaid.client.commons.reflection.syntax.FieldOps.Private
import plaid.client.commons.shapeless.syntax.tags._
import plaid.client.commons.scalatest.syntax.matchers._
import org.scalatest.Matchers._
import scala.language.postfixOps

/* TODO: Replace with ImplicitConversions when 2.11 is no longer supported. */
import plaid.client.models.request.ImplicitConversions._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
class ImplicitConversionsSpec extends FlatSpec {
	it must "convert to reference institutions get request" in {
		import InstitutionsGetRequestSpec.Generators._
		forAll(InstitutionsGetRequests.gen) { request =>
			whenever(0 <= request.offset && 0 < request.length && 500 >= request.length) {
				val r: ReferenceInstitutionsGetRequest = request
				r should have(
					'count.tagged[Private](request.length),
					'offset.tagged[Private](request.offset)
				)
			}
		}
	}
}
