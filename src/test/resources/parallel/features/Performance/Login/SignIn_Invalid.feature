Feature: Sign In with multiple invalid users

#	Background:
#		Given the user opens URL
#		And the user launches the RAC home page

#@Epic1 @Story1 @Scenario2 @smoke @regression
#@smoke @regression @SignIn @Pending
Scenario Outline: Verify Invalid Sign In for multiple users

	When the user clicks on Sign In button
	And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
	And the user clicks on Sign In button in the My Account dialog
	#Then the user should see the error message "<ExpectedResult>" on My Account Dialog
	Then the user should see the error message "Sign in failed." on My Account Dialog

	Examples:
		| SheetName  | RowNumber |
		| loginSheet | 0         |
		#| loginSheet | 1         |


	#@Epic1 @Story1 @Scenario50 @regression @smokeBS
	Scenario Outline: Verify Invalid Sign In for fifty users

		#Then skip execution if the iteration status is "No" in workbook "Login" on sheet "<SheetName>" at row <RowNumber>
		Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
		Given the user opens URL
		And the user launches the RAC home page

		When the user clicks on Sign In button
		And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
		And the user clicks on Sign In button in the My Account dialog
		#Then the user should see the error message "<ExpectedResult>" on My Account Dialog
		Then the user should see the error message "Sign in failed." on My Account Dialog

		Examples:
			| SheetName  	   | RowNumber |
			| Login-loginSheet | 0         |
#			| Login-loginSheet | 1         |
#			| Login-loginSheet | 2         |
#			| Login-loginSheet | 3         |
#			| Login-loginSheet | 4         |
