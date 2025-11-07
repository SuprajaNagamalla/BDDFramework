Feature: HeaderSearch_bar- Display Frequently Searched Categories on Focus

  Background:
    Given the user opens URL
    And the user launches the RAC home page

  #@SFCC7462
  @TC_SFCC7528 @regression @Search
  Scenario: Verify the Display Frequently Searched Categories on Focus
    When verify user navigate to search bar in the header
    When the user click on search bar
    Then verify frequently searched category is displayed