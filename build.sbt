lazy val commons =
	project in file("modules") / "commons"

lazy val models =
	(project in file("modules") / "models")
		.dependsOn(commons % "test->test;compile->compile")

normalizedName := "client"

lazy val client =
	(project in file("."))
		.aggregate(commons, models)
		.dependsOn(commons % "test->test;compile->compile")

/* Enable integration tests. */
configs(IntegrationTest)
Defaults.itSettings
scalariformItSettings
