Feature: Header other Frontend

  Background:
    Given the user opens URL
    And the user launches the RAC home page

  #SFCC543
  @TC_SFCC-5413_To_5414  @regression @HomePage_set3
  Scenario Outline: Verify Header Functionality Logo, Sign-in, and Cart Icon
    When the user hover an L1 category "Furniture" in the menu
    And the user clicks on L2 subcategory "Living Rooms"
    Then the user is redirected to the "Living Rooms" category page
    When the user click on the Logo in the header
    Then the feature banner carousel should be visible on the top of homepage
#    When the user click on accept cookie popup
    When the user clicks on Sign In button
    And the user fills the sign in form using data from "<SheetName>" at row <RowNumber>
    And the user clicks on Sign In button in the My Account dialog
    #When the user sign in sheet "<SheetName>" at row <RowNumber>
    Then verify the users first name "Klopin" is displayed on header
    And the user searches for product from "<SheetName>" at row <RowNumber>
    #Then the "Washers and Dryers" PDP should load successfully
    When the user clicks on add to cart button
    And the user clicks on item cart section close button
    And the user searches for product from "<SheetName>" at row <RowNumber1>
    #When the user pick a "200022759" product
    #Then the "Acer" PDP should load successfully
    When the user clicks on add to cart button
    And the user clicks on item cart section close button
    Then the cart icon displays the number of items "2"
    When the user click on cart link button
    Then the user is redirected to appropriate proper page "cart"

    Examples:
      | SheetName    | RowNumber | RowNumber1 |
      | E2E-HomePage | 0         | 1         |