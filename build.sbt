lazy val `clients-reference` =
	(project in file("modules") / "clients-reference")
		.aggregate(commons, models)
		.dependsOn(commons % "test->test;compile->compile")

lazy val commons =
	project in file("modules") / "commons"

lazy val models =
	(project in file("modules") / "models")
		.dependsOn(commons % "test->test;compile->compile")

/* Enable integration tests. */
configs(IntegrationTest)
Defaults.itSettings
scalariformItSettings
