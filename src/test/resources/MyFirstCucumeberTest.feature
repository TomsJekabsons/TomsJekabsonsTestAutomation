Feature: My First Cucumber feature

  Scenario: User can login with correct username/password
    Given user navigates to 'https://www.google.com'
    Then user sees page title 'Google'

    Given user navigates to 'https://www.lu.lv'
    Then user sees page title 'Latvijas Universitāte'


#    When user enters 'standart-user' and 'secret_sauce'
#    And user clicks login button
#    Then user sees inventory page