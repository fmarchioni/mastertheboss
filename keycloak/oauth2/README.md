# Keycloak OAUTH2 Example

Simple JAX-RS application with Keycloak to be run against Thorntail 

 ### Start the example application

```sh
 mvn clean install thorntail:run  -Dthorntail.keycloak.json.path=classpath:keycloak.json
```

### Access the secured resource

```sh
curl -v http://localhost:8080/app/secured
```

You'll get the response with `401 Unauthorized`. Let's get a Token to access it.

### Obtain token from Keycloak server

```sh

export access_token=$(\
    curl -X POST http://localhost:8180/auth/realms/demo-realm/protocol/openid-connect/token \
    --user customer-manager-client:mysecret \
    -H 'content-type: application/x-www-form-urlencoded' \
    -d 'username=customer-admin&password=admin&grant_type=password' | jq --raw-output '.access_token' \
 )

curl -H "Authorization: Bearer $access_token" http://localhost:8080/app/secured
```

### Access the secured resource with the token

```sh
curl -H "Authorization: Bearer $access_token" http://localhost:8080/app/secured
```

You'll get the response which contains `Hi user1, this is Secured Resource`.

 
