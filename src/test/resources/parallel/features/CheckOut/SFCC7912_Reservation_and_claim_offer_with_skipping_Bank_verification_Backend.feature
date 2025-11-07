Feature: Reservation and claim offer with skipping Bank verification - Backend

  Background:
    Given the user opens URL
    And the user launches the RAC home page

  @TC_SFCC7913_and_7914 @regression @CheckOut
  Scenario Outline: Show message box with BAV prompt on Reservation and claim offer page if high Risk user skips BAV
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
    When the user clicks on reservation link on BAV section
    When the user clicks on the Congrats popup from "<SheetName>" at row <RowNumber>
    Then verify approved section is displayed from "<SheetName>" at row <RowNumber>
    Then the user verify BAV prompt message
    Then verify bank account link is displayed

    Examples:
      | SheetName    | RowNumber | ApprovalType |
      | E2E-Payments | 16        | Reservation  |
      | E2E-Payments | 17        | Claim Offer  |