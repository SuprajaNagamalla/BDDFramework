#Priorities- RAC checkout with promo, EA checkout
# Dry run pending-- CA store Checkout,Georgia store checkout
# scripting Pending-- NC store checkout, Indiana store checkout, BP Checkout
 Feature: Checkout Flows - Generate Agreements


# Covered in this scenario--  Rac checkout- with CC
  @CHECKOUT @Checkout_CC @regression @TC_SFCC_4033 @TC_SFCC_7879
   Scenario Outline: Generate Agreements for payment type <PaymentType> for product type <ProductType>

     Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
     Given the user opens URL
     And the user launches the RAC home page
     When the user clicks on Find your store button
     And the user enters Zipcode from "<SheetName>" at row <RowNumber>
     And the user clicks on Go button
     And the user clicks on Make This My Store link
     And the user searches for product from "<SheetName>" at row <RowNumber>
     When the user clicks on Start Order button
     And the user clicks on "I don't have a mobile" link on pdp
    #Check out form
     When the user fills check out form from "<SheetName>" at row <RowNumber>
     And the user clicks on Continue button Paypal
    #new check out form
     And the user enters ssn and dob
     And the user selects agree and continue button

       #Then the user gets correlation id
#     Then the user gets correlation id "<PaymentType>"

     When the user clicks on the CONTINUE button on the Congrats popup
     And the user selects pay schedule from "<SheetName>" at row <RowNumber>
     And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
     And  the user clicks on the Continue button on the Pay Schedule section guest user

#    Then verify pricing and renewal rate calculations on renewal terms page from "<SheetName>" at row <RowNumber>

    #Then the user gets correlation id
#     Then the user gets correlation id "<PaymentType>"

#     Then the user verify see details link functionality on renewal terms page
#     Then the user verify Tooltip for payment fields on renewal terms Page

     And the user clicks on Continue To Payment button


     And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
#     When the user selects Save this payment method check box
     Then the user verify Billing Address Same As Delivery And Auto Selected from sheet "<SheetName>" at row <RowNumber>
     Then the user verify enroll in Autopay checkbox pre selected and Verbiage displayed

     And the user selects I agree checkbox
     And the user clicks Submit And Continue To E Sign Button "<PaymentType>"
          #Skip sign flow
#     Then the user clicks on Sign Agreement Later on docu sign page
#     Then the order confirmation success message should be displayed
#     And agreement number is generated
    #DocuSign flow
  When the user selects docusign check box I sign
  When the user clicks continue button under docusign section
  Then the user Esign document and click on finish
  Then the order confirmation success message should be displayed
   And agreement number is generated

     Examples:
       | SheetName               | RowNumber | PaymentType | ProductType |
       | CheckOut-CheckOuts      | 0         | CreditCard  | RAC         |

 @TC_SFCC4013 @CHECKOUT @CHECKOUT_AllPayments @smoke
  Scenario Outline: Generate Agreements for payment type <PaymentType> for product type <ProductType>

    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    And the user searches for product from "<SheetName>" at row <RowNumber>
    When the user clicks on Start Order button
    And the user clicks on "I don't have a mobile" link on pdp
    #Check out form
    When the user fills check out form from "<SheetName>" at row <RowNumber>
    And the user clicks on Continue button Paypal
    #new check out form
    And the user enters ssn and dob
    And the user selects agree and continue button

   #Then the user gets correlation id
   Then the user gets correlation id "<PaymentType>"

    When the user clicks on the CONTINUE button on the Congrats popup
    And the user selects pay schedule from "<SheetName>" at row <RowNumber>
    And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
    And  the user clicks on the Continue button on the Pay Schedule section guest user

   #Then the user gets correlation id
   Then the user gets correlation id "<PaymentType>"

    And the user clicks on Continue To Payment button
    And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
    #When the user selects Save this payment method check box
    And the user selects I agree checkbox
    And the user clicks Submit And Continue To E Sign Button "<PaymentType>"
    #Skip sign flow
    Then the user clicks on Sign Agreement Later on docu sign page
    Then the order confirmation success message should be displayed
    And agreement number is generated
