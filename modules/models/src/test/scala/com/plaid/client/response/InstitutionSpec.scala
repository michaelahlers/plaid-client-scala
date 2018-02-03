package com.plaid.client.response

import com.plaid.client.request.common.ProductSpec.Generators._
import org.scalacheck.Arbitrary._
import org.scalacheck.Gen._
import org.scalacheck._
import plaid.client.commons.reflection

/* TODO: Replace with ImplicitConversions when 2.11 is no longer supported. */
import scala.collection.JavaConverters._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object InstitutionSpec {

	object Generators {
		object Institutions {
			val gen: Gen[Institution] =
				for {
					id <- arbitrary[String]
					name <- arbitrary[String]
					institution = new Institution
					credentials <- listOf({
						for {
							label <- arbitrary[String]
							name <- arbitrary[String]
							typ <- arbitrary[String]
						} yield {
							import reflection.syntax.fields._
							(new institution.Credential)
								.set('label, label)
								.set('name, name)
								.set('type, typ)
						}
					}).map(_.asJava)
					hasMFA <- arbitrary[Boolean]
					multiFactorAuthentications <- listOf(arbitrary[String]).map(_.asJava)
					products <- listOf(Products.gen).map(_.asJava)
				} yield {
					import reflection.syntax.fields._
					institution
						.set('institutionId, id)
						.set('name, name)
						.set('credentials, credentials)
						.set('hasMfa, hasMFA)
						.set('mfa, multiFactorAuthentications)
						.set('products, products)
				}
		}
	}

}
