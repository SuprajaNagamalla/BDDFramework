Feature: HomePage_Store Locator New customer Frontend

  Background:
    Given the user opens URL
    And the user launches the RAC home page

#  @TC1926_To_1937 @regression
#  Scenario Outline: Verify home page store locator
#
#
#    #1926 --*
#    Then the store Locator should be visible below the header on all pages
#    #Pending
#    #Open various pages of the website
#    #The Store Locator should be visible below the header on all pages(top left corner)
#
#    #1927
#    And the "Find your store" text should be displayed on the Store Locator
#     #1928
#    When the user clicks on Find your store button
#    Then the Store locator pop-up should contain the options "Enter Zip Code" and "Use My Location"
#
#    #1929
#    #When the user enters zipcode "02915"
#    When the user enters zipcode "<Zipcode>"
#    And the user clicks on Go button
#    Then the Store Locator should process the valid Zip code without errors
#    #1937
#    #44128 - valid,   55001>Minnesota,   Wisconsin >53008
#    Then the store information for the respective state "<State>" should be displayed
#
#    #1930
#    When the user clicks on the i info icon
#    Then the pop-up should contain Store Info details
#
#    #1931
#    When the user click on GET DIRECTIONS link
#    Then the user should be redirected to Google Maps with the store location
#
#    #1932
#    When the user clicks on the Back button on Store Info pop up
#    Then the user should return to the list of stores pop up
#
#    #1933
#    When the user clicks on the i info icon
#    And the user clicks on the X icon on store locator popup
#    Then the store information pop up should be closed
#
#    #1935
#    #Bug - if giving 44128
#    When the user clicks on Find your store button
#    And the user enters zipcode "02915"
#    And the user clicks on Go button
#    And the user clicks on Make This My Store link
#    Then "Pricing for" "02915-4913" should be displayed in the top right corner below the header on the home page
#
#    #1936
#    When the user clicks on Find your store button
#    And the user clicks on change option
#    When the user enters zipcode "75019"
#    And the user clicks on Go button
#    #Veify msg pending
#    And the user clicks on Make This My Store link
#    #Bug
#    #Then "Pricing for" "75019" should be displayed in the top right corner below the header on the home page
#    Then "Pricing for" "75225" should be displayed in the top right corner below the header on the home page
#
#
#    Examples:
#    | Zipcode  |State|
#    | 44128    | OH  |
#   # | 02915    |
#   # | 44128   |
#
#  #TC deleted
#  @SFCC1934
#  Scenario Outline: Verify home page store locator for invalid zipcodes
#
#    #1934 -- Bug
#    When the user clicks on Find your store button
#    When the user enters invalid zipcode "<Zipcode>"
#    And the user clicks on Go button
#    #Bug below msg
#    Then error message "No store was found within 30 miles of your location." should be displayed
#
#    Examples:
#      | Zipcode |
#      | 00000   |
#      | aaaaa   |

#--------------------------------------------------------------------------

                  #QAStory  #QAStory
  #@SFCC47 @SFCC22 @SFCC549 @SFCC2874 @TC_SFCC5448 @regression @Homepage
  #@SFCC549 @SFCC2874 @TC_SFCC5448 @regression @Homepage
  @TC_SFCC5448 @regression @Homepage_Set1
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
  @regression @Homepage_Set1 @TC_SFCC5449
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

  #@SFCC549
  @TC_SFCC5422 @regression @Homepage_Set3
  Scenario: Validate store selection for signed-in users
    Then the user scrolls to "Store Locator" footer section
    Then the user clicks on footer section store locator
    And the user enters zipcode "79761"
    And the user clicks on Go button
    And the user clicks the Make This My Store link in the store locator

  #@SFCC549
  @TC_SFCC5422 @regression @Homepage_Set3
  Scenario: Validate store selection for guest users and validate invalid ZIP code scenario
    #SFCC-5422
    When the user clicks on Find your store button
    And the user enters zipcode "02915"
    And the user clicks on Go button
    And the user clicks the Make This My Store link in the store locator
    Then "Pricing for" "02915" should be displayed in the top right corner below the header on the home page
    When the user clicks on an item from the popular items section
    Then verify user is redirected to the pdp page
    When the user clicks on Start Order button
    And the user clicks on "I don't have a mobile" link on pdp
    #invalid zip code
    When the user clicks on Find your store button
    And the user enters zipcode "6543"
    And the user clicks on Go button
    Then store locator pop up error message displayed as "Sorry, there are no locations in this area"