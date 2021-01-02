docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 --name quarkus_test -e POSTGRES_USER=quarkus -e POSTGRES_PASSWORD=quarkus -e POSTGRES_DB=quarkusdb -p 5432:5432 postgres:10.5

