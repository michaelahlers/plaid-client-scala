package com.plaid.client.response

import com.plaid.client.request.common.Generators.Products
import org.mockito.Mockito._
import org.scalacheck._
import org.scalatest.mockito.MockitoSugar

import scala.collection.convert.ImplicitConversions._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object Generators extends MockitoSugar {

	object Institutions {
		object Credentials {
			val gen =
				for {
					label <- Arbitrary.arbString.arbitrary
					name <- Arbitrary.arbString.arbitrary
					typ <- Gen.identifier
				} yield {
					val credential = mock[Institution#Credential]
					when(credential.getLabel).thenReturn(label)
					when(credential.getName).thenReturn(name)
					when(credential.getType).thenReturn(typ)
					credential
				}
		}

		val gen =
			for {
				id <- Gen.identifier
				name <- Arbitrary.arbString.arbitrary
				credentials <- Gen.listOf(Credentials.gen)
				hasMFA <- Arbitrary.arbBool.arbitrary
				multiFactorAuthentications <- Gen.listOf(Gen.identifier)
				products <- Gen.listOf(Products.gen)
			} yield {
				val institution = mock[Institution]
				when(institution.getInstitutionId).thenReturn(id)
				when(institution.getName).thenReturn(name)
				when(institution.getCredentials).thenReturn(credentials)
				when(institution.hasMfa).thenReturn(hasMFA)
				when(institution.getMfa).thenReturn(multiFactorAuthentications)
				when(institution.getProducts).thenReturn(products)
				institution
			}
	}

}
