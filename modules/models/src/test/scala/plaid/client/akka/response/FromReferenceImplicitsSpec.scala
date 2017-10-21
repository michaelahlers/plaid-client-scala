package plaid.client.akka.response

import com.plaid.{ client => reference }
import org.scalatest._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import plaid.client.akka.response.ImplicitConversions._

import scala.language.postfixOps

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
class FromReferenceImplicitsSpec extends FlatSpec
	with Matchers
	with GeneratorDrivenPropertyChecks
	with FromReferenceComparisons {

	it must s"convert ${classOf[Institution.Credential]}" in {
		forAll(reference.response.Generators.Institutions.Credentials.gen) { sample =>
			verifyInstitutionCredential(sample, sample)
		}
	}

	it must s"convert ${classOf[Institution]}" in {
		forAll(reference.response.Generators.Institutions.gen) { sample =>
			verifyInstitution(sample, sample)
		}
	}

}
