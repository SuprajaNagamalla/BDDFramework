Feature:Products Availability

  Background:
    Given user opens BM URL


#  @BMCheckProducts
#  Scenario Outline: Verify Products Availability
#
#    #Then skip execution if the iteration status is "No" in workbook "Payments" on sheet "<SheetName>" at row <RowNumber>
#    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
#    And the user fills the BM Login form using data from "<SheetName>" at row <RowNumber>
#
#
#    Examples:
#      | SheetName        | RowNumber |
#      | BM-Products      | 0         |

  @BMCheckProducts
  Scenario Outline: Verify Products Availability

    #Then skip execution if the iteration status is "No" in workbook "Payments" on sheet "<SheetName>" at row <RowNumber>
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    And the user fills the BM Login form using data from "<SheetName>" at row <RowNumber>
    And navigate to RAC products page
    And select RAC store no "02271"
#    And select RAC RMS no "200119230"
    And enter sorting data for product with attributes "available", "ascending", "100"
    Then click on find data for given search values
    Then get the productIDs from search results


    Examples:
      | SheetName        | RowNumber |
#      | BM-Products     | 0         |
#      | BM-Products     | 1         |
      | BM-Products      | 3         |

  @BMCheckEAProductsNew
  Scenario Outline: Verify EA Products Availability

    #Then skip execution if the iteration status is "No" in workbook "Payments" on sheet "<SheetName>" at row <RowNumber>
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    And the user fills the BM Login form using data from "<SheetName>" at row <RowNumber>
    And navigate to Inventory page
    And navigate to EA products Records Page Search
    Then get the productIDs from EA Records search results


    Examples:
      | SheetName        | RowNumber | RACStoreNo| RACRMSNo  |
      | BM-Products      | 3         | 02271     | 200119230 |


  @BMCheckSpecialProducts
  Scenario Outline: Make EA product as Special Products

    #Then skip execution if the iteration status is "No" in workbook "Payments" on sheet "<SheetName>" at row <RowNumber>
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    And the user fills the BM Login form using data from "<SheetName>" at row <RowNumber>
    And navigate to Inventory page
    And navigate to EA products Records Page Search
    Then make EA product as Special Order product


    Examples:
      | SheetName        | RowNumber |
      | BM-Products      | 3         |