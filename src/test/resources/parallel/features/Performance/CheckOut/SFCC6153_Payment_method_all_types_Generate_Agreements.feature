Feature: CheckOut_PaymentgateWay_Verify Agreements for all payment types

#  Background:
#    Given the user opens URL
#    And the user launches the RAC home page

  @SFCC374 @SFCC6153 @TC_SFCC4199_Agreements_AllPayments  @PaymentGateWay
  Scenario Outline: Generate Agreements for payment types <PaymentType> for product type <ProductType>

    Then the user skips execution if the iteration status is "No" in sheet "<SheetName>" at row <RowNumber>
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link
    And the user searches for product from "<SheetName>" at row <RowNumber>
    When the user clicks on Start Order button
    And the user clicks on "I don't have a mobile" link on pdp
    #Check out form
    When the user fills check out form from "<SheetName>" at row <RowNumber>
    And the user clicks on Continue button Paypal
    #new check out form
    And the user enters ssn and dob
    And the user selects agree and continue button
    When the user clicks on the CONTINUE button on the Congrats popup
    #And the user selects pay schedule "Weekly"
    And the user selects pay schedule from "<SheetName>" at row <RowNumber>
    #And the user selects paid next date "09/25/2025"
    And the user selects paid next date as per pay schedule from "<SheetName>" at row <RowNumber>
    And the user clicks on the CONTINUE button on the Pay Schedule section
    #When the user selects choose optional benefits radio buttons off
    And the user clicks on Continue To Payment button
    And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
    When the user selects Save this payment method check box
    And the user selects I agree checkbox
    And the user clicks Submit And Continue To E Sign Button

        #Skip sign flow
    Then the user clicks on Sign Agreement Later on docu sign page
    Then the order confirmation success message should be displayed
    And agreement number is generated

#    #DocuSign flow
#    When the user selects docusign check box I sign
#    When the user clicks continue button under docusign section


    Examples:
      | SheetName               | RowNumber |PaymentType| ProductType|
      | Agreements-PaymentTypes       | 0         |CreditCard |RAC   |
#      | Agreements-PaymentTypes       | 0         |CreditCard |EA   |
#      | Payments-AllTypes       | 1         |paypal     |RAC   |
#      | Payments-AllTypes       | 2         |venmo      |RAC   |