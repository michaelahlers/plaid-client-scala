package plaid.client

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
package object syntax {

	object clientBuilders extends ToPlaidClientBuilderOps

	object credentials extends ToCredentialOps
	object credentialProviders extends ToCredentialProviderOps

}
