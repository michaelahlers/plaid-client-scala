package com.plaid.client.response

import com.plaid.client.request.common.ProductSpec.Generators._
import org.mockito.Mockito.{ mock => _, _ }
import org.scalacheck.Arbitrary._
import org.scalacheck.Gen._
import org.scalacheck._
import org.scalatest.mockito.MockitoSugar._

/* TODO: Replace with ImplicitConversions when 2.11 is no longer supported. */
import scala.collection.JavaConverters._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object InstitutionSpec {

	object Generators {
		object Institutions {
			object Credentials {
				val gen: Gen[Institution#Credential] =
					for {
						label <- arbitrary[String]
						name <- arbitrary[String]
						typ <- arbitrary[String]
					} yield {
						val credential = mock[Institution#Credential]
						when(credential.getLabel).thenReturn(label)
						when(credential.getName).thenReturn(name)
						when(credential.getType).thenReturn(typ)
						credential
					}
			}

			val gen: Gen[Institution] =
				for {
					id <- arbitrary[String]
					name <- arbitrary[String]
					credentials <- listOf(Credentials.gen)
					hasMFA <- arbitrary[Boolean]
					multiFactorAuthentications <- listOf(arbitrary[String])
					products <- listOf(Products.gen)
				} yield {
					val institution = mock[Institution]
					when(institution.getInstitutionId).thenReturn(id)
					when(institution.getName).thenReturn(name)
					when(institution.getCredentials).thenReturn(credentials.asJava)
					when(institution.hasMfa).thenReturn(hasMFA)
					when(institution.getMfa).thenReturn(multiFactorAuthentications.asJava)
					when(institution.getProducts).thenReturn(products.asJava)
					institution
				}
		}
	}

}
