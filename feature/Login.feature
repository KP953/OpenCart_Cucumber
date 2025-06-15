Feature: login with Valid Credentials

  @sanity @regression
  Scenario: Successful login with Valid Credentials
    Given the user navigate to the login page
    When user enter the email as "kp@gamil.com" and password as "Shiv@2023";
    And the user clicks on the loggin button
    Then the user should redirected to the MyAccount Page

 # @regression
 # Scenario Outline: Login Data Driven
 #   Given the user navigate to the login page
 #   When user enter the email as "<email>" and password as "<password>";
 #   And the user clicks on the loggin button
 #   Then the user should redirected to the MyAccount Page

  #  Examples: 
  #   | email             | password  |
  #    | kp@gamil.com      | Shiv@2023 |
  #    | pavanol@gmail.com | test1234  |
      
