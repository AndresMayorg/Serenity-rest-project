Feature: Validate Shazam's song detection API

  @SuccessfulSongDetection
  Scenario Outline: Validate the response for the song detection API
    Given That I have a time zone <timeZone>
    When When I make a POST request to Shazam endpoint of a song encoded in base64
    Then The response then contains a status code 200
    And The response should contain at least one match
    And Each match should have id
    Examples:
      | timeZone         |
      | America%2FChicago|

  @EmptySongDetection
  Scenario Outline: Validate the song detection API response when sending empty bodies
    Given That I have a time zone <timeZone>
    When When I make a POST request to the Shazam endpoint for an empty song
    Then The response then contains a status code 204

    Examples:
      | timeZone         |
      | America%2FChicago|