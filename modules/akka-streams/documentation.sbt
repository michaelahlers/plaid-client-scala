/* FIXME: Setting is not propagated correctly? */
scalacOptions in Compile in doc := (scalacOptions in ThisBuild in doc).value
scalacOptions in Test in doc := (scalacOptions in ThisBuild in doc).value
