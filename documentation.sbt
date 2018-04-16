/** See http://scala-sbt.org/0.13/docs/Howto-Scaladoc.html for details. */
autoAPIMappings in ThisBuild := true

javacOptions in ThisBuild in doc :=
	"-linksource" ::
		Nil
