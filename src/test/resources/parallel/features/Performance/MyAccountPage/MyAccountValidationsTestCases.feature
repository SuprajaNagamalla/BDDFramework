Feature: MyAccount_Validate MyAccount Features

  Background:
    #Given the user opens URL
    #And the user launches the RAC home page

  @TC_SFCC3967_and_4055 @regression @MYACCOUNT
  Scenario Outline: Verify Payment Details in Payment Section
    Given the user opens URL
		And the user launches the RAC home page
		When the user clicks on Sign In button
		And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
		And the user clicks on Sign In button in the My Account dialog
		#4055
		When the user clicks on the Payment Methods section
		And the user clicks on the Delete button in the Saved Payment Methods section if card Present
		When the user clicks on the Payment Methods section
		And the user clicks on Credit Card payment method if Not Selected
		And the user adds payment method credit or debit card credentials in my accounts from "<SheetName>" at row <RowNumber>
		Then credit card is saved under Payment Methods section as per the sheet "<SheetName>" at row <RowNumber>
		When the user clicks on the Delete button in the Saved Payment Methods section
		
		#3967
		Then validate clicking on cancel button doesnt delete payment method
    
 Examples:
			| SheetName  	   					 					| RowNumber | CardType		| AgreementType						 					 |
			| MyAccount-SingleAgrwithoutBP		  | 0         | Discover		| Single Agreement with Saved Card 	 |
			
			
	@TC_SFCC7685 @regression @MYACCOUNT
  Scenario Outline: Verify dollar Zero payment
    Given the user opens URL
		And the user launches the RAC home page
		When the user clicks on Sign In button
		And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
		And the user clicks on Sign In button in the My Account dialog
		Then verify Total Amount is zero when COA is applied
		Then user Clicks on continue button to make payment
		
		#7685
		Then validate only CreditCard radio button is displayed
	Examples:
			| SheetName  	   					 					  | RowNumber | CardType		| AgreementType						 					         |
			| MyAccount-AgreementWithSuspCOA		  | 0         | Discover		| Single Agreement with Suspense and COA 	   |
			
	
	@MYACCOUNT @regression @TC_SFCC4046_to_4051
  Scenario Outline: Verify Agreement Details for My Account for - <Payment Type>
   Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
		Given the user opens URL
		And the user launches the RAC home page
		When the user clicks on Sign In button
		And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
		And the user clicks on Sign In button in the My Account dialog
		Then the user clicks on Account Details in My Account Section
	  Then verify makePayment button and agreement Details link is displayed
	  Then verify Clicking on MakePayment button displays all Agreements and get Agreement Details
	  Then user Clicks on continue button to make payment
	  Then the user clicks on select Payments and validate all checkboxes are autochecked
    Examples:
			| SheetName  	   					 				| RowNumber | Payment Type							   													   			 | Payment 				   |
			| MyAccount-MulAgrSameStore				| 0         | Multiple Agreements																			 			 | CreditCard		     |
	