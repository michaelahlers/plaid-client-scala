publishMavenStyle := false

publishTo := Some(s3resolver.value("Ahlers Consulting", s3("artifacts.ahlers.consulting")))
