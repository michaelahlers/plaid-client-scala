package plaid.client

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
package object syntax {

	object clients extends ToPlaidClientBuilderOps

	object credentials
		extends ToCredentialOps
		with ToCredentialProviderOps

	object services extends ToServiceOps

}
