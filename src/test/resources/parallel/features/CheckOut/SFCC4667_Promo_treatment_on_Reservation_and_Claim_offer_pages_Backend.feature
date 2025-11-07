Feature: Promo treatment on Reservation and Claim offer pages- Backend

  Background:
    Given the user opens URL
    And the user launches the RAC home page

  @TC_SFCC4021_and_4022 @regression @CheckOut
  Scenario Outline: Verify Global Configurable Refundable Amount Displayed When No Store Level or High Risk Configured
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
    Then verify store refundable amount "$25.00" is displayed

    Examples:
      | SheetName    | RowNumber | ApprovalType |
      | E2E-Payments | 12        | Reservation  |


  @TC_SFCC4080 @regression @CheckOut
  Scenario Outline: Ensure that a clipped promo from "My Offer" is selected by default
    Then the user skips execution if the iteration status is "Yes" in sheet "<SheetName>" at row <RowNumber>
    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link

    #Navigate to PDP
    When the user click on my offer button
    When the user click on code applied "1WK1PENNY" button
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
    And the user clicks on the Continue button on the Pay Schedule section guest user
    Then verify my offers applied promo code is applied "1WK1PENNY"

    Examples:
      | SheetName    | RowNumber | ApprovalType |
      | E2E-Payments | 2         | CheckOut     |


