Feature: MyAccount_Validate Agreement Details Page and Make Payment

  Background:
    #Given the user opens URL
    #And the user launches the RAC home page

  @TC_SFCC3951_to_3963_and_3976_to_3980 @regression @MYACCOUNT
  Scenario Outline: Verify Agreement Details page and Make Payment for - <AgreementType>
   Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
		Given the user opens URL
		And the user launches the RAC home page
		When the user clicks on Sign In button
		And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
		And the user clicks on Sign In button in the My Account dialog
		When the user clicks on the Payment Methods section
		And the user clicks on the Delete button in the Saved Payment Methods section if card Present
		And the user clicks on BackToDashboard link
	  Then verify makePayment button and agreement Details link is displayed
	  Then verify Clicking on MakePayment button displays all Agreements and get Agreement Details
	  
	  #3960
	  Then verify no dropdown option displays for benefits plus
	  Then user Clicks on continue button to make payment
	  And the user clicks on Credit Card payment method
	  And the user adds payment method credit card credentials in my accounts from "<SheetName>" at row <RowNumber>
	  
	  #sfcc-3976 to sfcc-3979
	  Then validate payment successful screen details
	  Then Validate back to dashboard link is Displayed
	  Then Validate payment success Message on Dashboard
	  
    Examples:
			| SheetName  	   					 				| RowNumber | CardType		| AgreementType							   						 |
			| MyAccount-AgreementWithBP				| 1         | Discover		| Single Agreement With Benefits Plus			 |
			
	#@SFCC3133 @SFCC4618 @smoke @regression @MYACCOUNT @saveCardWhilePayment 
  #@regression @MYACCOUNT
  Scenario Outline: Save Card While Making payment for - <AgreementType>
   Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
		Given the user opens URL
		And the user launches the RAC home page
		When the user clicks on Sign In button
		And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
		And the user clicks on Sign In button in the My Account dialog
		When the user clicks on the Payment Methods section
		And the user clicks on the Delete button in the Saved Payment Methods section if card Present
		Then the user clicks on Account Details in My Account Section
	  Then verify makePayment button and agreement Details link is displayed
	  Then verify Clicking on MakePayment button displays all Agreements and get Agreement Details
	  Then user Clicks on continue button to make payment
	  And the user clicks on Credit Card payment method
	  And the user adds payment method credit card credentials in my accounts and save card from "<SheetName>" at row <RowNumber>
	  Then the user clicks on Account Details in My Account Section
	  When the user clicks on the Payment Methods section
	  When the user clicks on the Delete button in the Saved Payment Methods section
		And the user clicks on the Delete Payment Method button in the pop-up
    Examples:
			| SheetName  	   					 				| RowNumber | CardType		| AgreementType							   						 |
			| MyAccount-AgreementWithBP				| 1         | Discover		| Single Agreement With Benefits Plus			 |
			
#	@SFCC3133 @SFCC4618 @TC_SFCC3958 @regression @MYACCOUNT
  Scenario Outline: Verify Agreement Details page for different agreements
   Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
		Given the user opens URL
		And the user launches the RAC home page
		When the user clicks on Sign In button
		And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
		And the user clicks on Sign In button in the My Account dialog
	  Then verify makePayment button and agreement Details link is displayed
	  Then verify Clicking on MakePayment button displays all Agreements and get Agreement Details
    Then validate agreement based on payment option sent
      | paymentOption1 |
      | Weekly    		 |
    Examples:
			| SheetName  	   					 					| RowNumber |
			| MyAccount-SingleAgrwithoutBP      | 0         |
			
	
#	@SFCC3133 @SFCC4618 @TC_ValidatePaymentHistory @regression @MYACCOUNT
	@MYACCOUNT @TC_SFCC4034_to_4038 @regression
  Scenario Outline: Verify Payment history Page
   Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
		Given the user opens URL
		And the user launches the RAC home page
		When the user clicks on Sign In button
		And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
		And the user clicks on Sign In button in the My Account dialog
		And the user clicks on Account Details in My Account Section
    Then verify quick links component header is displayed
    
    #SFCC-4034 #SFCC-4035 #SFCC-4036 #SFCC-4037 #SFCC-4038
    Then Validate Payment History
     Then the following quick link components should be displayed:
      | My Account Dashboard |
      | Cart                 |
      | Saved Items          |
      | Payment Methods      |
      | Payment History      |
      | Personal Info        |
    Examples:
			| SheetName  	   					 					| RowNumber |
			| MyAccount-SingleAgrwithoutBP      | 0         |
			
			
#	 @SFCC4737 @TC_SFCC4147 @regression @MYACCOUNT
	 Scenario Outline: Verify Personal Details Section
   	Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
		Given the user opens URL
		And the user launches the RAC home page
		When the user clicks on Sign In button
		And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
		And the user clicks on Sign In button in the My Account dialog
		And the user clicks on Account Details in My Account Section
    Then verify quick links component header is displayed
    Then Validate Personal Info Section
    Examples:
			| SheetName  	   					 					| RowNumber |
			| MyAccount-SingleAgrwithoutBP		  | 0         |
			
#	@SFCC3133 @SFCC4618 @TC_ValidateSavedItems @regression @MYACCOUNT
	 Scenario Outline: Verify Saved Item Section
   	Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
		Given the user opens URL
		And the user launches the RAC home page
		When the user clicks on Sign In button
		And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
		And the user clicks on Sign In button in the My Account dialog
		And the user clicks on Account Details in My Account Section
    Then verify quick links component header is displayed
    Then Validate Saved Items Section
    Examples:
			| SheetName  	   					 					| RowNumber |
			| MyAccount-SingleAgrwithoutBP		  | 0         |
			
	