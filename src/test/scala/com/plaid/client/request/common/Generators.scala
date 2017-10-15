package com.plaid.client.request.common

import org.scalacheck._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object Generators {

	object Products {
		val gen = Gen.oneOf(Product.values())
	}

}
