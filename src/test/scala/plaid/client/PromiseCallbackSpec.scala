package plaid.client

import com.plaid.client.PlaidClient
import com.plaid.client.response.ErrorResponse
import org.mockito.Mockito._
import org.scalacheck.{ Arbitrary, Gen }
import org.scalatest._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.mockito.MockitoSugar
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import plaid.client.PromiseCallback._
import plaid.client.PromiseCallbackSpec.Generators
import retrofit2.{ Call, Response }

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object PromiseCallbackSpec {

	trait Generators { self: MockitoSugar =>

		object Calls {
			def gen[T]: Gen[Call[T]] =
				for {
					cancelled <- Arbitrary.arbBool.arbitrary
				} yield {
					val call = mock[Call[T]]
					when(call.isCanceled()).thenReturn(cancelled)
					call
				}
		}

		object Responses {
			def gen[T](implicit a: Arbitrary[T]): Gen[Response[T]] =
				for {
					successful <- Arbitrary.arbBool.arbitrary
					body <- Arbitrary.arbitrary[T]
				} yield {
					val response = mock[Response[T]]
					when(response.isSuccessful()).thenReturn(successful)
					when(response.body()).thenReturn(body)
					response
				}
		}

	}

}

class PromiseCallbackSpec extends WordSpec with Matchers
	with Generators with GeneratorDrivenPropertyChecks
	with ScalaFutures
	with MockitoSugar {

	type Body = Int

	"On failure" must {
		"propagate exceptions " in {
			val exception = mock[Exception]
			val callback = PromiseCallback[Body](mock[PlaidClient])
			callback.onFailure(mock[Call[Body]], exception)
			callback.promise.future.failed.futureValue should be(exception)
		}
	}

	val scenarios =
		for {
			call <- Calls.gen[Body]
			response <- Responses.gen[Body]
		} yield (call, response)

	"On response" must {

		"propagate cancellations" in {
			forAll(scenarios) {
				case (call, response) =>
					whenever(call.isCanceled) {
						val callback = PromiseCallback[Body](mock[PlaidClient])
						callback.onResponse(call, response)
						callback.promise.future.futureValue should be(Cancellation)
					}
			}
		}

		"propagate success" in {
			forAll(scenarios) {
				case (call, response) =>
					whenever(!call.isCanceled && response.isSuccessful) {
						val callback = PromiseCallback[Body](mock[PlaidClient])
						callback.onResponse(call, response)
						callback.promise.future.futureValue should be(Success(response))
					}
			}
		}

		"propagate errors" in {
			forAll(scenarios) {
				case (call, response) =>
					whenever(!call.isCanceled && !response.isSuccessful) {
						val client = mock[PlaidClient]
						val error = mock[ErrorResponse]
						when(client.parseError(response)).thenReturn(error)

						val callback = PromiseCallback[Body](client)
						callback.onResponse(call, response)
						callback.promise.future.futureValue should be(Failure(error))
					}
			}
		}

	}

}
