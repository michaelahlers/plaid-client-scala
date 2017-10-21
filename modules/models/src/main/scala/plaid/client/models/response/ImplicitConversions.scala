package plaid.client.models.response

import plaid.client.support.syntax.shapeless.tags._
import com.plaid.{ client => reference }
import plaid.client.models.request.common.ImplicitConversions._
import plaid.client.models.request.common.Product

/* TODO: Replace with ImplicitConversions when 2.11 is no longer supported. */
import scala.collection.JavaConverters._
import scala.language.implicitConversions

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object ImplicitConversions
	extends FromReferenceImplicits
	with ToReferenceImplicits

trait FromReferenceImplicits {

	implicit def implyInstitutionCredential(c: reference.response.Institution#Credential): Institution.Credential = {
		import Institution.Credential.Tags._
		import c._

		Institution.Credential(
			label = getLabel.tagged[Label],
			name = getName.tagged[Name],
			typ = getType.tagged[Type]
		)
	}

	implicit def implyInstitution(i: reference.response.Institution): Institution = {
		import Institution.Tags._
		import i._

		Institution(
			id = getInstitutionId.tagged[Id],
			name = getName.tagged[Name],
			credentials = getCredentials.asScala.toList.map({ c => c: Institution.Credential }),
			hasMFA = hasMfa,
			multiFactorAuthentications = getMfa.asScala.toList.map(_.tagged[MFA]),
			products = getProducts.asScala.toList.map({ p => p: Product })
		)
	}

}

object FromReferenceImplicits extends FromReferenceImplicits

trait ToReferenceImplicits

object ToReferenceImplicits extends ToReferenceImplicits

