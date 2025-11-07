Feature: Promo treatment on Reservation and Claim offer pages- Backend

  Background:
    Given the user opens URL
    And the user launches the RAC home page

  @TC_SFCC4212_To_4217 @regression @PaymentGateway
  Scenario Outline: Verify in Reservation and Claim offer page store level refundable is displayed when selected promo is in exclusion list
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
    Then verify total due amount match with "<ApprovalType>"
    When the user enter and apply promo code from "<SheetName>" at row <RowNumber>
    Then verify total due amount match with "<ApprovalType>"
    When the user apply "ExclusionPromo" from list from "<SheetName>" at row <RowNumber>
    Then verify total due amount match with "<ApprovalType>"

    Examples:
      | SheetName    | RowNumber | ApprovalType |
      | E2E-Payments | 10        | Reservation  |
      | E2E-Payments | 11        | Claim Offer  |


  @TC_SFCC4073 @regression @PaymentGateway
  Scenario Outline: Validate which payment methods are available and functional when the total payment amount is $0 in the checkout flow
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
    And the user selects pay schedule from "<SheetName>" at row <RowNumber>
    And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
    And the user clicks on the CONTINUE button on the Pay Schedule section
    When the user apply "ZeroPromo" from list from "<SheetName>" at row <RowNumber>
    And the user clicks on Continue To Payment button
    Then the user verify list of All payment methods available on Promo code from "<SheetName>" at row <RowNumber>

    Examples:
      | SheetName    | RowNumber | ApprovalType |
      | E2E-Payments | 2         | Reservation  |