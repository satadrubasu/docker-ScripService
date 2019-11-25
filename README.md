# docker-ScripService
Spring Boot Rest JPA docker and docker compose with dependency to mysql container ( multi container )

## Essence of :
 - Multi container application with external mysql db container
 - Map external volume for data ( logs in external mounted location )
 - Docker File best practice for container
 - Docker Compose
 - Docker compose to introduce a wait script before service boots and tries to connect to mysql
 - Docker Network in Compose
       By default Compose sets up a single network for your app. Each container for a service joins the default network and is both reachable by other containers on that network, and discoverable by them at a hostname identical to the container name.


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
 
 Rest Client Reference
 =======================
 1. Post data to add a scrip
 
 ```
 curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"scripId":"tatamotors","scripName":"Tata Motors Ltd","faceValue":"150"}' \
  http://node1:8087/api/scrips
 ```
 
 Setup in Docker Playground ( https://labs.play-with-docker.com  )
 ===========================
 
Install maven: apk add maven

Install JDK : apk add --no-cache openjdk8

git clone https://github.com/satadrubasu/docker-ScripService.git

mvn clean install

Docker create image or pull from dockerhub

docker pull mysql:latest
docker pull scripservice:latest
 
 ```docker build -t scripservice:latest .```
 
 Before we can use the docker stack deploy command we first run 
 
 ```docker swarm init```
 
 ```docker swarm init --advertise-addr eth0```
 
 
 You need to give your app a name. Here, it is set to scripapp
  
 ```docker stack deploy -c docker-compose.yml scripapp```
 
 To check stack status
 
 ```docker stack ls```
 
 ```docker stack rm scripapp```
 
|Date|Tims Folder |Total TC | Automatable | Non-Automatable |  Completed | InProgress | Not Started | Comments |
|---|---|---|---|---|---|---|---|---|
2019-11-25T13:04:49.459Z | Noisy CPU | 6 | 6 | 0 | 3 | 0 | 0 | |
2019-11-25T13:04:59.745Z | Noisy IO | 9 | 9 | 0 | 7 | 0 | 0 | |
2019-11-25T13:05:07.851Z | Chaos Testing | 5 | 5 | 0 | 3 | 2 | 0 | |

