package plaid.client.models.response

import com.plaid.client.response.{ Institution => ReferenceInstitution }
import com.plaid.client.response.{ InstitutionsGetResponse => ReferenceInstitutionsGetResponse }
import plaid.client.commons.shapeless.syntax.tags._
import plaid.client.models.request.common.ImplicitConversions._
import plaid.client.models.request.common.Product
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

	implicit def implyInstitutionCredential(v: ReferenceInstitution#Credential): Credential = {
		import Institution.Credential.Tags._
		import v._
		Institution.Credential(
			label = getLabel.tagged[Label],
			name = getName.tagged[Name],
			typ = getType.tagged[Type]
		)
	}

	implicit def implyInstitution(v: ReferenceInstitution): Institution = {
		import Institution.Tags._
		Institution(
			id = v.getInstitutionId.tagged[Id],
			name = v.getName.tagged[Name],
			credentials = v.getCredentials.asScala.toList map { c => c: Credential },
			hasMFA = v.hasMfa,
			multiFactorAuthentications = v.getMfa.asScala.toList.map(_.tagged[MFA]),
			products = v.getProducts.asScala.toList.map({ p => p: Product })
		)
	}

	implicit def implyInstitutionsGetResponse(v: ReferenceInstitutionsGetResponse): InstitutionsGetResponse = {
		import InstitutionsGetResponse._
		InstitutionsGetResponse(
			institutions = v.getInstitutions.asScala.toList.map({ v => v: Institution }),
			total = Count(v.getTotal)
		)
	}

}
