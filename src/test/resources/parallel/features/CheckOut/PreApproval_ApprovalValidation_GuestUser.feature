Feature: PreApproval_ApprovalValidation_GuestUser

  #@SFCC484 @smoke @preapproval
  @smoke @PREAPPROVAL @SFCC484 @regression @smokererun
  Scenario Outline: Verify pre approval flow for UserType - <UserType>
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link
#    Add script here
    When the user clicks on Apply for Instant Approval button
    And the user clicks on "I don't have a mobile" link on pdp
    #WOF and IDV
    When the user fills check out form from "<SheetName>" at row <RowNumber>
    And the user clicks on Continue button Paypal
    #IDV page
    And the user enters ssn and dob
    And the user selects agree and continue button
    Then verify approval message for "<UserType>"
    #SFCC-3403, SFCC-3405
    Then the user able to click on RAC logo
    Then verify butter bar is displayed at the top
    And the user searches for product from "<SheetName>" at row <RowNumber>
    Then verify butter bar is displayed at the top
    Then the user click on cart link button
    Then verify butter bar is displayed at the top

    Examples:
      | SheetName            | RowNumber | UserType            |
      | PreApproval-UserType | 0         | Approved            |
      | PreApproval-UserType | 1         | ConditionalApproved |

  @TC_SFCC4230 @preapproval @regression
  Scenario Outline: Verify pre-filled data in web order form after approval flow for guest user
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link
#    Add script here
    When the user clicks on Apply for Instant Approval button
    And the user clicks on "I don't have a mobile" link on pdp
    #WOF and IDV
    When the user fills check out form from "<SheetName>" at row <RowNumber>
    And the user clicks on Continue button Paypal
    #IDV page
    And the user enters ssn and dob
    And the user selects agree and continue button
    Then verify approval message for "<UserType>"
    And the user searches for product from "<SheetName>" at row <RowNumber>
    When the user clicks on Start Order button
    And the WOF have prefilled user data from "<SheetName>" at row <RowNumber>

    Examples:
      | SheetName            | RowNumber | UserType            |
      | PreApproval-UserType | 0         | Approved            |

  @TC_SFCC_3403 @regression @Butterbar
  Scenario Outline: Verify Butter bar for <UserType>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Sign In button
    And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
    And the user clicks on Sign In button in the My Account dialog
    Then the user able to click on RAC logo
    Then verify butter bar is displayed at the top
    And the user searches for product from "<SheetName>" at row <RowNumber>
    Then verify butter bar is displayed at the top
    Then the user click on cart link button
    Then verify butter bar is displayed at the top

    Examples:
      | SheetName            | RowNumber | UserType            |
      | PreApproval-UserType | 2         | Approved            |
      | PreApproval-UserType | 3         | ConditionalApproved |