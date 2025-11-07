Feature: Performance: Verify Deals and Clearance Accordion in home page Footer

  Background:
    Given the user opens URL
    And the user launches the RAC home page

@SFCC576 @regression @Homepage_set2 @homepage_perf @performance
Scenario Outline: Verify Deals and Clearance Accordion in home page Footer
Given I perform the test for "<iteration>"
When the user scrolls to "Deals & Clearance" Accordion and Clicks

Then the user redirected corresponding page Deals And Clearance
| Link Name            | Corresponding Page |
| Deals                      | deals                             |
| Clearance              | clearance               |
#      | Sign Up For Deals | sign-up-for-deals      |
When the user scrolls to "Shop Products" Accordion and Clicks

Then the user redirected corresponding page on same browser tab
| Link Name                               | Corresponding Page  |
| Rent to Own Furniture        | furniture                        |
| Rent to Own Appliances     | appliances                     |
| Rent to Own Computers     | computers                    |
| Rent to Own Electronics     | electronics                     |

Examples:
| iteration |
| 1         |
| 2         |
| 3         |
| 4        |
| 5         |
| 6         |
| 7         |
| 8         |
| 9         |
| 10         |
| 11         |
| 12        |
| 13         |
| 14         |
| 15         |
| 16         |
| 17         |
| 18         |
| 19         |
| 20         |

  @PDP @PDPregression @regression @performance @PDP_performance
  Scenario Outline: PDP_Verify PDP page UI component for Test Scenario <TCDescription>
    Given I read data from the workbook "PDPWorkbook" sheet "<SheetName>"
    Then I am reading the test data <RowNumber> from workbook sheet name "<SheetName>" for Test Scenario of "<TCDescription>"

    Examples:
      | SheetName  | RowNumber | TCDescription|
#      | PDPSheet   | 0         |In Stock Product all functionality|
#      | PDPSheet   | 1         |Out of stock Product|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|


  @SFCC1375 @TC_SFCC2551_To_2557 @TC_SFCC-2698 @regression @PLP_performance @performance
  Scenario: PLP - Filters- Frontend
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters zipcode "79761"
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    Then "Pricing for" "12206" should be displayed in the top right corner below the header on the home page
    When the user hover an L1 category "Furniture" in the menu
    Then the user clicks on L3 category "Living Rooms" within an L2 category "Living Room Sets"
    And the user should see the filter section on the PLP
    Then the user selects the following categories and collapses the filters
      | Rental Payment Options:    |
      | Price Range:               |
      | Brand                      |
      | Category                   |
      | Availability               |
      | Condition                  |
      | Estimated Delivery Options |
      | Clearance                  |
    And the user selects "Rental Payment Options:" from the list of categories
    Then the user chooses "Weekly" from the Rental Payment Options category
    Then the user verifies that "per week" displayed on all product cards
    And the user selects "Estimated Delivery Options" from the list of categories
    And the user applying the "1-3 Days" filter should update the product list accordingly
    When the user clicks the CLEAR ALL button
    Then the list of products should reset to display all available items without any filters
    And the user selects option "Price Range:" as a filter
    When the user applies a price range filter from $30 to $40
    Then only products with prices between $30 and $40 should be displayed


  @STC_FCC7525_To_7527_and_7522  @regression @Search_per @performance @Search_per1

  Scenario Outline: Verify Product Suggestions based on entered keywords

    Then the user skips execution if the iteration status is "Yes" in sheet "<SheetName>" at row <RowNumber>

    Given the user opens URL

    And the user launches the RAC home page

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

    Examples:

      | SheetName             | RowNumber |

      | E2E-UserFunctionality | 11        |

      | E2E-UserFunctionality | 11        |

      | E2E-UserFunctionality | 11        |

      | E2E-UserFunctionality | 11        |

      | E2E-UserFunctionality | 11        |

      | E2E-UserFunctionality | 11        |

      | E2E-UserFunctionality | 11        |

      | E2E-UserFunctionality | 11        |

      | E2E-UserFunctionality | 11        |

      | E2E-UserFunctionality | 11        |
      | E2E-UserFunctionality | 11        |
      | E2E-UserFunctionality | 11        |
      | E2E-UserFunctionality | 11        |
      | E2E-UserFunctionality | 11        |
      | E2E-UserFunctionality | 11        |
      | E2E-UserFunctionality | 11        |
      | E2E-UserFunctionality | 11        |
      | E2E-UserFunctionality | 11        |

  @TC_SFCC5448 @regression @Homepage_Set1 @performance
  Scenario Outline: Verify Store Locator Visibility and Basic Functionality - <Page>
    When the user navigate to page "<Page>"
    Then the store Locator should be visible below the header on all pages
  #Bug - Find your store to see pricing
    And the "Find your store" text should be displayed on the Store Locator
    When the user clicks on Find your store button
    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
    And the user clicks on the X icon on store locator popup

    Examples:
      | Page                |
      | Home Page           |
      | How RAC Works       |
      | Contact Us          |
      | Furniture           |
      | Appliances          |

  @TC_SFCC5448 @regression @Homepage_Set1 @performance
  Scenario Outline: Verify Store Locator Visibility and Basic Functionality - <Page>
    When the user navigate to page "<Page>"
    Then the store Locator should be visible below the header on all pages
  #Bug - Find your store to see pricing
    And the "Find your store" text should be displayed on the Store Locator
    When the user clicks on Find your store button
    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
    And the user clicks on the X icon on store locator popup

    Examples:
      | Page                |
      | Home Page           |
      | How RAC Works       |
      | Contact Us          |
      | Furniture           |
      | Appliances          |

  @TC_SFCC5448 @regression @Homepage_Set1 @performance
  Scenario Outline: Verify Store Locator Visibility and Basic Functionality - <Page>
    When the user navigate to page "<Page>"
    Then the store Locator should be visible below the header on all pages
  #Bug - Find your store to see pricing
    And the "Find your store" text should be displayed on the Store Locator
    When the user clicks on Find your store button
    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
    And the user clicks on the X icon on store locator popup

    Examples:
      | Page                |
      | Home Page           |
      | How RAC Works       |
      | Contact Us          |
      | Furniture           |
      | Appliances          |

  @TC_SFCC5448 @regression @Homepage_Set1 @performance
  Scenario Outline: Verify Store Locator Visibility and Basic Functionality - <Page>
    When the user navigate to page "<Page>"
    Then the store Locator should be visible below the header on all pages
  #Bug - Find your store to see pricing
    And the "Find your store" text should be displayed on the Store Locator
    When the user clicks on Find your store button
    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
    And the user clicks on the X icon on store locator popup

    Examples:
      | Page                |
      | Home Page           |
      | How RAC Works       |
      | Contact Us          |
      | Furniture           |
      | Appliances          |
  @TC_SFCC5448 @regression @Homepage_Set1 @performance
  Scenario Outline: Verify Store Locator Visibility and Basic Functionality - <Page>
    When the user navigate to page "<Page>"
    Then the store Locator should be visible below the header on all pages
  #Bug - Find your store to see pricing
    And the "Find your store" text should be displayed on the Store Locator
    When the user clicks on Find your store button
    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
    And the user clicks on the X icon on store locator popup

    Examples:
      | Page                |
      | Home Page           |
      | How RAC Works       |
      | Contact Us          |
      | Furniture           |
      | Appliances          |

  @TC_SFCC5448 @regression @Homepage_Set1 @performance
  Scenario Outline: Verify Store Locator Visibility and Basic Functionality - <Page>
    When the user navigate to page "<Page>"
    Then the store Locator should be visible below the header on all pages
  #Bug - Find your store to see pricing
    And the "Find your store" text should be displayed on the Store Locator
    When the user clicks on Find your store button
    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
    And the user clicks on the X icon on store locator popup

    Examples:
      | Page                |
      | Home Page           |
      | How RAC Works       |
      | Contact Us          |
      | Furniture           |
      | Appliances          |

  @TC_SFCC5448 @regression @Homepage_Set1 @performance
  Scenario Outline: Verify Store Locator Visibility and Basic Functionality - <Page>
    When the user navigate to page "<Page>"
    Then the store Locator should be visible below the header on all pages
  #Bug - Find your store to see pricing
    And the "Find your store" text should be displayed on the Store Locator
    When the user clicks on Find your store button
    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
    And the user clicks on the X icon on store locator popup

    Examples:
      | Page                |
      | Home Page           |
      | How RAC Works       |
      | Contact Us          |
      | Furniture           |
      | Appliances          |

                #QAStory  #QAStory
