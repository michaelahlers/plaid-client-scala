package plaid.client.akka.request

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object ImplicitConversions
	extends ToProductImplicits
	with ToBeanImplicits

trait ToProductImplicits
	extends common.ToProductImplicits

trait ToBeanImplicits
	extends common.ToBeanImplicits
