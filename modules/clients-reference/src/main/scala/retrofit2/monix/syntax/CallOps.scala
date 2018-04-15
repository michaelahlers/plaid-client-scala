package retrofit2.monix.syntax

import monix.eval.Task
import monix.execution.Cancelable
import retrofit2.{ Call, Calls, Response }

import scala.language.{ implicitConversions, postfixOps }
import scala.util.control.NonFatal

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
class CallOps[A](val self: Call[A]) extends AnyVal {

	/**
	 * @param copy Copies the [[Call]] being operated upon, parameterized to support unit testing (as SI-151 prevents mocking the clone method).
	 *
	 * @see <a href="https://issues.scala-lang.org/browse/SI-151">SI-151</a>
	 */
	def asTask(copy: Call[A] => Call[A]): Task[Response[A]] =
		Task create { (_, cb) =>
			import cb._

			/* Copied to guarantee exclusive access and permit many executions. */
			try onSuccess(copy(self).execute()) catch {
				case NonFatal(e) => onError(e)
			}

			/* Don't pretend Retrofit calls can be cancelled. */
			Cancelable.empty
		}

	def asTask: Task[Response[A]] = asTask(Calls.copy)

}

trait ToCallOps {
	implicit def implyCallOps[A](c: Call[A]): CallOps[A] = new CallOps(c)
}
