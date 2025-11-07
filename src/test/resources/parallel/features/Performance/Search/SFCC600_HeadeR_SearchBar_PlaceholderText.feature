Feature: HeaderSearch_Bar- Placeholder text

  Background:
    Given the user opens URL
    And the user launches the RAC home page

    #@SFCC600
  @TC_SFCC7530 @regression @Search
  Scenario Outline: Verify store level restriction on search in homepage
    When verify user navigate to search bar in the header
    Then verify all categories are displayed in search placeholder text:
#      | Search My Items                  |
      | Search Deals                     |
      | Search Living Room Sets          |
      | Search Clearance                 |
      | Search Washers and Dryers        |
      | Search Living Rooms              |
      | Search Furniture                 |
      | Search Recliners & Accent Chairs |
      | Search Refrigerators             |
      | Search Desktops                  |
    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    When verify user navigate to search bar in the header
    Then verify all categories are displayed in search placeholder text:
#      | Search My Items                  |
      | Search Deals                     |
      | Search Living Room Sets          |
      | Search Clearance                 |
      | Search Washers and Dryers        |
      | Search Living Rooms              |
      | Search Furniture                 |
      | Search Recliners & Accent Chairs |
      | Search Refrigerators             |
      | Search Desktops                  |

    Examples:
      | SheetName             | RowNumber |
      | E2E-UserFunctionality | 0         |

    #@SFCC600
  @TC_SFCC7529 @regression @Search
  Scenario: Header search bar
    When verify user navigate to search bar in the header
    Then verify top categories are displayed in search placeholder text:
      | Search Recliners & Accent Chairs |
      | Search Living Room Sets          |
      | Search Furniture                 |