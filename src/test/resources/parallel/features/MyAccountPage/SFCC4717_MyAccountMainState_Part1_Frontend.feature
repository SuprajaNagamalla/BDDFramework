Feature: MyAccount_Validate MyAccount Page Details
  Background:
#    Given the user opens URL
#    And the user launches the RAC home page
#    When the user clicks on Sign In button

  @TC_SFCC4118_To_4128_and_4130_To_4133_and_4150_To_4151 @regression @MYACCOUNT
  Scenario Outline: Verify My Account dashboard page field validations and redirection
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Sign In button
    And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
    And the user clicks on Sign In button in the My Account dialog
    And the user clicks on Account Details in My Account Section
    
    #4118
    And verify the "Welcome back," text is displayed on header
    #And verify the "Phani" text is displayed on header
    And Verify total amount due text along with amount is displayed
    Then validate current Due Date is less than todays Date
    Then Verify Make Payment is displayed
    Then the user clicks on Make Payment button
    Then the user is redirected to the "Agreement Payment" category page
    
    #4048
    Then the user clicks on BackToDashboard link
    Then Verify List Of Agreements section is displayed
    And The user see the latest agreement tile at the topList Of Agreements section is displayed
    Then The user verify product image is displayed in the agreement section
    Then The user verify product Name is displayed in the agreement section
    Then The user verifies that the payments made by the customer are displayed in the agreement section
    Then Verify that either the AutoPay Unavailable option or the Enroll in AutoPay option is displayed in the agreement section
    And the user verify the Product delivery status is displayed
    When the user clicks on agreement details button
    Then the user is redirected to the agreement details screen
    Then verify quick links component header is displayed
    Then the following quick link components should be displayed:
      | My Account Dashboard |
      | Cart                 |
      | Saved Items          |
      | Payment Methods      |
      | Payment History      |
      | Personal Info        |
    #4130  
    Then verify My Stores component header is displayed
    Then User verify Store Address is displayed in my store section
    Then User verify Store Contact details and Today's hours are displayed in my store section
    And Verify Benefits plus product tile section is displayed
    Examples:
      | SheetName  	   					   | RowNumber |
      | MyAccount-AgreementWithBP	 | 3         |