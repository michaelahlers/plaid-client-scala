import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import scalariform.formatter.preferences._

ScalariformKeys.preferences :=
	ScalariformKeys.preferences.value
		.setPreference(IndentWithTabs, true)
		.setPreference(NewlineAtEndOfFile, true)
		.setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk, false)
