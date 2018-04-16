package plaid.client.syntax

import com.plaid.client.PlaidApiService

import scala.language.implicitConversions

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
final class ServiceOps(val self: PlaidApiService) extends AnyVal {
}

trait ToServiceOps {
	implicit def implyServiceOps(c: PlaidApiService): ServiceOps = new ServiceOps(c)
}
