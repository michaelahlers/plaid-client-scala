package plaid.client.commons.reflection.syntax

import scala.language.{ dynamics, implicitConversions }

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
final class FieldOps[A <: AnyRef](val self: A) extends AnyVal {

	def get(name: Symbol): AnyRef =
		self synchronized {
			val field = self.getClass.getDeclaredField(name.name)
			field.setAccessible(true)
			field.get(self)
		}

	def set[B](name: Symbol, value: B): A =
		self synchronized {
			val field = self.getClass.getDeclaredField(name.name)
			field.setAccessible(true)
			field.set(self, value)
			self
		}

}

object FieldOps {

	sealed trait Private

}

trait ToFieldOps {
	implicit def implyReflectionOps[A <: AnyRef](t: A): FieldOps[A] = new FieldOps(t)
}
