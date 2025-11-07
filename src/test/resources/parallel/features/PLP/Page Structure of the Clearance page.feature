Feature: Page Structure of the Clearance page and deals page
  Background:
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters zipcode "79761"
    And the user clicks on Go button
    And the user clicks on Make This My Store link

  @TC_SFCC7862_2691 @regression @PLP
  Scenario: Verify the Products and Page Structure of the Clearance page
    When the user click on L1 category "Clearance"
    Then the user is redirected to the "Clearance" category page
    #SFCC-2691
    And the user verifies that product badges are displayed on each product tile in the PLP
    And the user clicks on Read More link
    Then the user should see the Category Description section on the PLP
    Then the user clicks on the Read Less link to collapse the details

  @TC_SFCC7843_7854 @regression @PLP
  Scenario: Verify the Products and Page Structure of the deals page
    When the user hover an L1 category "Deals" in the menu
    And user verifies L2 subcategory "Shop All Deals"
    When the user click on L1 category "Deals"
    Then the user is redirected to the "Deals" category page