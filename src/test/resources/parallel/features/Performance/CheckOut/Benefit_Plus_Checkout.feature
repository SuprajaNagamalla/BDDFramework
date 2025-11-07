Feature: CheckOut_Benefit Plus Checkout

  Background:
    Given the user opens URL
    And the user launches the RAC home page

  #@SFCC374 @smoke @CHECKOUT
  @regression_InProgress
  Scenario Outline: Verify Benefit plus product check out

    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    And the user searches for product from "<SheetName>" at row <RowNumber>
    Then the product type "RAC Benefits Plus" is displayed
    When the user clicks on Start Order button
    And the user clicks on "I don't have a mobile" link on pdp
    #Check out form
    When the user fills check out form from "<SheetName>" at row <RowNumber>
    And the user clicks on Continue button Paypal
    #new check out form
    And the user enters ssn and dob
    And the user selects agree and continue button
    Then the user verifies Benefit plus product details display
    When the user clicks on the CONTINUE button on the Congrats popup

#    #And the user selects pay schedule "Weekly"
#    And the user selects pay schedule from "<SheetName>" at row <RowNumber>
#    #And the user selects paid next date "09/25/2025"
#    And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
#    And the user clicks on the CONTINUE button on the Pay Schedule section
#    #When the user selects choose optional benefits radio buttons off
#    And the user clicks on Continue To Payment button
#    And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
#    When the user selects Save this payment method check box
#    And the user selects I agree checkbox

    Examples:
      | SheetName               | RowNumber |
      | CheckOut-BenefitPlus    | 0         |
