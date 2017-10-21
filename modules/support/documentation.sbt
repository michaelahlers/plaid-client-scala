/*
 * FIXME: Setting is not propagated correctly?
 * See also https://stackoverflow.com/questions/20009263/sbt-ignores-my-scaladoc-settings.
 */
scalacOptions in Compile in doc := (scalacOptions in ThisBuild in doc).value
scalacOptions in Test in doc := (scalacOptions in ThisBuild in doc).value
