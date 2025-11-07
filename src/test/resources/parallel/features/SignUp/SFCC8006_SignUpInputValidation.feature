Feature: Sign-Up Input Validation

  Background:
    Given the user opens URL
    And the user launches the RAC home page
    When the user hover an L1 category "Deals" in the menu
    When the user clicks on L3 category "Shop All Deals" within an L2 category "Sign Up for Deals"

  @TC_SFCC8006_1 @regression
  Scenario: Verify UI elements in signup model
    Then signup text is displayed
    Then required text and secure form is displayed
    Then email address text is displayed with blank input field
    Then mobile number text is displayed with blank input field
    Then check box for agree to receive deals text is displayed
    Then disabled submit button is displayed
    Then 3 links are displayed containing terms and conditions , information,privacy policy

  @TC_SFCC8006_2 @regression
  Scenario Outline: Validate email and  mobile number
    When the user enter a "<email>"  in email input field
    And  the user enter a "<mobile>" in mobile number input field
    And the user clicks on agree to receive deals
    And  the user click the "Submit" button
    Then the user gets "<message>"

    Examples:
      | email   | mobile  | message      |
      | invalid | valid   | email_error  |
      | valid   | invalid | mobile_error |
      | valid   | valid   | success      |

  @TC_SFCC8006_3 @regression
  Scenario: Validate links in signup page
    And the user clicks on "SMS Terms and Conditions" link
    Then the link redirects the user to the "termsofservice" page
    And the user clicks on "Privacy Policy" link
    Then the link redirects the user to the "privacy-policy" page
    Then the user redirected corresponding page on same browser tab and stayed
      | Link Name  | Corresponding Page |
      | click here | contact-us         |