#@SFCC47 @SFCC22 @SFCC549 @SFCC2874 @TC_SFCC5449 @smoke @regression @HomePage @HomePage_Header
#@SFCC549 @SFCC2874 @TC_SFCC5449 @regression @Homepage
  @regression @Homepage_Set1 @TC_SFCC5449 @performance
  Scenario Outline: Verify the store information pop up and error handling for invalid Zip codes
    When the user clicks on Find your store button
    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
    When the user enters zipcode "<Zipcode>"
    And the user clicks on Go button
    Then the Store Locator should process the valid Zip code without errors
  #1937
  #44128 - valid,   55001>Minnesota,   Wisconsin >53008
    Then the store information for the respective state "<State>" should be displayed
  #1930
    When the user clicks on the i info icon
    Then the pop-up should contain Store Info details
  #1931
    When the user click on GET DIRECTIONS link
    Then the user should be redirected to Google Maps with the store location

  #When the user clicks on the Back button on Store Info pop up
  #Then the user should return to the list of stores pop up
    And the user clicks on the X icon on store locator popup

    When the user clicks on Find your store button
    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
    When the user enters zipcode "<Zipcode>"
    And the user clicks on Go button
    Then the Store Locator should process the valid Zip code without errors

    When the user clicks on the i info icon
    And the user clicks on the X icon on store locator popup
    Then the store information pop up should be closed
    When the user clicks on Find your store button
    And the user enters zipcode "<InvalidZipCode>"
    And the user clicks on Go button
  #Bug below msg
    Then error message "Sorry, there are no locations in this area" should be displayed

    Examples:
      | Zipcode  |State| InvalidZipCode |
      | 44128    | OH  | 00000          |
     # | 02915    |
     # | 44128   |


#@SFCC47 @SFCC22 @SFCC549 @SFCC2874 @TC_SFCC5450 @smoke @regression @HomePage @HomePage_Header
#@SFCC549 @SFCC2874 @TC_SFCC5450 @regression @Homepage
  @regression @Homepage_Set1 @TC_SFCC5450
  Scenario: Verify the display of pricing for the entered Zip code and the confirmation pop-up when changing the store

    When the user clicks on Find your store button
    And the user enters zipcode "02915"
    And the user clicks on Go button
    And the user clicks on Make This My Store link
  #Then "Pricing for" "02915-4913" should be displayed in the top right corner below the header on the home page
    Then "Pricing for" "02915" should be displayed in the top right corner below the header on the home page

  #1936
    When the user clicks on Find your store button
  #And the user clicks on change option
    When the user enters zipcode "75019"
    And the user clicks on Go button
    Then change store error message is displayed on pop up
  #Then error message "Store changes may affect your current pricing, promotion, and inventory availability." should be displayed

