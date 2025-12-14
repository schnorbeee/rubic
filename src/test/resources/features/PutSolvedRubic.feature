@rubic @solve
Feature: Rubic solve

  @happy
  Scenario: Rubic solve valid
    When Send POST request to "/api/rubics" url and "ChipsGoodRequest.json" content
    When Send PUT request to "/api/rubics/solve" url
    Then Validate response with result "FirstResolvedRubicTable.json" and the response code is 200

  @sad
  Scenario: Rubic solve invalid
    When Send POST request to "/api/rubics" url and "ChipsBadRequest.json" content
    When Send PUT request to "/api/rubics/solve" url
    Then Validate response with result "FirstUnresolvedRubicTable.json" and the response code is 200