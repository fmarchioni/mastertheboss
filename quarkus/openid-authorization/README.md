
export access_token=$(curl --insecure -X POST http://localhost:8180/realms/demorealm/protocol/openid-connect/token --user demorealm-client:secret -H 'content-type: application/x-www-form-urlencoded' -d 'username=joe&password=password&grant_type=password' | jq --raw-output '.access_token' )

 curl -v -X GET http://localhost:8080/api/dev -H "Authorization: Bearer "$access_token

 export access_token=$(curl --insecure -X POST http://localhost:8180/realms/demorealm/protocol/openid-connect/token --user demorealm-client:secret -H 'content-type: application/x-www-form-urlencoded' -d 'username=alice&password=password&grant_type=password' | jq --raw-output '.access_token' )

curl -v -X GET  http://localhost:8080/api/jr  -H "Authorization: Bearer "$access_token  