#    #DocuSign flow
#  When the user selects docusign check box I sign
#  When the user clicks continue button under docusign section
#  Then the user Esign document and click on finish
#  Then the order confirmation success message should be displayed
#   And agreement number is generated


    Examples:
      | SheetName          | RowNumber | PaymentType | ProductType |
      | CheckOut-CheckOuts | 0         | CreditCard  | RAC         |
#      | CheckOut-CheckOuts | 1         | paypal      | RAC         |
#      | CheckOut-CheckOuts | 2         | venmo       | RAC         |

  @CHECKOUT @Checkout_EA  @regression
   Scenario Outline: Generate Agreements for payment type <PaymentType> for product type <ProductType>

     Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
     Given the user opens URL
     And the user launches the RAC home page
     When the user clicks on Find your store button
     And the user enters Zipcode from "<SheetName>" at row <RowNumber>
     And the user clicks on Go button
     And the user clicks on Make This My Store link
     And the user searches for product from "<SheetName>" at row <RowNumber>
     When the user clicks on Start Order button
     And the user clicks on "I don't have a mobile" link on pdp
    #Check out form
     When the user fills check out form from "<SheetName>" at row <RowNumber>
     And the user clicks on Continue button Paypal
    #new check out form
     And the user enters ssn and dob
     And the user selects agree and continue button

       #Then the user gets correlation id
#     Then the user gets correlation id "<PaymentType>"

     When the user clicks on the CONTINUE button on the Congrats popup
     And the user selects pay schedule from "<SheetName>" at row <RowNumber>
     And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
     And  the user clicks on the Continue button on the Pay Schedule section guest user

       #Then the user gets correlation id
#     Then the user gets correlation id "<PaymentType>"

     And the user clicks on Continue To Payment button
     And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
#     When the user selects Save this payment method check box
     And the user selects I agree checkbox
     And the user clicks Submit And Continue To E Sign Button "<PaymentType>"
          #Skip sign flow
     Then the user clicks on Sign Agreement Later on docu sign page
     Then the order confirmation success message should be displayed
     And agreement number is generated
#    #DocuSign flow
#  When the user selects docusign check box I sign
#  When the user clicks continue button under docusign section
#  Then the user Esign document and click on finish
#  Then the order confirmation success message should be displayed
#   And agreement number is generated


     Examples:
       | SheetName               | RowNumber | PaymentType | ProductType |
       | CheckOut-CheckOuts | 4         | CreditCard  | EA          |


   @Checkout_WithPromo @regression @CHECKOUT
   Scenario Outline: Generate Agreements with promo  for payment type <PaymentType> for product type <ProductType>
     Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
     Given the user opens URL
     And the user launches the RAC home page
     When the user clicks on Find your store button
     And the user enters Zipcode from "<SheetName>" at row <RowNumber>
     And the user clicks on Go button
     And the user clicks on Make This My Store link
     And the user searches for product from "<SheetName>" at row <RowNumber>
     When the user clicks on Start Order button
     And the user clicks on "I don't have a mobile" link on pdp
    #Check out form
     When the user fills check out form from "<SheetName>" at row <RowNumber>
     And the user clicks on Continue button Paypal
    #new check out form
     And the user enters ssn and dob
     And the user selects agree and continue button
     When the user clicks on the CONTINUE button on the Congrats popup
     And the user selects pay schedule from "<SheetName>" at row <RowNumber>
     And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
     And  the user clicks on the Continue button on the Pay Schedule section guest user

     #invalid Promo validation
     Then the user apply text enabled invalid promo code and verify error message from "<SheetName>" at row <RowNumber>

    #Promo validation
     Then the user apply promo and verify Total Due from "<SheetName>" at row <RowNumber>

     And the user clicks on Continue To Payment button
     And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
