Feature: PaymentGateWay_PaymentGateWay - Payment method - Frontend

#	Background:
#		Given the user opens URL
#		And the user launches the RAC home page

#@SFCC3133 @SFCC4634 @TC_SFCC3975_1 @smoke @PaymentGateWay @regression
#@SFCC4634 @TC_SFCC3975_1 @smoke @PaymentGateWay @regression
@TC_SFCC3975 @PaymentGateway @regression
Scenario Outline: Verify Payment bank account enroll auto pay

	Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
	Given the user opens URL
	And the user launches the RAC home page
	When the user clicks on Sign In button
	And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
	#And the user fills the sign in form
	And the user clicks on Sign In button in the My Account dialog
	#Then the user verifies that Sign In is successful
	#Then the user verifies pricing for store locator displayed
	#Then the user able to click on RAC logo
	#When the user navigates to Account Details page

	#Pre-Condition
	#And the user clicks the "AutoPay Enrolled" button in My Account Section
	And the user clicks the "AutoPay Enrolled" button and un enroll in My Account Section if displayed
#	And the user clicks the "Unenroll AutoPay" button
#	And the user clicks on the UnEnroll AutoPay popUp

	When the user clicks on the Payment Methods section

	#Pre-Condition
#	When the user clicks on the Delete button in the Saved Payment Methods section
#	And the user clicks on the Delete Payment Method button in the pop-up
#	#And the user refreshes the page
#	Then the payment method should be deleted


	And the user clicks on Bank Account payment method
	When the user saves bank account details under Add Payment method from "<SheetName>" at row <RowNumber>
	Then the bank account method is saved under the Payment Methods section
	When the user clicks on Back to Dashboard button
	When the user clicks on Enroll in AutoPay button in account details
	And the user clicks the "Continue" button
	When the user clicks I agree to the check box enroll auto pay
	And the user clicks the "ENROLL IN AUTOPAY" button under saved payment methods
	When the user clicks on Back to Dashboard button
	Then AutoPay enrollment is successful
	When the user clicks on the Payment Methods section
	And the user clicks the "Enrolled in AutoPay - Details" button
	Then the system displays a message with instructions to delete an auto-enrolled payment method
	#UnEnrolled

	Examples:
		| SheetName           		    	| RowNumber |
		| PaymentsMyAccounts-BankAccount 	| 0         |



