lazy val `models` =
	project in file("modules") / "models"

lazy val `plaid-client-scala` =
	(project in file("."))
		.aggregate(`models`)

/* Enable integration tests. */
configs(IntegrationTest)
Defaults.itSettings
scalariformItSettings
