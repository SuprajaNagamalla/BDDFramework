Feature: HeaderSearch_Bar- Keyword Typing and Suggestions- Backend

  Background:
    Given the user opens URL
    And the user launches the RAC home page

    #@SFCC7466
  @STC_FCC7525_To_7527_and_7522  @regression @Search
  Scenario: Verify Product Suggestions based on entered keywords
    When verify user navigate to search bar in the header
    #SFCC7522
    When the user search for a product "wa"
    Then verify search suggestions list displayed
    #SFCC7525
    When the user search for a product "ele"
    Then the following product suggestions provided:
      | electric    |
      | electronics |
    When the user select a suggestion
    Then the user is redirected to appropriate proper page "electronics"
    When the user click on the Logo in the header
    #SFCC7526
    When the user search for a product "num"
    Then verify no suggestions displayed if the keyword has no relevant categories
  #SFCC7527
    When the user search for a product "electronics"
    Then Verify exact phrase matches displayed "electronics" at the top of the list
    Then Verify matches that contain "electronics" all words appear in the results
    When the user search for a product "ele*"
    Then the following product suggestions provided:
      | electronics |
      | electric    |
    When the user select a suggestion
    Then the user is redirected to appropriate proper page "electronics"
    Then verify final list is sorted by availability