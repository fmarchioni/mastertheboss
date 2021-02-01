#!/bin/bash

cd /opt/jboss/keycloak/bin


./kcadm.sh config credentials --server http://localhost:8180/auth --realm master --user admin --password admin
./kcadm.sh create realms -s realm=wildfly-realm -s enabled=true -o
./kcadm.sh create users -r wildfly-realm -s username=demo -s enabled=true
./kcadm.sh set-password -r wildfly-realm --username demo --new-password demo
./kcadm.sh create clients -r wildfly-realm -s clientId=simple-webapp -s publicClient="true"  -s "redirectUris=[\"http://localhost:8080/simple-webapp/*\"]" -s enabled=true
./kcadm.sh create roles -r wildfly-realm -s name=Users
./kcadm.sh add-roles --uusername demo --rolename Users -r wildfly-realm