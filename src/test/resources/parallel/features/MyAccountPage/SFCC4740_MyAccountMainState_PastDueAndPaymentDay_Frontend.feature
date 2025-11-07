Feature: MyAccount_Validate MyAccount Dashoboard PastDue Details

  Background:
    #Given the user opens URL
    #And the user launches the RAC home page

  @TC_SFCC4139_SFCC4142 @regression @MYACCOUNT
  Scenario Outline: Verify PastDue details on My Account Page
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
		Given the user opens URL
		And the user launches the RAC home page
		When the user clicks on Sign In button
		And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
		And the user clicks on Sign In button in the My Account dialog
    And the user clicks on Account Details in My Account Section
    Then verify past due date banner is displayed
    Then verify past due text is displayed and value not equal to Zero
    Then validate current Due Date is less than todays Date
    Then verify my approval amount section
Examples:
			| SheetName  	   					   | RowNumber |
			| MyAccount-LoginSheet		   | 0         |
			
	
	@TC_SFCC7759_and_7774 @regression @MYACCOUNT
  Scenario Outline: Verify the dashboard with Multiple Agreements with Multiple Stores
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
		Given the user opens URL
		And the user launches the RAC home page
		When the user clicks on Sign In button
		And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
		And the user clicks on Sign In button in the My Account dialog
		Then the user verifies multiple stores displayed under MyStores
		Then user clicks on AgreementDetails link and validate StoreDetails
		And the user validate myStore address is displayed once logged in
		And the user validates payment methods displayed
Examples:
			| SheetName  	   					       | RowNumber |
			| MyAccount-MulAgrMulStores		   | 0         |