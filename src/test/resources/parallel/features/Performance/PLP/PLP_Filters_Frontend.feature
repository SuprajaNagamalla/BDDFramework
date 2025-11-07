Feature: PLP - Filters- Frontend

  @Performance_PLP
  Scenario: PLP - Filters- Frontend
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters zipcode "12206"
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    Then "Pricing for" "12206" should be displayed in the top right corner below the header on the home page
    When the user hover an L1 category "Furniture" in the menu
    Then the user clicks on L3 category "Living Rooms" within an L2 category "Living Room Sets"
    And the user should see the filter section on the PLP
    Then the user selects the following categories and collapses the filters
      | Rental Payment Options:    |
      | Price Range:               |
      | Brand                      |
      | Category                   |
      | Availability               |
      | Condition                  |
      | Estimated Delivery Options |
      | Clearance                  |
    And the user selects "Rental Payment Options:" from the list of categories
    Then the user chooses "Weekly" from the Rental Payment Options category
    Then the user verifies that "per week" displayed on all product cards
    And the user selects "Estimated Delivery Options" from the list of categories
    And the user applying the "1-3 Days" filter should update the product list accordingly
    When the user clicks the CLEAR ALL button
    Then the list of products should reset to display all available items without any filters
    And the user selects option "Price Range:" as a filter
    When the user applies a price range filter from $30 to $40
    Then only products with prices between $30 and $40 should be displayed