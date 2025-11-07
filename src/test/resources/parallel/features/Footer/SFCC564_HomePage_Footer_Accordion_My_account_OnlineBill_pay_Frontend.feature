Feature: Footer_Footer Accordion - My account - Online Bill pay- frontend


  #@SFCC47 @SFCC68 @SFCC564 @TC_SFCC5427 @smoke @regression @HomePage @HomePage_Footer
  #@SFCC564 @TC_SFCC5427 @smoke @regression @Homepage
  @TC_SFCC5427 @regression @Homepage_Set1
  Scenario: Validate My Account - Online Bill Pay Accordion Functionality

    Given the user opens URL
    And the user launches the RAC home page
    When the user scrolls to footer "My Account - Online Bill Pay" section
    #Then the "Info & Tools" accordion button should be collapsed when the page loads
    Then "My Account - Online Bill Pay" accordion button should be collapsed when the page loads
    #When the user expands the "Info & Tools" accordion
    And the user clicks on "My Account - Online Bill Pay" link
    Then the "My Account - Online Bill Pay" accordion section should be expanded
    And the following accordion sections should be present:
      | Pay Your Bill Online  |
      | Enroll in AutoPay     |
      | Update Your Info      |
      | How It Works          |
      | FAQs                  |
#      | Worry-Free Guarantee  |
#      | Why Rent-To-Own       |
#      | Contact Us            |
#      | FAQs                  |
#      | Site Map              |

    When the user clicks on accordion section links
    #Then the About Rent-A-Center accordion section links redirected corresponding page
    Then the My Account - Online Bill Pay accordion section links redirected corresponding page
    #Then the user redirected corresponding page
      | Link Name             | Corresponding Page    |
      | Pay Your Bill Online  | payonline-getstarted  |
      | Enroll in AutoPay     | payonline-getstarted  |
      | Update Your Info      | payonline-getstarted  |
      | How It Works          | faqs                  |
      | FAQs                  | faqs                  |
     # | Worry-Free Guarantee  | rent-a-center-benefits  |
     # | Why Rent-To-Own       | why-rent-to-own         |
     # | Contact Us            | contact-us              |
     # | FAQs                  | faqs                    |
     # | Site Map              | Site%20Map              |


