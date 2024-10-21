Feature: Creating a new checking account

  Scenario: Positive - Create a standard individual checking account
    Given the user logged in as "angie5jolie@gmail.com" "helloKitty55"
    When the user clicks "Submit" while creating a new checking account with the following data
      | accountType       | accountOwnership | accountName       | initialDeposit |
      | Standard Checking | Individual       | Angelita Checking | 100000.00      |
    Then the user should see the green "Successfully created" message
    And the user should see newly added account card with following details
      | accountName       | accountType       | accountOwnership | accountNumber | interestRate | balance   |
      | Angelita Checking | Standard Checking | Individual       | 486139597     | 0.0%         | 100000.00 |
    And the user should see the following transactions details
      | date   | category | description               | amount    | balance   |
      | Sep 22 | Income   | 845334343 (DPT) - Deposit | 100000.00 | 100000.00 |

  Scenario Outline: Negative - creating a checking account with empty field or incorrect deposit amount format
    Given the user logged in as "angie5jolie@gmail.com" "helloKitty55"
    When the user clicks "Submit" while creating a new checking account with the following data
      | accountType   | accountOwnership   | accountName   | initialDeposit   |
      | <accountType> | <accountOwnership> | <accountName> | <initialDeposit> |
    Then the user should see an "<errorMessage>" on "<fieldType>" field

    Examples:
      | accountType       | accountOwnership | accountName          | initialDeposit | fieldType         | errorMessage                        |
      |                   | Individual       | Abline Checking      | 100000.00      | Standard Checking | Please select one of these options. |
      | Standard Checking |                  | Polina Test Checking | 100000.00      | Individual        | Please select one of these options. |
      | Standard Checking | Individual       |                      | 100000.00      | name              | Please fill out this field.         |
      | Standard Checking | Individual       | Angie76 Checking     |                | openingBalance    | Please fill out this field.         |
      | Standard Checking | Individual       | Angie987 Checking    | thousand       | openingBalance    | Please match the requested format.  |


  Scenario: Negative - creating checking account with initial deposit less than $25
    Given the user logged in as "angie5jolie@gmail.com" "helloKitty55"
    When the user clicks "Submit" while creating a new checking account with the following data
      | accountType       | accountOwnership | accountName        | initialDeposit |
      | Standard Checking | Individual       | Aang test checking | 12.00          |
    Then an error message should be displayed including 12.00 "does not meet the minimum amount ($25.00)"


  Scenario: creating checking account and hitting Reset
    Given the user logged in as "angie5jolie@gmail.com" "helloKitty55"
    When the user clicks "Reset" while creating a new checking account with the following data
      | accountType       | accountOwnership | accountName    | initialDeposit |
      | Standard Checking | Individual       | Reset Checking | 150000.00      |
    Then the user should see all fields reset to default

