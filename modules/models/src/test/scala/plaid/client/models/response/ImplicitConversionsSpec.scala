package plaid.client.models.response

import org.scalatest._
import org.scalatest.prop.GeneratorDrivenPropertyChecks._
import plaid.client.models.response.ImplicitConversions._
import plaid.client.models.request.common.ImplicitConversions._
import com.plaid.client.response.{ InstitutionSpec => ReferenceInstitutionSpec }

import scala.language.postfixOps
import Matchers._
import plaid.client.models.response.Institution.Credential
import plaid.client.models.request.common.Product

/* TODO: Replace with ImplicitConversions when 2.11 is no longer supported. */
import scala.collection.JavaConverters._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
class ImplicitConversionsSpec extends FlatSpec {

	it must "convert from reference institution credential" in {
		import ReferenceInstitutionSpec.Generators.Institutions._
		forAll(Credentials.gen, minSuccessful(100)) { expected =>
			val actual: Credential = expected
			actual should have(
				'label(expected.getLabel),
				'name(expected.getName),
				'typ(expected.getType)
			)
		}
	}

	it must "convert from reference institution" in {
		import ReferenceInstitutionSpec.Generators._
		forAll(Institutions.gen, minSuccessful(100)) { expected =>
			val actual: Institution = expected
			actual should have(
				'id(expected.getInstitutionId),
				'name(expected.getName),
				'credentials(expected.getCredentials.asScala map { c => c: Credential }),
				'hasMFA(expected.hasMfa),
				'multiFactorAuthentications(expected.getMfa.asScala),
				'products(expected.getProducts.asScala map { p => p: Product })
			)
		}
	}

}
