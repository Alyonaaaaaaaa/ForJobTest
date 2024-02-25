FROM amazoncorretto
COPY . /src/main
WORKDIR /src/main
ENTRYPOINT ["java"]
EXPOSE 80