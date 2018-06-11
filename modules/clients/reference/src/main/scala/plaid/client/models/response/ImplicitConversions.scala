package plaid.client.models.response

import plaid.client.commons.shapeless.syntax.tags._
import plaid.client.models.request.common.instances._
import plaid.client.models.response.Institution.Credential

/* TODO: Replace with ImplicitConversions when 2.11 is no longer supported. */
import scala.collection.JavaConverters._
import scala.language.implicitConversions

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object ImplicitConversions
	extends FromReferenceImplicits

object FromReferenceImplicits extends FromReferenceImplicits
trait FromReferenceImplicits {

	implicit def implyInstitutionCredential(v: com.plaid.client.response.Institution#Credential) = {
		import Institution.Credential.Tags._
		import v._
		Institution.Credential(
			label = getLabel.tagged[Label],
			name = getName.tagged[Name],
			typ = getType.tagged[Type]
		)
	}

	implicit def implyInstitution(v: com.plaid.client.response.Institution) = {
		import Institution.Tags._
		Institution(
			id = v.getInstitutionId.tagged[Id],
			name = v.getName.tagged[Name],
			credentials = v.getCredentials.asScala.toList map { c => c: Credential },
			hasMFA = v.hasMfa,
			multiFactorAuthentications = v.getMfa.asScala.toList.map(_.tagged[MFA]),
			products = v.getProducts.asScala.toList.flatMap(InjectRefProduct.unapply)
		)
	}

	implicit def implyInstitutionsGetResponse(v: com.plaid.client.response.InstitutionsGetResponse) = {
		import plaid.client.models.response.InstitutionsGetResponse._
		InstitutionsGetResponse(
			institutions = v.getInstitutions.asScala.toList.map({ v => v: Institution }),
			total = Count(v.getTotal)
		)
	}

}
