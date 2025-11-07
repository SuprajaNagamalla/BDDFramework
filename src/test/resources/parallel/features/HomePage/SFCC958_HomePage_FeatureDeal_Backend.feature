Feature: HomePage_Home page Feature deal Backend

  Background:
    Given the user opens URL
    And the user launches the RAC home page

    #@SFCC958
  @TC_SFCC5447 @regression @HomePage_set3
  Scenario: Ensure that clicking on any category in the menu redirects the user to the correct category page
    When the user click on L1 category "Furniture"
    Then the user is redirected to the "Furniture" category page
    When the user hover an L1 category "Furniture" in the menu
    When the user clicks on L2 subcategory "Living Rooms"
    Then the user is redirected to the "Living Rooms" category page
    When the user hover an L1 category "Furniture" in the menu
    When the user clicks on L3 category "Bedrooms" within an L2 category "Bedroom Sets"
    Then the user is redirected to the "Bedroom Sets" category page
    When the user click on L1 category "Clearance"
    Then the user is redirected to the "Clearance" category page

  #@SFCC958
  @TC_SFCC5444_To_5445_5460_5456 @regression @HomePage_set3
  Scenario: Verify Home page - Feature deal- Backend
    When the user clicks on Find your store button
    And the user enters zipcode "79761"
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    Then "Pricing for" "79761" should be displayed in the top right corner below the header on the home page
    When the user scrolls to "Featured Deals" section on the home screen
    Then verify featured deals section is displayed on the homepage
    Then verify product pricing should be displayed
    Then verify product description should be displayed
    #SFCC-5460
    And the user clicks the right navigation arrow in the featured deals section
    Then the user verifies that the next set of deals is displayed
    And the user clicks the left navigation arrow in the featured deals section
    Then the user verifies that the previous set of deals is displayed
    #SFCC-5456
    And the user clicks on an item from the featured deals section
    Then verify user is redirected to the pdp page