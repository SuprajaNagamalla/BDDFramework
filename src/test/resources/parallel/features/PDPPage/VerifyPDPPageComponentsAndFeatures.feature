Feature: PDP_Validate PDP page data features

  Background:
    Given the user opens URL
    And the user launches the RAC home page
  @PDP @PDPsmoke
  Scenario Outline: PDP_Verify PDP page UI component for smoke Test Scenario <TCDescription>
    Given I read data from the workbook "PDPWorkbook" sheet "<SheetName>"
    Then I am reading the test data <RowNumber> from workbook sheet name "<SheetName>" for Test Scenario of "<TCDescription>"

    Examples:
      | SheetName  | RowNumber | TCDescription|
      | PDPSheet   | 0         |In Stock Product all functionality|
      | PDPSheet   | 1         |Out of stock Product|
      | PDPSheet   | 3         |Special order Product|
      | PDPSheet   | 10        |EA Product     |

  @PDP @PDPregression @regression
  Scenario Outline: PDP_Verify PDP page UI component for Test Scenario <TCDescription>
    Given I read data from the workbook "PDPWorkbook" sheet "<SheetName>"
    Then I am reading the test data <RowNumber> from workbook sheet name "<SheetName>" for Test Scenario of "<TCDescription>"

    Examples:
      | SheetName  | RowNumber | TCDescription|
      | PDPSheet   | 0         |In Stock Product all functionality|
      | PDPSheet   | 1         |Out of stock Product|
      | PDPSheet   | 2         |Promotional Product|
      | PDPSheet   | 3         |Special order Product|
      | PDPSheet   | 4         |Clearance Product|
      | PDPSheet   | 5         |Benefit plus Product|
      | PDPSheet   | 7         |Tires Product|
      | PDPSheet   | 8         |Jewellery Product|
      | PDPSheet   | 9         |Bundle Product|
      | PDPSheet   | 10        |EA Product     |
      | PDPSheet   | 11        |Ashley Product|
      | PDPSheet   | 12        |Used Product |
      | PDPSheet   | 6         |is Was Pricing Product|
#      | PDPSheet   | 13        |Popular Product|
      | PDPSheet   | 14        |Geo Pool Product|

  @TC_SFCC2365 @regression @PDP
  Scenario: Verify the error message for invalid zipcode entry
    When the user click on L1 category "Tires"
    And the user clicks on Go button
    And the user enters zipcode "6543"
    And the user clicks the 'Go' button on the PDP
    Then store locator pop up error message displayed as "Sorry, there are no locations in this area"

  @TC_SFCC2366 @regression @PDP_pending
  Scenario: Verify behavior when no zipcode is entered and "Go" button is clicked
    When the user click on L1 category "Tires"
    And the user clicks on Go button
    And the user clicks the 'Go' button on the PDP
    Then store locator pop up with message displayed as "Select your store to see local pricing, promotions, product availability, and delivery timeframes!"
    #have bug for above step_nd not at developed