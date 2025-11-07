Feature: Error messages for PROMO application- Frontend

  Background:
    Given the user opens URL
    And the user launches the RAC home page

    #@SFCC2869
  @TC_SFCC4158 @TC_SFCC4161 @regression @PaymentGateway
  Scenario Outline: Verify whether error message is displaying when customer enters correct promo code but it is already applied on Reservation and claim offer flow
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
    When the user apply "PromoCode" from list from "<SheetName>" at row <RowNumber>
    When the user enter and apply promo code from "<SheetName>" at row <RowNumber>
    Then verify promo code error message "Looks like you already applied this promo code" is displayed

    Examples:
      | SheetName    | RowNumber | ApprovalType |
      | E2E-Payments | 4         | Reservation  |
      | E2E-Payments | 5         | Claim Offer  |

    #@SFCC2869
  @TC_SFCC4156_To_4157_and_@TC_SFCC4159_To_4160 @regression @PaymentGateway
  Scenario Outline: Verify whether error message is displaying when customer enters wrong promo code on Reservation and Claim Offer flow
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
    When the user enter and apply promo code from "<SheetName>" at row <RowNumber>
    Then verify promo code error message "The entered promo code can’t be applied. Please enter or choose another one." is displayed

    Examples:
      | SheetName    | RowNumber | ApprovalType |
      | E2E-Payments | 6         | Reservation  |
      | E2E-Payments | 7         | Claim Offer  |
      | E2E-Payments | 12        | Reservation  |
      | E2E-Payments | 13        | Claim Offer  |

  @TC_SFCC4210_To_4211_and_4162_and_4164 @regression @PaymentGateway
  Scenario Outline: Verify in Reservation page, if Promo rate >= 0 (greater than or equal to 0) then strikethrough store level refundable and display promo rate
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
    When the user apply "PromoCode" from list from "<SheetName>" at row <RowNumber>
    Then verify payment due "Refundable Deposit:" amount is displayed
    Then verify payment due "Promo" amount is displayed
    Then verify payment due "Total Due Today:" amount is displayed
    Then verify promo rate after applying promo code from "<SheetName>" at row <RowNumber>
    #Then verify totalDue amount after applying promo code

    Examples:
      | SheetName    | RowNumber | ApprovalType |
      | E2E-Payments | 8         | Reservation  |
      | E2E-Payments | 9         | Claim Offer  |