#@SFCC47 @SFCC22 @SFCC549 @SFCC2874 @TC_SFCC5451_Blocked @smoke @regression @HomePage @HomePage_Header
  @TC_SFCC5451 @regression @Homepage_Set1
  Scenario: Verify the message changes for Minnesota and Wisconsin Zip codes and the behavior when using the location

    When the user clicks on Find your store button
  # Minnesota Zip code
    And the user enters zipcode "55101"
    And the user clicks on Go button
    Then minnesota store locator pop up error message displayed as "Rent-A-Center doesn't have store locations in Minnesota, but our affiliate company, Home Choice, does"
  # Wisconsin  Zip code
    And the user enters zipcode "53703"
    And the user clicks on Go button
    Then wisconsin store locator pop up error message displayed as "Rent-A-Center doesn't have store locations in Wisconsin, but our affiliate company, Get It Now, does"
  #When the user clicks on USE MY LOCATION button

  @SFCC1375 @TC_SFCC2551_To_2557 @TC_SFCC-2698 @regression @PLP_performance
  Scenario: PLP - Filters- Frontend
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters zipcode "79761"
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    Then "Pricing for" "12206" should be displayed in the top right corner below the header on the home page
    When the user hover an L1 category "Furniture" in the menu
    Then the user clicks on L3 category "Living Rooms" within an L2 category "Living Room Sets"
    And the user should see the filter section on the PLP
    Then the user selects the following categories and collapses the filters
      | Rental Payment Options:    |
      | Price Range:               |
      | Brand                      |
      | Category                   |
      | Availability               |
      | Condition                  |
      | Estimated Delivery Options |
      | Clearance                  |
    And the user selects "Rental Payment Options:" from the list of categories
    Then the user chooses "Weekly" from the Rental Payment Options category
    Then the user verifies that "per week" displayed on all product cards
    And the user selects "Estimated Delivery Options" from the list of categories
    And the user applying the "1-3 Days" filter should update the product list accordingly
    When the user clicks the CLEAR ALL button
    Then the list of products should reset to display all available items without any filters
    And the user selects option "Price Range:" as a filter
    When the user applies a price range filter from $30 to $40
    Then only products with prices between $30 and $40 should be displayed

  @CHECKOUT_performace @performance
  Scenario Outline: Generate Agreements for payment type <PaymentType> for product type <ProductType>

    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    And the user searches for product from "<SheetName>" at row <RowNumber>
    When the user clicks on Start Order button
    And the user clicks on "I don't have a mobile" link on pdp
 #Check out form
    When the user fills check out form from "<SheetName>" at row <RowNumber>
    And the user clicks on Continue button Paypal
 #new check out form
#    And the user enters ssn and dob
#    And the user selects agree and continue button
#
#
#    When the user clicks on the CONTINUE button on the Congrats popup
#    And the user selects pay schedule from "<SheetName>" at row <RowNumber>
#    And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
#    And  the user clicks on the Continue button on the Pay Schedule section guest user
#
#    And the user clicks on Continue To Payment button

    Examples:
      | SheetName                    | RowNumber | PaymentType | ProductType |
      | CheckOut-CheckOuts_Perf      | 0         | CreditCard  | RAC         |
      | CheckOut-CheckOuts_Perf      | 1         | CreditCard  | RAC         |
      | CheckOut-CheckOuts_Perf      | 2         | CreditCard  | RAC         |
      | CheckOut-CheckOuts_Perf      | 3         | CreditCard  | RAC         |
      | CheckOut-CheckOuts_Perf      | 4         | CreditCard  | RAC         |
      | CheckOut-CheckOuts_Perf      | 5         | CreditCard  | RAC         |
      | CheckOut-CheckOuts_Perf      | 6         | CreditCard  | RAC         |
      | CheckOut-CheckOuts_Perf      | 7         | CreditCard  | RAC         |
      | CheckOut-CheckOuts_Perf      | 8         | CreditCard  | RAC         |
      | CheckOut-CheckOuts_Perf      | 9         | CreditCard  | RAC         |

  @SFCC576 @regression @Homepage_set2 @homepage_perf @performance
  Scenario Outline: Verify Deals and Clearance Accordion in home page Footer
    Given I perform the test for "<iteration>"
    When the user scrolls to "Deals & Clearance" Accordion and Clicks

    Then the user redirected corresponding page Deals And Clearance
      | Link Name            | Corresponding Page |
      | Deals                      | deals                             |
      | Clearance              | clearance               |
#      | Sign Up For Deals | sign-up-for-deals      |
    When the user scrolls to "Shop Products" Accordion and Clicks

    Then the user redirected corresponding page on same browser tab
      | Link Name                               | Corresponding Page  |
      | Rent to Own Furniture        | furniture                        |
      | Rent to Own Appliances     | appliances                     |
      | Rent to Own Computers     | computers                    |
      | Rent to Own Electronics     | electronics                     |

    Examples:
      | iteration |
      | 1         |
      | 2         |
      | 3         |
      | 4        |
      | 5         |
      | 6         |
      | 7         |
      | 8         |
      | 9         |
      | 10         |
      | 11         |
      | 12        |
      | 13         |
      | 14         |
      | 15         |
      | 16         |
      | 17         |
      | 18         |
      | 19         |
      | 20         |

  @PDP @PDPregression @regression @performance @PDP_performance
  Scenario Outline: PDP_Verify PDP page UI component for Test Scenario <TCDescription>
    Given I read data from the workbook "PDPWorkbook" sheet "<SheetName>"
    Then I am reading the test data <RowNumber> from workbook sheet name "<SheetName>" for Test Scenario of "<TCDescription>"

    Examples:
      | SheetName  | RowNumber | TCDescription|
