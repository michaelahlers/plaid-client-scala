lazy val `clients-reference` =
	(project in file("modules") / "clients" / "reference")
		.dependsOn(commons % "test->test;compile->compile", models % "test->test;compile->compile")

lazy val `commons` =
	project in file("modules") / "commons"

lazy val `models` =
	(project in file("modules") / "models")
		.dependsOn(commons % "test->test;compile->compile")

lazy val `plaid-client-scala` =
	(project in file("."))
		.aggregate(`clients-reference`, `commons`, `models`)

/* Enable integration tests. */
configs(IntegrationTest)
Defaults.itSettings
scalariformItSettings
