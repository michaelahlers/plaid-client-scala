package plaid.client.akka.response

import com.plaid.{ client => reference }
import org.scalatest.Inspectors._
import org.scalatest.Matchers._
import org.scalatest._
import plaid.client.akka.request.common

import scala.collection.convert.ImplicitConversions._
import scala.language.postfixOps

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
trait ReferenceComparisons extends common.ReferenceComparisons { self: Suite =>

	def verifyInstitutionCredential(sample: reference.response.Institution#Credential, actual: Institution.Credential) = {
		import sample._
		actual should have(
			'label(getLabel),
			'name(getName),
			'typ(getType)
		)
	}

	def verifyInstitution(sample: reference.response.Institution, actual: Institution) = {
		import sample._
		actual should have(
			'id(getInstitutionId),
			'name(getName),
			'hasMFA(hasMfa)
		)

		forAll(getCredentials zip actual.credentials)(verifyInstitutionCredential _ tupled)
		actual.multiFactorAuthentications should contain theSameElementsInOrderAs getMfa
		forAll(getProducts zip actual.products)(verifyProduct _ tupled)
	}

}
