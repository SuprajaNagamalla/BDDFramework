Feature: SignIn

	Background:
		Given the user opens URL
		And the user launches the RAC home page

 #@regression @SignIn
 Scenario: Verify Sign In successful

	 When the user clicks on Sign In button
	#And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
	 And the user fills the sign in form
	 And the user clicks on Sign In button in the My Account dialog
	 Then the user verifies that Sign In is successful
	 #Then the user verifies pricing for store locator displayed