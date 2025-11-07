Feature: PLP approval flow

  @TC_SFCC7846 @regression @PLP
  Scenario: Verify whether the "My Approved Items" filter isn't showing on PLP and Super PLP if user has no approval info in session
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters zipcode "02915"
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    Then "Pricing for" "02915" should be displayed in the top right corner below the header on the home page
    When the user scrolls to "Shop By Category" section
    Then the "Shop By Category" section is displayed on the homepage
    When user click on shop all button in shop by category section
    Then the user is redirected to the "My Items" category page
    And the user verifies the items in my approval range option is not displayed

  @TC_SFCC7848_To_7849 @regression @PLP
  Scenario Outline: Verify PLP approval flow for UserType - <UserType>
  Verify whether the Products that are displayed on PLP are less than the approved amount and verify price ranges
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    When the user clicks on Apply for Instant Approval button
    And the user clicks on "I don't have a mobile" link on pdp
    #WOF and IDV
    When the user fills check out form from "<SheetName>" at row <RowNumber>
    And the user clicks on Continue button Paypal
    #IDV page
    And the user enters ssn and dob
    And the user selects agree and continue button
    Then verify approval message for "<UserType>"
    #RAC
    Then the user able to click on RAC logo
    Then verify butter bar is displayed at the top
    When the user scrolls to "Shop By Category" section
    Then the "Shop By Category" section is displayed on the homepage
    When user click on shop all button in shop by category section
    Then the user is redirected to the "My Items" category page
    And the user unchecks the items in my approval range
    #SFCC-7849
    Then the user verifies that price range up to dollar is not visible when approval range is unchecked
    And the user checks the items in my approval range
    Then the user verifies the price Range Up to dollar
    Examples:
      | SheetName            | RowNumber | UserType            |
      | PreApproval-UserType | 0         | Approved            |