@rubic @start
Feature: Rubic start game

  @happy
  Scenario: Rubic start game
    When Send POST request to "/api/rubics" url and "ChipsGoodRequest.json" content
    Then Validate response with result "FirstInitialValidTable.json" and the response code is 201