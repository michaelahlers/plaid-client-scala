//package plaid.client
//
//import com.plaid.client.PlaidClient
//import com.plaid.client.response.ErrorResponse
//import org.mockito.ArgumentMatchers._
//import org.mockito.Mockito._
//import org.scalatest._
//import org.scalatest.concurrent.ScalaFutures
//import org.scalatest.mockito.MockitoSugar
//import plaid.client.PromiseCallback._
//import retrofit2.{ Call, Response }
///**
// * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
// */
//class PromiseCallbackSpec extends WordSpec with Matchers
//	with ScalaFutures
//	with MockitoSugar {
//
//	type Body = Int
//
//	"On failure" must {
//		"propagate exceptions " in {
//			val exception = mock[Exception]
//			val client = spy(PlaidClient.newBuilder().baseUrl("https://host").publicKey("").build())
//			val callback = PromiseCallback[Body](client)
//			callback.onFailure(mock[Call[Body]], exception)
//			callback.promise.future.failed.futureValue should be(exception)
//		}
//	}
//
//	"On response" must {
//
//		"propagate exceptions" when {
//
//			/** [[PlaidClient]]'s contract for parsing errors dictates that failures may occur due to incomplete or malformed responses. Any exceptions thrown by that function must be propagated here. */
//			"parsing error responses" in {
//				val client = mock[PlaidClient]
//				val exception = mock[RuntimeException]
//				when(client.parseError(any[Response[Body]]())).thenThrow(exception)
//
//				val call = mock[Call[Body]]
//				when(call.isCanceled()).thenReturn(false)
//
//				val response = mock[Response[Body]]
//				when(response.isSuccessful()).thenReturn(false)
//
//				val callback = PromiseCallback[Body](client)
//				callback.onResponse(call, response)
//				callback.promise.future.failed.futureValue should be(exception)
//			}
//
//		}
//
//		"propagate cancellations" in {
//			val call = mock[Call[Body]]
//			when(call.isCanceled()).thenReturn(true)
//
//			val response = mock[Response[Body]]
//			verifyNoMoreInteractions(response)
//
//			val callback = PromiseCallback[Body](mock[PlaidClient])
//			callback.onResponse(call, response)
//			callback.promise.future.futureValue should be(Cancellation)
//		}
//
//		"propagate success" in {
//			val call = mock[Call[Body]]
//			when(call.isCanceled()).thenReturn(false)
//
//			val response = mock[Response[Body]]
//			when(response.isSuccessful()).thenReturn(true)
//
//			val callback = PromiseCallback[Body](mock[PlaidClient])
//			callback.onResponse(call, response)
//			callback.promise.future.futureValue should be(Success(response))
//		}
//
//		"propagate errors" in {
//			val call = mock[Call[Body]]
//			when(call.isCanceled()).thenReturn(false)
//
//			val response = mock[Response[Body]]
//			when(response.isSuccessful()).thenReturn(false)
//
//			val client = mock[PlaidClient]
//			val error = mock[ErrorResponse]
//			when(client.parseError(response)).thenReturn(error)
//
//			val callback = PromiseCallback[Body](client)
//			callback.onResponse(call, response)
//			callback.promise.future.futureValue should be(Failure(error))
//		}
//
//	}
//
//}
