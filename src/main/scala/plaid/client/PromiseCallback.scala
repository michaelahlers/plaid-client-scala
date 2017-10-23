package plaid.client

import com.plaid.client.PlaidClient
import com.plaid.client.response.ErrorResponse
import plaid.client.PromiseCallback._
import retrofit2.{ Call, Callback, Response }

import scala.concurrent.Promise

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object PromiseCallback {

	sealed trait Result[+Body]
	case class Success[Body](response: Response[Body]) extends Result[Body]
	case class Failure(response: ErrorResponse) extends Result[Nothing]
	case object Cancellation extends Result[Nothing]

}

case class PromiseCallback[Body](client: PlaidClient, promise: Promise[Result[Body]] = Promise[Result[Body]]()) extends Callback[Body] {

	override def onFailure(call: Call[Body], reason: Throwable) = promise.failure(reason)

	override def onResponse(call: Call[Body], response: Response[Body]) =
		promise success {
			if (call.isCanceled) Cancellation
			else {
				if (response.isSuccessful) Success(response)
				else Failure(client.parseError(response))
			}
		}

}
