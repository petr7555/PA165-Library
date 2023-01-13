# PA165-Library

This application is running on https://pa165-library.onrender.com.
You can use `user` with password `user` or `admin` with password `admin`.

See the project's GitHub wiki at https://github.com/petr7555/PA165-Library/wiki.

See complete REST documentation [here](https://documenter.getpostman.com/view/9355808/SzfAzmgs?version=latest).
First, log to the app in the browser. Copy your browser JSESSIONID Cookie and use it with the requests.

Run application:

* from project root folder run `mvn clean install -DskipTests`
* then either from folder `library-rest` run `mvn spring-boot:run`
  or from project root folder run `java -jar library-rest/target/library-rest.jar` (you need Java 11)

## Docker

Alternatively, you can run the application in a Docker container.
To do so, run the following commands from the project root folder:

- `docker build -t pa165-library .`
- `docker run -p 8080:8080 pa165-library`
- The application will be available at [http://localhost:8080/](http://localhost:8080/).

There are two maven profiles:

* `prod` - builds frontend sources and creates fully working jar file
* `dev` - skips building frontend sources

## Development:

Run FE:

* from folder `library-rest/src/main/react` run `npm start`

Run BE:

* from folder `library-rest` run `mvn spring-boot:run`

## Technologies

![Technologies used](https://github.com/petr7555/PA165-Library/blob/master/docs/technologies.PNG)
[Presentation](https://docs.google.com/presentation/d/1guReZ06l8OnDNLTPyGrTZ3cfEIyQ8Wz-0AIRIfOs3Bw/edit?usp=sharing)
