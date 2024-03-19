Feature: Book search
  To allow a customer to find his favourite books quickly, the library must offer multiple ways to search for a book.

  Scenario: Search books by publication year
    Given a book with the title 'The 48 Laws of Power', written by 'Robert Greene', published in 1998-03-14
    When the customer searches for books published between 1998 and 2014
    Then 1 books should have been found
    And Book 1 should have the title 'The 48 Laws of Power'

  Scenario: Search by a given title
    Given a title 'you-zitus', written by 'Shōgo Kinugasa', published in 2015-05-25
    When the customer searches for a book titled as 'you-zitus'
    Then the customer should see the following book details:
      | Title    | Author          | Published Date      |
      | you-zitus| Shōgo Kinugasa | 2015-05-25T00:00:00 |


