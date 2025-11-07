Feature: Payments Gateway Cash App pay

#  Background:
#    Given the user opens URL
#    And the user launches the RAC home page

 # @TC_SFCC4349_4360_4364_4365_4366 @regression @PaymentGateway
  @TC_SFCC4349_4360 @regression @PaymentGateway
  Scenario Outline: Desktop - Guest user - Verify user can save cash app as payment method in Checkout flow

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

    When the user clicks on the CONTINUE button on the Congrats popup if displayed
    Then the user clicks RESERVE MY PRODUCT button if displayed on almost done dialog

#    #And the user selects pay schedule "Weekly"
#    And the user selects pay schedule from "<SheetName>" at row <RowNumber>
#    #And the user selects paid next date "05/12/2025"
#    And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
#    #And the user clicks on the CONTINUE button on the Pay Schedule section
#    And the user clicks on the Continue button on the Pay Schedule section guest user
#    #When the user selects choose optional benefits radio buttons off
#    And the user clicks on Continue To Payment button
#    #CC
#    #And the user clicks on Credit Card payment method


    And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
   # When the user selects Save this payment method check box
    Then the user selects I agree checkbox

#    And the user clicks Submit And Continue To E Sign Button "<PaymentType>"
#    When the user selects docusign check box I sign
#    Then the user verifies continue button is displayed under docusign section

    Examples:
      | SheetName               | RowNumber |
      |  Payments-AllTypes      | 3         |

  @TC_SFCC4364_4365_4366_android @regression_android @PaymentGateway
  Scenario Outline: Mobile android - Guest user - checkout flow - verify cash app pay payment success

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

    When the user clicks on the CONTINUE button on the Congrats popup if displayed
    Then the user clicks RESERVE MY PRODUCT button if displayed on almost done dialog


#    #And the user selects pay schedule "Weekly"
#    And the user selects pay schedule from "<SheetName>" at row <RowNumber>
#    #And the user selects paid next date "05/12/2025"
#    And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
#    #And the user clicks on the CONTINUE button on the Pay Schedule section
#    And the user clicks on the Continue button on the Pay Schedule section guest user
#    #When the user selects choose optional benefits radio buttons off
#    And the user clicks on Continue To Payment button


    #CC
    #And the user clicks on Credit Card payment method
    And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
   # When the user selects Save this payment method check box
    Then the user selects I agree checkbox
    And the user clicks Submit And Continue To E Sign Button "<PaymentType>"
    When the user selects docusign check box I sign
    Then the user verifies continue button is displayed under docusign section

    Examples:
      | SheetName               | RowNumber |
      |  Payments-AllTypes      | 3         |

  #@SFCC_6440
  @TC_SFCC4361 @regression @PaymentGateway
  Scenario Outline: Guest user - Verify whether the Cash App method is displaying as a Payment method in Reservation flow

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

    #When the user clicks on the CONTINUE button on the Congrats popup if displayed

    When the user clicks RESERVE MY PRODUCT button if displayed on almost done dialog

    #Reservation - screen verification pending
    Then Reservation screen shall be displayed

#    #And the user selects pay schedule "Weekly"
#    And the user selects pay schedule from "<SheetName>" at row <RowNumber>
#    #And the user selects paid next date "05/12/2025"
#    And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
#    #And the user clicks on the CONTINUE button on the Pay Schedule section
#    And the user clicks on the Continue button on the Pay Schedule section guest user
#    #When the user selects choose optional benefits radio buttons off
#    And the user clicks on Continue To Payment button

    Then the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>


    Examples:
      | SheetName               | RowNumber |
      |  Payments-CashApp       | 0         |

  @TC_SFCC6255 @regression_wip @PaymentGateway
  Scenario Outline: Guest user - Verify whether the Cash App method is displaying as a Payment method in Reservation flow

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

    #When the user clicks on the CONTINUE button on the Congrats popup if displayed

    When the user clicks RESERVE MY PRODUCT button if displayed on almost done dialog

    #Reservation - screen verification pending
    Then Reservation screen shall be displayed

