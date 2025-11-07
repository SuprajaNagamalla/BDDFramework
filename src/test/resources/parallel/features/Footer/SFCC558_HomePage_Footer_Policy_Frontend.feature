Feature: Footer_Footer - Policy - Frontend

  #@SFCC47 @SFCC35 @SFCC558 @TC_SFCC5419 @smoke @regression @HomePage @HomePage_Footer
  #@SFCC558 @TC_SFCC5419_and_5421 @smoke @regression @Homepage
  @TC_SFCC5419_and_5421 @regression @Homepage_Set1
  Scenario: Ensures all policy links in the footer are visible and functional

    Given the user opens URL
    And the user launches the RAC home page
    When the user scrolls to footer "Privacy Policy" section
    #And the user clicks on "SMS Terms and Conditions." link
    When the user clicks on footer section policy links
    Then the user is redirected corresponding footer section page
      | Link Name                                      | Corresponding Page                        |
      | Privacy Policy                                 | privacy-policy                            |
      | ePay Terms                                     | termsandconditions                        |
      | California Transparency In Supply Chains Act   | california-transparency-in-supply-chain-act |
      | SSM/MMS Text Terms                             | termsofservice                            |
      | Web Accessibility                              | accessibility                             |
      | Your Privacy Choices                           | index                                     |
      | California Privacy Policy                      | CAprivacy                                 |