package plaid.client.akka.request

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object ImplicitConversions
	extends FromReferenceImplicits
	with ToReferenceImplicits

trait FromReferenceImplicits
	extends common.FromReferenceImplicits

object FromReferenceImplicits extends FromReferenceImplicits

trait ToReferenceImplicits
	extends common.ToReferenceImplicits

object ToReferenceImplicits extends ToReferenceImplicits