#      | PDPSheet   | 0         |In Stock Product all functionality|
#      | PDPSheet   | 1         |Out of stock Product|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|


  @SFCC1375 @TC_SFCC2551_To_2557 @TC_SFCC-2698 @regression @PLP_performance @performance
  Scenario: PLP - Filters- Frontend
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters zipcode "79761"
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    Then "Pricing for" "12206" should be displayed in the top right corner below the header on the home page
    When the user hover an L1 category "Furniture" in the menu
    Then the user clicks on L3 category "Living Rooms" within an L2 category "Living Room Sets"
    And the user should see the filter section on the PLP
    Then the user selects the following categories and collapses the filters
      | Rental Payment Options:    |
      | Price Range:               |
      | Brand                      |
      | Category                   |
      | Availability               |
      | Condition                  |
      | Estimated Delivery Options |
      | Clearance                  |
    And the user selects "Rental Payment Options:" from the list of categories
    Then the user chooses "Weekly" from the Rental Payment Options category
    Then the user verifies that "per week" displayed on all product cards
    And the user selects "Estimated Delivery Options" from the list of categories
    And the user applying the "1-3 Days" filter should update the product list accordingly
    When the user clicks the CLEAR ALL button
    Then the list of products should reset to display all available items without any filters
    And the user selects option "Price Range:" as a filter
    When the user applies a price range filter from $30 to $40
    Then only products with prices between $30 and $40 should be displayed


  @STC_FCC7525_To_7527_and_7522  @regression @Search_per @performance

  Scenario Outline: Verify Product Suggestions based on entered keywords

    Then the user skips execution if the iteration status is "Yes" in sheet "<SheetName>" at row <RowNumber>

    Given the user opens URL

    And the user launches the RAC home page

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

    Examples:

      | SheetName             | RowNumber |

      | E2E-UserFunctionality | 11        |

      | E2E-UserFunctionality | 11        |

      | E2E-UserFunctionality | 11        |

      | E2E-UserFunctionality | 11        |

      | E2E-UserFunctionality | 11        |

      | E2E-UserFunctionality | 11        |

      | E2E-UserFunctionality | 11        |

      | E2E-UserFunctionality | 11        |

      | E2E-UserFunctionality | 11        |

      | E2E-UserFunctionality | 11        |

  @TC_SFCC5448 @regression @Homepage_Set1 @performance
  Scenario Outline: Verify Store Locator Visibility and Basic Functionality - <Page>
    When the user navigate to page "<Page>"
    Then the store Locator should be visible below the header on all pages
  #Bug - Find your store to see pricing
    And the "Find your store" text should be displayed on the Store Locator
    When the user clicks on Find your store button
    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
    And the user clicks on the X icon on store locator popup

    Examples:
      | Page                |
      | Home Page           |
      | How RAC Works       |
      | Contact Us          |
      | Furniture           |
      | Appliances          |

  @TC_SFCC5448 @regression @Homepage_Set1 @performance
  Scenario Outline: Verify Store Locator Visibility and Basic Functionality - <Page>
    When the user navigate to page "<Page>"
    Then the store Locator should be visible below the header on all pages
  #Bug - Find your store to see pricing
    And the "Find your store" text should be displayed on the Store Locator
    When the user clicks on Find your store button
    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
    And the user clicks on the X icon on store locator popup

    Examples:
      | Page                |
      | Home Page           |
      | How RAC Works       |
      | Contact Us          |
      | Furniture           |
      | Appliances          |

  @TC_SFCC5448 @regression @Homepage_Set1 @performance
  Scenario Outline: Verify Store Locator Visibility and Basic Functionality - <Page>
    When the user navigate to page "<Page>"
    Then the store Locator should be visible below the header on all pages
  #Bug - Find your store to see pricing
    And the "Find your store" text should be displayed on the Store Locator
    When the user clicks on Find your store button
    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
    And the user clicks on the X icon on store locator popup

    Examples:
      | Page                |
      | Home Page           |
      | How RAC Works       |
      | Contact Us          |
      | Furniture           |
      | Appliances          |

  @TC_SFCC5448 @regression @Homepage_Set1 @performance
  Scenario Outline: Verify Store Locator Visibility and Basic Functionality - <Page>
    When the user navigate to page "<Page>"
    Then the store Locator should be visible below the header on all pages
  #Bug - Find your store to see pricing
    And the "Find your store" text should be displayed on the Store Locator
    When the user clicks on Find your store button
    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
    And the user clicks on the X icon on store locator popup

    Examples:
      | Page                |
      | Home Page           |
      | How RAC Works       |
      | Contact Us          |
      | Furniture           |
      | Appliances          |
  @TC_SFCC5448 @regression @Homepage_Set1 @performance
  Scenario Outline: Verify Store Locator Visibility and Basic Functionality - <Page>
    When the user navigate to page "<Page>"
    Then the store Locator should be visible below the header on all pages
  #Bug - Find your store to see pricing
    And the "Find your store" text should be displayed on the Store Locator
    When the user clicks on Find your store button
    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
    And the user clicks on the X icon on store locator popup

    Examples:
      | Page                |
      | Home Page           |
      | How RAC Works       |
      | Contact Us          |
      | Furniture           |
      | Appliances          |

  @TC_SFCC5448 @regression @Homepage_Set1 @performance
  Scenario Outline: Verify Store Locator Visibility and Basic Functionality - <Page>
    When the user navigate to page "<Page>"
    Then the store Locator should be visible below the header on all pages
  #Bug - Find your store to see pricing
    And the "Find your store" text should be displayed on the Store Locator
    When the user clicks on Find your store button
    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
    And the user clicks on the X icon on store locator popup

    Examples:
      | Page                |
      | Home Page           |
      | How RAC Works       |
      | Contact Us          |
      | Furniture           |
      | Appliances          |

  @TC_SFCC5448 @regression @Homepage_Set1 @performance
  Scenario Outline: Verify Store Locator Visibility and Basic Functionality - <Page>
    When the user navigate to page "<Page>"
    Then the store Locator should be visible below the header on all pages
  #Bug - Find your store to see pricing
    And the "Find your store" text should be displayed on the Store Locator
    When the user clicks on Find your store button
    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
    And the user clicks on the X icon on store locator popup

    Examples:
      | Page                |
      | Home Page           |
      | How RAC Works       |
      | Contact Us          |
      | Furniture           |
      | Appliances          |

                #QAStory  #QAStory
