organization := "consulting.ahlers"

name := "plaid-client-akka"

description := "Akka integration for Plaid's official Java client."

homepage := None

startYear := Some(2017)

developers :=
  Developer("michaelahlers", "Michael Ahlers", "michael@ahlers.consulting", url("http://ahlers.consulting")) ::
    Nil

scmInfo :=
  Some(ScmInfo(
    browseUrl = url("https://github.com/michaelahlers/plaid-client-akka"),
    connection = "https://github.com/michaelahlers/plaid-client-akka.git",
    devConnection = None
  ))

licenses :=
  Nil
