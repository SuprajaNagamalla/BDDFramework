Feature: E2EFlow_BAV and Approval flows

  Background:
    Given the user opens URL
    And the user launches the RAC home page

  @regression @E2EFlow
  Scenario Outline: Verify bank verifications and Approvals <ApprovalType>
    #Selct store
    Then the user skips execution if the iteration status is "Yes" in sheet "<SheetName>" at row <RowNumber>
    #When the user click on accept cookie popup
    #When the user sign in sheet "<SheetName>" at row <RowNumber>
    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link
#    Then "Pricing for" "79761-4802" should be displayed in the top right corner below the header on the home page

    #Navigate to PDP
    And the user searches for product from "<SheetName>" at row <RowNumber>
    #Then the "Sofas & Sectionals" PDP should load successfully
    When the user clicks on Start Order button
#    Then user redirected to payfone or prefill page
    And the user clicks on "I don't have a mobile" link on pdp

    #Check out form
    When the user fills check out form from "<SheetName>" at row <RowNumber>
    And the user clicks on Continue button Paypal
    And the user enters ssn and dob
    And the user selects agree and continue button
    When the user enters BAV from "<SheetName>" at row <RowNumber>
    When the user clicks on the Congrats popup from "<SheetName>" at row <RowNumber>
    Then verify approved section is displayed from "<SheetName>" at row <RowNumber>

    Examples:
      | SheetName             | RowNumber | ApprovalType                                     |
      | E2E-UserFunctionality | 0         | BAV-CheckoutApproval-CODEONE                     |
      | E2E-UserFunctionality | 2         | BAV-ConditionalApproval-CODETHREE                |
      | E2E-UserFunctionality | 3         | BAV-ConditionalApproval-CODEFOUR                 |
      | E2E-UserFunctionality | 4         | BAV-ConditionalApproval-CODEFIVE                 |
      | E2E-UserFunctionality | 6         | BAV-CheckoutApproval-CODESEVEN                   |
      | E2E-UserFunctionality | 7         | Denied                                           |
      | E2E-UserFunctionality | 9         | Checkout Approval                                |
      | E2E-UserFunctionality | 10        | Conditional Checkout Approval                    |
      | E2E-UserFunctionality | 11        | Reservation                                      |
      | E2E-UserFunctionality | 15        | Claim Offer                                      |
      | E2E-UserFunctionality | 17        | Checkout Approval - Greater than approval amount |

  @regression @E2EFlow
  Scenario Outline: Verify bank verifications and Approvals <ApprovalType>
    #Selct store
    Then the user skips execution if the iteration status is "Yes" in sheet "<SheetName>" at row <RowNumber>
    #When the user click on accept cookie popup
    #When the user sign in sheet "<SheetName>" at row <RowNumber>
    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link
#    Then "Pricing for" "79761-4802" should be displayed in the top right corner below the header on the home page

    #Navigate to PDP
    And the user searches for product from "<SheetName>" at row <RowNumber>
    #Then the "Sofas & Sectionals" PDP should load successfully
    When the user clicks on Start Order button
#    Then user redirected to payfone or prefill page
    And the user clicks on "I don't have a mobile" link on pdp

    #Check out form
    When the user fills check out form from "<SheetName>" at row <RowNumber>
    And the user clicks on Continue button Paypal
    And the user enters ssn and dob
    And the user selects agree and continue button
    When the user clicks on the Congrats popup from "<SheetName>" at row <RowNumber>
    Then verify approved section is displayed from "<SheetName>" at row <RowNumber>

    Examples:
      | SheetName             | RowNumber | ApprovalType                                     |
      | E2E-UserFunctionality | 1         | BAV-CheckoutApproval-CODETWO                     |
      | E2E-UserFunctionality | 5         | BAV-ConditionalApproval-CODESIX                  |

  @smoke @E2EFlow
  Scenario Outline: Verify user navigation to ApprovalType - <ApprovalType>
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
    When the user clicks on the Congrats popup from "<SheetName>" at row <RowNumber>
    Then verify approved section is displayed from "<SheetName>" at row <RowNumber>
    When the user apply "PromoCode" from list from "<SheetName>" at row <RowNumber>
    And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
    And the user selects I agree checkbox
    And the user clicks Submit And Continue To E Sign Button
    Then Verify order confirmation message is displayed

    Examples:
      | SheetName             | RowNumber | ApprovalType |
      | E2E-UserFunctionality | 11        | Reservation  |
      | E2E-UserFunctionality | 19        | Claim Offer  |

  @smoke @E2EFlow
  Scenario Outline: Verify Bank verification ApprovalType - <ApprovalType>
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
    When the user enters BAV from "<SheetName>" at row <RowNumber>
    When the user clicks on the Congrats popup from "<SheetName>" at row <RowNumber>
    Then verify approved section is displayed from "<SheetName>" at row <RowNumber>
    And the user selects pay schedule from "<SheetName>" at row <RowNumber>
    And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
    And the user clicks on the Continue button on the Pay Schedule section guest user
    And the user clicks on Continue To Payment button
    And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
    #When the user selects Save this payment method check box
    And the user selects I agree checkbox
    And the user clicks Submit And Continue To E Sign Button "<PaymentType>"
          #Skip sign flow
    Then the user clicks on Sign Agreement Later on docu sign page
    Then the order confirmation success message should be displayed
    And agreement number is generated

    Examples:
      | SheetName             | RowNumber | ApprovalType                 | PaymentType |
      | E2E-UserFunctionality | 0         | BAV-CheckoutApproval-CODEONE | CreditCard  |
