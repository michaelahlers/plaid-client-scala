name := "plaid-client-scala"
description := "Scala language bindings for Plaid's services."

organization in ThisBuild := "consulting.ahlers"
organizationName in ThisBuild := "Ahlers Consulting"
organizationHomepage in ThisBuild := Some(url("http://ahlers.consulting"))

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
	("MIT", url("https://en.wikipedia.org/wiki/MIT_License")) ::
		Nil

