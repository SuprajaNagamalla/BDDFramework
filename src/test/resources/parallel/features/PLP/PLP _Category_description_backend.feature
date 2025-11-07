Feature: PLP - Category description - backend

  @TC_SFCC7836_4420_2841_2692_7852 @regression @PLP
  Scenario: Verify PLP - Category description - backend
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters zipcode "02915"
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    Then "Pricing for" "02915" should be displayed in the top right corner below the header on the home page
    #SFCC-7852
    When the user scrolls to "Shop By Category" section
    When user click on shop all button in shop by category section
    Then the user is redirected to the "My Items" category page
    And the user selects "Estimated Delivery Options" from the list of categories
    And The user verifies that "1-3 Days" option is checked by default
    And the user selects "Condition" from the list of categories
    And The user verifies that "New" option is checked by default
    And The user verifies that "Previously Rented - Fair" option is checked by default
    And The user verifies that "Previously Rented - Great" option is checked by default
    When the user adds a product to the wishlist
    Then the user verifies that the We've saved your item! popup is displayed
    Then the user verifies that the item is saved in the wishlist
    When the user hover an L1 category "Furniture" in the menu
    Then the user clicks on L3 category "Living Rooms" within an L2 category "Living Room Sets"
    And the user removes a product from the wishlist
    Then the user verifies that the We've removed your item! popup is displayed
    And The user verify product name is displayed in the plp
    And user click on marketing banner
    Then user verify that a new window is opened and user is redirected to the desktop banner