#     When the user selects Save this payment method check box
     And the user selects I agree checkbox
     And the user clicks Submit And Continue To E Sign Button "<PaymentType>"
        #Skip sign flow
     Then the user clicks on Sign Agreement Later on docu sign page
    Then the order confirmation success message should be displayed
    And agreement number is generated

     Examples:
       | SheetName               | RowNumber | PaymentType | ProductType |
       | CheckOut-CheckOuts       | 3        |CreditCard |RAC   |


   @Checkout_CAStore @regression @CHECKOUT
   Scenario Outline: Generate Agreements for CA store payment type <PaymentType> for product type <ProductType>

     Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
     Given the user opens URL
     And the user launches the RAC home page
     When the user clicks on Find your store button
     And the user enters Zipcode from "<SheetName>" at row <RowNumber>
     And the user clicks on Go button
     And the user clicks on Make This My Store link
     And the user searches for product from "<SheetName>" at row <RowNumber>
     When the user clicks on Start Order button
     And the user clicks on "I don't have a mobile" link on pdp
    #Check out form
     When the user fills check out form from "<SheetName>" at row <RowNumber>
     And the user clicks on Continue button Paypal
    #new check out form
     And the user enters ssn and dob
     And the user selects agree and continue button
     When the user clicks on the CONTINUE button on the Congrats popup
     And the user selects pay schedule from "<SheetName>" at row <RowNumber>
     And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
     And  the user clicks on the Continue button on the Pay Schedule section guest user

        #verify LDW and BP not displayed for CA store
     Then the user verify ldw and bp not displayed on renewal terms page

     And the user clicks on Continue To Payment button
     And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
#     When the user selects Save this payment method check box
     And the user selects I agree checkbox
     And the user clicks Submit And Continue To E Sign Button "<PaymentType>"
        #Skip sign flow
     Then the user clicks on Sign Agreement Later on docu sign page
     Then the order confirmation success message should be displayed
     And agreement number is generated

     Examples:
       | SheetName               | RowNumber | PaymentType | ProductType |
       | CheckOut-CheckOuts       | 8        |CreditCard |RAC   |



  @Checkout_GeorgiaStore_BenefitsModal_Autodisplay @regression @CHECKOUT
  Scenario Outline: Generate Agreements for Georgia store payment types <PaymentType> for product type <ProductType>

    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    And the user searches for product from "<SheetName>" at row <RowNumber>
    When the user clicks on Start Order button
    And the user clicks on "I don't have a mobile" link on pdp
    #Check out form
    When the user fills check out form from "<SheetName>" at row <RowNumber>
    And the user clicks on Continue button Paypal
    #new check out form
    And the user enters ssn and dob
    And the user selects agree and continue button
    When the user clicks on the CONTINUE button on the Congrats popup
    #And the user selects pay schedule "Weekly"
    And the user selects pay schedule from "<SheetName>" at row <RowNumber>
    #And the user selects paid next date "09/25/2025"
    And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
    And  the user clicks on the Continue button on the Pay Schedule section guest user

    #Add Georgia store specific validations
    Then the user verify benefits modal auto displayed when scrolled to Continue CTA

#    And the user clicks on Continue To Payment button
#    And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
#    And the user selects I agree checkbox
#    And the user clicks Submit And Continue To E Sign Button "<PaymentType>"
#        #Skip sign flow
#    Then the user clicks on Sign Agreement Later on docu sign page
#    Then the order confirmation success message should be displayed
#    And agreement number is generated


    Examples:
      | SheetName               | RowNumber | PaymentType | ProductType |
      | CheckOut-CheckOuts       | 9         |CreditCard |RAC   |

   @Checkout_GeorgiaStore_BenefitsComponent @regression @CHECKOUT
   Scenario Outline: Generate Agreements for Georgia store payment type <PaymentType> for product type <ProductType>

     Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
     Given the user opens URL
     And the user launches the RAC home page
     When the user clicks on Find your store button
     And the user enters Zipcode from "<SheetName>" at row <RowNumber>
     And the user clicks on Go button
     And the user clicks on Make This My Store link
     And the user searches for product from "<SheetName>" at row <RowNumber>
     When the user clicks on Start Order button
     And the user clicks on "I don't have a mobile" link on pdp
    #Check out form
     When the user fills check out form from "<SheetName>" at row <RowNumber>
     And the user clicks on Continue button Paypal
    #new check out form
     And the user enters ssn and dob
     And the user selects agree and continue button
     When the user clicks on the CONTINUE button on the Congrats popup
    #And the user selects pay schedule "Weekly"
     And the user selects pay schedule from "<SheetName>" at row <RowNumber>
    #And the user selects paid next date "09/25/2025"
     And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
     And  the user clicks on the Continue button on the Pay Schedule section guest user

    #Add Georgia store specific validations
     Then the user verify store specific features for the georgia store wrt ldw and bp

     And the user clicks on Continue To Payment button
     And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
