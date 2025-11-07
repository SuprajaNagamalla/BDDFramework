Feature: CheckOut_Tire Payfone - Frontend

  Background:
    Given the user opens URL
    And the user launches the RAC home page

 # @SFCC374 @SFCC1316 @TC_SFCC2143 @smoke @regression @CheckOut
  Scenario Outline: Verify Tire Payfone page

    #Then skip execution if the iteration status is "No" in workbook "Payments" on sheet "<SheetName>" at row <RowNumber>
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>

    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link

    #TC_2143
    #Navigate to Tire prod page
    And the user searches for product from "<SheetName>" at row <RowNumber>

    #TC_2144
   When the user clicks on "HOW IT WORKS" link
  #Then should display an informational modal or redirect to a page explaining the process



Then the user is navigated to the PDP Benefits Plus page
    Examples:
      | SheetName           | RowNumber |
      | CheckOut-US_1316    | 0         |
      # | paypal    | 1         |
       #| paypal    | 2         |
      # | paypal    | 3         |
     # | paypal    | 4         |