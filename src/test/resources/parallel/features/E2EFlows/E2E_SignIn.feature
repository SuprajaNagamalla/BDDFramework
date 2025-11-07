Feature: E2EFlow_BAV and Approval flows with signin

  Background:
    Given the user opens URL
    And the user launches the RAC home page

  @regression @E2EFlow
  Scenario Outline: Verify Approvals with SignIn user <ApprovalType>
    #Selct store
    Then the user skips execution if the iteration status is "Yes" in sheet "<SheetName>" at row <RowNumber>
    #When the user sign in sheet "<SheetName>" at row <RowNumber>
    When the user clicks on Sign In button
    And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
    And the user clicks on Sign In button in the My Account dialog
#    When the user clicks on Find your store button
#    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
#    And the user clicks on Go button
#    And the user clicks on Make This My Store link

    #Navigate to PDP
    And the user searches for product from "<SheetName>" at row <RowNumber>
    When the user clicks on Start Order button
    When the user clicks on the I Agree and Continue button

    #new check out form
#    And the user enters ssn and dob
#    And the user selects agree and continue button
    When the user clicks on the Congrats popup from "<SheetName>" at row <RowNumber>
    Then verify approved section is displayed from "<SheetName>" at row <RowNumber>

    Examples:
      | SheetName             | RowNumber | ApprovalType                                     |
      | E2E-UserFunctionality | 12        | Checkout Approval                                |
      | E2E-UserFunctionality | 13        | Conditional Checkout Approval                    |
      | E2E-UserFunctionality | 14        | Reservation                                      |
      | E2E-UserFunctionality | 16        | Claim Offer                                      |
      | E2E-UserFunctionality | 18        | Checkout Approval - Greater than approval amount |