#    #And the user selects pay schedule "Weekly"
#    And the user selects pay schedule from "<SheetName>" at row <RowNumber>
#    #And the user selects paid next date "05/12/2025"
#    And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
#    #And the user clicks on the CONTINUE button on the Pay Schedule section
#    And the user clicks on the Continue button on the Pay Schedule section guest user
#    #When the user selects choose optional benefits radio buttons off
#    And the user clicks on Continue To Payment button

    Then the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
    And Save card and enroll me in AutoPay check box shall not be displayed
    When the user selects Save this payment method check box cash app
    And the user selects I agree checkbox cash app

    Examples:
      | SheetName               | RowNumber |
      |  Payments-CashApp       | 0         |

  @TC_SFCC4362 @regression @PaymentGateway
  Scenario Outline: Guest User - EA Product - Verify whether the Cash App method is displaying as a Payment method in claim offer flow

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
    #When the user clicks on the CONTINUE button on the Congrats popup if displayed

    When the user clicks RESERVE MY PRODUCT button if displayed on almost done dialog

    Then Claim Offer screen shall be displayed

#    #And the user selects pay schedule "Weekly"
#    And the user selects pay schedule from "<SheetName>" at row <RowNumber>
#    #And the user selects paid next date "05/12/2025"
#    And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
#    #And the user clicks on the CONTINUE button on the Pay Schedule section
#    And the user clicks on the Continue button on the Pay Schedule section guest user
#    #When the user selects choose optional benefits radio buttons off
#    And the user clicks on Continue To Payment button

    Then the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
    # When the user selects Save this payment method check box
    #And the user selects I agree checkbox

    Examples:
      | SheetName               | RowNumber |
       |  Payments-CashApp       | 1        |

  @TC_SFCC6258_1 @regression @PaymentGateway
  Scenario Outline: Guest User - Desktop - EA Product - Verify whether the Cash App Payment method in claim offer flow

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
    #When the user clicks on the CONTINUE button on the Congrats popup if displayed

    When the user clicks RESERVE MY PRODUCT button if displayed on almost done dialog
    Then Claim Offer screen shall be displayed
    Then the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
    # When the user selects Save this payment method check box
    #And the user selects I agree checkbox

    Examples:
      | SheetName               | RowNumber |
      |  Payments-CashApp       | 1        |

  @TC_SFCC6258_2 @regression_android @PaymentGateway
  Scenario Outline: Guest User - mobile android - EA Product - Verify whether the Cash App Payment method in claim offer flow

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
    #When the user clicks on the CONTINUE button on the Congrats popup if displayed

    When the user clicks RESERVE MY PRODUCT button if displayed on almost done dialog
    Then Claim Offer screen shall be displayed
    Then the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
    # When the user selects Save this payment method check box
    Then the user selects I agree checkbox
    And the user clicks Submit And Continue To E Sign Button "<PaymentType>"
    When the user selects docusign check box I sign
    Then the user verifies continue button is displayed under docusign section

    Examples:
      | SheetName               | RowNumber |
      |  Payments-CashApp       | 1        |

  @TC_SFCC6258_3 @regression_ios @PaymentGateway
  Scenario Outline: Guest User - mobile ios - EA Product - Verify whether the Cash App Payment method in claim offer flow

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
    #When the user clicks on the CONTINUE button on the Congrats popup if displayed

    When the user clicks RESERVE MY PRODUCT button if displayed on almost done dialog
    Then Claim Offer screen shall be displayed
    Then the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
    # When the user selects Save this payment method check box
    #And the user selects I agree checkbox

    Examples:
      | SheetName               | RowNumber |
      |  Payments-CashApp       | 1        |

  @TC_SFCC4362_2_6255 @regression_wip @PaymentGateway
  Scenario Outline: Guest user - RAC Product - Verify whether the Cash App method is displaying as a Payment method in claim offer flow

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
    #When the user clicks on the CONTINUE button on the Congrats popup if displayed

    When the user clicks RESERVE MY PRODUCT button if displayed on almost done dialog
    Then Claim Offer screen shall be displayed

