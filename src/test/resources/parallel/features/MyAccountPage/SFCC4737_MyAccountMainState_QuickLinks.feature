Feature: MyAccount_Main state - Quick links

  Background:
    #Given the user opens URL
    #And the user launches the RAC home page

  @TC_SFCC4144_SFCC4149 @regression @MYACCOUNT
  Scenario Outline: Verify and Navigate Quick links and My store
  	Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
		Given the user opens URL
		And the user launches the RAC home page
		When the user clicks on Sign In button
		And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
		And the user clicks on Sign In button in the My Account dialog
    And the user clicks on Account Details in My Account Section
    Then verify quick links component header is displayed
    Then the following quick link components should be displayed:
      | My Account Dashboard |
      | Cart                 |
      | Saved Items          |
      | Payment Methods      |
      | Payment History      |
      | Personal             |
    Then verify quick link components redirected to corresponding page
      | Link Name       | Corresponding Page |
      | Cart            | cart               |
      | Saved Items     | wishlist           |
      | Payment Methods | payment-method     |
      | Payment History | payment-history    |
      | Personal Info   | personal-info      |
 Examples:
			| SheetName  	   					 					| RowNumber |
			| MyAccount-SingleAgrwithoutBP		  | 0         |
			
  
  @TC_SFCC7800_and_7707 @regression @MYACCOUNT
  Scenario Outline: Verify and Navigate Quick links MyAccount Menu
  	Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
		Given the user opens URL
		And the user launches the RAC home page
		When the user clicks on Sign In button
		And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
		And the user clicks on Sign In button in the My Account dialog
    And the user clicks on Account Details in My Account Section
    Then the following quick link components should be displayed:
      | My Account Dashboard |
      | Saved Items          |
      | Payment Methods      |
      | My Store             |
      | SignOut              |
    Then verify menu components redirected to corresponding page
      | Link Name                       | Corresponding Page 				|
      | My Account Dashboard            | my-account                |
      | Saved Items     								| wishlist           				|
      | Payment Methods 								| payment-method     				|
      | My Store											  | find-a-store       			  |
    Then verify menu components redirected to corresponding page signout
      | Sign Out         								| payonline-getstarted      |
 Examples:
			| SheetName  	   					          | RowNumber |
			| MyAccount-AgreementWithBP   		  | 0         |