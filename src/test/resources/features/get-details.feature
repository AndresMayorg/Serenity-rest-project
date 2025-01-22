Feature: Get song details from Shazam API

  @ValidSongDetails
  Scenario Outline: Get details of a valid song
    Given that I have a song ID <songId>
    When I make a GET request to the Shazam endpoint
    Then the response must have a status code of 200
    And the song title must not be empty
    And the song ID in the response must be the same as requested
    Examples:
        | songId    |
        | 1217912247|

  @InvalidSongDetails
  Scenario Outline: Get reply from an invalid song id
    Given that I have a song ID <songId>
    When I make a GET request to the Shazam endpoint
    Then the response must have a status code of 200
    And the response should contain an error with status 404
    And the error title should be 'Resource Not Found'
    And the error code should be '40400'

    Examples:
      | songId    |
      | 0000000000|