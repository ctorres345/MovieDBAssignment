### CS ANDROID ASSIGNMENT

This project has been develop as a technical mobile assessment for
Backbase

## Requirements

With an already created project, implement the following requirements

UI

* Create UI for MovieListScreen and MovieDetailScreen
* MovieListScreen should display a two list, One for "Playing Now" and
  the other for "Popular" movies
* Playing Now movies should be displayed as an horizontal list, no
  pagination request needed
* Popular movies should be display in a vertical list, apply pagination
  request
* Once a popular movie is selected the MovieDetailScreen should be show
  with the expected information regarding the selected movie
* Implement Rating View to display a circle like progress view to
  display the rating of a movie

Data Management

* Write network implementation to fetch and parse the obtained JSON from
  The Movie Database API and render it on the UI
* Implement data cache saving mechanism to store data for smooth usage
  of the application
* Implement paging mechanism for popular movie data fetch

Test

* Provide UI/Unit Test

Limitations

* 3rd party libraries are allowed except for replacing the purpose of
  the RatingView

## Architecture

From the base project I decided to add new modules to separate concerns
in layers and handle an implementation of clean architecture in android.
The layers are the following.

* App Module -> This serves as the presentation layer for all the UI
  work and DI implementation
* Domain Module -> This will hold all the use cases. Pure Kotlin class,
  no android dependencies.
* Data Module -> This will hold the repository for handling the
  different data sources and their implementations.

## Libraries Used

Basic libraries for android projects including some Jetpack Compose suit

* Kotlin Coroutines -> Used across all modules
* Android Material Library -> For Material Themes in some views
* Constraint Layout -> To usage in layouts
* Android Navigation Library -> For App navigation
* Room -> For Local Database storage
* LiveData -> Used for MVVM implementation
* Lifecycle Extensions -> Used for MVVM implementation
* Android Security Library -> For Shared Preferences secure storage

For Networking

* Retrofit2 -> For basic networking management
* OkHttp3 -> For basic networking management and loging
* GSON -> For basic networking management and model management

For Dependency Injection

* Dagger
* Hilt

For Image Loading and Management

* Glide

Extra UI Libraries

* Lottie -> To handle animations used in the loading and error dialog

For Testing

* Mockk -> To mock dependencies
* AndroidX Testing Libraries -> For base classes and extensions

## Personal Commentary about development

**PHASE 1**
The first thing I did was build the architecture in order to start
working on the project piece by piece. My roadmap was the following:

***Data Layer -> Domain Layer -> DI -> UI Work -> Test ***

While working on the data layer I had some doubts about the data models
since I was provided with the api url directly but no swagger file, so I
started mocking the models based on the JSON I received. Later I found
about all the documentation provided by The Movie Database API and make
the corrections in the models based on the data provided by their page.
I started with the remote implementation since it's the fastest in order
to start working with the usecases and UI based on the info retrieved.

**PHASE 2**
Once I had some basic UI to display and verify there's no problem with
the data source I started working on the local db implementation and the
paging mechanism for the popular movies. While working on the database
implementation I had to made some tweaks to all the other modules in
order to keep consistency and remove some unused models I mapped at the
beginning. Had some problems here with the dependency injection but it
was managed at the end. Once I got both data sources all completed I
started working on the UI flow to leave it as close to the provided
mocks as possible. Once I had everything mapped I added some visual
flavor with Loattie and small changes to the base color pallete to match
the ones in The Movie Database website.

**PHASE 3**
By having all the UI work and data management already completed I
started working on the unit testing. The only problem here I found out
was with the Espresso and Hilt that I'm still trying to figure out how
to apply a workaround to provide test with a dependency injection setup.

The project was really interesting overall, I had to rethink my
architecture implementation in the data layer a lot but I think it ended
up well. There's a lot of things I could improve but due to the time
frame I wanted to cover just the base scope first and work on more stuff
later based on that.

Regarding the rating view I had two options, create it based on a simple
view and draw it with canvas or extend the progress bar. Since I've done
something similar in another project I used the first approach by
playing with those 2 circle shape and different colors by multiplying
one with a progress value from 0 to 1 to make the design like a progress
bar.

Regarding the paging approach is just a simple pagination mechanism by
also storing the maxPages that comes from any movie list response and
performing request using a custom class that extends from
RecyclerView.OnScrollListener. There I just played with a flag and the
item count in order to trigger the pagination event and call the use
case when needed.

Also not part of the scope but I stored the date when the values are
being added in order to ask for new data once that date has overdue.
This would cover data refreshing.

## Observations

* The duration of movies, or runtime as it is called by the API, doesn't
  come as part of the movies in the popular movie list data. It is
  obtained in the detailed so I ended up not showing that data in the
  list. It can be manage by calling the detail API before and storing
  the info and working around that but since it's something small I
  didn't want to overcomplicated with something that could lead me out
  of scope.
* Not on the scope but adding a Logging mechanism and managing custom
  Exceptions could have been interesting too

## Changelog

Version 1.0.0

* Added HomeView to show a list of the ongoing movies and popular movies
* Added MovieDetailView to show the detail of a selected movie

