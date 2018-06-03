package plaid.client.models.request

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
case class InstitutionsGetRequest(offset: Int, length: Int) {
	require(0 <= offset)
	require(0 < length)
	require(500 >= length)
}
