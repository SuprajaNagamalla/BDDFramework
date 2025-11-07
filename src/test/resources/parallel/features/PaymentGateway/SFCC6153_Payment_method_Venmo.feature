Feature: PaymentGateWay_Payment method Venmo

  Background:
    Given the user opens URL
    And the user launches the RAC home page

  #@SFCC374 @SFCC6153 @TC_SFCC4198 @smoke @regression @PaymentGateWay
  Scenario Outline: Verify Venmo payment type

    #Then skip execution if the iteration status is "No" in workbook "Payments" on sheet "<SheetName>" at row <RowNumber>
    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>

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
    And the user selects pay schedule "Weekly"
    And the user selects pay schedule from "<SheetName>" at row <RowNumber>
    And the user selects paid next date "08/25/2025"
    And the user clicks on the CONTINUE button on the Pay Schedule section

    #When the user selects choose optional benefits radio buttons off
    #And the user clicks on Continue To Payment button

    #Venmo
    And the user clicks on Venmo payment method
    #And the user saves Venmo credentials from "<SheetName>" at row <RowNumber>
    And the user saves Venmo credentials
    Then venmo payment details success message displayed
    #When the user selects I agree checkbox
    #And the user clicks Submit And Continue To E Sign Button
    #Then the order confirmation success message should be displayed


    Examples:
      | SheetName  | RowNumber |
      | Payments-venmo    | 0         |
       | Payments-venmo    | 1         |
       #| Payments-venmo    | 2         |
      # | Payments-venmo    | 3         |
     # | Payments-venmo    | 4         |