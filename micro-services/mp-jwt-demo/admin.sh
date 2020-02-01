RESULT=`curl -k --data "grant_type=client_credentials&client_id=quarkus-client&client_secret=mysecret&username=admin&password=test" http://localhost:8180/auth/realms/quarkus-realm/protocol/openid-connect/token`
TOKEN=`echo $RESULT | sed 's/.*access_token":"\([^"]*\).*/\1/'`
echo $TOKEN
echo "-------------------------"
curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/jwt-demo-1.0.0-SNAPSHOT/rest/customers/goadmin
echo "-------------------------"
