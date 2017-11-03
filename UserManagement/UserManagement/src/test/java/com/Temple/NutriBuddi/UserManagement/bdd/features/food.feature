Feature: Food get, add, etc.

  Scenario: Demogorgon wants to look up a food because it's expanding its diet
    When Demogorgon is hungry and makes a request
    Then it gets back a success and goes hunting
