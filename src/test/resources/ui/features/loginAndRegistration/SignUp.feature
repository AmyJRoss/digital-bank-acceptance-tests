Feature: Sign up

  Background:
    Given the user with email "morganfree1170@gmail.com" is not in DB
    And the user navigates to Digital Bank signup page

  @Regression
  Scenario: Positive. As new user I want to successfully create Digital Bank account
    When the user creates account with following fields
      | title | firstName | lastName | gender | dateOfBirth | ssn         | email                    | password     | confirmPassword | address        | locality | region | postalCode | country | homePhone       | mobilePhone | workPhone | agreeToTerms |
      | Ms.   | Morgan    | Freeman  | M      | 11/11/1970  | 122-22-9807 | morganfree1170@gmail.com | Freeman1970$ | Freeman1970$    | 123 Wolf Drive | Hartford | CO     | 90082      | USA     | (234) 111- 2233 |             |           | true         |
    Then the user should be displayed with the message "Registration Successful. Please Login."
    Then the following user info should be saved in the db
      | title | firstName | lastName | gender | dateOfBirth | ssn         | email                    | password     | confirmPassword | address        | locality | region | postalCode | country | homePhone       | mobilePhone | workPhone |accountNonExpired|accountNonLocked|credentialsNonExpired|enabled|
      | Ms.   | Morgan    | Freeman  | M      | 11/11/1970  | 122-22-9807 | morganfree1170@gmail.com | Freeman1970$ | Freeman1970$    | 123 Wolf Drive | Hartford | CO     | 90082      | USA     | (234) 111- 2233 |             |           |true             |true            |true                 |true   |


  Scenario Outline:  Negative test cases. As a Digital Bank admin I want to make sure users can't register without providing all valid data
    When the user creates account with following fields
      | title   | firstName   | lastName   | gender   | dateOfBirth   | ssn   | email   | password   | confirmPassword   | address   | locality   | region   | postalCode   | country   | homePhone   | mobilePhone   | workPhone   | agreeToTerms   |
      | <title> | <firstName> | <lastName> | <gender> | <dateOfBirth> | <ssn> | <email> | <password> | <confirmPassword> | <address> | <locality> | <region> | <postalCode> | <country> | <homePhone> | <mobilePhone> | <workPhone> | <agreeToTerms> |
    Then the user should see the "<fieldWithError>" required field error message "<errorMessage>"

    Examples:
      | title | firstName | lastName | gender | dateOfBirth | ssn         | email                  | password     | confirmPassword | address | locality | region | postalCode | country | homePhone | mobilePhone | workPhone | agreeToTerms | fieldWithError  | errorMessage                        |
      |       |           |          |        |             |             |                        |              |                 |         |          |        |            |         |           |             |           |              | title           | Please select an item in the list.  |
      | Mr.   |           |          |        |             |             |                        |              |                 |         |          |        |            |         |           |             |           |              | firstName       | Please fill out this field.         |
      | Mr.   | Jack      |          |        |             |             |                        |              |                 |         |          |        |            |         |           |             |           |              | lastName        | Please fill out this field.         |
      | Mr.   | Jack      | Hook     |        |             |             |                        |              |                 |         |          |        |            |         |           |             |           |              | gender          | Please select one of these options. |
      | Ms.   | Vanessa   | Hodge    | F      |             |             |                        |              |                 |         |          |        |            |         |           |             |           |              | dateOfBirth     | Please fill out this field.         |
      | Mr.   | Jack      | Black    | M      | 19/07/1989  |             |                        |              |                 |         |          |        |            |         |           |             |           |              | ssn             | Please fill out this field.         |
      | Mr.   | Dave      | Sigel    | M      | 18/11/1997  | 123-34-4569 |                        |              |                 |         |          |        |            |         |           |             |           |              | email           | Please fill out this field.         |
      | Mr.   | Dave      | Sigel    | M      | 18/11/1997  | 123-34-0549 | testingemail@gmail.com |              |                 |         |          |        |            |         |           |             |           |              | password        | Please fill out this field.         |
      | Ms.   | Iris      | Queen    | F      | 21/05/1998  | 758-54-2893 | irisqueen21@gmail.com  | Iris123Test# |                 |         |          |        |            |         |           |             |           |              | confirmPassword | Passwords Do Not Match              |
#      | Ms.   | Iris      | Queen    | F      | 21/05/1998  | 758-54-2893 | irisqueen21@gmail.com  | Iris123Test# | Iris123Test#    |                |               |        |            |         |              |             |           |              | address         | Please fill out this field.                   |
#      | Ms.   | Iris      | Queen    | F      | 21/05/1998  | 758-54-2893 | irisqueen21@gmail.com  | Iris123Test# | Iris123Test#    | 123 Pride Lane |               |        |            |         |              |             |           |              | locality        | Please fill out this field.                   |
#      | Ms.   | Iris      | Queen    | F      | 21/05/1998  | 758-54-2893 | irisqueen21@gmail.com  | Iris123Test# | Iris123Test#    | 123 Pride Lane | Internet City |        |            |         |              |             |           |              | region          | Please fill out this field.                   |
#      | Ms.   | Iris      | Queen    | F      | 21/05/1998  | 758-54-2893 | irisqueen21@gmail.com  | Iris123Test# | Iris123Test#    | 123 Pride Lane | Internet City | CA     |            |         |              |             |           |              | postalCode      | Please fill out this field.                   |
#      | Ms.   | Iris      | Queen    | F      | 21/05/1998  | 758-54-2893 | irisqueen21@gmail.com  | Iris123Test# | Iris123Test#    | 123 Pride Lane | Internet City | CA     | 12345      |         |              |             |           |              | country         | Please fill out this field.                   |
#      | Ms.   | Iris      | Queen    | F      | 21/05/1998  | 758-54-2893 | irisqueen21@gmail.com  | Iris123Test# | Iris123Test#    | 123 Pride Lane | Internet City | CA     | 12345      | USA     |              |             |           |              | homePhone       | Please fill out this field.                   |
#      | Ms.   | Iris      | Queen    | F      | 21/05/1998  | 758-54-2893 | irisqueen21@gmail.com  | Iris123Test# | Iris123Test#    | 123 Pride Lane | Internet City | CA     | 12345      | USA     | 123-567-9009 |             |           |              | agreeToTerms    | Please check this box if you want to proceed. |