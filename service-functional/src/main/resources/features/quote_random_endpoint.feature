Feature: Quote-random endpoint

  Scenario: Quote-random endpoint is OK
    Given application response metric for QUOTE_RANDOM with response status 200 gets initialised
    When the client intends to call QUOTE_RANDOM endpoint
    Then a response with code 200 is returned
    And the response body is "random quote"
    And application response metric for QUOTE_RANDOM with response status 200 is increased
