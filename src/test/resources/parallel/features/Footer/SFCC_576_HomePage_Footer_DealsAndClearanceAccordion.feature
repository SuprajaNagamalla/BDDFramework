Feature: Homepage_DealsAndClearance_ShopByCategory_MediaLinks

  Background:
    Given the user opens URL
    And the user launches the RAC home page

 # @SFCC576 @smoke @regression @Homepage_set2 @footer @SFCC573
  #QAStory #QAStory
    @SFCC576 @regression @Homepage_set2
  Scenario: Verify Deals and Clearance Accordion in home page Footer
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
#      | Rent to Own Smartphones| smartphones                |
      | Previously Rented Products| pre-rented     |
#      | Product Catalog            | products           |


  #@SFCC893 @SFCC2909 @smoke @regression @Homepage_set2 @ShopbyCategory
  @SFCC893 @SFCC2909 @regression @Homepage_set2
  Scenario: Verify shop by category section on home page
    When the user scrolls to Shop By Category section
    And    the user clicks on "Shop All" link
    Then  the user should be redirected to Super PLP
    And   the user navigate to previous page
    Then the user redirected corresponding page from shop by category section
      | Category Name                   | Corresponding Page              |
      | Deals                           | deals                           |
#      | Home Theater and Stereo Systems | home-theater-and-stereo-systems |
      | My Items                        | my-items                        |
#      | Living Rooms                    | living-rooms                    |
#      | Appliances                      | appliances                      |
#      | Virtual Reality                 | virtual-reality                 |
      | Living Room Sets                | living-room-sets                |
      | Furniture                       | furniture                       |
      | Recliners & Accent Chairs       | recliners-and-accent-chairs     |
#      | Heating                         | heating                         |
#      | Arcade                          | arcade                          |
      | Clearance                       | clearance                       |
#      | Tablets                         | tablets                         |
#      | Desktops                         | desktops                         |




  @SFCC5441 @regression @Homepage_set2 @ShopbyCategory
  Scenario: Verify shop by category section on home page
    When the user scrolls to Shop By Category section
    Then verify the number of categories "10" displayed
    Then verify the category names  displayed in shop by category section
      | Deals                           |
#      | Home Theater and Stereo Systems |
      | My Items                        |
#      | Living Rooms                    |
#      | Appliances                      |
#      | Virtual Reality                 |
      | Living Room Sets                |
      | Furniture                       |
      | Recliners & Accent Chairs       |
#      | Heating                         |
#      | Arcade                          |
      | Clearance                       |
#      | Tablets                         |
#      | Desktops                         |

    When the user clicks on Find your store button
    And  the user enters zipcode "79761"
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    When the user scrolls to Shop By Category section
    Then verify the number of categories "10" displayed
    Then verify the category names  displayed in shop by category section
      | Deals                           |
#      | Home Theater and Stereo Systems |
      | My Items                        |
#      | Living Rooms                    |
#      | Appliances                      |
#      | Virtual Reality                 |
      | Living Room Sets                |
      | Furniture                       |
      | Recliners & Accent Chairs       |
#      | Heating                         |
#      | Arcade                          |
      | Clearance                       |
#      | Tablets                         |
#      | Desktops                         |


  #@SFCC-13 @SFCC-1469  @TC_19 @SFCC-488 @SFCC47 @SFCC490 @TC20 @SFCC612 @smoke @regression @Homepage_set2 @Homepage_set2_footer
  @SFCC549 @regression @Homepage_set2
  Scenario: Verify Footer links on home page
    Then verify various components are displayed on home page
    When the user scrolls to social media links section in Footer
    Then the user redirected corresponding page on same browser tab for media links
      | Link Name                 | Corresponding Page |
      | facebook                    | facebook                     |
      | instagram                  | instagram                    |
      | tiktok                          | tiktok                            |
      | x                                   | x                                     |
      | youtube                     | youtube                       |
      | blog                             | blog                               |
      | racmobile                  | racmobile                    |
      | apple                           | apple                             |
      | privacy-policy           | privacy-policy             |
#    |  Store Locator             |   find-a-store               |


