Feature: HomePage_Home page Shop By category New Design Backend

  Background:
    Given the user opens URL
    And the user launches the RAC home page

  #SFCC3118
  @TC_SFCC5437  @regression @Homepage_set3
  Scenario: Validate "Shop by Category" Section Functionality
    When the user scrolls to "Shop By Category" section
    Then the "Shop By Category" section is displayed on the homepage
    Then the following shop by category section all categories are shown:
      | My Items                  |
      | Deals                     |
      | Living Room Sets          |
      | Living Rooms              |
      | Clearance                 |
      | Washers and Dryers        |
      | Furniture                 |
      | Recliners & Accent Chairs |
      | Refrigerators             |
      | Desktops                  |
    When the user click on "Living Room Sets" Shop by Category section
    Then the user is redirected to the "Living Room Sets" category page
    When the user click on the Logo in the header
    Then the Shop All button is visible in the Shop by Category section
    When the user click on Shop All button in Shop by Category section
    Then the user is redirected to the "My Items" category page