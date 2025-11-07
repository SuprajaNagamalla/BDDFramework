Feature: Reservation Scenarios - Backend

  Background:
    Given the user opens URL
    And the user launches the RAC home page

    #@SFCC374 @SFCC4655
  @TC_SFCC3999 @regression @PaymentGateway
  Scenario Outline: Verify customer can reserve product with $25 if bank verification is skipped
    #Selct store
    Then the user skips execution if the iteration status is "Yes" in sheet "<SheetName>" at row <RowNumber>
    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link
#    Then "Pricing for" "79761-4802" should be displayed in the top right corner below the header on the home page

    #Navigate to PDP
    And the user searches for product from "<SheetName>" at row <RowNumber>
    #Then the "Sofas & Sectionals" PDP should load successfully
    When the user clicks on Start Order button
    #Then user redirected to payfone or prefill page
    And the user clicks on "I don't have a mobile" link on pdp

    #Check out form
    When the user fills check out form from "<SheetName>" at row <RowNumber>
    And the user clicks on Continue button Paypal
    And the user enters ssn and dob
    And the user selects agree and continue button
    When the user clicks on reservation link on BAV section
    When the user clicks on the Congrats popup from "<SheetName>" at row <RowNumber>
    Then verify approved section is displayed from "<SheetName>" at row <RowNumber>
    Then the user should allow to reserve the product for "$25.00"

    Examples:
      | SheetName    | RowNumber |
      | E2E-Payments | 16        |

    #@SFCC374 @SFCC4655
  @TC_SFCC4002 @regression @Payments
  Scenario Outline: Verify reservation option at payment step
    #Selct store
    Then the user skips execution if the iteration status is "Yes" in sheet "<SheetName>" at row <RowNumber>
    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link
#    Then "Pricing for" "79761-4802" should be displayed in the top right corner below the header on the home page

    #Navigate to PDP
    And the user searches for product from "<SheetName>" at row <RowNumber>
    When the user clicks on Start Order button
    And the user clicks on "I don't have a mobile" link on pdp

    #Check out form
    When the user fills check out form from "<SheetName>" at row <RowNumber>
    And the user clicks on Continue button Paypal
    And the user enters ssn and dob
    And the user selects agree and continue button
    When the user clicks on the Congrats popup from "<SheetName>" at row <RowNumber>
    Then verify approved section is displayed from "<SheetName>" at row <RowNumber>
    And the user selects pay schedule from "<SheetName>" at row <RowNumber>
    And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
    And the user clicks on the CONTINUE button on the Pay Schedule section
    When the user click on reserve & complete in store link
    Then verify approved section is displayed from "<SheetName>" at row <RowNumber>

    Examples:
      | SheetName    | RowNumber |
      | E2E-Payments | 2         |

    #@SFCC374 @SFCC4655
  @TC_SFCC4008 @regression @Payments
  Scenario Outline: Verify reservation flow for high-risk customers when BAV flag is off for a store
    #Special product #Selct store
    Then the user skips execution if the iteration status is "Yes" in sheet "<SheetName>" at row <RowNumber>
    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link

    #Navigate to PDP
    And the user searches for product from "<SheetName>" at row <RowNumber>
    When the user clicks on start order button
    And the user clicks on "I don't have a mobile" link on pdp

    #Check out form
    When the user fills check out form from "<SheetName>" at row <RowNumber>
    And the user clicks on Continue button Paypal

    #new check out form
    And the user enters ssn and dob
    And the user selects agree and continue button
    When the user clicks on the Congrats popup from "<SheetName>" at row <RowNumber>
    Then verify approved section is displayed from "<SheetName>" at row <RowNumber>

    Examples:
      | SheetName    | RowNumber |
      | E2E-Payments | 3         |