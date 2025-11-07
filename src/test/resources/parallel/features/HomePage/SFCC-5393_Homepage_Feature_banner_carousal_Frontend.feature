Feature: Header other Frontend

  Background:
    Given the user opens URL
    And the user launches the RAC home page

  #SFCC-5393
  @TC_SFCC5439 @regression @Homepage_Set3
  Scenario: Validate that the feature banner carousel is displayed and functions correctly on the home page
    Then the user clicks the right arrow button in the banner
    Then the user should be navigated to the next banner
    Then the user clicks the left arrow button in the banner
    And the user clicks on the navigation dot below the banner
    Then the user verifies that the banner displayed corresponds to the selected dot
    And the user clicks on the banner
    Then the user redirected to the Appliances