# GraphQL quickstart 

## Prerequisites
- JDK 1.8+
- Maven 
- Galleon downloaded and available on your path as described in the [main README](../README.md). The main README also provides background on how to provision WildFly servers and which Galleon layers are available.


## Building and deploying the quickstart
You need to provision a server (remember you need to have Galleon installed as described in 
the [main README](../README.md)). Go to the folder containing this README in a new
terminal, and then run:
```
galleon.sh provision ./provision.xml --dir=target/my-wildfly
./target/my-wildfly/bin/standalone.sh
```
This provisions the server with the relevant Galleon layers, and starts it. The
[main README](../README.md) contains information about the layers in this feature pack.

Then in another terminal window, go to the same folder and run:
```
mvn package wildfly:deploy
```
This builds and deploys the application into the provisioned WildFly server.

## Check the GraphQL schema
To view the schema, execute this command:
```
curl localhost:8080/quickstart/graphql/schema.graphql
```                                                  

## Experimenting with queries using GraphiQL
GraphiQL is a UI tool that can be used to execute GraphQL queries. Open GraphiQL by navigating to 
`http://localhost:8080/quickstart/graphql-ui` in your browser. Queries that you should try out are listed below.
You will probably also notice that GraphiQL, based on the knowledge of the GraphQL schema, offers autocompletion!

### Queries
Enter the following query to GraphiQL and press the play button:

```
query allFilms {
  allFilms {
    title
    director
    releaseDate
    episodeID
  }
}   
```

Since our query contains all the fields in the Film class we will retrieve all the fields in our response. 
Since GraphQL API responses are client determined, the client can choose which fields it will require.

Let’s assume that our client only requires `title` and `releaseDate` making the previous call to the API 
Over-fetching of unnecessary data.

Enter the following query into GraphiQL and hit the play button:

```
query allFilms {
  allFilms {
    title
    releaseDate
  }
}  
```

Notice in the response we have only retrieved the required fields. Therefore, we have prevented Over-fetching.

Let's now try out a way how to retrieve one film my its id. It's implemented by this query:
```
@Query
@Description("Get a Films from a galaxy far far away")
public Film getFilm(@Name("filmId") int id) {
    return service.getFilm(id);
}                
```

Enter the following into GraphiQL and make a request.

```
query getFilm {
  film(filmId: 1) {
    title
    director
    releaseDate
    episodeID
  }
}  
```

The film query method requested fields can be determined as such in our previous example. 
This way we can retrieve individual film information.

However, say our client requires both films with filmId 0 and 1. In a REST API the client would have to make 
two calls to the API. Therefore, the client would be Under-fetching.

In GraphQL it is possible to make multiple queries at once.

Enter the following into GraphiQL to retrieve two films:
    
```    
query getFilms {
  film0: film(filmId: 0) {
    title
    director
    releaseDate
    episodeID
  }
  film1: film(filmId: 1) {
    title
    director
    releaseDate
    episodeID
  }
}    
```

This enabled the client to fetch the required data in a single request.

Until now, we have created a GraphQL API to retrieve film data. 
We now want to enable the clients to retrieve the Hero data of the Film.  

Review the following source-query in our `FilmResource` class:

```
public List<Hero> heroes(@Source Film film) { 
    return service.getHeroesByFilm(film);
}
```
Enter the following into GraphiQL to retrieve the film and hero data.

```
query getFilmHeroes {
  film(filmId: 1) {
    title
    director
    releaseDate
    episodeID
    heroes {
      name
      height
      mass
      darkSide
      lightSaber
    }
  }
}      
```

The response now includes the heroes of the film.

### Mutations

Mutations are used when data is created, updated or deleted.
Review the following mutations in our `FilmResource` class:

```
@Mutation
public Hero createHero(Hero hero) {
    service.addHero(hero);
    return hero;
}

@Mutation
public Hero deleteHero(int id) {
    return service.deleteHero(id);
}
```      

Enter the following into GraphiQL to insert a Hero:

```
mutation addHero {
  createHero(hero: {
      name: "Han",
      surname: "Solo"
      height: 1.85
      mass: 80
      darkSide: false
      episodeIds: [4, 5, 6]
  	}
  )
  {
    name
    surname
  }
}               
```

By using this mutation we have created a Hero entity in our service.

Notice how in the response we have retrieved the name and surname of the created Hero.
This is because we selected to retrieve these fields in the response within the `{ }` 
in the mutation query. This can easily be a server side generated field that the client may require.

Let’s now try deleting an entry:

```
mutation DeleteHero {
  deleteHero(id :3){
    name
    surname
  }
}    
```

Similar to the `createHero` mutation method we also retrieve the name and surname of the hero
we have deleted which is defined in { }.

### Creating Queries by fields

Queries can also be done on individual fields. For example, let’s create a method to 
query heroes by their last name.

Review the following snippet from the FilmResource class:

```
@Query
public List<Hero> getHeroesWithSurname(@DefaultValue("Skywalker") String surname) {
    return service.getHeroesBySurname(surname);
}  
```

By using the `@DefaultValue` annotation we have determined that the surname value 
will be Skywalker when the parameter is not provided.

Test the following queries with GraphiQL:

```
query heroWithDefaultSurname {
  heroesWithSurname{
    name
    surname
    lightSaber
  }
}
query heroWithSurnames {
  heroesWithSurname(surname: "Vader") {
    name
    surname
    lightSaber
  }
}       
```

## Conclusion
MicroProfile GraphQL enables clients to retrieve the exact data that is required preventing 
Over-fetching and Under-fetching.

The GraphQL API can be expanded without breaking previous queries enabling easy API evolution.




