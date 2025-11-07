Feature: MyAccount_Enroll and UnEnroll AutoPay using different Payment Methods

  Background:
    #Given the user opens URL
    #And the user launches the RAC home page

  #@EnrollAutopayCardOnFile 
  @regression @MYACCOUNT @TC_SFCC4124_and_4137_to_4138
  Scenario Outline: Enroll Autopay with Card on File for - <AgreementType>
  Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
		Given the user opens URL
		And the user launches the RAC home page
		When the user clicks on Sign In button
		And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
		And the user clicks on Sign In button for MyAccount 
		And the user clicks the "AutoPay Enrolled" button in My Account Section if Enrolled
		When the user clicks on the Payment Methods section
		And the user clicks on the Delete button in the Saved Payment Methods section if card Present
		And the user clicks on Credit Card payment method if Not Selected
		And the user adds payment method credit or debit card credentials in my accounts from "<SheetName>" at row <RowNumber>
		Then credit card is saved under Payment Methods section as per the sheet "<SheetName>" at row <RowNumber>
		When the user clicks on Back to Dashboard button
		When the user clicks on Enroll in AutoPay button in account details
		And the user clicks the "Continue" button
		When the user clicks I agree to the check box enroll auto pay
		And the user clicks the "ENROLL IN AUTOPAY" button under saved payment methods
		Then the user clicks on Account Details in My Account Section
		Then AutoPay enrollment is successful
		When the user clicks on the Payment Methods section
		And the user clicks the "Enrolled in AutoPay - Details" button
		Then the system displays a message with instructions to delete an auto-enrolled payment method
		Then the user clicks on Account Details in My Account Section
		And the user clicks the "AutoPay Enrolled" button in My Account Section
		And the user clicks the "Unenroll AutoPay" button
		And the user clicks on the UnEnroll AutoPay popUp
	  Then the user clicks on Account Details in My Account Section
	  When the user clicks on the Payment Methods section
	  When the user clicks on the Delete button in the Saved Payment Methods section
		And the user clicks on the Delete Payment Method button in the pop-up
		And the user clicks on BackToDashboard link
		Then Verify Enroll in AutoPay option is displayed in the agreement section
		
    Examples:
			| SheetName  	   					 					| RowNumber | CardType		| AgreementType						 					 |
			| MyAccount-SingleAgrwithoutBP		  | 0         | Discover		| Single Agreement with Saved Card 	 |
			
	#@EnrollAutopayBankAccountOnFile  
	@regression @MYACCOUNT @TC_SFCC4733_and_7716 @TC_SFCC4072
  Scenario Outline: Enroll Autopay using Bank Account on File for - <AgreementType>
  Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
		Given the user opens URL
		And the user launches the RAC home page
		When the user clicks on Sign In button
		And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
		And the user clicks on Sign In button for MyAccount
		And the user clicks the "AutoPay Enrolled" button in My Account Section if Enrolled
		When the user clicks on the Payment Methods section
		And the user clicks on the Delete button in the Saved Payment Methods section if card Present
		And the user clicks on Bank Account payment method
		When the user saves bank account details under Add Payment method from "<SheetName>" at row <RowNumber>
		Then the bank account method is saved under the Payment Methods section
		When the user clicks on Back to Dashboard button
		When the user clicks on Enroll in AutoPay button in account details
		And the user clicks the "Continue" button
		When the user clicks I agree to the check box enroll auto pay
		And the user clicks the "ENROLL IN AUTOPAY" button under saved payment methods
		When the user clicks on Back to Dashboard button
		And the user refreshes the page
		Then the user clicks on Account Details in My Account Section
		Then AutoPay enrollment is successful
		When the user clicks on the Payment Methods section
		And the user clicks the "Enrolled in AutoPay - Details" button
		Then the system displays a message with instructions to delete an auto-enrolled payment method
		Then the user clicks on Back to Dashboard button
		And the user clicks the "AutoPay Enrolled" button in My Account Section
		And the user clicks the "Unenroll AutoPay" button
		And the user clicks on the UnEnroll AutoPay popUp
	  And the user refreshes the page 
		Then Verify Enroll in AutoPay option is displayed in the agreement section
		
    Examples:
			| SheetName  	   					 					| RowNumber | CardType		| AgreementType						 					 				 |
			| MyAccount-AgreementWithBP   		  | 0         | Discover		| Single Agreement with Saved BankAccount 	 |
	
	
	#@EnrollAutopayPayPalAccountOnFile		 //not a valid scenario
  #	@regression @MYACCOUNT @TC_SFCC4072
  Scenario Outline: Enroll Autopay using PayPal Account on File for - <AgreementType>
  Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
		Given the user opens URL
		And the user launches the RAC home page
		When the user clicks on Sign In button
		And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
		And the user clicks on Sign In button for MyAccount
		And the user clicks the "AutoPay Enrolled" button in My Account Section if Enrolled
		When the user clicks on the Payment Methods section
		And the user clicks on the Delete button in the Saved Payment Methods section if card Present
		When the user clicks on the Payment Methods section
		And the user clicks on PayPal payment method
		Then the user saves Paypal credentials
		When the user selects the "I agree" checkbox in My Account PayPal payment
		And the user clicks the "Make Payment" button
		When the user clicks on Back to Dashboard button
		When the user clicks on Enroll in AutoPay button in account details
		And the user clicks the "Continue" button
		When the user clicks I agree to the check box enroll auto pay paypal
		And the user clicks the "ENROLL IN AUTOPAY" button under saved payment methods
#		When the user clicks on Back to Dashboard button
#		And the user refreshes the page
		Then the user clicks on Account Details in My Account Section
		Then AutoPay enrollment is successful
		When the user clicks on the Payment Methods section
		And the user clicks the "Enrolled in AutoPay - Details" button
		Then the system displays a message with instructions to delete an auto-enrolled payment method
		Then the user clicks on Account Details in My Account Section
		And the user clicks the "AutoPay Enrolled" button in My Account Section
		And the user clicks the "Unenroll AutoPay" button
		And the user clicks on the UnEnroll AutoPay popUp
		And the user refreshes the page
		Then Verify Enroll in AutoPay option is displayed in the agreement section
		
    Examples:
			| SheetName  	   					 					  | RowNumber | CardType		| AgreementType						 					 				 |
			| MyAccount- AgreementWithBP   		    | 1         | Discover		| Single Agreement with Saved PayPal Account |
			