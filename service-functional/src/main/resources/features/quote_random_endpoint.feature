Feature: Quote-random endpoint

  Scenario: Quote-random endpoint is OK
    Given application response metric for QUOTE_RANDOM with response status 200 gets initialised
    When the client intends to call QUOTE_RANDOM endpoint
    Then a response with code 200 is returned
    And the response body is "Women have one of the great acts of all time. The smart ones act very feminine and needy, but inside they are real killers. The person who came up with the expression 'the weaker sex' was either very naive or had to be kidding. I have seen women manipulate men with just a twitch of their eye -- or perhaps another body part."
    And application response metric for QUOTE_RANDOM with response status 200 is increased
