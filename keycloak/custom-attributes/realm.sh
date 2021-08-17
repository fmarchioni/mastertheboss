./kcadm.sh config credentials --server http://localhost:8180/auth --realm master --user admin --password admin

./kcadm.sh create realms -s realm=security-realm -s enabled=true -o

./kcadm.sh create users -r security-realm -s username=customer-admin -s enabled=true -s email=mymail@admin.org

./kcadm.sh set-password -r security-realm --username customer-admin --new-password admin

./kcadm.sh create clients -r security-realm -s clientId=customer-manager-client -s secret="mysecret" -s "redirectUris=[\"http://localhost:8080/*\"]" -s enabled=true

./kcadm.sh create roles -r security-realm -s name=customer-manager

./kcadm.sh add-roles --uusername customer-admin --rolename customer-manager -r security-realm
