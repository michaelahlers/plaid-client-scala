package plaid.client.models.request

import org.scalatest.Matchers._
import org.scalatest._
import org.scalatest.prop.GeneratorDrivenPropertyChecks._
import plaid.client.commons.reflection.syntax.FieldOps._
import plaid.client.commons.scalatest.syntax.matchers._
import plaid.client.commons.shapeless.syntax.tags._
import plaid.client.models.request.ArbitraryInstances._
import plaid.client.models.request.InstitutionInstances._

import scala.language.postfixOps

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
class InstitutionInstancesSpec extends WordSpec {

	"Inject[InstitutionsGetRequest, InstitutionsGetRequest]" must {
		"apply" in {
			forAll { request: InstitutionsGetRequest =>
				InjectInstitutionsGetRequest.apply(request) should have(
					'count.tagged[Private](request.length),
					'offset.tagged[Private](request.offset)
				)
			}
		}

		"unapply" in {
			forAll { request: RefInstitutionsGetRequest =>
				InjectInstitutionsGetRequest.unapply(request) should be(empty)
			}
		}

	}

}
