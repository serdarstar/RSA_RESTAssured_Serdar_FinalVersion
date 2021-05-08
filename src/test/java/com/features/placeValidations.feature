@AddPlace
Feature: Validating Place API's


  Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "addPlaceAPI" with "POST" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created matches with "<name>" using "getPlaceAPI"

    Examples:
      | name | language | address |
      | Ali  | Turkish  | Ankara  |
     # | Jack | English  | London  |

  @DeletePlace
  Scenario: Verify if DELETE Place functionality is working
    Given DeletePlace payload is sent
    When user calls "deletePlaceAPI" with "POST" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"