Feature: HomePage_Home page Get started section

#  Background:
#    Given the user opens URL
#    And the user launches the RAC home page
                     #@TC_SFCC2300
  #@SFCC13 @SFCC905 @SFCC615 @TC_SFCC5433 @smoke @regression @HomePage
  #@SFCC615 @TC_SFCC5433 @regression @Homepage
  @TC_SFCC5433_1 @regression @Homepage_Set1
  Scenario: Verify home page Get started Sign Up section

    Given the user opens URL
    And the user launches the RAC home page
    #2300
    When the user scrolls to "Get started for Just $10" section
    Then section "Get Started for Just $10" is visible and allowing to sign up
    #2301,2306
    When the user clicks on the CTA Email option
    #And the user enters valid email "test121@racit.com"
    And the user enters valid email
    And the user clicks on Sign Up button
    #Then success message "Thank you for signing up!" displayed
    #Then success message "You're in! Keep an eye out for great deals headed your way!" displayed
    Then success message "Keep an eye out for great deals headed your way!" displayed

    #2302,2306
    When the user clicks on the CTA Text option
    And the user enters valid phone "9876534673"
    And the user clicks on Sign Up button
    #Then success message "Thank you for signing up!" displayed
    Then success message "Keep an eye out for great deals headed your way!" displayed
    #2303
    When the user clicks on the CTA Text option
    And the user clicks on "SMS Terms and Conditions." link
    Then the link redirects the user to the "termsofservice" page
    #2304
    And the user clicks on "Privacy Policy." link
    Then the link redirects the user to the "privacy-policy" page
    #2305
    And the user clicks on "click here" link
    Then the link redirects the user to the "contact-us" page