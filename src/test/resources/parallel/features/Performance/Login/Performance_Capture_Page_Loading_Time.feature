Feature: Performance

 @performance @performance_HomePage @performance_smoke
 Scenario: Verify Home pages loading times
   Given the user opens URL Perf
   When the user clicks on Find your store button
   And the user enters zipcode "02915" perf
   And the user clicks on Make This My Store link perf

  @performance @performance_SearchPage @performance_smoke
  Scenario: Verify product search loading times after store locator set
    Given the user opens URL Perf
    When the user clicks on Find your store button
    And the user enters zipcode "02915" perf
    And the user clicks on Make This My Store link perf
    When the user search for a product "electronics"
    Then Verify exact phrase matches displayed "electronics" at the top of the list perf
    #When the user select a suggestion
    When the user select a suggestion perf

  @performance @performance_SearchPage_NoStore_Debug
  Scenario: Verify Search pages loading times without store locator set
    Given the user opens URL Perf
    When the user search for a product "chair for living room"
    Then Verify exact phrase matches displayed "chair for living room" at the top of the list perf
    When the user select a suggestion perf

  @performance @performance_WebLeads @performance_smoke
  Scenario: Verify Web leads loading times after store locator set
    Given the user opens URL Perf
    When the user clicks on Find your store button
    And the user enters zipcode "02915" perf
    And the user clicks on Make This My Store link perf
    #When the user clicks on Apply for Instant Approval button
    When the user clicks on Apply for Instant Approval button perf
    And the user clicks on "I don't have a mobile" link perf