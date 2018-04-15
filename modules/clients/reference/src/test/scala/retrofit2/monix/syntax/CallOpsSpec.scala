package retrofit2.monix.syntax

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import okhttp3.ResponseBody
import org.scalamock.scalatest._
import org.scalatest.Inspectors._
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

	"Calls as tasks" when {

		def withFixtures[A](modified: Task[Response[Unit]] => Task[Response[Unit]] = identity)(borrower: (Call[Unit], Task[Response[Unit]]) => A): A = {
			val source = mock[Call[Unit]]
			val local = mock[Call[Unit]]

			val copy = mockFunction[Call[Unit], Call[Unit]]

			(source.cancel _).expects().never()
			(source.enqueue _).expects(*).never()
			(source.execute _).expects().never()

			(local.cancel _).expects().never()
			(local.enqueue _).expects(*).never()

			copy.expects(source).anyNumberOfTimes().returns(local)

			borrower(local, modified(source.asTask(copy)))
		}

		"never run" must {
			"not execute calls" in withFixtures() { (local, _) =>
				(local.execute _).expects().never()
			}
		}

		"run repeatedly" must {
			val responses = Response.success(()) :: Response.error[Unit](400, mock[ResponseBody]) :: Nil
			val errors = List.fill(2) { new Exception }

			"execute many calls" that {

				"return responses" in withFixtures() { (local, task) =>
					inSequence {
						responses foreach { response =>
							(local.execute _).expects().once().returns(response)
						}
					}

					forAll(responses) { response =>
						task.runAsync.futureValue should be(response)
					}
				}

				"throw exceptions" in withFixtures() { (local, task) =>
					inSequence {
						errors foreach { error =>
							(local.execute _).expects().once().throws(error)
						}
					}

					forAll(errors) { error =>
						task.runAsync.failed.futureValue should be(error)
					}
				}

			}

			"execute one memoized call" that {

				"return responses" in withFixtures(_.memoize) { (local, task) =>
					val response = responses.head
					(local.execute _).expects().once().returns(response)
					forAll(responses) { _ =>
						task.runAsync.futureValue should be(response)
					}
				}

				"throw exceptions" in withFixtures(_.memoize) { (local, task) =>
					val error = errors.head
					(local.execute _).expects().once().throws(error)
					forAll(errors) { _ =>
						task.runAsync.failed.futureValue should be(error)
					}
				}

			}

			"execute one success-memoized call" that {

				"return responses" in withFixtures(_.memoizeOnSuccess) { (local, task) =>
					val response = responses.head
					(local.execute _).expects().once().returns(response)
					forAll(responses) { _ =>
						task.runAsync.futureValue should be(response)
					}
				}

				"throw exceptions" in withFixtures(_.memoizeOnSuccess) { (local, task) =>
					inSequence {
						errors foreach { error =>
							(local.execute _).expects().once().throws(error)
						}
					}

					forAll(errors) { error =>
						task.runAsync.failed.futureValue should be(error)
					}
				}

			}

		}

	}

}
