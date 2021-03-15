# Provide Tests
## Project overview

In this project we're going to implement a GraphQL API in Java + Spring Boot. We're using an in-memory database (H2) to store the data which we're going to work with.

Firstly, the system is going to consume a public API of RSS news in XML format (http://feeds.nos.nl/nosjournaal?format=xml), later we need to parse the response to our entities so we can work with it.

The project is mainly conformed by two entities, News and Param.
News entity is the entity where we're going to persist the data retrieved from the RSS News API. 
The News entity has the following attributes:
- ID: Its an unique id generated from the publication date of the news (ddMMyyhhmmss)
- title: news title
- description: news description
- image url: news post image url
- date: news publication date
- update date: news updated date in our system 

Param entity is just to store some significant useful parameters inside the project, such as the api URL and path, etc. 
The Param entity has the following attributes:
- ID: Autoincrement generic id
- param key: An unique key identifier to the params
- value: param value
- data type: param data type
- creation date: param creation date
- updated date: param updated date

Note: The param table is not going to store any configuration data.

The entities mentioned before are going to be served through a GraphQL API to be consumed by any client; we're going to do so using the following schemas:

```GraphQL
type Query {
  newsById(id: ID): News
  allNews(filter: Filter): [News]
}

type News {
  id: ID
  title: String
  description: String
  date: String
  updatedDate: String
  image: String
}

input Filter {
  criteria: String
  size: Int
  page: Int
}
```

## Installation

The first thing we need to do is clone the project from github.

```bash
git clone https://github.com/manuelpimentel/test-provide.git
```

After cloning the project and locate yourself in the base directory of the project (/provideTest), we're going to compile and deploy the application using the following commands.

```bash
mvn clean install
java -jar target/provideTest-0.0.1-SNAPSHOT.war
```

After this the project should be up and running in http://localhost:8080.

If you would like to run the tests just execute the following command.

```bash
mvn test
```

Note: 
The H2 database is available at http://localhost:8080/h2-console (Credentials can be found in application.properties file).

## Usage

There are 2 GraphQL services available, both to query data from de server:

### newsById(id: ID): News

This service returns the News stored data by id.

```graphQL
query
{
    newsById(id: ID)
    {
        id
        date
        title
        description
        image
        date
        updatedDate
    }
}
```

### allNews(Filter: filter): [News]

This service returns all the News stored, the schema has an optional filter attribute that can be used to filter the results by any criteria and to paginate them (Filter schema mentioned in the project overview section).

```graphQL
query
{
    allNews(Filter: filter)
    {
        id
        date
        title
        description
        image
        date
        updatedDate
    }
}
```

This services are available at http://localhost:8080/graphql and could be consumed from any GraphQL IDE such as GraphiQL (https://www.electronjs.org/apps/graphiql), GraphQL Playground (https://github.com/prisma-labs/graphql-playground), etc; Even could be consumed the API from Postman (https://learning.postman.com/docs/sending-requests/supported-api-frameworks/graphql/).
