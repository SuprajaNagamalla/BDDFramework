Feature: Header other Frontend

  Background:
    Given the user opens URL
    And the user launches the RAC home page


  @TC_SFCC5436_5438  @regression @Homepage_Set3
  Scenario: Verify Home page -Popular Items | New Design - backend
    When the user scrolls to "Shop By Category" section
    When the user scrolls to "Popular Items" section on home screen
    Then Verify popular items section is displayed on the homepage
    When the user clicks on an item from the popular items section
    Then verify user is redirected to the pdp page