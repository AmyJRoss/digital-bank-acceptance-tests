Feature: DBank Updating Password

  Scenario Outline: positive
    Given the user logged in as "elonmusk67@gmail.com" "<oldPass>"
    And the user navigated to the "Change Password" page
    When the user updates the password with
      | oldPass   | newPass   | confirmPass |
      | <oldPass> | <newPass> | <newPass>   |
    Then success message should be displayed
    And the user is able to sign in with "new" credentials

    Examples:
      | oldPass          | newPass          |
      | Elonmusk123!     | experimentinG78) |
      | experimentinG78) | helloFresh123)   |
      | helloFresh123)   | Elonmusk123!     |

  Scenario Outline: negative
    Given the user logged in as "elonmusk67@gmail.com" "Elonmusk123!"
    And the user navigated to the "Change Password" page
    When the user updates the password with
      | oldPass   | newPass   | confirmPass   |
      | <oldPass> | <newPass> | <confirmPass> |
    Then error message should be displayed
    And the user is able to sign in with "old" credentials

    Examples:
      | oldPass      | newPass           | confirmPass      |
      | Elonmusk123! | 1441              | 1441             |
      | Elonmusk123! | hello             | hello            |
      | Elonmusk123! | CorrectFormat123$ | notMatchingPass  |
      | -            | CorrectFormat123  | CorrectFormat123 |
