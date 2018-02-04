package plaid.client.models.response

import com.plaid.client.response.{ Institution => ReferenceInstitution }
import plaid.client.commons.syntax.shapeless.tags._
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

	implicit def implyInstitutionCredential(c: ReferenceInstitution#Credential): Credential = {
		import Institution.Credential.Tags._
		import c._
		Institution.Credential(
			label = getLabel.tagged[Label],
			name = getName.tagged[Name],
			typ = getType.tagged[Type]
		)
	}

	implicit def implyInstitution(i: ReferenceInstitution): Institution = {
		import Institution.Tags._
		Institution(
			id = i.getInstitutionId.tagged[Id],
			name = i.getName.tagged[Name],
			credentials = i.getCredentials.asScala.toList map { c => c: Credential },
			hasMFA = i.hasMfa,
			multiFactorAuthentications = i.getMfa.asScala.toList.map(_.tagged[MFA]),
			products = i.getProducts.asScala.toList.map({ p => p: Product })
		)
	}

}
