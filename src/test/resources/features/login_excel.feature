Feature: User Authentication Security
  Como un usuario administrativo
  Quiero autenticarme en la plataforma OrangeHRM
  Para gestionar el talento humano de la compañía

  @Login @Regression
  Scenario Outline: Successful administrative login
    Given User attempts to authenticate using data from row <excelRow> of "<feature>"
    Then he should receive a redirection status
    And a new session cookie should be assigned
    And he should be granted access to the dashboard
    And the response must be valid HTML

    Examples:
      | excelRow | feature |
      | 2        | login   |