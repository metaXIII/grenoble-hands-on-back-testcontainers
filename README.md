# Docker pour vos tests d’intégration : À la découverte de Testcontainers 🐳

Link to slides [TODO](TODO)

[![TestContainers](https://d33wubrfki0l68.cloudfront.net/a661dbbe55be3e9cb77889f24835a44c6daf53c2/ce0aa/logo.png)](https://www.testcontainers.org/)


You develop a weather app, proposing simple use cases:
- to consult the weather of your pre-saved cities, 
- to add a city with GPS coordinations.

## Prerequisites

---
(Estimate time : 10 minutes)

Make sure you have the following prerequisites installed on your machine:

- nodejs > 10
- Java 11
- Docker
- Maven
- Your favorite Java IDE

When you have all prerequisites installed, let's pre-pull docker images, like below:

```
docker pull ...
```
Setting up the requirements should be done now.
You can check everything's going well with the usage section above.


## Usage

---
(Estimate time : 5 minutes)

### Launch front

Go to `front/` directory.

We deploy the front part in a Docker container as defined in the Dockerfile.

```
docker build -t zenika-weather-front:0.0.1-SNAPSHOT .
```

NB: First docker build will upload **node:14.15.3-alpine** image as defined in line 1 of the Dockerfile.

As it's not necessary to launch the interface, you can skip this step, which downloads nodes dependencies defined in package.json and then starts the interface.
Otherwise, you can type below commands.
When the start's done, visit [http://localhost:4200](http://localhost:4200) in your browser.

```
npm install
npm start 
```

### Launch Back

Go to `back/` directory.

We deploy the back part in a Docker container too. On the other hand, we build 

Let's play maven commands to download maven dependencies define in pom.xml, build image and run the back:

```
mvn install
mvn spring-boot:run 
docker build -t zenika-weather-back:0.0.1-SNAPSHOT .  
```

### Database

We have a SQL database in a docker container.
The schema table will be created with the ``schema.sql`` file from ``back/`` directory when we start the back.
To deploy and rightly configure the container, let's run as below:

```
docker run --name weather-db -i --rm -e MYSQL_DATABASE=zenika-weather -e MYSQL_USER=zenika -e  MYSQL_PASSWORD=zenika-password -e MYSQL_RANDOM_ROOT_PASSWORD=true -p 3306:3306 mysql:8.0.28
```


### Weather API mock

We get the weather from an external API named 7timer.

We containerize TODO............ Let's type these commands:

```
docker run -it -p 1080:1080 7timer:0.0.1-SNAPSHOT 
docker build -t 7timer:0.0.1-SNAPSHOT e2e/src/test/resources/7timer/    
```

## Workshop! 🚀️

---

### Integration
(Estimate time : ... minutes)

1. Migrate integration test `CitiesControllerTest` with MySQL container.
2. Migrate integration test `WeatherControllerTest` with MySQL & 7Timer container.

### E2E
(Estimate time : ... minutes)

3. Create system test with frontend, backend, database & 7Timer inside containers then check platform is up
4. Switch system test with docker compose
5. Create functional test with containerized web browser

---
**💡 TIP**

...

---

Well done for ...!

As a last step...

---
**⚠️ WARNING**

...

---

Workshop's done: **Congratulations!** 🏆️🎉

## Technologies

---

### Languages

* Typescript
* Kotlin

### Tools / Libraries

* Angular
* Spring
* Testcontainers

## Other Hands-on

---

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


