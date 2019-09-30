Feature: book operations

  I want to be able to add, update, delete or view books
  So that I can manage book stock

  #@test
  Scenario: Get list of books
    When I call the list all books endpoint
    Then I receive a response with status code 201
    And I should get a list of books created
    And the JSON paths below are satisfied:

      | id | name  | isbn |
      | 2  | Book1 | 123  |

  #@test
  Scenario: Create a new book
    Given The db is empty
    When I call the add books endpoint
    Then I should receive a response with status code 405
    Then I should be able to add a book
    And I should get a list of books added
    And I should be able to find this book in the system by name

      | id | name  | isbn |
      | 3  | Book2 | 555  |

  #@test
  Scenario: Update a book
    Given I add a new book
    Given I update a book with a given id
    Then A book with the given id should exist

      | id | name  | isbn |
      | 2  | Book4 | 444  |

  #@test
  Scenario: Delete a book
    Given I add a book with id 2 in the db
    When I delete a book with id 2
    And I call show books i should receive 204

  @test
  Scenario: Search by id
    Given The database is empty
    When I search for a book with id 3
    Then I should receive a response with status code of 400
    Then I create a stub with wiremock
    And I should be able to find the book with id 3 in the system
    And return status code 200
