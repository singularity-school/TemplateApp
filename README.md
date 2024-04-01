# Template Project

This is template for basic registration and authorization for quick start in hackathons.

# Set Up

### Database Setup

Before running the project you should write environment variables for database connection. More precisely you should set up:
```
DB_NAME
DB_USER
DB_PASSWORD
```
In Intellij you can set it in "Edit Configuration" tab:
![Screenshot_1732.png](..%2F..%2Fscreenshots%2FScreenshot_1732.png)

### Token signing key

Don't forget to generate strong singing key and write it to `application.properties`. You can use online resources like https://www.grc.com/passwords.htm to generate signing key.

### Server configuration

By default, server listens on context path `/api` and port `8080` you can change it as you want inside `application.properties`.

# Swagger

This project has built-in API documentation. If you have not changed default server configuration then swagger would be on page http://localhost:8080/api/swagger-ui/index.html#/.