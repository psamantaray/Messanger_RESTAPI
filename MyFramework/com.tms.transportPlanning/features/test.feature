Feature: Transport plan creation

Scenario: Login as a shipper user
Given open one browser
And launch GTNexus application
When user logged in with valid username and password
Then home page should be displayed

Scenario: Process a Purchase order
Given navigate to GTNx application page
When user process a orderFile
Then Purchase order should be created successfully

Scenario: Search for the purchase order to create SO
Given shadow login as a shipper user
When search for the purchase order
Then Order number with id should be displayed