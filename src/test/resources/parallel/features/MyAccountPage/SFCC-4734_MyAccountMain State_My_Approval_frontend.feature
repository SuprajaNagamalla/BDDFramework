Feature: MyAccount_Validate DueDate and Other Fields on MyAccount Dashboard
  Background:
#    Given the user opens URL
#    And the user launches the RAC home page
#    When the user clicks on Sign In button

  @TC_SFCC4112_To_SFCC4117 @regression @MYACCOUNT
  Scenario Outline: Verify Amount due and date fields on My Account Page
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Sign In button
    And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
    And the user clicks on Sign In button in the My Account dialog
    And the user clicks on Account Details in My Account Section
    
    #4733
    Then Verify total amount due text along with amount is displayed
    Then Verify List Of Agreements section is displayed
    Then Verify My Approval Amount component header is displayed
    Then the following My Approval Amount details should be displayed:
      | Approved to rent items up to: |
    Then the user clicks on "Approval Details" link
    Then Verify Total Approval Details is displayed
    Examples:
      | SheetName  	   					 					| RowNumber |
      | MyAccount-SingleAgrwithoutBP		  | 0         |
