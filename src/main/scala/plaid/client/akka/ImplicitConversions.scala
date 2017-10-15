package plaid.client.akka

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object ImplicitConversions
	extends ToProductImplicits
	with ToReferenceImplicits

trait ToProductImplicits
	extends request.ToProductImplicits
	with response.ToProductImplicits

trait ToReferenceImplicits
	extends request.ToReferenceImplicits
	with response.ToReferenceImplicits
