# App description and user stories

## App description

This application functions as a workout diary. The purpose is for the users to be able to log and keep track of their workout sessions to easily find them again later.

To keep track of requirements and priorities when developing the app, we have user stories. These describe the key functionalities our app should have and why they are important.

Userstories below:

- Register workout
- Date log
- Edit
- Delete and Clear

## Register workout (us-1)

As a health and fitness interested person, I want to keep track of my workouts so that I can repeat the excercises later without worrying about remembering past sessions.

The user wants a place to save completed workout excercises to make it easy to find them again later. The excercises should be added to a file by entering the details into a text field and clicking an "Add" button. All the excercises should end up in the same file and displayed on screen after they are registered so that the user can easily view their workout history.

### Important to be able to see

- When working out: a text field to write excercises into and a button to register them
- After working out: history of previous excercises displayed on screen

### Important to be able to do
  
- Add new excercises

### Screenshot

![App Screenshot](ui/src/main/resources/ui/img/workoutapp.PNG)
*Figure 1: Screenshot of the UI*

The screenshot shows the input field with the "Add" button underneath and a display area where the workout history will appear when excercises are registered.

## Date log (us-2)

As a health and fitness interested person who keeps a log of my workouts, I want to be able to go back in time and look at my previous workouts so that I can se my improvement.

The user need to connect each workout to a date to make it easier for the user to go back in time and see their workout at a certain date. This should be added as a date field above the input field. The log should also be sorted in order by date.


### Important to be able to see

- The date input field to register date
- The workout log displaying date sorted in order

### Important to be able to do
  
- Add date to the excercises
- Sort workout log in date order

### Screenshot

Se bottom figure 2

## Edit (us-3)

As a person that logs my workouts, I want to be able to edit earlier registered workouts, so that I can correct mistakes and add important information.

The user want the oppertunety to add changes after clicking register on a workout. This will be added by doubleclicking on the element that the user wish to change. If the user is in the process of adding a new element this will need to be regisered before editing another.

### Important to be able to see

- When doubleclicking on an element the user can edit a workout

### Important to be able to do
  
- Edit (the workout is deleted from list and the input fields are filled out)

## Delete and Clear input (us-4)

As a person that logs my workouts, I want to be able to delete workouts that was very long ago or was added by mistake so that my workout log contains only the workouts I want.

Also I want the option to clear all workout from the list, so that I can start creating a new list fast without having to use a lot of time to delete each workout individually.

The user want to be able to remove (delete and clear (delete all) )workout in list if something is added by mistake or if the user do not want any of their previous workouts.

### Important to be able to see

- Clear: button for removing all of the workouts
- Delete: buttons on all the workouts

### Important to be able to do
  
- Remove all the workouts
- Remove workouts individually

### Screenshot

![App Screenshot](ui/src/main/resources/ui/img/workoutapp-release2.PNG)
*Figure 2: Screenshot of the UI after release 2*

The screenshot with clear button and dateinput