#     When the user selects Save this payment method check box
     And the user selects I agree checkbox
     And the user clicks Submit And Continue To E Sign Button "<PaymentType>"
        #Skip sign flow
     Then the user clicks on Sign Agreement Later on docu sign page
     Then the order confirmation success message should be displayed
     And agreement number is generated


     Examples:
       | SheetName               | RowNumber | PaymentType | ProductType |
       | CheckOut-CheckOuts       | 9         |CreditCard |RAC   |


   @CHECKOUT  @BPCheckoutFlow_FromConfirmationPage @regression
   Scenario Outline: Generate Agreements for BP checkout from confirmation page payment type <PaymentType> for product type <ProductType>

     Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
     Given the user opens URL
     And the user launches the RAC home page
     When the user clicks on Find your store button
     And the user enters Zipcode from "<SheetName>" at row <RowNumber>
     And the user clicks on Go button
     And the user clicks on Make This My Store link
     And the user searches for product from "<SheetName>" at row <RowNumber>
     When the user clicks on Start Order button
     And the user clicks on "I don't have a mobile" link on pdp
    #Check out form
     When the user fills check out form from "<SheetName>" at row <RowNumber>
     And the user clicks on Continue button Paypal
#     Then verify spinner is displayed while page is loading
    #new check out form
     And the user enters ssn and dob
     And the user selects agree and continue button
     When the user clicks on the CONTINUE button on the Congrats popup
     And the user selects pay schedule from "<SheetName>" at row <RowNumber>
     And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
     And  the user clicks on the Continue button on the Pay Schedule section guest user

#     turn off BP on renewal terms page
     And the user toggle benefit plus checkbox

     And the user clicks on Continue To Payment button
     And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
#     When the user selects Save this payment method check box
     And the user selects I agree checkbox
     And the user clicks Submit And Continue To E Sign Button "<PaymentType>"
          #Skip sign flow
     Then the user clicks on Sign Agreement Later on docu sign page
     Then the order confirmation success message should be displayed
     And agreement number is generated

   #     Bp Checkout flow1
     When the user clicks on Enroll Now on BP modal
     And the user clicks on the I Agree and Continue button On BP Start order page
     When the user clicks on the CONTINUE button on the Congrats popup
     And the user selects pay schedule from "<SheetName>" at row <RowNumber>
     And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
     And  the user clicks on the CONTINUE button on the Pay Schedule section
     Then the user verify no promos displayed on renewal terms page
     And the user clicks on Continue To Payment button in BP flow
     And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
#     When the user selects Save this payment method check box
     And the user selects I agree checkbox
     And the user clicks Submit And Continue To E Sign Button "<PaymentType>"
          #Skip sign flow
     Then the user clicks on Sign Agreement Later on docu sign page
     Then the order confirmation success message should be displayed
     And agreement number is generated
     Examples:
       | SheetName               | RowNumber | PaymentType | ProductType |
       | CheckOut-CheckOuts | 10         | CreditCard  | RAC         |

   @CHECKOUT  @BPCheckoutFlow_Setup_MyAccount @regression
   Scenario Outline: Generate Agreements for BP checkout with setup myaccount payment type <PaymentType> for product type <ProductType>

     Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
     Given the user opens URL
     And the user launches the RAC home page
     When the user clicks on Find your store button
     And the user enters Zipcode from "<SheetName>" at row <RowNumber>
     And the user clicks on Go button
     And the user clicks on Make This My Store link
     And the user searches for product from "<SheetName>" at row <RowNumber>
     When the user clicks on Start Order button
     And the user clicks on "I don't have a mobile" link on pdp
    #Check out form
     When the user fills check out form from "<SheetName>" at row <RowNumber>
     And the user clicks on Continue button Paypal
    #new check out form
     And the user enters ssn and dob
     And the user selects agree and continue button
     When the user clicks on the CONTINUE button on the Congrats popup
     And the user selects pay schedule from "<SheetName>" at row <RowNumber>
     And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
     And  the user clicks on the Continue button on the Pay Schedule section guest user

