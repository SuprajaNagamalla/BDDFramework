Feature: Product attached flow Bank verification( BAV) - Backend

  Background:
    Given the user opens URL
    And the user launches the RAC home page

  @TC_SFCC3449_and_3455_and_4020 @regression @E2EFlow
  Scenario Outline: Verify user navigation to ApprovalType - <ApprovalType>
    Then the user skips execution if the iteration status is "Yes" in sheet "<SheetName>" at row <RowNumber>
    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link

    #Navigate to PDP
    And the user searches for product from "<SheetName>" at row <RowNumber>
    When the user clicks on Start Order button
    And the user clicks on "I don't have a mobile" link on pdp

    #Check out form
    When the user fills check out form from "<SheetName>" at row <RowNumber>
    And the user clicks on Continue button Paypal
    And the user enters ssn and dob
    And the user selects agree and continue button
    When the user enters BAV routing and account number from "<SheetName>" at row <RowNumber>
    Then the user verify bank routing linked to "Jpmorgan Chase Bank, Na"
    When the user clicks on reservation link on BAV section
    Then verify store refundable amount "$25.00" is displayed

    Examples:
      | SheetName             | RowNumber |
      | E2E-UserFunctionality | 0         |