Feature: Footer_Footer Accordion - Info and tools - Frontend

  #@SFCC47 @SFCC92 @SFCC579 @TC_SFCC5423 @smoke @regression @HomePage @HomePage_Footer
  #@SFCC579 @TC_SFCC5423 @smoke @regression @Homepage
  @regression @Homepage_Set1 @TC_SFCC5423
  Scenario: Validate 'Info & Tools' Accordion Functionality

    Given the user opens URL
    And the user launches the RAC home page
    When the user scrolls to footer "Info & Tools" section
    #Then the "Info & Tools" accordion button should be collapsed when the page loads
    Then the Info and Tools accordion button should be collapsed when the page loads
    #When the user expands the "Info & Tools" accordion
    And the user clicks on "Info & Tools" link
    Then the "Info & Tools" accordion section should be expanded
    And the following Info and Tools accordion sections should be present:
      | Worry-Free Guarantee  |
      | Why Rent-To-Own       |
      | Contact Us            |
      | FAQs                  |
      | Site Map              |

    When the user clicks on accordion section links
    Then the info and tools redirected corresponding page
      | Link Name             | Corresponding Page      |
      | Worry-Free Guarantee  | rac-benefits            |
      | Why Rent-To-Own       | why-rent-to-own         |
     # | Site Map              | Site%20Map              |
     # | Contact Us          | contact-us            |
     # | FAQs                | faqs                  |
#    Then Info and Tools links redirected corresponding different tab
#      | Link Name           | Corresponding Page    |
#      | Contact Us          | contact-us            |
#      | FAQs                | faqs                  |





