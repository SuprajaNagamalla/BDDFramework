Feature: Product attached flow conditional Approval- Frontend

  Background:
    Given the user opens URL
    And the user launches the RAC home page

#  #@SFCC374 @SFCC3486
#  @TC_SFCC3323_To_3324 @regression @PaymentGateway
#  Scenario Outline: clicking on 'Reserve My Product' button, Customer will continue on Reservation page
#   #Selct store
#    Then the user skips execution if the iteration status is "Yes" in sheet "<SheetName>" at row <RowNumber>
#    When the user sign in sheet "<SheetName>" at row <RowNumber>
#    When the user clicks on Find your store button
#    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
#    And the user clicks on Go button
#    And the user clicks on Make This My Store link
#
#    #Navigate to PDP
#    And the user searches for product from "<SheetName>" at row <RowNumber>
##    When the user click on accept cookie popup
#    When the user clicks on add to cart button
#    And the user clicks on item cart section close button
##    Then the cart icon displays the number of items "1"
#    When the user click on cart link button
#    When the user clicks on start order button
#    And the user clicks on "I don't have a mobile" link on pdp
#
#    #Check out form
#    When the user fills check out form from "<SheetName>" at row <RowNumber>
#    And the user clicks on Continue button Paypal
#
#    #new check out form
#    And the user enters ssn and dob
#    And the user selects agree and continue button
#
#    Then verify reserve my product section is displayed
#    When the user clicks on the Congrats popup from "<SheetName>" at row <RowNumber>
#    Then verify approved section is displayed from "<SheetName>" at row <RowNumber>
#
#    Examples:
#      | SheetName     | RowNumber |
#      | E2E-Payments | 0         |

    #@SFCC374 @SFCC3486
  @TC_SFCC3325 @regression @PaymentGateway
  Scenario Outline: Upon clicking on 'Just Submit contact info' button web lead should submit
   #Selct store
    Then the user skips execution if the iteration status is "Yes" in sheet "<SheetName>" at row <RowNumber>
    #When the user click on accept cookie popup
    When the user sign in sheet "<SheetName>" at row <RowNumber>
    When the user clicks on Find your store button
    And the user enters Zipcode from "<SheetName>" at row <RowNumber>
    And the user clicks on Go button
    And the user clicks on Make This My Store link
 
    #Navigate to PDP
    And the user searches for product from "<SheetName>" at row <RowNumber>
    When the user clicks on Start Order button
    #Then user redirected to payfone or prefill page
    And the user clicks on "I don't have a mobile" link on pdp
 
    #Check out form
    When the user fills check out form from "<SheetName>" at row <RowNumber>
    And the user clicks on Continue button Paypal
    And the user enters ssn and dob
    And the user selects agree and continue button

    Then verify reserve my product section is displayed
    When the user clicks on submit my contact info link
    Then Verify order confirm message "We have received your order and emailed you a copy of your receipt." is displayed

    Examples:
      | SheetName    | RowNumber |
      | E2E-Payments | 1         |