#@SFCC47 @SFCC22 @SFCC549 @SFCC2874 @TC_SFCC5449 @smoke @regression @HomePage @HomePage_Header
#@SFCC549 @SFCC2874 @TC_SFCC5449 @regression @Homepage
  @regression @Homepage_Set1 @TC_SFCC5449 @performance
  Scenario Outline: Verify the store information pop up and error handling for invalid Zip codes
    When the user clicks on Find your store button
    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
    When the user enters zipcode "<Zipcode>"
    And the user clicks on Go button
    Then the Store Locator should process the valid Zip code without errors
  #1937
  #44128 - valid,   55001>Minnesota,   Wisconsin >53008
    Then the store information for the respective state "<State>" should be displayed
  #1930
    When the user clicks on the i info icon
    Then the pop-up should contain Store Info details
  #1931
    When the user click on GET DIRECTIONS link
    Then the user should be redirected to Google Maps with the store location

  #When the user clicks on the Back button on Store Info pop up
  #Then the user should return to the list of stores pop up
    And the user clicks on the X icon on store locator popup

    When the user clicks on Find your store button
    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
    When the user enters zipcode "<Zipcode>"
    And the user clicks on Go button
    Then the Store Locator should process the valid Zip code without errors

    When the user clicks on the i info icon
    And the user clicks on the X icon on store locator popup
    Then the store information pop up should be closed
    When the user clicks on Find your store button
    And the user enters zipcode "<InvalidZipCode>"
    And the user clicks on Go button
  #Bug below msg
    Then error message "Sorry, there are no locations in this area" should be displayed

    Examples:
      | Zipcode  |State| InvalidZipCode |
      | 44128    | OH  | 00000          |
     # | 02915    |
     # | 44128   |


#@SFCC47 @SFCC22 @SFCC549 @SFCC2874 @TC_SFCC5450 @smoke @regression @HomePage @HomePage_Header
#@SFCC549 @SFCC2874 @TC_SFCC5450 @regression @Homepage
  @regression @Homepage_Set1 @TC_SFCC5450
  Scenario: Verify the display of pricing for the entered Zip code and the confirmation pop-up when changing the store

    When the user clicks on Find your store button
    And the user enters zipcode "02915"
    And the user clicks on Go button
    And the user clicks on Make This My Store link
  #Then "Pricing for" "02915-4913" should be displayed in the top right corner below the header on the home page
    Then "Pricing for" "02915" should be displayed in the top right corner below the header on the home page

  #1936
    When the user clicks on Find your store button
  #And the user clicks on change option
    When the user enters zipcode "75019"
    And the user clicks on Go button
    Then change store error message is displayed on pop up
  #Then error message "Store changes may affect your current pricing, promotion, and inventory availability." should be displayed

#@SFCC47 @SFCC22 @SFCC549 @SFCC2874 @TC_SFCC5451_Blocked @smoke @regression @HomePage @HomePage_Header
  @TC_SFCC5451 @regression @Homepage_Set1
  Scenario: Verify the message changes for Minnesota and Wisconsin Zip codes and the behavior when using the location

    When the user clicks on Find your store button
  # Minnesota Zip code
    And the user enters zipcode "55101"
    And the user clicks on Go button
    Then minnesota store locator pop up error message displayed as "Rent-A-Center doesn't have store locations in Minnesota, but our affiliate company, Home Choice, does"
  # Wisconsin  Zip code
    And the user enters zipcode "53703"
    And the user clicks on Go button
    Then wisconsin store locator pop up error message displayed as "Rent-A-Center doesn't have store locations in Wisconsin, but our affiliate company, Get It Now, does"
  #When the user clicks on USE MY LOCATION button

  @SFCC1375 @TC_SFCC2551_To_2557 @TC_SFCC-2698 @regression @PLP_performance
  Scenario: PLP - Filters- Frontend
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters zipcode "79761"
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    Then "Pricing for" "12206" should be displayed in the top right corner below the header on the home page
    When the user hover an L1 category "Furniture" in the menu
    Then the user clicks on L3 category "Living Rooms" within an L2 category "Living Room Sets"
    And the user should see the filter section on the PLP
    Then the user selects the following categories and collapses the filters
      | Rental Payment Options:    |
      | Price Range:               |
      | Brand                      |
      | Category                   |
      | Availability               |
      | Condition                  |
      | Estimated Delivery Options |
      | Clearance                  |
    And the user selects "Rental Payment Options:" from the list of categories
    Then the user chooses "Weekly" from the Rental Payment Options category
    Then the user verifies that "per week" displayed on all product cards
    And the user selects "Estimated Delivery Options" from the list of categories
    And the user applying the "1-3 Days" filter should update the product list accordingly
    When the user clicks the CLEAR ALL button
    Then the list of products should reset to display all available items without any filters
    And the user selects option "Price Range:" as a filter
    When the user applies a price range filter from $30 to $40
    Then only products with prices between $30 and $40 should be displayed

  @CHECKOUT_performace @performance
  Scenario Outline: Generate Agreements for payment type <PaymentType> for product type <ProductType>

    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    And the user searches for product from "<SheetName>" at row <RowNumber>
    When the user clicks on Start Order button
    And the user clicks on "I don't have a mobile" link on pdp
 #Check out form
    When the user fills check out form from "<SheetName>" at row <RowNumber>
    And the user clicks on Continue button Paypal
 #new check out form
