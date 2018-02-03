package plaid.client.models.response

import com.plaid.client.response.{ InstitutionSpec => ReferenceInstitutionSpec }
import org.scalatest.Matchers._
import org.scalatest._
import org.scalatest.prop.GeneratorDrivenPropertyChecks._
import plaid.client.models.request.common.ImplicitConversions._
import plaid.client.models.request.common.Product
import plaid.client.models.response.ImplicitConversions._
import plaid.client.models.response.Institution.Credential

import scala.language.postfixOps

/* TODO: Replace with ImplicitConversions when 2.11 is no longer supported. */
import scala.collection.JavaConverters._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
class ImplicitConversionsSpec extends FlatSpec {

	it must "convert from reference institution" in {
		import ReferenceInstitutionSpec.Generators._
		forAll(Institutions.gen, minSuccessful(100)) { institution =>
			val i: Institution = institution
			i should have(
				'id(institution.getInstitutionId),
				'name(institution.getName),
				'credentials(institution.getCredentials.asScala map { credential =>
					val c: Credential = credential
					c should have(
						'label(credential.getLabel),
						'name(credential.getName),
						'typ(credential.getType)
					)
					c
				}),
				'hasMFA(institution.hasMfa),
				'multiFactorAuthentications(institution.getMfa.asScala),
				'products(institution.getProducts.asScala map { p => p: Product })
			)
		}
	}

}
