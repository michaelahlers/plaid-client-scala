package plaid.client.commons.shapeless

import shapeless.tag._
import plaid.client.commons.syntax.shapeless.tags._

/**
 *
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
trait TaggerCompanion[T, U] {
	final def apply(v: T): T @@ U = v.tagged[U]
}
