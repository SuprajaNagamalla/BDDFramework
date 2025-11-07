Feature: MyAccount_Suspense and COA- Frontend

	Background:
#		Given the user opens URL
#		And the user launches the RAC home page

#@SFCC3133 @SFCC4685 @TC_SFCC4039 @smoke @regression @PaymentGateWay
@TC_SFCC4039_to_4045_and_7652_to_7653 @regression @MYACCOUNT
Scenario Outline: Verify My Account Suspense and COA- Frontend

	  Given the user opens URL
		And the user launches the RAC home page
		When the user clicks on Sign In button
		And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
		And the user clicks on Sign In button in the My Account dialog

	#TC_SFCC4039
	When the user navigates to Account Details page
	Then Rental Account Balance is displayed
	And Available Funds is displayed
	When the user clicks on Available Funds tool tip button
	Then Available Funds tool tip text is displayed
	And able to close Available Funds tool tip

	#SFCC-4040
	When the user clicks on agreement payment
	Then the user navigated to payment page
	And  the default state of Available Funds toggle is OFF

#	#SFCC-4041
	When the user turns ON the Available funds toggle
	Then text box is displayed to enter suspense amount
	And payment due, balance applied, funds applied are displayed on page

#	#SFCC-4042
	And the user should be able to see default "$0" is displayed in suspense amount text box
#	And message is displayed below the text box indicating the amount applied and amount available
#
#	#SFCC-4043
#	When the user enters suspense amount "50"
#	Then the amount applied and amount available values get updated according to the entered value
#	And the message displayed below the text box updated with new values of amount applied and amount available
#
#	#SFCC-4044
#	When the user clicks on continue button
#	Then complete payment page is displayed with payment due, balance applied, funds applied
#
#	#SFCC-4045
#	When the user submits the payment
#	Then payment successful page is displayed with payment due, balance applied, funds applied
	
Examples:
			| SheetName  	   					 					  | RowNumber | CardType		| AgreementType						 					           |
			| MyAccount-AgreementWithSuspCOA		  | 0         | Discover		| Multiple Agreement with Suspense and COA 	   |



  @TC_SFCC7650 @regression @MYACCOUNT 
  Scenario Outline: Verify Suspense display on My Account Page
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Sign In button
    And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
    And the user clicks on Sign In button in the My Account dialog
   	And Available Funds is displayed
		When the user clicks on Available Funds tool tip button
		Then Available Funds tool tip text is displayed
		And able to close Available Funds tool tip    
 Examples:
			| SheetName  	   					 					  | RowNumber | CardType		| AgreementType						 	 |
			| MyAccount-AgreementWithSusp   		  | 0         | Discover		| Agreement with Suspense		 | 
  
