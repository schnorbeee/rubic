@rubic @solve
Feature: Rubic solve

  @happy
  Scenario: Rubic solve valid
    When Send POST request to "/api/rubics" url and "ChipsGoodRequest.json" content
    When Send PUT request to "/api/rubics/solve" url
    Then Validate response with result "ResolvedRubicTable.json" and the response code is 200

  @sad
  Scenario: Rubic solve with bad params invalid
    When Send POST request to "/api/rubics" url and "ChipsBadRequest.json" content
    When Send PUT request to "/api/rubics/solve" url
    Then Validate response with result "InitialUnresolvableInvalid.json" and the response code is 417

  @sad
  Scenario: Rubic solve without call start invalid
    When Send PUT request to "/api/rubics/solve" url
    Then Validate response with result "MustCallStartGameBeforeSolveInvalid.json" and the response code is 412