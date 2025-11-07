Feature: PLP - Base Template| New Design- Frontend

  @SFCC2097 @TC_SFCC2576_To_2587_And_SFCC7836 @regression @PLP @PLP_performance
  Scenario: Verify PLP - Base Template| New Design- Frontend
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters zipcode "02915"
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    Then "Pricing for" "02915" should be displayed in the top right corner below the header on the home page
    When the user hover an L1 category "Furniture" in the menu
    And user verifies L2 subcategory "Living Rooms"
    Then the user clicks on L3 category "Living Rooms" within an L2 category "Living Room Sets"
    #SFCC-3375
    Then User verify FastestDelivery option is displayed
    #SFCC-7836
    And The user verifies that Fastest Delivery toggle is OFF by default
    And The user enables the Fastest Delivery toggle
    Then The user verifies that the Fastest Delivery product list is displayed
    #SFCC-3376
    Then User verify The items count displays the correct number of results shown and the total number of results
    #SFCC-3415, SFCC-3414
    And User verify Marketing Banner should be displayed
    #SFCC-2697
    Then the user should see the product label and pricing
    #SFCC-2690
    And The user verify product image is displayed
    And verify the page title is "Living Room Sets"
    #SFCC-7861, #SFCC-3357
    And verify banner is displayed at the top of the page
    #SFCC-4082
    And I click on the "Furniture" breadcrumb
    Then the user is redirected to the "Furniture" category
    When the user hover an L1 category "Furniture" in the menu
    Then the user clicks on L3 category "Living Rooms" within an L2 category "Living Room Sets"
    #SFCC-7859
    And User selects "Price Low To High" from the dropdown
    Then Verify product prices are sorted in "Low To High" order
    And User selects "Price High To Low" from the dropdown
    Then Verify product prices are sorted in "High To Low" order
#    And the user clicks on list view toggle
#    Then Verify 20 products are displayed
#    And the user clicks on grid view toggle
#    Then Verify 20 products are displayed
#    When the user clicks on Show More link
#    Then Verify 20 products are displayed
    And the user clicks on Read More link
    Then the user should see the Category Description section on the PLP
    Then the user clicks on the Read Less link to collapse the details