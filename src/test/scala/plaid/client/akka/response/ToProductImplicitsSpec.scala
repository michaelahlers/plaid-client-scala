package plaid.client.akka.response

import com.plaid.{ client => reference }
import org.scalatest._
import org.scalatest.prop.GeneratorDrivenPropertyChecks.{ forAll => forAllProp }
import plaid.client.akka.response.ImplicitConversions._

import scala.language.postfixOps

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
class ToProductImplicitsSpec extends WordSpec
	with Matchers with Inspectors
	with ReferenceComparisons {

	"Implicit conversion" must {

		s"return ${classOf[Institution.Credential]} from ${classOf[reference.response.Institution#Credential]}" in {
			forAllProp(reference.response.Generators.Institutions.Credentials.gen) { sample =>
				verifyInstitutionCredential(sample, sample)
			}
		}

		s"return ${classOf[Institution]} from ${classOf[reference.response.Institution]}" in {
			forAllProp(reference.response.Generators.Institutions.gen) { sample =>
				verifyInstitution(sample, sample)
			}
		}

	}

}
