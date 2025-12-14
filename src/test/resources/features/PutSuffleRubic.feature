@rubic @shuffle
Feature: Rubic shuffle

  @happy
  Scenario: Rubic shuffle valid
    When Send POST request to "/api/rubics" url and "ChipsGoodRequest.json" content
    When Send PUT request to "/api/rubics" url
    Then Validate response code is 200

  @happy
  Scenario: Rubic shuffle unresolvable request valid
    When Send POST request to "/api/rubics" url and "ChipsBadRequest.json" content
    When Send PUT request to "/api/rubics" url
    Then Validate response code is 200

  @sad
  Scenario: Rubic shuffle invalid
    When Send PUT request to "/api/rubics" url
    Then Validate response with result "MustCallStartGameBeforeShuffleInvalid.json" and the response code is 412