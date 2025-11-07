Feature: MyAccount_Validate Dashboard Page

  Background:
#    Given the user opens URL
#    And the user launches the RAC home page
#    When the user clicks on Sign In button

  @TC_SFCC3984_To_3989_and_7708 @regression @MYACCOUNT
  Scenario Outline: Validate my approval amount header and validate redirecting to PHP
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Sign In button
    And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
    And the user clicks on Sign In button in the My Account dialog
    And the user clicks on Account Details in My Account Section
    #SFCC-3986 #SFCC-3987 #SFCC-3990
    Then Verify My Approval Amount component header is displayed
    Then the following My Approval Amount details should be displayed:
      | Approved to rent items up to: |
    #SFCC-3989
    And the user clicks on Shop My Approved Items button
    Then the user is redirected to the PLP
    Examples:
      | SheetName  	   					 					| RowNumber |
      | MyAccount-SingleAgrwithoutBP		  | 0         |

 
  @TC_SFCC3985 @regression @MYACCOUNT
  Scenario Outline: Verify redirection to "Get Instant Approval" on clicking "Get Pre-Approval"
    #SFCC-3985
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Sign In button
    And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
    And the user clicks on Sign In button in the My Account dialog
    And the user clicks on Account Details in My Account Section
    Then Verify Get Pre-Approved button is displayed
    When the user clicks on Get Pre-Approved button
    Then the user is redirected to the Prefilled web order form
    And the user clicks on Account Details in My Account Section
    Examples:
      | SheetName  	   					 				| RowNumber   |
      | MyAccount-AgreementWithSusp 		| 0           |
     