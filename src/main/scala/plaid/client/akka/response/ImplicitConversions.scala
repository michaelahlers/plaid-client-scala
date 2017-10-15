package plaid.client.akka.response

import ahlers.plaid.syntax.shapeless.tags._
import com.plaid.client.{ response => reference }
import plaid.client.akka.request.common.ImplicitConversions._
import plaid.client.akka.request.common.Product

import scala.collection.convert.ImplicitConversions._
import scala.language.implicitConversions

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object ImplicitConversions
	extends ToProductImplicits
	with ToBeanImplicits

trait ToProductImplicits {

	implicit def implyInstitution(i: reference.Institution): Institution = {
		import Institution.Tags._
		import i._

		Institution(
			id = getInstitutionId.tagged[Id],
			name = getName.tagged[Name],
			credentials = getCredentials.toList map { c =>
				import Institution.Credential
				import Credential.Tags._
				import c._

				Credential(
					label = getLabel.tagged[Label],
					name = getName.tagged[Name],
					`type` = getType.tagged[Type]
				)
			},
			hasMFA = hasMfa,
			MFAs = getMfa.toList.map(_.tagged[MFA]),
			products = getProducts.toList.map({ p => p: Product })
		)
	}

}

trait ToBeanImplicits {

}
