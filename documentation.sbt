/** See http://scala-sbt.org/0.13/docs/Howto-Scaladoc.html for details. */
autoAPIMappings := true

scalacOptions in(Compile, doc) ++=
	"-no-link-warnings" ::
		Nil

/** See http://stackoverflow.com/a/35673212/700420 for details. */
apiMappings ++= {
	def mappingsFor(organization: String, names: List[String], location: String, revision: (String) => String = identity): Seq[(File, URL)] =
		for {
			entry: Attributed[File] <- (fullClasspath in Compile).value
			module: ModuleID <- entry.get(moduleID.key)
			if module.organization == organization
			if names.exists(module.name.startsWith)
		} yield entry.data -> url(location.replace("${revision}", revision(module.revision)))

	/** Several of these may not be used in this project as this is a reusable set of mappings. */
	val mappings: Seq[(File, URL)] = Nil

	mappings.toMap
}
