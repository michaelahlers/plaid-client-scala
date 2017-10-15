package plaid.client.akka

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object ImplicitConversions
	extends FromReferenceImplicits
	with ToReferenceImplicits

trait FromReferenceImplicits
	extends request.FromReferenceImplicits
	with response.FromReferenceImplicits

object FromReferenceImplicits extends FromReferenceImplicits

trait ToReferenceImplicits
	extends request.ToReferenceImplicits
	with response.ToReferenceImplicits

object ToReferenceImplicits extends ToReferenceImplicits
