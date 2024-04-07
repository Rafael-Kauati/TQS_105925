Feature: Purchase Feature

  Scenario: User purchases tickets
    Given User is on the home page
    When User selects departure city "Aveiro" and destination city "Paris" for the journey date "2024-07-11"
    And User selects 3 seats
    And User completes the purchase
    Then User should see the purchase details for the journey
