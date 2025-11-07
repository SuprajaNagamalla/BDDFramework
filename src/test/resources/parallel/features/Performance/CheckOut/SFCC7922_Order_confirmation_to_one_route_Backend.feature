Feature: Order confirmation to one route - Backend

  Background:
    Given the user opens URL
    And the user launches the RAC home page

  @TC_SFCC7779 @regression @CheckOut
  Scenario Outline: Verify on confirmation page if there are items remaining in customer cart
    Then the user skips execution if the iteration status is "Yes" in sheet "<SheetName>" at row <RowNumber>
    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link

    And the user searches for product from "<SheetName>" at row <RowNumber1>
    When the user clicks on add to cart button
    And the user clicks on item cart section close button

    #Navigate to PDP
    And the user searches for product from "<SheetName>" at row <RowNumber>
    When the user clicks on Start Order button
    And the user clicks on "I don't have a mobile" link on pdp

    When the user fills check out form from "<SheetName>" at row <RowNumber>
    And the user clicks on Continue button Paypal
    And the user enters ssn and dob
    And the user selects agree and continue button
    When the user clicks on the Congrats popup from "<SheetName>" at row <RowNumber>
    Then verify approved section is displayed from "<SheetName>" at row <RowNumber>
    And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
    And the user selects I agree checkbox
    And the user clicks Submit And Continue To E Sign Button
    Then Verify order confirmation message is displayed
    Then Verify cart remaining message is displayed
    When the user click on cart view button
    Then the user is redirected to appropriate proper page "cart"

    Examples:
      | SheetName    | RowNumber | ApprovalType | RowNumber1 |
      | E2E-Payments | 10        | Reservation  | 11         |