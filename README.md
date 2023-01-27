# Task Management System

This is a RESTful task management system build with Spring and Spring Boot Framework.

## Features

- ✅ Authentication with username using JWT
- ✅ Adding new Users, Boards and Cards to Database
- ✅ Viewing cards by boards
- ✅ ...

## Technologies used

- ✨ [Java](https://openjdk.org/) - Programming Language
- ✨ [Spring](https://spring.io/) - Web Framework
- ✨ [Spring Boot](https://spring.io/projects/spring-boot) - For Building RESTful APIs
- ✨ [Docker](https://www.docker.com/) - Container Platform
- ✨ [MongoDB](https://www.mongodb.com/) - Database
- ✨ [Git](https://git-scm.com/doc) - Version Control System

## Installation

Clone the project

``` git
git clone https://github.com/Ali79Asgary/task_management_system_ampada
```

[//]: # (📄 Note that there are three environment files:)

[//]: # ()
[//]: # (- `.env`: for developing locally)

[//]: # (- `.env.dev`: for developing locally but inside docker container)

[//]: # (- `.env.prod`: for production purposes)

[//]: # (⚠️ Remove `.sample` postfix after all of them and considering your development environment &#40;local or inside docker&#41; change `env_file` field in `docker-compose.yml`)

Now you can run the project

```docker
docker-compose up -d --build
```

You currently have 7 containers running (production docker-compose is not including pgadmin)

- web
- mongo

You can access to app from `http://0.0.0.0:8080`
