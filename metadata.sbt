name := "plaid-client-scala"
description := "Akka integration for Plaid's official Java client."

organization in ThisBuild := "consulting.ahlers"
homepage in ThisBuild := None
startYear in ThisBuild := Some(2017)

developers in ThisBuild ++=
	Developer("michaelahlers", "Michael Ahlers", "michael@ahlers.consulting", url("http://ahlers.consulting")) ::
		Nil

scmInfo in ThisBuild :=
	Some(ScmInfo(
		browseUrl = url("https://github.com/michaelahlers/plaid-client-scala"),
		connection = "https://github.com/michaelahlers/plaid-client-scala.git",
		devConnection = None
	))

licenses in ThisBuild :=
	Nil

