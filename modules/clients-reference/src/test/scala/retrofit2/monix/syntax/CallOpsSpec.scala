package retrofit2.monix.syntax

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import org.scalamock.scalatest._
import org.scalatest.Matchers._
import org.scalatest._
import org.scalatest.concurrent.ScalaFutures._
import retrofit2.monix.syntax.calls._
import retrofit2.{ Call, Response }

import scala.language.postfixOps

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
class CallOpsSpec extends WordSpec with MockFactory {

	"Calls as tasks" must {
		def withFixtures[A](borrower: (Call[Unit], Task[Response[Unit]]) => A): A = {
			val source = mock[Call[Unit]]
			val local = mock[Call[Unit]]

			val copy = mockFunction[Call[Unit], Call[Unit]]

			(source.cancel _).expects().never()
			(source.enqueue _).expects(*).never()
			(source.execute _).expects().never()

			(local.cancel _).expects().never()
			(local.enqueue _).expects(*).never()

			copy.expects(source).anyNumberOfTimes().returns(local)

			borrower(local, source.asTask(copy))
		}

		"not execute eagerly" in withFixtures { (local, _) =>
			(local.execute _).expects().never()
		}

		"execute calls" that {

			"return responses" in withFixtures { (local, task) =>
				val response = Response.success(())
				(local.execute _).expects().returns(response)
				task.runAsync.futureValue should be(response)
			}

			"throw exceptions" in withFixtures { (local, task) =>
				val error = new Exception
				(local.execute _).expects().throws(error)
				task.runAsync.failed.futureValue should be(error)
			}

		}
	}

}
