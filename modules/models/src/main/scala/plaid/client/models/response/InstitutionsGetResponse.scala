package plaid.client.models.response

import plaid.client.commons.shapeless.TaggerCompanion
import plaid.client.models.response.InstitutionsGetResponse._
import shapeless.tag._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
case class InstitutionsGetResponse(institutions: List[Institution], total: Int @@ Count)

object InstitutionsGetResponse {

	sealed trait Count
	object Count extends TaggerCompanion[Int, Count]

}
