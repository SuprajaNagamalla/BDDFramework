Feature: PaymentGateWay - Add and Delete Payment Frontend

#	Background:
#		Given the user opens URL
#		And the user launches the RAC home page

#@SFCC3133 @SFCC4622 @TC_SFCC3965 @smoke @regression @PaymentGateway
#@SFCC4622 @TC_SFCC3965 @smoke @regression @PaymentGateway
@TC_SFCC3965_To_3968 @smoke @PaymentGateway @regression
Scenario Outline: Verify My Account add and delete Payments CC - <CardType>

	Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
	Given the user opens URL
	And the user launches the RAC home page
	When the user clicks on Sign In button
	And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
	#And the user fills the sign in form
	And the user clicks on Sign In button in the My Account dialog

	#Then the user verifies that Sign In is successful

	#Then the user verifies pricing for store locator displayed
	#When the user navigates to Account Details page
	#New Story: Add payment
	When the user clicks on the Payment Methods section

	And the user clicks on Credit Card payment method

	And the user adds payment method credit or debit card credentials in my accounts from "<SheetName>" at row <RowNumber>
	#And the user saves credit or debit card credentials
	Then credit card is saved under Payment Methods section as per the sheet "<SheetName>" at row <RowNumber>

	#TC_SFCC-3966
	And the user clicks on the Delete button in the Saved Payment Methods section
	Then the Delete Payment Method pop-up modal is displayed

	#TC_SFCC-3967
	When the user clicks on the Cancel button in the pop-up
	Then the payment method should not be deleted

	#TC_SFCC-3968
	When the user clicks on the Delete button in the Saved Payment Methods section
	And the user clicks on the 'X' (close) button on the pop-up
	Then the pop-up modal should be closed

	#TC_SFCC-3965
	When the user clicks on the Delete button in the Saved Payment Methods section
	And the user clicks on the Delete Payment Method button in the pop-up
	#And the user refreshes the page
	Then the payment method should be deleted

	Examples:
		| SheetName           		    | RowNumber |CardType		|
		| PaymentsMyAccounts-CCAllTypes | 0         |Discover		|
		#| PaymentsMyAccounts-CCAllTypes | 1         |Visa    		|
		#| PaymentsMyAccounts-CCAllTypes | 2         |Mastercard		|
		#| PaymentsMyAccounts-CCAllTypes | 3         |American Express|

	#@SFCC3133 @SFCC4622 @TC_SFCC3966 @regression @PaymentGateway
	#@SFCC4622 @TC_SFCC3966 @regression @PaymentGateway
	@regression @PaymentGateway
	Scenario Outline: Verify My Account add and delete Payment - PayPal

#		Given the user opens URL
#		And the user launches the RAC home page
#		When the user clicks on Sign In button
#		And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
#		#And the user fills the sign in form
#		And the user clicks on Sign In button in the My Account dialog
#		Then the user verifies that Sign In is successful
#		When the user navigates to Account Details page

		Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
		Given the user opens URL
		And the user launches the RAC home page
		When the user clicks on Sign In button
		And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
		#And the user fills the sign in form
		And the user clicks on Sign In button in the My Account dialog
		#Then the user verifies that Sign In is successful
	 	#Then the user verifies that Sign In is successful
		#Then the user verifies pricing for store locator displayed

		When the user clicks on the Payment Methods section
		And the user clicks on PayPal payment method
     	#And the user saves Paypal credentials from "<SheetName>" at row <RowNumber>
		Then the user saves Paypal credentials
		When the user selects the "I agree" checkbox in My Account PayPal payment
		And the user clicks the "Make Payment" button
		Then the PayPal payment method is saved under the Payment Methods section
		When the user clicks on the Delete button in the Saved Payment Methods section
		And the user clicks on the Delete Payment Method button in the pop-up
		# Bug
		And the user refreshes the page
		Then the payment method should be deleted

		Examples:
			| SheetName           		    | RowNumber |
			| PaymentsMyAccounts-paypal 	| 0         |


	#@SFCC3133 @SFCC4622 @TC_SFCC3967 @smoke @regression @PaymentGateWay
	#@SFCC4622 @TC_SFCC3967 @regression @PaymentGateWay
	@regression @PaymentGateway
	Scenario Outline: Verify My Account add and delete Payment - Bank Account

#		Given the user opens URL
#		And the user launches the RAC home page
#		When the user clicks on Sign In button
#		And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
#	 	# And the user fills the sign in form
#		And the user clicks on Sign In button in the My Account dialog
#		Then the user verifies that Sign In is successful
#		When the user navigates to Account Details page

		Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
		Given the user opens URL
		And the user launches the RAC home page
		When the user clicks on Sign In button
		And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
		#And the user fills the sign in form
		And the user clicks on Sign In button in the My Account dialog
		#Then the user verifies that Sign In is successful
	 	#Then the user verifies that Sign In is successful
		Then the user verifies pricing for store locator displayed

		When the user clicks on the Payment Methods section
		And the user clicks on Bank Account payment method

		When the user saves bank account details under Add Payment method from "<SheetName>" at row <RowNumber>
		Then the bank account method is saved under the Payment Methods section
		#Delete payment
		When the user clicks on the Delete button in the Saved Payment Methods section
		And the user clicks on the Delete Payment Method button in the pop-up
		# Bug - without refresh
		#And the user refreshes the page
		Then the payment method should be deleted

		Examples:
			| SheetName           		    	| RowNumber |
			| PaymentsMyAccounts-BankAccount 	| 0         |
