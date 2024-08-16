Basic Keycloak Hello World example
=====================================

###### Install Realm - Linux
```shell
./kcadm.sh config credentials --server http://localhost:8180 --realm master --user admin --password admin

./kcadm.sh create realms -s realm=MyRealm -s enabled=true -o

./kcadm.sh create users -r MyRealm -s username=frank -s enabled=true

./kcadm.sh set-password -r MyRealm --username frank --new-password admin

./kcadm.sh create clients -r MyRealm -s clientId=demo-keycloak -s publicClient="true"  -s "redirectUris=[\"http://localhost:8080/*\"]" -s enabled=true

./kcadm.sh create roles -r MyRealm -s name=Manager

./kcadm.sh add-roles --uusername frank --rolename Manager -r MyRealm
```


###### Install Realm - Windows
```shell
kcadm.bat config credentials --server http://localhost:8180  --realm master --user admin --password admin

kcadm.bat create realms -s realm=MyRealm -s enabled=true -o  

kcadm.bat create users -r MyRealm -s username=frank -s enabled=true

kcadm.bat set-password -r MyRealm --username frank --new-password admin

kcadm.bat create clients -r MyRealm -s clientId=demo-keycloak -s publicClient="true"  -s "redirectUris=[\"http://localhost:8080/*\"]" -s enabled=true

kcadm.bat create roles -r MyRealm -s name=Manager

kcadm.bat add-roles --uusername frank --rolename Manager -r MyRealm
```

###### Build and Deploy
```shell
mvn clean install wildfly:deploy  
```

 
