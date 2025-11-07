Feature: PaymentGateWay_Payment method PayPal Venmo - Backend

#  Background:
#    Given the user opens URL
#    And the user launches the RAC home page

  #@SFCC374 @SFCC6153 @TC_SFCC4197 @regression @PaymentGateWay
  Scenario Outline: Verify paypal payment type

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
    Then Brand Name is displayed as a hyperlink from "<SheetName>" at row <RowNumber>

    When the user clicks on Start Order button

    Then user redirected to payfone or prefill page
    And the user clicks on "I don't have a mobile" link on pdp
    #Check out form
    When the user fills check out form from "<SheetName>" at row <RowNumber>
    And the user clicks on Continue button Paypal

    #new check out form
    And the user enters ssn and dob
    And the user selects agree and continue button

    #####
    When the user clicks on the CONTINUE button on the Congrats popup
    #And the user selects pay schedule "Weekly"
    And the user selects pay schedule from "<SheetName>" at row <RowNumber>
    And the user selects paid next date "08/25/2025"
    And the user clicks on the CONTINUE button on the Pay Schedule section
    #When the user selects choose optional benefits radio buttons off
    #And the user clicks on Continue To Payment button

    #Paypal
    And the user clicks on PayPal payment method

     #And the user saves Paypal credentials from "<SheetName>" at row <RowNumber>
    And the user saves Paypal credentials

    #And the user clicks Submit And Continue To E Sign Button
    #Then the order confirmation success message should be displayed


    Examples:
      | SheetName          | RowNumber |
      | Payments-paypal    | 0         |
      | Payments-paypal    | 1         |
       #| Payments-paypal    | 2         |
      # | Payments-paypal    | 3         |
     # | Payments-paypal    | 4         |