# API

We tested the springbootserver using [postman](https://www.postman.com/).

## Base endpoint

Methods:

* GET - retrieves the workoutlog with all the workouts
  * URI: host:port/
  <http://localhost:8080/>
  * parameters: none
  * returns JSON with the workoutLog

```json
{
  "workouts":[{
    "workoutInput":"workout",
    "date":""24-10-20""
    }]
}
```

## workout

<http://localhost:8080/workout?workoutInput=input>
<http://localhost:8080/workout?workoutInput=input&date="24-10-20">

Date is optional on these methods, workout input is required.

Methods:

* GET - retrieves the specified workout
  * URI: <http://localhost:8080/workout?workoutInput=input&date="24-10-20">
  * parameters: workoutInput, date (optional)
  * returns JSON with the workout you tried to get

```json
  {
    "workoutInput":"workout",
    "date":""24-10-20""
  }

```

* POST - adds workout to workoutLog
  * URI: host:port/workout?workoutInput=new_input&date=24-10-20
  * parameters: workoutInput, date (optional)
  * returns workout if added
    * writes to console if sucess or not

```json
 {
    "workoutInput":"workout",
    "date":""24-10-20""
  }
```
  
* DELETE - delete workout from workoutLog
  * URI: host:port/workout?workoutInput=new_input&date=24-10-20
  * parameters: workoutInput, date (optional) of workout to delete
  * available: jetty, springboot
  * returns nothing
    * writes to console if sucess or not
