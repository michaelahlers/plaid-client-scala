package plaid.client.akka

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object ImplicitConversions
	extends ToProductImplicits
	with ToBeanImplicits

trait ToProductImplicits
	extends request.ToProductImplicits
	with response.ToProductImplicits

trait ToBeanImplicits
	extends request.ToBeanImplicits
	with response.ToBeanImplicits
