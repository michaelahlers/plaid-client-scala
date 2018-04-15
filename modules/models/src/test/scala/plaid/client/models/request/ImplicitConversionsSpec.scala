package plaid.client.models.request

import org.scalatest.Matchers._
import org.scalatest._
import org.scalatest.prop.GeneratorDrivenPropertyChecks._
import plaid.client.commons.reflection.syntax.FieldOps._
import plaid.client.commons.scalatest.syntax.matchers._
import plaid.client.commons.shapeless.syntax.tags._

import scala.language.postfixOps

/* TODO: Replace with ImplicitConversions when 2.11 is no longer supported. */
import plaid.client.models.request.ImplicitConversions._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
class ImplicitConversionsSpec extends WordSpec {

	"To reference" when {
		import Generators._

		"institutions get request" in {
			forAll(InstitutionsGetRequests.gen) { request =>
				val r = request: com.plaid.client.request.InstitutionsGetRequest
				r should have(
					'count.tagged[Private](request.length),
					'offset.tagged[Private](request.offset)
				)
			}
		}

	}

}