#     turn off BP on renewal terms page
     And the user toggle benefit plus checkbox

     And the user clicks on Continue To Payment button
     And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
#     When the user selects Save this payment method check box
     And the user selects I agree checkbox
     And the user clicks Submit And Continue To E Sign Button "<PaymentType>"
          #Skip sign flow
     Then the user clicks on Sign Agreement Later on docu sign page
     Then the order confirmation success message should be displayed
     And agreement number is generated

     #     Bp Checkout flow 2
     When the user close the BP modal on checkout confirmation page
     When the user click Set My Password button on confirmation page
     And  the user login to account after password setup
     Then verify user is navigated to MyAccount Dashboard
     And the user verify BP component on Dashboard and click on view
     And the user clicks on the I Agree and Continue button On BP Start order page
     When the user clicks on the CONTINUE button on the Congrats popup
     And the user selects pay schedule from "<SheetName>" at row <RowNumber>
     And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
     And  the user clicks on the CONTINUE button on the Pay Schedule section
     And the user clicks on Continue To Payment button
     And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
#     When the user selects Save this payment method check box
     And the user selects I agree checkbox
     And the user clicks Submit And Continue To E Sign Button "<PaymentType>"
          #Skip sign flow
     Then the user clicks on Sign Agreement Later on docu sign page
     Then the order confirmation success message should be displayed
     And agreement number is generated

     Examples:
       | SheetName               | RowNumber | PaymentType | ProductType |
       | CheckOut-CheckOuts | 10         | CreditCard  | RAC         |

     #   Author: Nidhi (added on 28-05-2025)
   @TC_SFCC4176_4178_4079_4180_3426_3427 @regression @CHECKOUT
   Scenario Outline: Verify whether we can do Zero $ total payment using new credit card on checkout flow
     Then the user skips execution if the iteration status is "Yes" in sheet "<SheetName>" at row <RowNumber>
     Given the user opens URL
     And the user launches the RAC home page
     When the user clicks on Find your store button
     And the user enters Zipcode from "<SheetName>" at row <RowNumber>
     And the user clicks on Go button
     And the user clicks on Make This My Store link
     And the user searches for product from "<SheetName>" at row <RowNumber>
     When the user clicks on Start Order button
     And the user clicks on "I don't have a mobile" link on pdp
     When the user fills check out form from "<SheetName>" at row <RowNumber>
     And the user clicks on Continue button Paypal
     And the user enters ssn and dob
     And the user selects agree and continue button
     When the user clicks on the CONTINUE button on the Congrats popup
     And the user selects pay schedule from "<SheetName>" at row <RowNumber>
     And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
     And  the user clicks on the Continue button on the Pay Schedule section guest user
  #Promo validation
     Then the user apply text enabled promo code and verify Total Due from "<SheetName>" at row <RowNumber>
     And the user clicks on Continue To Payment button
     And the user verify list of payment methods available on Promo code from "<SheetName>" at row <RowNumber>
     And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
