@rubic @start
Feature: Rubic start game

  @happy
  Scenario: Rubic start game valid
    When Send POST request to "/api/rubics" url and "ChipsGoodRequest.json" content
    Then Validate response with result "InitialValidTable.json" and the response code is 201

  @happy
  Scenario: Rubic start game unresolvable request valid
    When Send POST request to "/api/rubics" url and "ChipsBadRequest.json" content
    Then Validate response with result "InitialUnresolvableValidTable.json" and the response code is 201