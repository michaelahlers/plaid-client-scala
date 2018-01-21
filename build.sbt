lazy val models =
	(project in file("modules") / "models")
		.dependsOn(support % "test->test;compile->compile")

lazy val support =
	project in file("modules") / "support"

lazy val client =
	(project in file("."))
		.aggregate(models)
		.dependsOn(support % "test->test;compile->compile")

/* Enable integration tests. */
configs(IntegrationTest)
Defaults.itSettings
scalariformItSettings
