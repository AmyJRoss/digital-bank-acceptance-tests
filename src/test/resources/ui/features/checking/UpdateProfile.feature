Feature: Updating Profile

  Scenario: Updating profile positive
    Given the user logged in as "madison523@gmail.com" "Madison123$"
    And the user navigated to the "Update Profile" page by hitting profile picture and selecting "My Profile"
    When the user updates the following fields with valid data
      | title | firstName | lastName | homePhone | mobilePhone | workPhone | address | locality | region | postalCode | country |
      | Ms.   | Maddy     | Cee      | -         | -           | -         | -       | Hartford | -      | -          | -       |
    Then the user should see the success message
    And the user should see updated information


  Scenario: updating profile as a new user
    Given the user entered random info in signup fields on signup page
    And the user logged in after registering as a new user
    When the user updates profile with random data
    Then the user should see the success message
    And the user should see updated information


