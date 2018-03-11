package plaid.client

import com.plaid.client.PlaidApiService
import com.plaid.client.request.InstitutionsGetRequest
import com.plaid.client.response.InstitutionsGetResponse
import retrofit2.Call

import scala.concurrent.Future

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
sealed abstract class Executor[Request, Response](delegate: PlaidApiService => Request => Call[Response]) {
	def apply(service: PlaidApiService, request: Request): Future[Response] = ???
}

object Executor {

	implicit object InstitutionsGet extends Executor[InstitutionsGetRequest, InstitutionsGetResponse](_.institutionsGet)

}
