Feature: verify functionality of create schedule
  @createScheduleTest
  Scenario:
    Given admin logs in to studymate with "admin@codewise.com" and "codewise123"
    When admin go to Schedule Tab
    Then click on create event button
    Then click on schedule button and perform necessary steps
    Then verify presence of created schedule

    @deleteEvent
    Scenario:
      When admin click on a date
      Then click on delete button
      Then verify that event is deleted

