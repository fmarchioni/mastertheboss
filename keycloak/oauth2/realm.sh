#Authenticate with the Admin Server    
./kcadm.sh config credentials --server http://localhost:8180/auth --realm master --user admin --password admin
#Create Realm Demo-Realm
./kcadm.sh create realms -s realm=demo-realm -s enabled=true -o
#Create User customer-admin
./kcadm.sh create users -r demo-realm -s username=customer-admin -s enabled=true -s email="joe@gmail.com"
#Set customer-admin password
./kcadm.sh set-password -r demo-realm --username customer-admin --new-password admin
#Create Client
./kcadm.sh create clients -r demo-realm -s clientId=customer-manager-client -s bearerOnly="false"   -s "redirectUris=[\"http://localhost:8080/*\"]" -s enabled=true -s directAccessGrantsEnabled=true -s clientAuthenticatorType=client-secret -s secret=mysecret
#Create Role customer-manager
./kcadm.sh create roles -r demo-realm -s name=customer-manager
#Assign Role to customer-admin 
./kcadm.sh add-roles --uusername customer-admin --rolename customer-manager -r demo-realm