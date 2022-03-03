# Docker pour vos tests d’intégration : À la découverte de Testcontainers 🐳

[Link to slides](https://docs.google.com/presentation/d/e/2PACX-1vQWpi8rGJx5soMg4l4IiDHSRaqQEW2A9yQuAHeOztTwQ75hNSg1k46lv_2e65QsA6AORUcRNeASdKgm/pub?start=false&loop=false&delayms=3000)

[![TestContainers](https://d33wubrfki0l68.cloudfront.net/a661dbbe55be3e9cb77889f24835a44c6daf53c2/ce0aa/logo.png)](https://www.testcontainers.org/)


You develop a weather app, proposing simple use cases:
- to consult the weather of your pre-saved cities, 
- to add a city with GPS coordinations.

## Prerequisites

(Estimate time : 10 minutes)

Make sure you have the following prerequisites installed on your machine:

- nodejs > 10
- Java 11
- Docker
- Maven
- Your favorite Java IDE
- Docker images for backend, frontend & weather api are built 

When you have all prerequisites installed, let's build docker images required to be able to write & execute tests with testcontainers:

```
docker pull mysql:8
docker build -t 7timer:0.0.1-SNAPSHOT e2e/src/test/resources/7timer/
docker build -t zenika-weather-front:0.0.1-SNAPSHOT front/  
docker build -t zenika-weather-back:0.0.1-SNAPSHOT back/ 
```

Setting up the requirements should be done now.
You can check everything's going well with the usage section above.


## Usage

(Estimate time : 5 minutes)

### Launch front

Go to `front/` directory.

As it's not necessary for our workshop to launch the interface, you can skip this step, which downloads nodes dependencies defined in package.json and then starts the interface.
Otherwise, you can type below commands.
When the start's done, visit [http://localhost:4200](http://localhost:4200) in your browser.

```
npm install
npm start 
```

### Launch Back

Go to `back/` directory.

We deploy the back part in a Docker container too. So, you don't have to run it manually but if you want to, let's play maven commands to download maven dependencies define in pom.xml, build image and run the back:

```
mvn install
mvn spring-boot:run 
```

### Database

We have a SQL database in a docker container.
The schema table will be created with the ``schema.sql`` file from ``back/`` directory when we start the back.
To deploy and rightly configure the container, let's run as below:

```
docker run --name weather-db -i --rm -e MYSQL_DATABASE=zenika-weather -e MYSQL_USER=zenika -e  MYSQL_PASSWORD=zenika-password -e MYSQL_RANDOM_ROOT_PASSWORD=true -p 3306:3306 mysql:8.0.28
```

**⚠️ WARNING**

Don't forget to stop mysql container for the workshop:

```
docker stop weather-db
```

## Workshop! 🚀️

### Integration test
(Estimate time : 30 minutes)

For now, integration test depends on MySQL instance started locally. We would evolve the test to start a MySQL docker image.

Step 1: Migrate integration test `CitiesControllerTest` with Generic container.

Step 2: Migrate integration test `CitiesControllerTest` with MySQL container.

Step 3: Migrate integration test `CitiesControllerTest` with testcontainers driver.

### Functional test
(Estimate time : 35 minutes)

As a last step, our functional test depends on services (front, api, db, weather api) started locally. We would evolve the test to start the platform inside a containers network.

Step 3: Migrate functional test to use containers network.

Step 4: Migrate functional test to use docker compose.

Step 5 (Bonus): Migrate functional test to use web driver container.


Workshop's done: **Congratulations!** 🏆️🎉

**🎓 Solutions**

Need help? 

Take a look at our solution for each step. 

<details>
  <summary>Step 1 </summary>
  https://github.com/Zenika/grenoble-hands-on-back-testcontainers/compare/master...step-1
</details>

<details>
  <summary>Step 2</summary>
  https://github.com/Zenika/grenoble-hands-on-back-testcontainers/compare/step-1...step-2
</details>

<details>
  <summary>Step 3</summary>
  https://github.com/Zenika/grenoble-hands-on-back-testcontainers/compare/step-2...step-3
</details>

<details>
  <summary>Step 4</summary>
  https://github.com/Zenika/grenoble-hands-on-back-testcontainers/compare/step-3...step-4
</details>

<details>
  <summary>Step 5</summary>
  https://github.com/Zenika/grenoble-hands-on-back-testcontainers/compare/step-4...step-5
</details>

<details>
  <summary>Step 6</summary>
  https://github.com/Zenika/grenoble-hands-on-back-testcontainers/compare/step-5...step-6
</details>

Every improvement is welcome!


## Technologies

### Languages

* Typescript
* Kotlin

### Tools / Libraries

* Angular
* Spring
* Testcontainers

## Other Hands-on


### Front

* [VueJS](https://github.com/Zenika/grenoble-hands-on-vuejs)
* [Angular](https://github.com/Zenika/grenoble-hands-on-angular)
* [React](https://github.com/Zenika/grenoble-hands-on-react)
* [Clean Architecture](https://github.com/Zenika/grenoble-hands-on-front-clean-architecture)
* [Monorepo](https://github.com/Zenika/grenoble-hands-on-lerna)
* [Green Code](https://github.com/Zenika/grenoble-hands-on-front-green-code)
* [Flutter](https://github.com/Zenika/grenoble-hands-on-flutter)

### Backend

* [Spring](https://github.com/Zenika/grenoble-hands-on-spring)
* [Quarkus](https://github.com/Zenika/grenoble-hands-on-quarkus)


## Contributing

<a href="https://github.com/mchoraine">
  <img src="https://github.com/mchoraine.png?size=50">
</a>
<a href="https://github.com/MrcdJ">
  <img src="https://github.com/MrcdJ.png?size=50">
</a>


