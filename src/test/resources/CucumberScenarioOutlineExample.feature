Feature: This is scenario outline feature

  Scenario Outline: Test multiple page title
    Given user navigates to '<url>'
    Then user sees page title '<page_title>'

    Examples:
      | url                    | page_title                                                    |
      | https://www.google.com | Google                                                        |
      | https://www.lu.lv      | Latvijas Universitāte                                         |
      | https://www.rtu.lv     | Rīgas Tehniskā universitāte \| Inženierzinātņu centrs Baltijā |
