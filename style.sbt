import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import scalariform.formatter.preferences._

ScalariformKeys.preferences :=
	ScalariformKeys.preferences.value
		.setPreference(DanglingCloseParenthesis, Force)
		.setPreference(IndentWithTabs, true)
		.setPreference(NewlineAtEndOfFile, true)
		.setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk, false)
