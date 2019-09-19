Feature: book operations

  I want to be able to add, update, delete or view books
  So that I can manage book stock

  @test
  Scenario: Get list of books
    When I call the list all books endpoint
    Then I receive a response with status code 200
    And I should get a list of books created
    And the JSON paths below are satisfied:

      | name         | id    |isbn |
      | Book1        | 1     | 123 |

  @test
  Scenario: Create a new book
    When I call the add books endpoint
    Then I should receive a response with status code 405
    Then I should be able to add a book
    And I should get a list of books added
    And I should be able to find this book in the system by name

      | name         | id    |isbn |
      | Book2        | 2     | 555 |

  @test
  Scenario: Update a book
    Given I update a book with id <id> with a new name <name>
    Then A book with id <id> should have a name <name>

      | name         | id    |isbn |
      | Book4        | 2     | 444 |