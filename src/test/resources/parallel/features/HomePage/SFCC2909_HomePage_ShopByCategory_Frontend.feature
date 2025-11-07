Feature: HomePage_Home page Shop by category Frontend

  Background:
    Given the user opens URL
    And the user launches the RAC home page

    #@SFCC2909
  @TC_SFCC5452  @regression  @Homepage_set3
  Scenario Outline: Verify that the Shop By Category section is displayed on the home page and categories are shown based on store selection
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
    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    #Then "Pricing for" "02915-4913" should be displayed in the top right corner below the header on the home page
    Then the following shop by category section categories available to the selected store are displayed:
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

    Examples:
      | SheetName    | RowNumber |
      | E2E-HomePage | 0         |
