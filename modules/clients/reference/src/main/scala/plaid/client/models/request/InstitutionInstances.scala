package plaid.client.models.request

import cats._
import cats.implicits._

import scala.language.implicitConversions

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object InstitutionInstances extends InstitutionInstances
trait InstitutionInstances {

	implicit object InjectInstitutionsGetRequest extends Inject[InstitutionsGetRequest, RefInstitutionsGetRequest] {
		override val inj = { x =>
			import x._
			new RefInstitutionsGetRequest(length, offset)
		}
		override val prj = _ => none
	}

}
