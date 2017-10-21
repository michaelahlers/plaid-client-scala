package plaid.client.models.response

import plaid.client.support.syntax.shapeless.tags._
import org.scalacheck._
import plaid.client.models.request.common

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object Generators {

	object Institutions {
		object Credentials {
			val gen =
				for {
					label <- Arbitrary.arbString.arbitrary
					name <- Arbitrary.arbString.arbitrary
					typ <- Gen.identifier
				} yield {
					import Institution.Credential
					import Credential.Tags._

					Institution.Credential(
						label = label.tagged[Label],
						name = name.tagged[Name],
						typ = typ.tagged[Type]
					)
				}
		}

		val gen =
			for {
				id <- Gen.identifier
				name <- Arbitrary.arbString.arbitrary
				credentials <- Gen.listOf(Credentials.gen)
				hasMFA <- Arbitrary.arbBool.arbitrary
				multiFactorAuthentications <- Gen.listOf(Gen.identifier)
				products <- Gen.listOf(common.Generators.Products.gen)
			} yield {
				import Institution.Tags._

				Institution(
					id = id.tagged[Id],
					name = name.tagged[Name],
					credentials = credentials,
					hasMFA = hasMFA,
					multiFactorAuthentications = multiFactorAuthentications.map(_.tagged[MFA]),
					products = products
				)
			}
	}

}
