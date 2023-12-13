# source: https://snyk.io/blog/best-practices-to-build-java-containers-with-docker/

FROM maven:3.6.3-jdk-11-slim@sha256:68ce1cd457891f48d1e137c7d6a4493f60843e84c9e2634e3df1d3d5b381d36c AS build

WORKDIR /project
COPY . /project

RUN mvn clean package -DskipTests


FROM adoptopenjdk/openjdk11:jre-11.0.9.1_1-alpine@sha256:b6ab039066382d39cfc843914ef1fc624aa60e2a16ede433509ccadd6d995b1f

RUN apk add dumb-init

WORKDIR /app
COPY --from=build /project/library-rest/target/library-rest.jar /app/java-application.jar

RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
RUN chown -R javauser:javauser /app
USER javauser

CMD "dumb-init" "java" "-Xmx512m" "-jar" "java-application.jar"
