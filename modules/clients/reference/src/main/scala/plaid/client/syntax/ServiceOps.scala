package plaid.client.syntax

import com.plaid.client.PlaidApiService
import plaid.client.Executor

import scala.concurrent.Future
import scala.language.implicitConversions

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
final class ServiceOps(val self: PlaidApiService) extends AnyVal {

	def execute[Request, Response](r: Request)(implicit executor: Executor[Request, Response]): Future[Response] = executor(self, r)

}

trait ToServiceOps {
	implicit def implyServiceOps(c: PlaidApiService): ServiceOps = new ServiceOps(c)
}
