# docker-ScripService
Spring Boot Rest JPA docker and docker compose with dependency to mysql container ( multi container)

## Essence of :
 - Multi container application with external db container
 - external volume for data
 - Docker File best practice for container
 - Docker Compose
 - Integrate with external logs


Refer to the following in this project :

1. Dockerfile
2. docker-compose.yml

For just one repo
==================
git config user.name "Satadru Basu"
git config user.email satadru.basu@gmail.com



GIT reference
=============
 git init   ( undo git init by : rm -rf .git )
 
 git add .  ( add the files to local repo and stages them for commit ) 
 
 git commit -m "commit"  ( git reset --soft HEAD~1 toRollback to previous state) 
 

 git remote add origin <remote repoURL>  ( Sets the new remote ) 
 
 git remote -v                ( Verifies the new remote URL ) 
 
 git push origin master 
 
 
 Docker Compose reference
 =========================
 Before we can use the docker stack deploy command we first run 
 
 ```docker swarm init```
 
 
 You need to give your app a name. Here, it is set to scripserviceapp
 
 
 ```docker stack deploy -c docker-compose.yml scripserviceapp```
 
 To check stack status
 
 ```docker stack ls```
 
 ```docker stack rm scripserviceapp```
 
 Rest Client Reference
 =======================
 1. Post data to add a scrip
 
 ```
 curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"scripId":"tatamotors","scripName":"Tata Motors Ltd","faceValue":"150"}' \
  http://node1:8087/api/scrips
 ```
 

