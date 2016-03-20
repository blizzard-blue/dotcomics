# dotcomics

Description about this project

## Quick start

Clone this repo:
```
git clone https://github.com/blizzard-blue/dotcomics.git
```

Make sure you have [IntelliJ](https://www.jetbrains.com/idea/download/#section=windows) and the [Google App Engine SDK](https://cloud.google.com/appengine/downloads#Google_App_Engine_SDK_for_Java) installed.

Open the cloned repo in IntelliJ, and make sure you have IntelliJ set to be using Java 7.

This project is currently deployed to [http://dot-comics-test.appspot.com/](http://dot-comics-test.appspot.com/).

## Documentation

### Routing

This project uses the Front Controller design pattern (sort of). All incoming requests go into the front controller [router](https://github.com/blizzard-blue/dotcomics/blob/master/src/conf/Router.java). The router inspects the incoming request and decides what to do with it; usually it delegates the request to another component in the application that best know how to handle it. These handlers/controllers are located in the [controlers](https://github.com/blizzard-blue/dotcomics/tree/master/src/controllers) package. Each controller must be added to the [ActionFactory](https://github.com/blizzard-blue/dotcomics/blob/master/src/conf/ActionFactory.java) for the router to route to application to the correct page. 

Register a new controller by appending to:
```
actions.put("GET/", new StartupAction());
actions.put("GET/login", new LoginAction());
```

In addition to the routing controllers, we have a [Filter](https://github.com/blizzard-blue/dotcomics/blob/master/src/conf/Filter.java) that makes sure the url is updated/correct. All incoming requests actually go to the Filter first, which examines if the url is to a page or for a static file (i.e. css files). If intended to load a page, Filter will send the request to our router.

### Database

Database configuration is located in db.java, under the db package (found [here](https://github.com/blizzard-blue/dotcomics/blob/master/src/db/db.java)).

Action Controllers should send their queries to be executed/updated in this db class.


## APIs
