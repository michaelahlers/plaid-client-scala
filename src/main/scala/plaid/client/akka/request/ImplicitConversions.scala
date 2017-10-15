package plaid.client.akka.request

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object ImplicitConversions
	extends ToProductImplicits
	with ToReferenceImplicits

trait ToProductImplicits
	extends common.FromReferenceImplicits

trait ToReferenceImplicits
	extends common.ToReferenceImplicits