#    And the user enters ssn and dob
#    And the user selects agree and continue button
#
#
#    When the user clicks on the CONTINUE button on the Congrats popup
#    And the user selects pay schedule from "<SheetName>" at row <RowNumber>
#    And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
#    And  the user clicks on the Continue button on the Pay Schedule section guest user
#
#    And the user clicks on Continue To Payment button

    Examples:
      | SheetName                    | RowNumber | PaymentType | ProductType |
      | CheckOut-CheckOuts_Perf      | 0         | CreditCard  | RAC         |
      | CheckOut-CheckOuts_Perf      | 1         | CreditCard  | RAC         |
      | CheckOut-CheckOuts_Perf      | 2         | CreditCard  | RAC         |
      | CheckOut-CheckOuts_Perf      | 3         | CreditCard  | RAC         |
      | CheckOut-CheckOuts_Perf      | 4         | CreditCard  | RAC         |
      | CheckOut-CheckOuts_Perf      | 5         | CreditCard  | RAC         |
      | CheckOut-CheckOuts_Perf      | 6         | CreditCard  | RAC         |
      | CheckOut-CheckOuts_Perf      | 7         | CreditCard  | RAC         |
      | CheckOut-CheckOuts_Perf      | 8         | CreditCard  | RAC         |
      | CheckOut-CheckOuts_Perf      | 9         | CreditCard  | RAC         |

  @SFCC576 @regression @Homepage_set2 @homepage_perf @performance
  Scenario Outline: Verify Deals and Clearance Accordion in home page Footer
    Given I perform the test for "<iteration>"
    When the user scrolls to "Deals & Clearance" Accordion and Clicks

    Then the user redirected corresponding page Deals And Clearance
      | Link Name            | Corresponding Page |
      | Deals                      | deals                             |
      | Clearance              | clearance               |
#      | Sign Up For Deals | sign-up-for-deals      |
    When the user scrolls to "Shop Products" Accordion and Clicks

    Then the user redirected corresponding page on same browser tab
      | Link Name                               | Corresponding Page  |
      | Rent to Own Furniture        | furniture                        |
      | Rent to Own Appliances     | appliances                     |
      | Rent to Own Computers     | computers                    |
      | Rent to Own Electronics     | electronics                     |

    Examples:
      | iteration |
      | 1         |
      | 2         |
      | 3         |
      | 4        |
      | 5         |
      | 6         |
      | 7         |
      | 8         |
      | 9         |
      | 10         |
      | 11         |
      | 12        |
      | 13         |
      | 14         |
      | 15         |
      | 16         |
      | 17         |
      | 18         |
      | 19         |
      | 20         |

  @PDP @PDPregression @regression @performance @PDP_performance
  Scenario Outline: PDP_Verify PDP page UI component for Test Scenario <TCDescription>
    Given I read data from the workbook "PDPWorkbook" sheet "<SheetName>"
    Then I am reading the test data <RowNumber> from workbook sheet name "<SheetName>" for Test Scenario of "<TCDescription>"

    Examples:
      | SheetName  | RowNumber | TCDescription|
