Basic Keycloak Hello World example
=====================================

###### Install Realm
```shell
./kcadm.sh config credentials --server http://localhost:8180/auth --realm master --user admin --password admin

./kcadm.sh create realms -s realm=MyRealm -s enabled=true -o

./kcadm.sh create users -r MyRealm -s username=frank -s enabled=true

./kcadm.sh set-password -r MyRealm --username frank --new-password admin

./kcadm.sh create clients -r MyRealm -s clientId=demo-keycloak -s publicClient="true"  -s "redirectUris=[\"http://localhost:8080/*\"]" -s enabled=true

./kcadm.sh create roles -r MyRealm -s name=Manager

./kcadm.sh add-roles --uusername frank --rolename Manager -r MyRealm
```

###### Build and Deploy
```shell
mvn clean install wildfly:deploy  
```

 
