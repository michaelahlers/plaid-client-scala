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
	"org.mockito" % "mockito-core" % "2.11.0" % Test ::
		"org.scalacheck" %% "scalacheck" % "1.13.4" % Test ::
		"org.scalamock" %% "scalamock-scalatest-support" % "3.6.0" % Test ::
		"org.scalatest" %% "scalatest" % "3.0.4" % Test ::
		Nil