#      | PDPSheet   | 0         |In Stock Product all functionality|
#      | PDPSheet   | 1         |Out of stock Product|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|
      | PDPSheet   | 1         |Promotional Product1|
      | PDPSheet   | 2         |Promotional Product2|
      | PDPSheet   | 3         |Promotional Product3|
      | PDPSheet   | 4         |Promotional Product4|
      | PDPSheet   | 5         |Promotional Product5|


  @SFCC1375 @TC_SFCC2551_To_2557 @TC_SFCC-2698 @regression @PLP_performance @performance
  Scenario: PLP - Filters- Frontend
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters zipcode "79761"
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    Then "Pricing for" "12206" should be displayed in the top right corner below the header on the home page
    When the user hover an L1 category "Furniture" in the menu
    Then the user clicks on L3 category "Living Rooms" within an L2 category "Living Room Sets"
    And the user should see the filter section on the PLP
    Then the user selects the following categories and collapses the filters
      | Rental Payment Options:    |
      | Price Range:               |
      | Brand                      |
      | Category                   |
      | Availability               |
      | Condition                  |
      | Estimated Delivery Options |
      | Clearance                  |
    And the user selects "Rental Payment Options:" from the list of categories
    Then the user chooses "Weekly" from the Rental Payment Options category
    Then the user verifies that "per week" displayed on all product cards
    And the user selects "Estimated Delivery Options" from the list of categories
    And the user applying the "1-3 Days" filter should update the product list accordingly
    When the user clicks the CLEAR ALL button
    Then the list of products should reset to display all available items without any filters
    And the user selects option "Price Range:" as a filter
    When the user applies a price range filter from $30 to $40
    Then only products with prices between $30 and $40 should be displayed


  @STC_FCC7525_To_7527_and_7522  @regression @Search_per @performance

  Scenario Outline: Verify Product Suggestions based on entered keywords

    Then the user skips execution if the iteration status is "Yes" in sheet "<SheetName>" at row <RowNumber>

    Given the user opens URL

    And the user launches the RAC home page

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

    Examples:

      | SheetName             | RowNumber |

      | E2E-UserFunctionality | 11        |

      | E2E-UserFunctionality | 11        |

      | E2E-UserFunctionality | 11        |

      | E2E-UserFunctionality | 11        |

      | E2E-UserFunctionality | 11        |

      | E2E-UserFunctionality | 11        |

      | E2E-UserFunctionality | 11        |

      | E2E-UserFunctionality | 11        |

      | E2E-UserFunctionality | 11        |

      | E2E-UserFunctionality | 11        |

  @TC_SFCC5448 @regression @Homepage_Set1 @performance
  Scenario Outline: Verify Store Locator Visibility and Basic Functionality - <Page>
    When the user navigate to page "<Page>"
    Then the store Locator should be visible below the header on all pages
  #Bug - Find your store to see pricing
    And the "Find your store" text should be displayed on the Store Locator
    When the user clicks on Find your store button
    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
    And the user clicks on the X icon on store locator popup

    Examples:
      | Page                |
      | Home Page           |
      | How RAC Works       |
      | Contact Us          |
      | Furniture           |
      | Appliances          |

  @TC_SFCC5448 @regression @Homepage_Set1 @performance
  Scenario Outline: Verify Store Locator Visibility and Basic Functionality - <Page>
    When the user navigate to page "<Page>"
    Then the store Locator should be visible below the header on all pages
  #Bug - Find your store to see pricing
    And the "Find your store" text should be displayed on the Store Locator
    When the user clicks on Find your store button
    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
    And the user clicks on the X icon on store locator popup

    Examples:
      | Page                |
      | Home Page           |
      | How RAC Works       |
      | Contact Us          |
      | Furniture           |
      | Appliances          |

  @TC_SFCC5448 @regression @Homepage_Set1 @performance
  Scenario Outline: Verify Store Locator Visibility and Basic Functionality - <Page>
    When the user navigate to page "<Page>"
    Then the store Locator should be visible below the header on all pages
  #Bug - Find your store to see pricing
    And the "Find your store" text should be displayed on the Store Locator
    When the user clicks on Find your store button
    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
    And the user clicks on the X icon on store locator popup

    Examples:
      | Page                |
      | Home Page           |
      | How RAC Works       |
      | Contact Us          |
      | Furniture           |
      | Appliances          |

  @TC_SFCC5448 @regression @Homepage_Set1 @performance
  Scenario Outline: Verify Store Locator Visibility and Basic Functionality - <Page>
    When the user navigate to page "<Page>"
    Then the store Locator should be visible below the header on all pages
  #Bug - Find your store to see pricing
    And the "Find your store" text should be displayed on the Store Locator
    When the user clicks on Find your store button
    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
    And the user clicks on the X icon on store locator popup

    Examples:
      | Page                |
      | Home Page           |
      | How RAC Works       |
      | Contact Us          |
      | Furniture           |
      | Appliances          |
  @TC_SFCC5448 @regression @Homepage_Set1 @performance
  Scenario Outline: Verify Store Locator Visibility and Basic Functionality - <Page>
    When the user navigate to page "<Page>"
    Then the store Locator should be visible below the header on all pages
  #Bug - Find your store to see pricing
    And the "Find your store" text should be displayed on the Store Locator
    When the user clicks on Find your store button
    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
    And the user clicks on the X icon on store locator popup

    Examples:
      | Page                |
      | Home Page           |
      | How RAC Works       |
      | Contact Us          |
      | Furniture           |
      | Appliances          |

  @TC_SFCC5448 @regression @Homepage_Set1 @performance
  Scenario Outline: Verify Store Locator Visibility and Basic Functionality - <Page>
    When the user navigate to page "<Page>"
    Then the store Locator should be visible below the header on all pages
  #Bug - Find your store to see pricing
    And the "Find your store" text should be displayed on the Store Locator
    When the user clicks on Find your store button
    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
    And the user clicks on the X icon on store locator popup

    Examples:
      | Page                |
      | Home Page           |
      | How RAC Works       |
      | Contact Us          |
      | Furniture           |
      | Appliances          |

  @TC_SFCC5448 @regression @Homepage_Set1 @performance
  Scenario Outline: Verify Store Locator Visibility and Basic Functionality - <Page>
    When the user navigate to page "<Page>"
    Then the store Locator should be visible below the header on all pages
  #Bug - Find your store to see pricing
    And the "Find your store" text should be displayed on the Store Locator
    When the user clicks on Find your store button
    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
    And the user clicks on the X icon on store locator popup

    Examples:
      | Page                |
      | Home Page           |
      | How RAC Works       |
      | Contact Us          |
      | Furniture           |
      | Appliances          |

                #QAStory  #QAStory
#@SFCC47 @SFCC22 @SFCC549 @SFCC2874 @TC_SFCC5449 @smoke @regression @HomePage @HomePage_Header
#@SFCC549 @SFCC2874 @TC_SFCC5449 @regression @Homepage
  @regression @Homepage_Set1 @TC_SFCC5449 @performance
  Scenario Outline: Verify the store information pop up and error handling for invalid Zip codes
    When the user clicks on Find your store button
    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
    When the user enters zipcode "<Zipcode>"
    And the user clicks on Go button
    Then the Store Locator should process the valid Zip code without errors
  #1937
  #44128 - valid,   55001>Minnesota,   Wisconsin >53008
    Then the store information for the respective state "<State>" should be displayed
  #1930
    When the user clicks on the i info icon
    Then the pop-up should contain Store Info details
  #1931
    When the user click on GET DIRECTIONS link
    Then the user should be redirected to Google Maps with the store location

  #When the user clicks on the Back button on Store Info pop up
  #Then the user should return to the list of stores pop up
    And the user clicks on the X icon on store locator popup

    When the user clicks on Find your store button
    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
    When the user enters zipcode "<Zipcode>"
    And the user clicks on Go button
    Then the Store Locator should process the valid Zip code without errors

    When the user clicks on the i info icon
    And the user clicks on the X icon on store locator popup
    Then the store information pop up should be closed
    When the user clicks on Find your store button
    And the user enters zipcode "<InvalidZipCode>"
    And the user clicks on Go button
  #Bug below msg
    Then error message "Sorry, there are no locations in this area" should be displayed

    Examples:
      | Zipcode  |State| InvalidZipCode |
      | 44128    | OH  | 00000          |
     # | 02915    |
     # | 44128   |


