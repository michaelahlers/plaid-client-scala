package plaid.client.support.syntax.shapeless

import shapeless.tag
import shapeless.tag._

import scala.language.implicitConversions

/**
 * Make it easier to tag values.
 * @see [[https://github.com/milessabin/shapeless/issues/557 milessabin/shapeless#557]]
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
final case class TagOps[T](self: T) extends AnyVal {
	def tagged[U]: T @@ U = tag[U].apply[T](self)
}

trait ToTagOps {
	implicit def implyTagOps[T](t: T): TagOps[T] = TagOps(t)
}
