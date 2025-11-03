Feature: OrangeHRM Login API using Excel data

  @Login
  Scenario Outline: Successful login in OrangeHRM
    Given I attempt to authenticate using user data from row <excelRow> of feature "<feature>"
    Then the login response should be successful
    And the session cookie must be generated
    And the response should contain the expected redirect
    And the response body must have the correct HTML structure

    Examples:
      | excelRow | feature |
      | 2        | login   |