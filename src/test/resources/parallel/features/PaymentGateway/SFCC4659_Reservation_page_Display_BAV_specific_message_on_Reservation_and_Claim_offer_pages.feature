Feature: PaymentGateWay_Display BAV specific message on Reservation and Claim offer pages

                      #Covered - @TC_SFCC4097,TC_SFCC4098,TC_SFCC4099
  #@SFCC374 @SFCC4659 @TC_SFCC4095_and_4097_To_4099 @smoke @regression @Agreements @PaymentGateWay
  @TC_SFCC4095_and_4097_To_4099 @regression @PaymentGateway
  Scenario Outline: Display BAV specific message on Reservation page - High Risk customer

    # Sign In - Flow
#    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
#    Given the user opens URL
#    And the user launches the RAC home page
#    When the user clicks on Sign In button
#    And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
#    And the user clicks on Sign In button in the My Account dialog
#    #Then the user verifies pricing for store locator displayed
#    Then the user able to click on RAC logo

    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    And the user searches for product from "<SheetName>" at row <RowNumber>
    #Then Brand Name is displayed as a hyperlink from "<SheetName>" at row <RowNumber>
    When the user clicks on Start Order button
    And the user clicks on "I don't have a mobile" link on pdp
    #Check out form
    When the user fills check out form from "<SheetName>" at row <RowNumber>
    And the user clicks on Continue button Paypal
    #new check out form
    And the user enters ssn and dob
    And the user selects agree and continue button
    When the user clicks on the CONTINUE button on the Congrats popup if displayed
    #And the user clicks on "Reservation & Complete in store" link
    And the user clicks on "Reservation & Complete in store" link under Bank verification section

    Then the user clicks RESERVE MY PRODUCT button if displayed on almost done dialog

    Then Reserve Your Product screen shall be displayed
    When the user clicks on "Verify Bank Account" link name
    Then Bank Verification screen is displayed

    Examples:
      | SheetName           | RowNumber |Customer|
      | PGW-ReservationNew     | 0         |HighRisk  |


    # Covered TC_SFCC4103
  #@SFCC374 @SFCC4659 @TC_SFCC4096 @smoke @regression @Agreements @PaymentGateWay
  #@SFCC4659 @TC_SFCC4096 @smoke @regression @PaymentGateWay
    #4104 -ADD?
  @TC_SFCC4096_and_4104_4253 @regression @PaymentGateway
  Scenario Outline: Display BAV specific message on claim offer - High Risk Customer


#    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
#    Given the user opens URL
#    And the user launches the RAC home page
#    When the user clicks on Sign In button
#    And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
#    And the user clicks on Sign In button in the My Account dialog
#    #Then the user verifies pricing for store locator displayed


    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    And the user searches for product from "<SheetName>" at row <RowNumber>

    #Then Brand Name is displayed as a hyperlink from "<SheetName>" at row <RowNumber>
    When the user clicks on Start Order button
    And the user clicks on "I don't have a mobile" link on pdp
    #Check out form
    When the user fills check out form from "<SheetName>" at row <RowNumber>
    And the user clicks on Continue button Paypal
    #new check out form
    And the user enters ssn and dob
    And the user selects agree and continue button
    #And the user clicks on "Reservation & Complete in store" link
    And the user clicks on "Reservation & Complete in store" link under Bank verification section
    Then the user clicks RESERVE MY PRODUCT button if displayed on almost done dialog
    Then Claim Offer screen shall be displayed

    #Pending  ***** TC_SFCC4100

    #TC_SFCC4253

   And The refundable deposit is displayed with the correct high-risk label and amount "$99.00"

    #Pending below ***** TC_SFCC4100 - step 2
    #No promotion is displayed, and the promo line item is hidden

    #TC_SFCC4104,#TC_SFCC4100
    Then the promo code is not displayed on the screen

    #Pending below ***** TC_SFCC4106 - step 1
    #The refundable deposit, promo (if applicable), and tooltip load efficiently.

    #And Checkout Online Now tool tip displayed
    When the user clicks on "Verify Bank Account" link name
    Then Bank Verification screen is displayed

    Examples:
      | SheetName           | RowNumber |
      | PGW-ClaimOffer     | 0          |


    # Covered TC_SFCC4103
  #@SFCC374 @SFCC4659 @TC_SFCC4096 @smoke @regression @Agreements @PaymentGateWay
  @TC_SFCC4100_and_4101_and_4104_and_4106 @regression @PaymentGateway
  Scenario Outline: Display BAV specific message on claim offer - Regular Customer

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
    #And the user clicks on "Reservation & Complete in store" link
    When the user clicks RESERVE MY PRODUCT button if displayed on almost done dialog
    And the user clicks on "Reservation & Complete in store" link under Bank verification section

    Then Claim Offer screen shall be displayed

    #Pending below ***** TC_SFCC4100
    #The store-level refundable deposit specific to the store is displayed.
    #Simulate a scenario where refundable amounts for both high-risk and store-level customers are not configured

    #Pending below ***** TC_SFCC4101
    #The promotion associated with the customer and store is displayed correctly.

    # TC_SFCC4104
    Then the promo box is displayed with the applicable promo details

    #Pending below ***** TC_SFCC4106 - step 2
    #The refundable deposit, promo, and tooltip load efficiently without delays

    #And Checkout Online Now tool tip displayed

    #Bug or not valid flow
#    When the user clicks on "Verify Bank Account" link name
#    Then Bank Verification screen is displayed

    Examples:
      | SheetName           | RowNumber |
      | PGW-ClaimOffer     | 1         |



