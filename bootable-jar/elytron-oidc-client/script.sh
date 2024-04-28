./kcadm.sh config credentials --server http://localhost:8180 --realm master --user admin --password admin

./kcadm.sh create realms -s realm=wildfly-realm -s enabled=true -o

./kcadm.sh create users -r wildfly-realm -s username=customer-admin -s enabled=true

./kcadm.sh set-password -r wildfly-realm --username customer-admin --new-password admin

./kcadm.sh create clients -r wildfly-realm -s clientId=customer-manager-client -s publicClient="true"  -s "redirectUris=[\"http://localhost:8080/*\"]" -s enabled=true

./kcadm.sh create roles -r wildfly-realm -s name=customer-manager

./kcadm.sh add-roles --uusername customer-admin --rolename customer-manager -r wildfly-realm
