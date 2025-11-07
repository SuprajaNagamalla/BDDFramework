Feature: Payment method Apple Pay

#  Background:
#    Given the user opens URL
#    And the user launches the RAC home page

  #@SFCC374 @SFCC6153 @TC_SFCC4199_All @smoke @regression @PaymentsAll @PaymentGateWay
  @TC_SFCC4349_4360_4364_4365_ios @regression_ios @PaymentGateway
  Scenario Outline: Mobile ios - checkout flow - verify apple pay payment success

   #Then skip execution if the iteration status is "No" in workbook "Payments" on sheet "<SheetName>" at row <RowNumber>
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    And the user searches for product from "<SheetName>" at row <RowNumber>
    #Then Brand Name "<Brand Name>" is displayed as a hyperlink
    #Then Brand Name is displayed as a hyperlink from "<SheetName>" at row <RowNumber>
    When the user clicks on Start Order button
    #Then user redirected to payfone or prefill page
    And the user clicks on "I don't have a mobile" link on pdp

    #Check out form
    When the user fills check out form from "<SheetName>" at row <RowNumber>
    And the user clicks on Continue button Paypal

    #new check out form - IDV page
    And the user enters ssn and dob
    And the user selects agree and continue button

    When the user clicks on the CONTINUE button on the Congrats popup

    #And the user selects pay schedule "Weekly"
    And the user selects pay schedule from "<SheetName>" at row <RowNumber>
    #And the user selects paid next date "05/12/2025"
    And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
    #And the user clicks on the CONTINUE button on the Pay Schedule section
    And the user clicks on the Continue button on the Pay Schedule section guest user
    #When the user selects choose optional benefits radio buttons off
    And the user clicks on Continue To Payment button
    #CC
    #And the user clicks on Credit Card payment method
    And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
   # When the user selects Save this payment method check box
    Then the user selects I agree checkbox under Apple pay section
    And the user clicks Continue button under apple payment section

#    And the user clicks Submit And Continue To E Sign Button "<PaymentType>"
#    When the user selects docusign check box I sign
#    Then the user verifies continue button is displayed under docusign section

    Examples:
      | SheetName               | RowNumber |
      |  Payments-ApplePay     | 0         |