lazy val models =
	(project in file("modules") / "models")
		.dependsOn(support)

lazy val support =
	project in file("modules") / "support"

lazy val `plaid-client-scala` =
	(project in file("."))
		.aggregate(models)
		.dependsOn(support)

/* Enable integration tests. */
configs(IntegrationTest)
Defaults.itSettings
scalariformItSettings
