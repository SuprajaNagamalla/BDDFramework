Feature: Bank verification - Frontend

  Background:
    Given the user opens URL
    And the user launches the RAC home page

  @TC_SFCC2229_and_2231 @regression @CheckOut
  Scenario Outline:: Test the behavior of the masking/unmasking feature for the Bank Account Number field
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
    When the user enters BAV routing and account number from "<SheetName>" at row <RowNumber>
    When the user clicks on bank account toggle mask
    Then verify bank account number value "242412424"
    Then the user verify non-blacklisted routing number error message

    Examples:
      | SheetName    | RowNumber | ApprovalType                 | PaymentType |
      | E2E-Payments | 16        | BAV-CheckoutApproval-CODEONE | CreditCard  |