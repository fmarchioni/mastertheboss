# Build native application
mvn package -Pnative -Dnative-image.docker-build=true -DskipTests=true

# Create a new Quarkus project
oc new-project quarkus-hibernate

# Create a PostgreSQL application
oc new-app -e POSTGRESQL_USER=quarkus -e POSTGRESQL_PASSWORD=quarkus -e POSTGRESQL_DATABASE=quarkusdb postgresql

# Create a new Binary Build named "quarkus-hibernate"
oc new-build --binary --name=quarkus-hibernate -l app=quarkus-hibernate

# Set the dockerfilePath attribute into the Build Configuration
oc patch bc/quarkus-hibernate -p '{"spec":{"strategy":{"dockerStrategy":{"dockerfilePath":"src/main/docker/Dockerfile.native"}}}}'

# Start the build, uploading content from the local folder: 
oc start-build quarkus-hibernate --from-dir=. --follow

# Create a new Application, using as Input the "quarkus-hibernate" Image Stream:
oc new-app --image-stream=quarkus-hibernate:latest

# Expose the Service through a Route:
oc expose svc/quarkus-hibernate
