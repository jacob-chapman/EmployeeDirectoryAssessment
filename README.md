## Build tools & versions used

Android Build tools used is found in the [AndroidConfig]. The Build tools version is `30.0.3` and
the library version can be found in the [Versions] file found in the `buildSrc` module.

## Steps to run the app

- Open the project zip file
- Sync the project to download all the dependencies
- Run the app configuration on an Android device or emulator
- The initial screen is the employee directory list with a filter dropdown at the top.

## What areas of the app did you focus on?

I primarily focused on the architecture of the app and the setup of the project with gradle plugins.
I put a secondary focus on adding functionality that flowed through this arch setup and exposed the
view data pieces through view models.

## What was the reason for your focus? What problems were you trying to solve?

I wanted to focus on the architecture and setup because I believe a good foundation helps apps more
than anything else. A solid pattern and discipline there allows for developers to learn and produce
faster in my experience. I was trying to solve the problem of adding more functionality and being
able to test it easily as well. I did this by putting everything behind an interface that is able to
be quickly mocked.

By putting single business logic in a usecase or single methods it allows you to easily test single
pieces of code instead of large blocks that are better suited for full integration tests.

## How long did you spend on this project?

I spend about 5-6 hours on this project over a few days due to my schedule this past week. I
primarily wanted to focus on teh Android app structure and some functionality flow.

## Did you make any trade-offs for this project? What would you have done differently with more time?

I decided not to move all the UI values and constants into the correct files and places as I
normally would. I also did not refine the UI as much as I would like and instead focused on the
functionality of the UI more.

Given more time I would refine the UI, I would also add more unit tests around the implementation of
the repository. The other thing I would have done is moved the plugin applications to the custom
gradle plugin that I made.

## What do you think is the weakest part of your project?

I think the weakest part is the UI refinement.

## Did you copy any code or dependencies? Please make sure to attribute them here!

I did not copy any code or dependencies, I did use some guides on how to setup jetpack compose
navigation for the core piece. I also used some similar classes to what I have done before when
setting up projects and POCs. Those classes are ViewState, DomainData, and DomainDataManger.

Each class has its own purpose, I feel like ViewState is easily mapped and flexible for UI. It
allows a single flexible component to be made and used across the app, as you typically want to have
a similar error and empty state for screens.

The DomainDataManager is something I've used before, the idea is to abstract away disk or memory
cache data stores like maps, or room dbs. Makes the repository easier to mock without those
dependencies

## Is there any other information you’d like us to know?

I think overall this is a good display of my skillset. I've been a Senior Android engineer and have
done complicated UI screens, but I've also developed large scale projects and architecture to help
scale.