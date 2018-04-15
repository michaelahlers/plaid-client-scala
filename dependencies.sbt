libraryDependencies in ThisBuild ++=
	"org.scalactic" %% "scalactic" % "3.0.5" ::
		Nil

libraryDependencies in ThisBuild ++=
	"com.github.alexarchambault" %% "scalacheck-shapeless_1.13" % "1.1.8" % Test ::
		"org.mockito" % "mockito-core" % "2.18.0" % Test ::
		"org.scalacheck" %% "scalacheck" % "1.13.5" % Test ::
		"org.scalamock" %% "scalamock-scalatest-support" % "3.6.0" % Test ::
		"org.scalatest" %% "scalatest" % "3.0.5" % Test ::
		"org.scalamock" %% "scalamock" % "4.1.0" % Test ::
		Nil
