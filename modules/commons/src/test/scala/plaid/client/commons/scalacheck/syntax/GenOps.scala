package plaid.client.commons.scalacheck.syntax

import org.scalacheck._

import scala.language.implicitConversions

/**
 * Syntactic sugar satisfying needs for at least one arbitrary sample from a given [[Gen]].
 * @see [[Gen.sample]]
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
final class GenOps[A](self: Gen[A]) {
	def samples: Stream[A] = Stream.continually(self).flatMap(_.sample)
	def samples(p: A => Boolean): Stream[A] = samples.filter(p)
}

trait ToGenOps {
	implicit def implyGenOps[A](t: Gen[A]): GenOps[A] = new GenOps(t)
}
