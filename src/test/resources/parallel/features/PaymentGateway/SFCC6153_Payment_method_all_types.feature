Feature: PaymentGateWay_Verify all payment types

#  Background:
#    Given the user opens URL
#    And the user launches the RAC home page


   #@SFCC374 @SFCC6153 @TC_SFCC4199_All @smoke @regression @PaymentsAll @PaymentGateWay
  #@SFCC6153 @TC_SFCC4199 @smoke @PaymentGateWay
  @smoke @PaymentGateway @TC_SFCC4014
  Scenario Outline: RAC product - Registered user checkout flow make payment <PaymentType>

   #Then skip execution if the iteration status is "No" in workbook "Payments" on sheet "<SheetName>" at row <RowNumber>
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Sign In button
    And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
	#And the user fills the sign in form
    And the user clicks on Sign In button in the My Account dialog
    Then the user able to click on RAC logo
    When the user searches for product from "<SheetName>" at row <RowNumber>
     #Then Brand Name is displayed as a hyperlink from "<SheetName>" at row <RowNumber>
    When the user clicks on Start Order button
    #And the user clicks on the "I Agree & Continue" button
    And the user clicks on the I Agree and Continue button

#    And the user enters ssn and dob
#    And the user selects agree and continue button
    When the user clicks on the CONTINUE button on the Congrats popup Reg user

    And the user selects pay schedule from "<SheetName>" at row <RowNumber>
    And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
    And the user clicks on the CONTINUE button on the Pay Schedule section

     # Get correlation ID
    #Then the user gets correlation id
    Then the user gets correlation id "<PaymentType>"

    #When the user selects choose optional benefits radio buttons off
    And the user clicks on Continue To Payment button
    #CC
    And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>

    #Billing Address Same as Delivery - click check box
    #When the user selects Save this payment method check box

    Then the user selects I agree checkbox Reg user
    And the user clicks Submit And Continue To E Sign Button
    # Get correlation ID
    Then the user gets correlation id "<PaymentType>"
#    When the user selects docusign check box I sign
#    Then the user verifies continue button is displayed under docusign section
    #Skip sign flow
    Then the user clicks on Sign Agreement Later on docu sign page
    Then the order confirmation success message should be displayed
    And agreement number is generated

    Examples:
      | SheetName               | RowNumber |PaymentType|
      | Payments-CreditCard     | 1         |CC-VISA |


  @smoke_wip @PaymentGateway @TC_SFCC4014_2
  Scenario Outline: EA Product - Registered user checkout flow make payment <PaymentType>

   #Then skip execution if the iteration status is "No" in workbook "Payments" on sheet "<SheetName>" at row <RowNumber>
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Sign In button
    And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
	#And the user fills the sign in form
    And the user clicks on Sign In button in the My Account dialog
    Then the user able to click on RAC logo
    When the user searches for product from "<SheetName>" at row <RowNumber>
     #Then Brand Name is displayed as a hyperlink from "<SheetName>" at row <RowNumber>
    When the user clicks on Start Order button
    #And the user clicks on the "I Agree & Continue" button
    And the user clicks on the I Agree and Continue button

#    And the user enters ssn and dob
#    And the user selects agree and continue button
    When the user clicks on the CONTINUE button on the Congrats popup Reg user

    And the user selects pay schedule from "<SheetName>" at row <RowNumber>
    And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
    And the user clicks on the CONTINUE button on the Pay Schedule section

     # Get correlation ID
    #Then the user gets correlation id
    Then the user gets correlation id "<PaymentType>"

    #When the user selects choose optional benefits radio buttons off
    And the user clicks on Continue To Payment button
    #CC
    And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>

    #Billing Address Same as Delivery - click check box
    #When the user selects Save this payment method check box

    Then the user selects I agree checkbox Reg user
    And the user clicks Submit And Continue To E Sign Button
    # Get correlation ID
    Then the user gets correlation id "<PaymentType>"
