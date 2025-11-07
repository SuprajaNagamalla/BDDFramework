Feature: Footer_Footer Get started section

#  Background:
#    Given the user opens URL
#    And the user launches the RAC home page

  #@SFCC47 @SFCC492 @SFCC615 @TC_SFCC5433_2 @smoke @regression @HomePage @HomePage_Footer
  @TC_SFCC5433_2 @regression @Homepage_Set1
  Scenario: Verify footer Get started Sign Up section

    Given the user opens URL
    And the user launches the RAC home page
    #2300
    When the user scrolls to footer "Get started for Just $10" section
    #Then section "Get Started for Just $10" is visible and allowing to sign up
    Then section "Started for Just $10" is visible and allowing to sign up
    #2301,2306
    #When the user clicks on the CTA Email option
    When the user clicks on the footer CTA Email option
    And the user enters valid email
    And the user clicks on Sign Up button
    #Then success message "Thank you for signing up!" displayed
    Then success message "Keep an eye out for great deals headed your way!" displayed
    #2302,2306
    #When the user clicks on the CTA Text option
    When the user clicks on the footer CTA Text option
    And the user enters valid phone "9876534672"
    And the user clicks on Sign Up button
    #Then success message "Thank you for signing up!" displayed
    Then success message "Keep an eye out for great deals headed your way!" displayed
    #2303
    When the user clicks on the footer CTA Text option
    And the user clicks on "SMS Terms and Conditions." link
    Then the link redirects the user to the "termsofservice" page
    #2304
    And the user clicks on "Privacy Policy." link
    Then the link redirects the user to the "privacy-policy" page
    #2305
    And the user clicks on "click here" link
    Then the link redirects the user to the "contact-us" page