#     When the user selects Save this payment method check box
     And the user selects I agree checkbox
     And the user clicks Submit And Continue To E Sign Button "<PaymentType>"
     Then the user clicks on Sign Agreement Later on docu sign page
     Then the order confirmation success message should be displayed
     And agreement number is generated

     Examples:
       | SheetName               | RowNumber | PaymentType | ProductType |
       | CheckOut-CheckOuts      | 14        | CreditCard  | RAC         |

   @TC_SFCC4177_4179_4181_4188_4189 @regression @CHECKOUT
   Scenario Outline: Verify whether we can do Zero $ total payment using saved credit card on checkout flow
     Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
     Given the user opens URL
     And the user launches the RAC home page
     When the user clicks on Sign In button
     And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
     And the user clicks on Sign In button in the My Account dialog
     When the user searches for product from "<SheetName>" at row <RowNumber>
     When the user clicks on Start Order button
     And the user clicks on the I Agree and Continue button
     When the user clicks on the CONTINUE button on the Congrats popup
     And the user selects pay schedule from "<SheetName>" at row <RowNumber>
     And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
     And the user clicks on the CONTINUE button on the Pay Schedule section
     Then the user apply text enabled promo code and verify Total Due from "<SheetName>" at row <RowNumber>
     And the user clicks on Continue To Payment button
     And the user verify list of saved payment methods available on Promo code from "<SheetName>" at row <RowNumber>
     And the user selects one of the saved cc payment methods
     And the user ticks on agree to Terms and  Enroll to Autopay
     And the user clicks on Make Payment button
#
##     select saved credit card option
#     And the user clicks Submit And Continue To E Sign Button "<PaymentType>"
#     Then the user clicks on Sign Agreement Later on docu sign page
#     Then the order confirmation success message should be displayed
#     And agreement number is generated

     Examples:
       | SheetName           | RowNumber | PaymentType |
       | CheckOut-CheckOuts | 14         | CC-VISA     |

   @TC_SFCC2218_to_2221 @regression @CHECKOUT
   Scenario Outline:Identity Verification page front end validation
     Then the user skips execution if the iteration status is "Yes" in sheet "<SheetName>" at row <RowNumber>
     Given the user opens URL
     And the user launches the RAC home page
     When the user clicks on Find your store button
     And the user enters Zipcode from "<SheetName>" at row <RowNumber>
     And the user clicks on Go button
     And the user clicks on Make This My Store link
     And the user searches for product from "<SheetName>" at row <RowNumber>
     When the user clicks on Start Order button
     And the user clicks on "I don't have a mobile" link on pdp
     When the user fills check out form from "<SheetName>" at row <RowNumber>
     And the user clicks on Continue button Paypal
     When the user clicks on ssn tooltip button in Identification verification dialog
     Then the tooltip opens and configured information is displayed
     And the user clicks on "Click here" link
     Then the link redirects the user to the "privacy-policy" page
     Then the user clicks on X button to close the tooltip
     Then the user validates the ssn input field for different inputs
     Then the user validates the DOB input field for different inputs

     Examples:
       | SheetName          | RowNumber | PaymentType | ProductType |
       | CheckOut-CheckOuts | 11        | CreditCard  | RAC         |

  @SignedUser_Checkout_EA @CHECKOUT @regression
   Scenario Outline: Registered user EA checkout flow
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
#     Then the user gets correlation id "<PaymentType>"

    #When the user selects choose optional benefits radio buttons off
     And the user clicks on Continue To Payment button
    #CC
     And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>

    #Billing Address Same as Delivery - click check box
    #When the user selects Save this payment method check box

     Then the user selects I agree checkbox Reg user
     And the user clicks Submit And Continue To E Sign Button
    # Get correlation ID
#     Then the user gets correlation id "<PaymentType>"
#    When the user selects docusign check box I sign
#    Then the user verifies continue button is displayed under docusign section
    #Skip sign flow
     Then the user clicks on Sign Agreement Later on docu sign page
     Then the order confirmation success message should be displayed
     And agreement number is generated

     Examples:
       | SheetName               | RowNumber |PaymentType|
       | CheckOut-CheckOuts     | 13         |CC-VISA |

   @SFCC3532 @TC_SFCC1410 @TC_SFCC1553 @regression @TC_SFCC1576 @CHECKOUT
   Scenario Outline:Payfone front end validation
     Then the user skips execution if the iteration status is "Yes" in sheet "<SheetName>" at row <RowNumber>
     Given the user opens URL
     And the user launches the RAC home page
     When the user clicks on Find your store button
     And the user enters Zipcode from "<SheetName>" at row <RowNumber>
     And the user clicks on Go button
     And the user clicks on Make This My Store link
     And the user searches for product from "<SheetName>" at row <RowNumber>
     When the user clicks on Start Order button
     And the form fields Email, mobile and ssn are displayed
     Then the user verify all Payfone flow field are empty on intial page load
     And the disclaimer with default state contents is displayed
     And "Would you like to receive automated marketing texts with deals and offers?" is displayed "false"
     Then the Third Party Consent hyperlink is visible on the Payphone page under Legal Verbiage
     Then the "Security Form" Label is displayed
     And the user clicks on "Privacy Policy" link
     Then the link redirects the user to the "privacy-policy" page
     And the user clicks on "Terms of Use and Arbitration Agreement" link
     Then the link redirects the user to the "termsofservice" page
