Feature: Healthcheck endpoint

  Scenario: Service status endpoint is OK
    Given application response metric for PRIVATE_STATUS with response status 200 gets initialised
    When the client intends to call PRIVATE_STATUS endpoint
    Then a response with code 200 is returned
    And the response body is "OK"
    And application response metric for PRIVATE_STATUS with response status 200 is increased

  Scenario: Service healthcheck endpoint is OK
    Given application response metric for PRIVATE_HEALTHCHECK with response status 200 gets initialised
    When the client intends to call PRIVATE_HEALTHCHECK endpoint
    Then a response with code 200 is returned
    And the response body is "OK"
    And application response metric for PRIVATE_HEALTHCHECK with response status 200 is increased

  Scenario: Service metrics endpoint is OK
    Given application response metric for PRIVATE_METRICS with response status 200 gets initialised
    When the client intends to call PRIVATE_METRICS endpoint
    Then a response with code 200 is returned
    And the response body is "not empty"
#  this would be increased twice, since this step is calling metrics too
#    And application response metric for PRIVATE_METRICS with response status 200 is increased
