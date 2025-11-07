Feature: Product attached flow : Check Customer Approval Amount

  Background:
    Given the user opens URL
    And the user launches the RAC home page

  @TC_SFCC4009 @TC_SFCC4016 @TC_SFCC4018 @regression @PaymentGateway
  Scenario Outline: Verify redirection to Reservation Flow if Weekly Approval Amount is less than Renewal Rate
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
    When the user clicks on the Congrats popup from "<SheetName>" at row <RowNumber>
    Then verify approved section is displayed from "<SheetName>" at row <RowNumber>

    Examples:
      | SheetName    | RowNumber | ApprovalType |
      | E2E-Payments | 14        | Reservation  |
      | E2E-Payments | 15        | Reservation  |