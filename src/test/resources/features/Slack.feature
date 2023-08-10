Feature: Slack API message validation
  Scenario: Happy Path for Slack message validation
    Given User navigates to the website
    When User clicks on API channel and sends 'Hello people! This is Melek.'
    Then User validates 'the message' is in Slack through API request
