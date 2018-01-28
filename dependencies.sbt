libraryDependencies ++=
	"com.plaid" % "plaid-java" % "2.2.0" % Provided ::
		"com.typesafe.akka" %% "akka-stream" % "2.5.6" % Provided ::
		Nil

libraryDependencies ++=
	"com.typesafe.akka" %% "akka-stream-testkit" % "2.5.6" % Test ::
		Nil

libraryDependencies in ThisBuild ++=
	"org.scalactic" %% "scalactic" % "3.0.4" ::
		Nil

libraryDependencies in ThisBuild ++=
	"com.github.alexarchambault" %% "scalacheck-shapeless_1.13" % "1.1.8" % Test ::
		"org.mockito" % "mockito-inline" % "2.13.0" % Test ::
		"org.scalacheck" %% "scalacheck" % "1.13.5" % Test ::
		"org.scalamock" %% "scalamock-scalatest-support" % "3.6.0" % Test ::
		"org.scalatest" %% "scalatest" % "3.0.4" % Test ::
		"org.scalamock" %% "scalamock" % "4.0.0" % Test ::
		Nil
