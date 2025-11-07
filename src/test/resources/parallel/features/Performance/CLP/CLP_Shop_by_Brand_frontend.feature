Feature: CLP : Shop by Brand- frontend

  @SFCC2103_And_SFCC1564_And_SFCC1345 @TC_SFCC2601_2597_2598_3397_To_3398_3401_3402 @regression @CLP
  Scenario: Verify CLP : Shop by Brand- frontend
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters zipcode "02915"
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    Then "Pricing for" "02915" should be displayed in the top right corner below the header on the home page
    #When the user hover an L1 category "Electronics" in the menu
    When the user click on L1 category "Electronics"
    And I move the cursor to the element with locator
    Then the user scrolls to the "Shop By Brand" section
    Then User verify the Shop by Brand section is displayed
    And the user verifies that each category label is displayed on the Category Landing page
    And the user verifies that subcategories appear on the Category Landing page
    And the user verifies that components for the selected category are displayed on the CLP
    When the user clicks on a brand logo in the Shop by Brand section
    Then I verify that a new window is opened and user is redirected to the PLP Page
    And I close the new window and return to the previous window
    When the user hover an L1 category "Furniture" in the menu
    Then the user clicks on L3 category "Living Rooms" within an L2 category "Living Room Sets"
    And I click on the "Furniture" breadcrumb
    Then the user is redirected to the "Furniture" category
    Then user verifies l2 category "Living Rooms" in the subcategory panel
    Then user verifies l3 category "Recliners And Accent Chairs" in the subcategory panel
    And the user verifies that the Category specific PLP Banner is displayed
    And the user clicks on Read More link on CLP
    Then the user should see the Category Description on the Category Landing page
    Then the user clicks on the Read Less link to collapse the details on CLP