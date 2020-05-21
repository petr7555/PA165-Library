# PA165-Library

The app is running on [Heroku](https://pa165-library.herokuapp.com/) (it might take a while to start because the app goes to sleep when it is not used).
You can use `user` with password `user` or `admin` with password `admin`.

See the project's GitHub wiki at https://github.com/petr7555/PA165-Library/wiki

See complete REST documentation [here](https://documenter.getpostman.com/view/9355808/SzfAzmgs?version=latest).
Fisrt, log to the app in the browser. Copy your browser JSESSIONID Cookie and use it with the requests.

Run application:
* from project root folder run `mvn clean install -DskipTests`
* then from folder `library-rest` run `mvn spring-boot:run`


## Development:

Run FE:
* from folder `library-rest/src/main/react` run `npm start`

Run BE:
* from folder `library-rest` run `mvn spring-boot:run`

## Technologies
![Technologies used](https://github.com/petr7555/PA165-Library/blob/master/docs/technologies.PNG)
[Presentation](https://docs.google.com/presentation/d/1guReZ06l8OnDNLTPyGrTZ3cfEIyQ8Wz-0AIRIfOs3Bw/edit?usp=sharing)
