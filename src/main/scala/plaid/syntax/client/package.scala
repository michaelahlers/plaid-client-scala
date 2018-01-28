package plaid.syntax

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
package object client {

	object clientBuilders extends ToPlaidClientBuilderOps

	object credentials extends ToCredentialOps
	object credentialProviders extends ToCredentialProviderOps

}
