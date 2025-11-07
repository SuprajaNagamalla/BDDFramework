Feature: Footer_Footer Accordion - About Rent-A - Center- Frontend

  #@SFCC47 @SFCC34 @SFCC555 @TC_SFCC5416 @TC_SFCC5418 @smoke @regression @Homepage @HomePage_Footer
  #@SFCC555 @TC_SFCC5416_and_SFCC5418 @smoke @regression @Homepage
  @TC_SFCC5416_and_SFCC5418 @regression @Homepage_Set1
  Scenario: Ensures the About Rent-A-Center accordion is visible, expandable, and functional

    Given the user opens URL
    And the user launches the RAC home page
    When the user scrolls to footer "About Rent-A-Center" section
    #Then the "About Rent-A-Center" accordion button should be collapsed when the page loads
    Then the About Rent A Center accordion button should be collapsed when the page loads
    #When the user expands the "Info & Tools" accordion
    And the user clicks on "About Rent-A-Center" link
    Then the "About Rent-A-Center" accordion section should be expanded
    And the following About Rent A Centre accordion sections should be present:
      | Company Information |
      | Rent-A-Center Blog  |
      | Testimonials        |
      | RAC Cares           |
      | Store Locations     |
      | Investors           |
    #Then the info and tools redirected corresponding page
    #Then the user redirected corresponding page
    When the user clicks on accordion "About Rent-A-Center" section links
    Then the About Rent-A-Center accordion section links redirected corresponding page
    #Then the user redirected corresponding page
      | Link Name           | Corresponding Page    |
     # | Company Information | upbound               |
     # | Rent-A-Center Blog  | blog.rentacenter      |
      | Testimonials        | rac-testimonials      |
      | RAC Cares           | rac-cares             |
     # | Store Locations     | locations.rentacenter |
     #| Investors           | investor              |

  Then the user redirected corresponding page
  #Then the About Rent-A-Center accordion section links redirected corresponding page
    | Link Name           | Corresponding Page    |
    | Store Locations     | locations.rentacenter |
    | Investors           | investor              |

    And the user clicks on "About Rent-A-Center" link

   #Then the "About Rent-A-Center" accordion button should be collapsed when the page loads
    #Then the About Rent A Center accordion button should be collapsed when the page loads
    #Then the "About Rent-A-Center" accordion section should be expanded


