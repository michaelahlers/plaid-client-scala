publishMavenStyle in ThisBuild := false
publishTo in ThisBuild := Some(s3resolver.value("Ahlers Consulting", s3("artifacts.ahlers.consulting")))

