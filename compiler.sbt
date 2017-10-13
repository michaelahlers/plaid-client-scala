crossScalaVersions := Seq("2.11.11", "2.12.3")

scalaVersion := "2.12.3"

scalacOptions := {
  CrossVersion.partialVersion(scalaVersion.value) match {

    /** See also [[https://tpolecat.github.io/2014/04/11/scalac-flags.html ''Useful Scalac Flags'']]. */
    case Some((2, 11)) =>
      "-encoding" :: "UTF-8" ::
        "-target:jvm-1.8" ::
        "-deprecation" ::
        "-feature" ::
        "-language:existentials" ::
        "-language:higherKinds" ::
        "-language:implicitConversions" ::
        "-unchecked" ::
        "-Xfatal-warnings" ::
        "-Xlint" ::
        "-Yno-adapted-args" ::
        "-Ywarn-dead-code" ::
        "-Ywarn-numeric-widen" ::
        "-Ywarn-value-discard" ::
        // "-Xfuture"::
        // "-Ywarn-unused-import" ::
        Nil

      /** See also [[https://tpolecat.github.io/2017/04/25/scalac-flags.html ''Recommended Scalac Flags for 2.12'']]. */
    case Some((2, 12)) =>
      "-encoding" :: "UTF-8" ::
        "-target:jvm-1.8" ::
        "-deprecation" ::
        "-explaintypes" ::
        "-feature" ::
        // "-language:existentials" ::
        // "-language:experimental.macros" ::
        // "-language:higherKinds" ::
        // "-language:implicitConversions" ::
        "-unchecked" ::
        "-Xcheckinit" ::
        "-Xfatal-warnings" ::
        // "-Xfuture" ::
        "-Xlint:_" ::
        "-Yno-adapted-args" ::
        "-Ypartial-unification" ::
        "-Ywarn-dead-code" ::
        "-Ywarn-extra-implicit" ::
        "-Ywarn-inaccessible" ::
        "-Ywarn-infer-any" ::
        "-Ywarn-nullary-override" ::
        "-Ywarn-nullary-unit" ::
        "-Ywarn-numeric-widen" ::
        // "-Ywarn-unused:implicits" ::
        // "-Ywarn-unused:imports" ::
        // "-Ywarn-unused:locals" ::
        // "-Ywarn-unused:params" ::
        // "-Ywarn-unused:patvars" ::
        // "-Ywarn-unused:privates" ::
        "-Ywarn-value-discard" ::
        Nil

    case _ =>
      Nil

  }
}
