
Feature: PaymentGateway-Reservation Flow

	#@Reservation  @ReservationWithPaymentAmountZero
  # valid for RAC product also
  @TC_SFCC4182_4184_4186_4187_4074 @regression @PaymentGateway
  Scenario Outline: Guest user - EA product - Validate which payment methods are available and functional when the total payment amount is $0 in the reservation and claim offer flow

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

    And the user clicks on Continue button WOF
    #And the user clicks on Continue button Paypal

    #new check out form
    And the user enters ssn and dob
    And the user selects agree and continue button

    #Then the user gets correlation id
    #Then the user gets correlation id "<PaymentType>"

    #When the User clicks on the Reserve My Product button on Reservation Popup
    When the user clicks RESERVE MY PRODUCT button if displayed on almost done dialog

    When the user apply text enabled promo code and verify Total Due Amount from "<SheetName>" at row <RowNumber>
    Then the user verify list of All payment methods available on Promo code from "<SheetName>" at row <RowNumber>

    And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
    And the user selects I agree checkbox
    And the user clicks Submit And Continue To E Sign Button
    And Reservation number is generated

    Examples:
      | SheetName               | RowNumber | PaymentType |
      | PGW-Reservation | 3         | CreditCard          |


    #@Reservation  @SavedPaymentCheckReservationWithAmountZero
  @TC_SFCC4183_4185 @regression @PaymentGateway
  Scenario Outline: Signed user - EA Product - Validate which payment methods are available and functional when the total payment amount is $0
  in the reservation and claim offer flow
    Given the user opens URL
    And the user launches the RAC home page
    When the user clicks on Sign In button
    And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
	#And the user fills the sign in form
    And the user clicks on Sign In button in the My Account dialog
    Then the user able to click on RAC logo
    When the user searches for product from "<SheetName>" at row <RowNumber>

    When the user clicks on Start Order button
    #Check out form

    And the user clicks on Continue button WOF
       #Then the user gets correlation id
    Then the user gets correlation id "<PaymentType>"

    #When the User clicks on the Reserve My Product button on Reservation Popup
    When the user clicks RESERVE MY PRODUCT button if displayed on almost done dialog

    #Removed - api failing so changed defect
    #When the user clicks on the Congrats popup from "<SheetName>" at row <RowNumber>

    When the user apply text enabled promo code and verify Total Due Amount from "<SheetName>" at row <RowNumber>
   # Then the user verify list of All payment methods available on Promo code from "<SheetName>" at row <RowNumber>
    Then the user verify list of All payment methods available on Desired Promo code from "<SheetName>" at row <RowNumber>
    And the user saves the payment type credentials from sheet "<SheetName>" at row <RowNumber>
    And the user selects I agree checkbox
    And the user clicks Submit And Continue To E Sign Button

    Then the user gets correlation id "<PaymentType>"

    And Reservation number is generated

    Examples:
      | SheetName               | RowNumber | PaymentType |
      | PGW-Reservation         | 3         | CreditCard  |