libraryDependencies ++=
  "com.plaid" % "plaid-java" % "2.1.6" ::
    "org.scalactic" %% "scalactic" % "3.0.4" ::
    Nil

libraryDependencies ++=
  "org.mockito" % "mockito-core" % "2.10.0" % Test ::
    "org.scalamock" %% "scalamock-scalatest-support" % "3.6.0" % Test ::
    "org.scalatest" %% "scalatest" % "3.0.4" % Test ::
    Nil