#
#    # *****Delete Below ************
#
#
#
#
#                                  #TC_SFCC4097,TC_SFCC4098
#  @SFCC374 @SFCC4659 @TC_SFCC4095 @smoke @regression @Agreements @PaymentGateWay
#  Scenario Outline: Display BAV specific message on Reservation page login type -
#
#
#    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
#    Given the user opens URL
#    And the user launches the RAC home page
#    When the user clicks on Sign In button
#    And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
#    And the user clicks on Sign In button in the My Account dialog
#    Then the user verifies pricing for store locator displayed
#
##    When the user clicks on Find your store button
##    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
##    And the user clicks on Go button
##    And the user clicks on Make This My Store link
#
#    And the user searches for product from "<SheetName>" at row <RowNumber>
#    #Then Brand Name is displayed as a hyperlink from "<SheetName>" at row <RowNumber>
#    When the user clicks on Start Order button
#    Then user redirected to payfone or prefill page
#    And the user clicks on "I don't have a mobile" link on pdp
#    #Check out form
#    When the user fills check out form from "<SheetName>" at row <RowNumber>
#    And the user clicks on Continue button Paypal
#    #new check out form
#    And the user enters ssn and dob
#    And the user selects agree and continue button
#    #And the user clicks on "Reservation & Complete in store" link
#    And the user clicks on "Reservation & Complete in store" link under Bank verification section
#    Then Reserve Your Product screen shall be displayed
#    When the user clicks on "Verify Bank Account" link name
#    Then Bank Verification screen is displayed
#
#    Examples:
#      | SheetName           | RowNumber |Customer|
#      | PGW-Reservation     | 0         |HighRisk  |
#      | PGW-Reservation     | 0         |Regular |
#
#
#
#                      #TC_SFCC4099
#  @SFCC374 @SFCC4659 @TC_SFCC4096 @smoke @regression @Agreements @PaymentGateWay
#  Scenario Outline: Display BAV specific message on claim offer for customer - <Customer>
#
#   #Then skip execution if the iteration status is "No" in workbook "Payments" on sheet "<SheetName>" at row <RowNumber>
##    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
##    Given the user opens URL
##    And the user launches the RAC home page
##    When the user clicks on Find your store button
##    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
##    And the user clicks on Go button
##    And the user clicks on Make This My Store link
##    And the user searches for product from "<SheetName>" at row <RowNumber>
#
#    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
#    Given the user opens URL
#    And the user launches the RAC home page
#    When the user clicks on Sign In button
#    And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
#    And the user clicks on Sign In button in the My Account dialog
#    Then the user verifies pricing for store locator displayed
#
##    When the user clicks on Find your store button
##    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
##    And the user clicks on Go button
##    And the user clicks on Make This My Store link
#
#    And the user searches for product from "<SheetName>" at row <RowNumber>
#
#    #Then Brand Name is displayed as a hyperlink from "<SheetName>" at row <RowNumber>
#    When the user clicks on Start Order button
#    Then user redirected to payfone or prefill page
#    And the user clicks on "I don't have a mobile" link on pdp
#    #Check out form
#    When the user fills check out form from "<SheetName>" at row <RowNumber>
#    And the user clicks on Continue button Paypal
#    #new check out form
#    And the user enters ssn and dob
#    And the user selects agree and continue button
#    #And the user clicks on "Reservation & Complete in store" link
#    And the user clicks on "Reservation & Complete in store" link under Bank verification section
#
#    Then Claim Offer screen shall be displayed
#
#    #Pending below ***** TC_SFCC4100
#    #Then The high-risk refundable deposit amount is displayed
#    #The store-level refundable deposit specific to the store is displayed.
#    #Simulate a scenario where refundable amounts for both high-risk and store-level customers are not configured
#
#    And Checkout Online Now tool tip displayed
#
#    When the user clicks on "Verify Bank Account" link name
#    Then Bank Verification screen is displayed
#
#    Examples:
#      | SheetName           | RowNumber | Customer|
#      | PGW-ClaimOffer     | 1         | Regualr  |
#      #| PGW-ClaimOffer     | 2         | HighRisk |
#
#
## DELETE Below
#
#
#  @SFCC374 @SFCC4659 @TC_SFCC4096 @smoke @regression @Agreements @PaymentGateWay
#  Scenario Outline: Display BAV specific message on claim offer page No Login
#
#   #Then skip execution if the iteration status is "No" in workbook "Payments" on sheet "<SheetName>" at row <RowNumber>
#    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
#    Given the user opens URL
#    And the user launches the RAC home page
#    When the user clicks on Find your store button
#    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
#    And the user clicks on Go button
#    And the user clicks on Make This My Store link
#    And the user searches for product from "<SheetName>" at row <RowNumber>
#    #Then Brand Name is displayed as a hyperlink from "<SheetName>" at row <RowNumber>
#    When the user clicks on Start Order button
#    Then user redirected to payfone or prefill page
#    And the user clicks on "I don't have a mobile" link on pdp
#    #Check out form
#    When the user fills check out form from "<SheetName>" at row <RowNumber>
#    And the user clicks on Continue button Paypal
#    #new check out form
#    And the user enters ssn and dob
#    And the user selects agree and continue button
#    #And the user clicks on "Reservation & Complete in store" link
#    And the user clicks on "Reservation & Complete in store" link under Bank verification section
#
#    Then Claim Offer screen shall be displayed
#   # Then Reserve Your Product screen shall be displayed
#
#    When the user clicks on "Verify Bank Account" link name
#    Then Bank Verification screen is displayed
#
#    Examples:
#      | SheetName           | RowNumber |
#      | PGW-ClaimOffer     | 0         |