- The Donatrix has been redesigned to follow what we have learned in class

- The director 'donatrix_old' holds our original code as a reference for this big refractor

- Everyone needs to pick a milestone to refractor

- I strongly reccomend reading all of the milestones up to the one you are editing so you have an
  idea for how to model works

- I did not worry about imports, extends, implements, and packages. Double check to make sure the
  right clauses are in place
  
M4/M5 -------------
    Login and Register
        - There are 3 main activites (Login, Register, and Welcome) under the view directory
        - The Welcome activity has options to login or register
        - The login activity has an email field, password field, login button, cancel button, and
            maybe a register button
        - The methods for navigating between the activities are in the Navigation interface which
          every activity should implement
        - Activities access data through MainDataAccess. MainDataAccess uses the HerokuAPI interface
          to build methods such as login and register. MainDataAccess handles the logic. HerokuAPI
          interface handles the API requests (These two can be found under controller/dataAccess)
        - If an activity needs to handle an Exception, use the display Exception from the Display
          interface. Do not hardcode toast in the activities.

M6 -------------
    DisplayingLocationData
        - Implement the Location Object found under model/location
        - With an understanding of how the model works in M4/M5 you should be able to implement the
          location methods in the dataAccess interfaces and create an activity that is designed
          similiarly to the three previous ones
        - See M6 desc. online for full implementation
    
        
