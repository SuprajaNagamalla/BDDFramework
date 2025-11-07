Feature: Validate Cart page data features

  Background:
    Given the user opens URL
    And the user launches the RAC home page

  @CartPageValidations @regression @TC_SFCC-3373_SFCC-3374_SFCC4397
  Scenario Outline: Verify Cart Page UI component for Test Scenario <TCDescription>
    * I am reading data from the workbook "CartWorkBook" sheet "<SheetName>"
    Then I am reading the test data <RowNumber> from Cart Workbook sheet name "<SheetName>" for Test Scenario of "<TCDescription>"

    Examples:
      | SheetName  | RowNumber | TCDescription|
      | CartSheet  | 0         |In Stock Product |
      | CartSheet  | 1         |Special Order Product|
      | CartSheet  | 2         |Bundle Product|
      | CartSheet  | 3         |Validate Remove item from cart|
      | CartSheet  | 4         |Validate Save For Later Page and Remove item from saved item|
      | CartSheet  | 5         |Update Store value in cart page for guest user|
      | CartSheet  | 6         |Add In stock and Special order|
      | CartSheet  | 7         |Add In stock and pre-bundle product|
      | CartSheet  | 8         |Guest and Signed in User           |
      | CartSheet  | 9         |Guest Saved Item and Signed in User|
      | CartSheet  | 10        |Guest and Signed in User has same item in cart|
      | CartSheet  | 11        |Guest and Signin user store no matches|
      | CartSheet  | 12        |Multiple Special Products|
      | CartSheet  | 13        |EA Products|
      | CartSheet  | 14        |Benefit plus Product               |
      | CartSheet  | 15        |Validate Bundle empty wishlist data remove item from wishlist|





