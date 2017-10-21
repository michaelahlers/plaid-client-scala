lazy val `akka-streams` =
	(project in file("modules") / "akka-streams")
		.dependsOn(`models`)

lazy val `models` =
	project in file("modules") / "models"

lazy val `plaid-client-scala` =
	(project in file("."))
		.aggregate(`akka-streams`, `models`)

/* Enable integration tests. */
configs(IntegrationTest)
Defaults.itSettings
scalariformItSettings
