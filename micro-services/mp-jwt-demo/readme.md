# WildFly JWT Demo (WIP)

Example of OpenID secured Client with JWT authentication. JWTs are issued by Keycloak and contain
claims with general user information as well as current user roles.

## Run Keycloak
```
docker run --rm  \
   --name keycloak \
   -e KEYCLOAK_USER=admin \
   -e KEYCLOAK_PASSWORD=admin \
   -e KEYCLOAK_IMPORT=/tmp/myrealm.json  -v /tmp/myrealm.json:/tmp/myrealm.json \
   -p 8180:8180 \
   -it quay.io/keycloak/keycloak:7.0.1 \
   -b 0.0.0.0 \
   -Djboss.http.port=8180 \
   -Dkeycloak.profile.feature.upload_scripts=enabled  
```
Users available:
- Admin (admin/test) belonging to "admin" group
- User (test/test) belonging to "user" group

NOTE: Copy the file quarkus-realm.json in your /tmp path

## Start WildFly
```
./standalone.sh
```

## Deploy the application
```
mvn install wildfly:deploy
```

## Test the application
```
#Get a token for an user belonging to "user" group
export TOKEN=$(\
curl -L -X POST 'http://localhost:8180/auth/realms/myrealm/protocol/openid-connect/token' \
-H 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'client_id=jwt-client' \
--data-urlencode 'grant_type=password' \
--data-urlencode 'client_secret=mysecret' \
--data-urlencode 'scope=openid' \
--data-urlencode 'username=test' \
--data-urlencode 'password=test'  | jq --raw-output '.access_token' \
 )

#Display token
JWT=`echo $TOKEN | sed 's/[^.]*.\([^.]*\).*/\1/'`
echo $JWT | base64 -d | python -m json.tool

#Test user method (should pass)
curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/jwt-demo-1.0.0-SNAPSHOT/rest/customers/gouser

#Test admin method (should fail)
curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/jwt-demo-1.0.0-SNAPSHOT/rest/customers/goadmin
```