#    #And the user selects pay schedule "Weekly"
#    And the user selects pay schedule from "<SheetName>" at row <RowNumber>
#    #And the user selects paid next date "05/12/2025"
#    And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
#    #And the user clicks on the CONTINUE button on the Pay Schedule section
#    And the user clicks on the Continue button on the Pay Schedule section guest user
#    #When the user selects choose optional benefits radio buttons off
#    And the user clicks on Continue To Payment button

    Then the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
    # When the user selects Save this payment method check box
    #And the user selects I agree checkbox

    Examples:
      | SheetName               | RowNumber |
      |  Payments-CashApp       | 2         |

  @TC_SFCC6257 @regression @PaymentGateway
  Scenario Outline: Registered user - RAC Product - Verify Cash app payment option is displayed on the payment page for reservation made by signed users

    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Sign In button
    And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
	#And the user fills the sign in form
    And the user clicks on Sign In button in the My Account dialog
    Then the user able to click on RAC logo
    And the user searches for product from "<SheetName>" at row <RowNumber>
    #Then Brand Name "<Brand Name>" is displayed as a hyperlink
    #Then Brand Name is displayed as a hyperlink from "<SheetName>" at row <RowNumber>
    When the user clicks on Start Order button
    And the user selects agree and continue button
    #Bug ? -  below not displayed
    #When the user clicks RESERVE MY PRODUCT button if displayed on almost done dialog
    Then Claim Offer screen shall be displayed

#    #And the user selects pay schedule "Weekly"
#    And the user selects pay schedule from "<SheetName>" at row <RowNumber>
#    #And the user selects paid next date "05/12/2025"
#    And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
#    #And the user clicks on the CONTINUE button on the Pay Schedule section
#    And the user clicks on the Continue button on the Pay Schedule section guest user
#    #When the user selects choose optional benefits radio buttons off
#    And the user clicks on Continue To Payment button

    Then the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
    And Save card and enroll me in AutoPay check box shall not be displayed
    When the user selects Save this payment method check box cash app
    And the user selects I agree checkbox cash app
    #When the user clicks the "Submit" button in the Cash App payment method section


    Examples:
      | SheetName               | RowNumber |
      |  Payments-CashApp       | 4         |


  @TC_SFCC4367_4368 @regression @PaymentGateway
  Scenario Outline: Checkout flow - Verify whether the Cash App CTA is displayed on desktop device

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

    When the user clicks on the CONTINUE button on the Congrats popup if displayed
    Then the user clicks RESERVE MY PRODUCT button if displayed on almost done dialog

#    #And the user selects pay schedule "Weekly"
#    And the user selects pay schedule from "<SheetName>" at row <RowNumber>
#    #And the user selects paid next date "05/12/2025"
#    And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
#    #And the user clicks on the CONTINUE button on the Pay Schedule section
#    And the user clicks on the Continue button on the Pay Schedule section guest user
#    #When the user selects choose optional benefits radio buttons off
#    And the user clicks on Continue To Payment button


    #CC
    #And the user clicks on Credit Card payment method
    And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
   # When the user selects Save this payment method check box
    Then the user selects I agree checkbox

#    And the user clicks Submit And Continue To E Sign Button "<PaymentType>"
#    When the user selects docusign check box I sign
#    Then the user verifies continue button is displayed under docusign section

    Examples:
      | SheetName               | RowNumber |
      |  Payments-AllTypes      | 3         |


  @TC_SFCC4363 @regression @PaymentGateway
  Scenario Outline: Registered user - Verify whether the Cash App method is displaying as a Payment method in My Account flow

    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Sign In button
    And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
    And the user clicks on Sign In button in the My Account dialog
    When the user clicks on the Payment Methods section

    Then verify cash app method is displayed in my accounts

    Examples:
      | SheetName           		| RowNumber |
      | PaymentsMyAccounts-CashApp 	| 0         |