# Project Title
Sport Prediction

## Team Members
- Mehdi Boussalem , mehdi.boussalem@etu.cyu.fr
- Yanis Hocine, yanis.hocine@etu.cyu.fr

## Introduction
Our project is a RESTful web service that allows the creation, display, deletion, and updating of leagues and teams. Additionally, through an external API, it provides the functionality to display the top scorers of a team and predict the winner between two teams.


## Services Description
The server-side architecture of the "Sports Predictions Site + Football API" project comprises three main components: data models, resources, and services.

Data Models (Package: SportsPredictions.management.data):

League (League.java): Represents the structure of a league entity, encapsulating attributes such as name, sport, country, division, and a list of teams belonging to the league.
Team (Team.java): Defines the attributes and behavior of a team entity, including its name, level, and unique identifier (ID). Teams are associated with leagues and can be added or removed from them.
Resources (Package: SportsPredictions.management.resource):

League Resource (LeagueResource.java): Handles incoming HTTP requests related to leagues. It exposes RESTful endpoints for creating, retrieving, updating, and deleting league entities. It interacts with the LeagueService to perform operations on league data.
Team Resource (TeamResource.java): Manages HTTP requests associated with teams. It provides endpoints for managing teams within leagues, including adding, removing, and retrieving team details. It communicates with the TeamService to handle team-related operations.
Services (Package: SportsPredictions.management.service):

League Service (LeagueService.java): Implements the business logic for managing leagues. It includes methods for adding new leagues, deleting existing ones, retrieving league details, and managing teams associated with leagues.
Team Service (TeamService.java): Provides functionalities for handling teams within leagues. It manages the addition, deletion, and retrieval of teams, ensuring data integrity and consistency within the application.
These server-side components work together to handle incoming requests, process business logic, and interact with the underlying data models. The resources act as endpoints for external clients to access and manipulate league and team data, while the services encapsulate the core functionalities and logic required for managing leagues and teams effectively. The separation of concerns between data models, resources, and services promotes modularity, scalability, and maintainability within the server-side architecture.

## Clients Description
Our client is a graphical application that provides a user-friendly interface for interacting with our RESTful web service. Through a series of interactive buttons, users can perform a variety of actions. These include displaying the top scorers of a chosen league, predicting the outcome of a match between two teams, creating a league, creating a team, and displaying the teams within a selected league. This makes our client a versatile tool for managing and exploring sports leagues.



### Use Case 1: Creating a League

1. Execute the main class to launch the client application.
2. Click on the "Create League" button.
3. Enter the name of the league in the provided field.
4. Enter the sport of the league from the provided options.
5. Enter the country where the league is based.
6. Select the level of the league from the provided options.
7. Click on the "OK"" button to send the information.
8. The application sends a request to the web service to create a new league with the provided details.
9. The web service responds with a confirmation that the league has been created.

### Use Case 2: Creating a Team

1. Execute the main class to launch the client application.
2. Click on the "Create Team" button.
3. Enter the name of the team in the provided field.
4. Select the level of the team from the provided options.
5. Enter the ID of the league (which can be found on the page that opens after creating a league) in the provided field.
6. Click on the "OK" button to send the information.

### Use Case 3: Displaying Teams in a League

1. Execute the main class to launch the client application.
2. Click on the "Get Teams" button.
3. Enter the ID of the league (whose teams you want to display) in the provided field.
4. Click on the "OK" button to send the request.
5. The application sends a request to the web service to get all teams in the specified league.
6. The web service responds with a list of teams in the league.
7. The application displays a new page with all the teams in the selected league.


### Use Case 4: Retrieving Top Scorers of a League

1. Execute the main class to launch the client application.
2. Click on the "GET TOP SCORERS" button.
3. Enter the country of the league in the provided field.
4. Enter the name of the league in the provided field.
5. Click on the "OK" button to send the request.
6. The application sends a request to the web service to get the top scorers of the specified league.
7. The web service responds with a list of the top 15 scorers in the league.
8. The application displays a new window with the top 15 scorers in the selected league.

### Use Case 5: Predicting the Winner Between Two Teams

1. Execute the main class to launch the client application.
2. Click on the "Predict Winner" button.
3. Enter the name of Team A in the provided field.
4. Enter the name of Team B in the provided field.
5. Enter the country of the league in the provided field.
6. Enter the name of the league in the provided field.
7. Click on the "OK" button to send the request.
8. The application sends a request to the web service to predict the winner between the two specified teams.
9. The web service responds with the name of the team predicted to win by the API.
10. The application displays the predicted winner.
