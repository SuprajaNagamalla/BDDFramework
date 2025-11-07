Feature: MyAccount_Validate Make Payment is successful with different tender types

  Background:
    #Given the user opens URL
    #And the user launches the RAC home page

  @MYACCOUNT @AllPayments @MyAccountPayments @smoke
  Scenario Outline: Verify Agreement Payment from My Account for - <Payment Type>
   Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
		Given the user opens URL
		And the user launches the RAC home page
		When the user clicks on Sign In button
		And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
		And the user clicks on Sign In button in the My Account dialog
		When the user clicks on the Payment Methods section
		And the user clicks on the Delete button in the Saved Payment Methods section if card Present
		And the user clicks on Back to Dashboard button
	  Then verify makePayment button and agreement Details link is displayed
	  Then verify Clicking on MakePayment button displays all Agreements and get Agreement Details
	  Then user Clicks on continue button to make payment
	  Then the user gets correlation id "<Payment>"
	  And the user clicks on Venmo payment method
	  And the user saves Venmo credentials MyAccount
	  Then the user selects I agree checkbox Venmo
	  Then the user clicks on make payment button for Venmo
    Then validate payment successful screen details
    Examples:
			| SheetName  	   					 				| RowNumber | Payment Type							   													         | Payment 				   |
		  | MyAccount-AgreementWithBP				| 0         | Single Agreement With Benefits Plus	and Venmo Payment		 		   | Venmo 		         |

	@MYACCOUNT @AllPayments @MyAccountPayments @smoke @TC_SFCC7750
  Scenario Outline: Verify Agreement Payment from My Account for - <Payment Type>
   Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
		Given the user opens URL
		And the user launches the RAC home page
		When the user clicks on Sign In button
		And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
		And the user clicks on Sign In button in the My Account dialog
		Then the user clicks on Account Details in My Account Section
		When the user clicks on the Payment Methods section
		And the user clicks on the Delete button in the Saved Payment Methods section if card Present
		And the user clicks on Back to Dashboard button
	  Then verify makePayment button and agreement Details link is displayed
	  Then verify Clicking on MakePayment button displays all Agreements and get Agreement Details
	  Then user Clicks on continue button to make payment
	  Then the user gets correlation id "<Payment>"
	  And the user clicks on Credit Card payment method
	  And the user adds payment method credit card credentials in my accounts from "<SheetName>" at row <RowNumber>
	  Then validate payment successful screen details
    Examples:
			| SheetName  	   					 				| RowNumber | Payment Type							   													   			       | Payment 				         |
#			| MyAccount-MulAgrMulStores				| 0         | Multiple Agreements with Multiple Stores and Credit Card			  		 | Credit Card             |
 		  | MyAccount-AgreementWithBP				| 1         | Single Agreement With Benefits Plus	and Credit Card						 		   | Credit Card 		         |
			
	
	@MYACCOUNT @AllPayments @MyAccountPayments @smoke @TC_SFCC7757
  Scenario Outline: Verify Agreement Payment from My Account for - <Payment Type>
   Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
		Given the user opens URL
		And the user launches the RAC home page
		When the user clicks on Sign In button
		And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
		And the user clicks on Sign In button in the My Account dialog
		Then the user clicks on Account Details in My Account Section
		When the user clicks on the Payment Methods section
		And the user clicks on the Delete button in the Saved Payment Methods section if card Present
		And the user clicks on Back to Dashboard button
	  Then verify makePayment button and agreement Details link is displayed
	  Then verify Clicking on MakePayment button displays all Agreements and get Agreement Details
	  Then user Clicks on continue button to make payment
	  Then the user gets correlation id "<Payment>"
	  And the user clicks on PayPal payment method
	  And the user saves Paypal credentials
	  Then the user selects I agree checkbox Paypal
	  Then the user clicks on make payment button for PayPal
    Then validate payment successful screen details
	  
    Examples:
			| SheetName  	   					 				| RowNumber | Payment Type							   													   | Payment 				   |
			| MyAccount-MulAgrSameStore				| 0         | Multiple Agreements with Same Store	and Paypal			  	 | paypal            |
		
	
	@MYACCOUNT @AllPayments @MyAccountPayments @smoke @TC_SFCC7655	
  Scenario Outline: Add new card in payment Methods and Make Payment for - <AgreementType>
   Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
		Given the user opens URL
		And the user launches the RAC home page
		When the user clicks on Sign In button
		And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
		And the user clicks on Sign In button in the My Account dialog
		When the user clicks on the Payment Methods section
		And the user clicks on the Delete button in the Saved Payment Methods section if card Present
		And the user clicks on Back to Dashboard button
		When the user clicks on the Payment Methods section
		And the user clicks on Credit Card payment method if Not Selected
		And the user adds payment method credit or debit card credentials in my accounts from "<SheetName>" at row <RowNumber>
		Then credit card is saved under Payment Methods section as per the sheet "<SheetName>" at row <RowNumber>
	  Then verify makePayment button and agreement Details link is displayed
	  Then verify Clicking on MakePayment button displays all Agreements and get Agreement Details
	  Then Uncheck all agreement and verify continue button and total payment
	  Then user Clicks on continue button to make payment
	  Then the user gets correlation id "<Payment>"
	  And the user makes payment with the saved card and validate payment success message from "<SheetName>" at row <RowNumber>
	  Then the user clicks on Account Details in My Account Section
	  When the user clicks on the Payment Methods section
	  When the user clicks on the Delete button in the Saved Payment Methods section
		And the user clicks on the Delete Payment Method button in the pop-up
    Examples:
			| SheetName  	   					 					| RowNumber | CardType		| AgreementType						 					 | Payment 				   |
			| MyAccount-AgreementWithBP   		  | 2         | Discover		| Single Agreement with Saved Card 	 | CreditCard		     |
	
	