#    When the user selects docusign check box I sign
#    Then the user verifies continue button is displayed under docusign section
    #Skip sign flow
    Then the user clicks on Sign Agreement Later on docu sign page
    Then the order confirmation success message should be displayed
    And agreement number is generated

    Examples:
      | SheetName               | RowNumber |PaymentType|
      | Payments-CreditCard     | 2         |CC-VISA    |


  #@SFCC374 @SFCC6153 @TC_SFCC4199_All @smoke @regression @PaymentsAll @PaymentGateWay
 # @TC_SFCC4196_To_4198 @regression @PaymentGateway
  #covered already in tag - CHECKOUT_AllPayments scripts
  Scenario Outline: Guest user verify all payment types - <PaymentType>

   #Then skip execution if the iteration status is "No" in workbook "Payments" on sheet "<SheetName>" at row <RowNumber>
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    And the user searches for product from "<SheetName>" at row <RowNumber>
    #Then Brand Name "<Brand Name>" is displayed as a hyperlink
    #Then Brand Name is displayed as a hyperlink from "<SheetName>" at row <RowNumber>
    When the user clicks on Start Order button
    #Then user redirected to payfone or prefill page
    And the user clicks on "I don't have a mobile" link on pdp

    #Check out form
    When the user fills check out form from "<SheetName>" at row <RowNumber>
    And the user clicks on Continue button Paypal

    #new check out form - IDV page
    And the user enters ssn and dob
    And the user selects agree and continue button

    # Get correlation ID
    #Then the user gets correlation id
    Then the user gets correlation id "<PaymentType>"

    When the user clicks on the CONTINUE button on the Congrats popup
    #And the user selects pay schedule "Weekly"
    And the user selects pay schedule from "<SheetName>" at row <RowNumber>
    #And the user selects paid next date "05/12/2025"
    And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
    #And the user clicks on the CONTINUE button on the Pay Schedule section
    And the user clicks on the Continue button on the Pay Schedule section guest user

    # Get correlation IDs - normal id, payment id
    Then the user gets correlation id "<PaymentType>"

    #When the user selects choose optional benefits radio buttons off
    And the user clicks on Continue To Payment button
    #CC
    #And the user clicks on Credit Card payment method
    And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
   # When the user selects Save this payment method check box
    Then the user selects I agree checkbox

    And the user clicks Submit And Continue To E Sign Button "<PaymentType>"
    # Get correlation ID
    Then the user gets correlation id "<PaymentType>"
    When the user selects docusign check box I sign
    Then the user verifies continue button is displayed under docusign section

    Examples:
      | SheetName               | RowNumber |PaymentType|
      | Payments-AllTypes       | 0         |CreditCard |
      | Payments-AllTypes       | 1         |paypal     |
      | Payments-AllTypes       | 2         |venmo      |


  #@SFCC6153 @regression @PaymentGateway
  @TC_SFCC4199 @regression @PaymentGateway
  Scenario Outline: Guest user verify payment option displayed type - <PaymentType>

   #Then skip execution if the iteration status is "No" in workbook "Payments" on sheet "<SheetName>" at row <RowNumber>
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    And the user searches for product from "<SheetName>" at row <RowNumber>
    #Then Brand Name "<Brand Name>" is displayed as a hyperlink
    #Then Brand Name is displayed as a hyperlink from "<SheetName>" at row <RowNumber>
    When the user clicks on Start Order button
    #Then user redirected to payfone or prefill page
    And the user clicks on "I don't have a mobile" link on pdp
    #Check out form
    When the user fills check out form from "<SheetName>" at row <RowNumber>
    And the user clicks on Continue button Paypal

    #new check out form
    And the user enters ssn and dob
    And the user selects agree and continue button
    Then the user gets correlation id "<PaymentType>"
    When the user clicks on the CONTINUE button on the Congrats popup if displayed
    Then the user clicks RESERVE MY PRODUCT button if displayed on almost done dialog

#    #And the user selects pay schedule "Weekly"
#    And the user selects pay schedule from "<SheetName>" at row <RowNumber>
#    #And the user selects paid next date "05/12/2025"
#    And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
#    #And the user clicks on the CONTINUE button on the Pay Schedule section
#    And the user clicks on the Continue button on the Pay Schedule section guest user
#    Then the user gets correlation id "<PaymentType>"
#    #When the user selects choose optional benefits radio buttons off
#    And the user clicks on Continue To Payment button


    And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>

    #Apple
    #And the user clicks on Apple pay payment method
    #Google pay
    #And the user clicks on Google pay payment method
    #And the user saves GPay credentials from "<SheetName>" at row <RowNumber>

    Examples:
      | SheetName               | RowNumber |PaymentType  |
      | Payments-AllTypes       | 4         |ApplePay     |


  @TC_SFCC4200 @regression @PaymentGateway
  Scenario Outline: Guest user - EA product-Claim offer - verify payment option displayed type - <PaymentType>

   #Then skip execution if the iteration status is "No" in workbook "Payments" on sheet "<SheetName>" at row <RowNumber>
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    And the user searches for product from "<SheetName>" at row <RowNumber>
    #Then Brand Name "<Brand Name>" is displayed as a hyperlink
    #Then Brand Name is displayed as a hyperlink from "<SheetName>" at row <RowNumber>
    When the user clicks on Start Order button
    #Then user redirected to payfone or prefill page
    And the user clicks on "I don't have a mobile" link on pdp
    #Check out form
    When the user fills check out form from "<SheetName>" at row <RowNumber>
    And the user clicks on Continue button Paypal

    #new check out form
    And the user enters ssn and dob
    And the user selects agree and continue button

    When the user clicks on the CONTINUE button on the Congrats popup if displayed
    Then the user clicks RESERVE MY PRODUCT button if displayed on almost done dialog

#    #And the user selects pay schedule "Weekly"
#    And the user selects pay schedule from "<SheetName>" at row <RowNumber>
#    #And the user selects paid next date "05/12/2025"
#    And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
#    #And the user clicks on the CONTINUE button on the Pay Schedule section
#    And the user clicks on the Continue button on the Pay Schedule section guest user
#    Then the user gets correlation id "<PaymentType>"
#    And the user clicks on Continue To Payment button


    And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>

    #Apple
    #And the user clicks on Apple pay payment method
    #Google pay
    #And the user clicks on Google pay payment method
    #And the user saves GPay credentials from "<SheetName>" at row <RowNumber>

    Examples:
      | SheetName               | RowNumber |PaymentType  |
      | Payments-AllTypes       | 5         |GooglePay    |