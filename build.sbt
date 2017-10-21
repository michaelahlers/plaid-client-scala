lazy val `akka-streams` =
	(project in file("modules") / "akka-streams")
		.dependsOn(models)

lazy val models = project in file("modules") / "models"

aggregateProjects(`akka-streams`, models)

/* Enable integration tests. */
configs(IntegrationTest)
Defaults.itSettings
scalariformItSettings
