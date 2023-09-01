
Feature: user fill the contact us form
  As a user i would like to fill the contact us from

  Scenario: user fill the contact us form
    Given user is on Emumba home page
    When user clicks on the Contact us button
    And user fills the contact us form
    Then user should see the success message