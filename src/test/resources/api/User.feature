Feature: RestFull API Test
  As an admin
  I want to see list of user
  So that I can create new user

  Scenario: Successful login
    Given I have valid credentials
    When I submit a POST request to "/auth/login" with username "mor_2314" and password "83r5^_"
    Then I should get a 200 response code
    And the response should contain an access token

  Scenario: Invalid login
    Given I have valid credentials
    When I submit a POST request to "/auth/login" with username "83r5^_" and password "p@ssw0rd"
    Then I should get a 401 response code

  Scenario: Retrieve all products
    Given I set GET products endpoint
    When I send GET HTTP request
    Then the response status code should be 200
    And the response should contain products