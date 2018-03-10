package plaid.client.commons.shapeless

import plaid.client.commons.shapeless.syntax.tags._
import shapeless.tag._

/**
 *
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
trait TaggerCompanion[T, U] {
	final def apply(v: T): T @@ U = v.tagged[U]
}
