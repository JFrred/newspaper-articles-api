# newspaper-articles-rest-api

---

## About the project

This project is a simple newspaper articles REST service built with Spring Boot. It uses H2 database as a data storage. 

---
## Quick start
Project already contains sample data for testing purposes (resources/data.sql), so You just need to clone this repo and run the application. 

### clone project

```shell
git clone git@github.com:JFrred/newspaper-articles-api.git
cd newspaper-articles-api
```

### run via command line
You can run application via command line using following commands:

```shell
mvn package spring-boot:repackage
java -jar target/*.jar
```

### run with maven plugin

or You can run application directly from Maven using the Spring Boot Maven plugin

```shell
mvn spring-boot:run
```

---

## Api requests

* Request for information about all newspapers<br>
Example: localhost:8080/articles
* Request for information about specific newspaper<br>
  Example: localhost:8080/articles/1


``
GET /articles
``<br>
Submit a GET request to this URI in order to get information about newspapers sorted by publication date in descending order (from newest to oldest). <br>
Article information consists of following attributes: publication date, journal name, author name, article title and article content. journal name, publication date, publication title, author name.

``
GET /articles/{id}
``<br>
Submit a GET request to this URI in order to get information about article with given id.

``
GET /articles/name?keyword=somekeyword
`` <br>
Submit a GET request to this URI in order to get information about article which contain given keyword in their title/content.


``
POST /articles
`` <br>
Submit a POST request to this URI in order to save new article.<br>
In the body of the request, include the articleRequest.<br>

``
PUT /articles/{id}
`` <br>

Submit a PUT request to this URI in order to update article with given id.<br>
In the body of the request, include the articleRequest.


``
DELETE /articles/{id}
`` <br>
Submit a DELETE request to this URI in order to delete article with given id.


### ArticleRequest 
#### Example Value
```shell 
{
   "journalName": "New York Times",
   "title": "The Science of Sleep",
   "content": "If you want to learn how to sleep better, then you're in the right place...",
   "authorFirstName": "Cool",
   "authorLastName": "Author"
}
```

#### Schema
```shell
  journalName*        String [NotNull, notEmpty, maxSize=100]
  title*              String [NotNull, notEmpty, maxSize=100]
  content*            String [NotNull, notEmpty, ----------]
  authorFirstName*    String [NotNull, --------, maxSize=50]
  authorLastName*     String [NotNull, --------, maxSize=50]
```

Author's first name as well as last name must not be null but might be empty (in case You to stay anonymous).<br>
You may want to use nickname instead of year real name. In that case you can for example fill authorFirstName name and leave authorLastName empty (but not null!). 