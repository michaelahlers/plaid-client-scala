package plaid.client.akka.response

import com.plaid.{ client => reference }
import org.scalatest.Inspectors._
import org.scalatest.Matchers._
import plaid.client.akka.request.common.FromReferenceComparisons._

/* TODO: Replace with ImplicitConversions when 2.11 is no longer supported. */
import scala.collection.JavaConverters._
import scala.language.postfixOps

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
trait FromReferenceComparisons {

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

		forAll(getCredentials.asScala zip actual.credentials)(verifyInstitutionCredential _ tupled)
		actual.multiFactorAuthentications should contain theSameElementsInOrderAs getMfa.asScala
		forAll(getProducts.asScala zip actual.products)(verifyProduct _ tupled)
	}

}
