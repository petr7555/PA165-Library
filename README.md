# PA165-Library

The app is running on Heroku.

See the project's GitHub wiki at https://github.com/petr7555/PA165-Library/wiki

See complete REST documentation [here](https://documenter.getpostman.com/view/9355808/SzfAzmgs?version=latest).

Run tests and build:
* from project root folder run `mvn clean install`

Run both FE and BE:
* run `npm build` in `library-rest/src/main/webapp/WEB-INF/view/react`, then copy `build` folder to
`library-rest/src/main/resources`, then fom folder `library-rest` run `mvn spring-boot:run`

Run FE:
* from folder `library-rest/src/main/webapp/WEB-INF/view/react` run `npm start`

Run BE:
* from folder `library-rest` run `mvn spring-boot:run`
