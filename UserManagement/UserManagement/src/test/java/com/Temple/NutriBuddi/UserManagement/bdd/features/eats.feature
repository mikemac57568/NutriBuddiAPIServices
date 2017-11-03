Feature:  Eats add, get, & put

  Scenario: Eleven wants to get food nutritional values for Eggos
    When she makes the call to /eats/getEatsByFoodName
    Then a response should be retrieved with status 200