#     Then the "Continue" button is disabled as fields are required
     And the user validates the email input
       | Input   | Input Value      |
       | invalid | donald           |
       | valid   | donald@racit.com |
     And the user validates the mobile input
       | Input   | Input Value |
       | invalid | 976         |
       | valid   | 3136391801  |
     And "Would you like to receive automated marketing texts with deals and offers?" is displayed "true"
     When the user clicks on "Yes" option
     Then the marketing msg for "Yes" is displayed
     When the user clicks on "No" option
     Then the marketing msg for "No" is displayed
     Then the user validates SSN input field accepts only 4 digits
       | Input   | Input Value |
       | invalid | 53          |
       | valid   | 6758        |
#     Then the "Continue" button is enabled as fields have valid inputs
     When the user clicks on payfone ssn tooltip button
     Then the tooltip opens and configured information for payfone ssn is displayed
     Then the user clicks on X button to close the tooltip
     Then Then the user clicks on mobile tooltip and validates the text
     Then the user clicks on "Third Party Terms" link
     And  third party consent dialog is displayed
     And  the pop-up contains the correct information when opened from the hyperlink
     Then the user clicks on X button to close the popup.
     Then the user clicks on "Third Party Terms" link
     And  third party consent dialog is displayed
     Then the user redirected corresponding page on same browser tab and stayed
       | Link Name               | Corresponding Page |
       | Read our Privacy Policy | privacy-policy     |

     Examples:
       | SheetName          | RowNumber | PaymentType | ProductType |
       | CheckOut-CheckOuts | 11        | CreditCard  | RAC         |

   @TC_SFCC3393 @regression @CHECKOUT
   Scenario Outline: Verify Prefilled email is displayed in wof page for the sign in user
     Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
     Given the user opens URL
     And the user launches the RAC home page
     When the user clicks on Sign In button
     And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
     And the user clicks on Sign In button in the My Account dialog
     When the user searches for product from "<SheetName>" at row <RowNumber>
     When the user clicks on Start Order button
     Then the prefilled email in wof is same as email from "<SheetName>" at row <RowNumber>
     Then the user clicks on "Edit My Information"
     Then the prefilled value of email is disabled for edit

     Examples:
       | SheetName           | RowNumber | PaymentType |
       | PaymentsCheckout-CreditCard | 5         | CC-VISA     |

   @TC_SFCC_7831 @regression @CHECKOUT
   Scenario Outline: Verify Modified Declined Page Components For HighRiskProduct
     Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
     Given the user opens URL
     And the user launches the RAC home page
     When the user clicks on Find your store button
     And the user enters Zipcode from "<SheetName>" at row <RowNumber>
     And the user clicks on Go button
     And the user clicks on Make This My Store link
     And the user searches for product from "<SheetName>" at row <RowNumber>
     When the user clicks on Start Order button
     And the user clicks on "I don't have a mobile" link on pdp
    #Check out form
     When the user fills check out form from "<SheetName>" at row <RowNumber>
     And the user clicks on Continue button Paypal
    #new check out form
     And the user enters ssn and dob
     And the user selects agree and continue button

        #Validations on modified declined page
     Then the user verify navigation to modified declined page from "<SheetName>" at row <RowNumber>
     Then the user verify CarouselComponent On Modified Declined Page from "<SheetName>" at row <RowNumber>


     Examples:
       | SheetName               | RowNumber | PaymentType | ProductType |
       | CheckOut-CheckOuts      | 12        | CreditCard  | RAC         |
