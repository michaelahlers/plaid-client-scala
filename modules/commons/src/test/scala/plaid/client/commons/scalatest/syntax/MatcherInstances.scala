package plaid.client.commons.scalatest.syntax

import java.lang.reflect.Modifier

import org.scalactic.source.Position
import org.scalatest.exceptions.{ StackDepthException, TestFailedException }
import org.scalatest.matchers.{ HavePropertyMatchResult, HavePropertyMatcher }
import plaid.client.commons.reflection.syntax.FieldOps.Private
import plaid.client.commons.reflection.syntax.fields._
import shapeless.tag._

import scala.language.implicitConversions

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
trait MatcherInstances {

	/**
	 * While ScalaTest provides helpful tools like [[org.scalatest.PrivateMethodTester]] for testing non-public interfaces, it offers no such feature for testing non-public fields. Augments the arbitrary property matching syntax by reading any non-public declared field on the object under test.
	 *
	 * @see [[http://scalatest.org/user_guide/using_matchers#checkingArbitraryProperties ''Using matchers'']]
	 * @see [[org.scalatest.Matchers.convertSymbolToHavePropertyMatcherGenerator]]
	 */
	implicit def implyPrivatePropertyMatcher(field: Symbol @@ Private)(implicit position: Position): Any => HavePropertyMatcher[AnyRef, Any] = { expectedValue =>
		/* TODO: Use SAM if (when) this project removes support for Scala 2.11. */
		new HavePropertyMatcher[AnyRef, Any] {
			override def apply(objectUnderTest: AnyRef) =
				objectUnderTest.getClass.getDeclaredFields.find(_.getName == field.name) match {

					case None => throw new TestFailedException((_: StackDepthException) => Some(s"""No field named "${field.name}."."""), None, position)
					case Some(f) if Modifier.isPublic(f.getModifiers) => throw new TestFailedException((_: StackDepthException) => Some(s"""Field "${field.name} was public (had modifiers: ${Modifier.toString(f.getModifiers).replace(" ", ", ")}); prefer default property matching syntax for these."."""), None, position)

					case Some(_) =>
						val actualValue = objectUnderTest.get(field)
						new HavePropertyMatchResult(
							actualValue == expectedValue,
							field.name,
							expectedValue,
							actualValue
						)
				}
		}
	}

}