#@SFCC47 @SFCC22 @SFCC549 @SFCC2874 @TC_SFCC5450 @smoke @regression @HomePage @HomePage_Header
#@SFCC549 @SFCC2874 @TC_SFCC5450 @regression @Homepage
  @regression @Homepage_Set1 @TC_SFCC5450
  Scenario: Verify the display of pricing for the entered Zip code and the confirmation pop-up when changing the store

    When the user clicks on Find your store button
    And the user enters zipcode "02915"
    And the user clicks on Go button
    And the user clicks on Make This My Store link
  #Then "Pricing for" "02915-4913" should be displayed in the top right corner below the header on the home page
    Then "Pricing for" "02915" should be displayed in the top right corner below the header on the home page

  #1936
    When the user clicks on Find your store button
  #And the user clicks on change option
    When the user enters zipcode "75019"
    And the user clicks on Go button
    Then change store error message is displayed on pop up
  #Then error message "Store changes may affect your current pricing, promotion, and inventory availability." should be displayed

#@SFCC47 @SFCC22 @SFCC549 @SFCC2874 @TC_SFCC5451_Blocked @smoke @regression @HomePage @HomePage_Header
  @TC_SFCC5451 @regression @Homepage_Set1
  Scenario: Verify the message changes for Minnesota and Wisconsin Zip codes and the behavior when using the location

    When the user clicks on Find your store button
  # Minnesota Zip code
    And the user enters zipcode "55101"
    And the user clicks on Go button
    Then minnesota store locator pop up error message displayed as "Rent-A-Center doesn't have store locations in Minnesota, but our affiliate company, Home Choice, does"
  # Wisconsin  Zip code
    And the user enters zipcode "53703"
    And the user clicks on Go button
    Then wisconsin store locator pop up error message displayed as "Rent-A-Center doesn't have store locations in Wisconsin, but our affiliate company, Get It Now, does"
  #When the user clicks on USE MY LOCATION button

  @SFCC1375 @TC_SFCC2551_To_2557 @TC_SFCC-2698 @regression @PLP_performance
  Scenario: PLP - Filters- Frontend
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters zipcode "79761"
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    Then "Pricing for" "12206" should be displayed in the top right corner below the header on the home page
    When the user hover an L1 category "Furniture" in the menu
    Then the user clicks on L3 category "Living Rooms" within an L2 category "Living Room Sets"
    And the user should see the filter section on the PLP
    Then the user selects the following categories and collapses the filters
      | Rental Payment Options:    |
      | Price Range:               |
      | Brand                      |
      | Category                   |
      | Availability               |
      | Condition                  |
      | Estimated Delivery Options |
      | Clearance                  |
    And the user selects "Rental Payment Options:" from the list of categories
    Then the user chooses "Weekly" from the Rental Payment Options category
    Then the user verifies that "per week" displayed on all product cards
    And the user selects "Estimated Delivery Options" from the list of categories
    And the user applying the "1-3 Days" filter should update the product list accordingly
    When the user clicks the CLEAR ALL button
    Then the list of products should reset to display all available items without any filters
    And the user selects option "Price Range:" as a filter
    When the user applies a price range filter from $30 to $40
    Then only products with prices between $30 and $40 should be displayed

  @CHECKOUT_performace @performance
  Scenario Outline: Generate Agreements for payment type <PaymentType> for product type <ProductType>

    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    And the user searches for product from "<SheetName>" at row <RowNumber>
    When the user clicks on Start Order button
    And the user clicks on "I don't have a mobile" link on pdp
 #Check out form
    When the user fills check out form from "<SheetName>" at row <RowNumber>
    And the user clicks on Continue button Paypal
 #new check out form
#    And the user enters ssn and dob
#    And the user selects agree and continue button
#
#
#    When the user clicks on the CONTINUE button on the Congrats popup
#    And the user selects pay schedule from "<SheetName>" at row <RowNumber>
#    And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
#    And  the user clicks on the Continue button on the Pay Schedule section guest user
#
#    And the user clicks on Continue To Payment button

    Examples:
      | SheetName                    | RowNumber | PaymentType | ProductType |
      | CheckOut-CheckOuts_Perf      | 0         | CreditCard  | RAC         |
      | CheckOut-CheckOuts_Perf      | 1         | CreditCard  | RAC         |
      | CheckOut-CheckOuts_Perf      | 2         | CreditCard  | RAC         |
      | CheckOut-CheckOuts_Perf      | 3         | CreditCard  | RAC         |
      | CheckOut-CheckOuts_Perf      | 4         | CreditCard  | RAC         |
      | CheckOut-CheckOuts_Perf      | 5         | CreditCard  | RAC         |
      | CheckOut-CheckOuts_Perf      | 6         | CreditCard  | RAC         |
      | CheckOut-CheckOuts_Perf      | 7         | CreditCard  | RAC         |
      | CheckOut-CheckOuts_Perf      | 8         | CreditCard  | RAC         |
      | CheckOut-CheckOuts_Perf      | 9         | CreditCard  | RAC         |