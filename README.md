## Refer to master branch for base information
Whats different in this branch against master ( Autoscale concept explored ).
Note: exercise done based of docker compose

  1. NGINX for load balance
  2. Centralized Logging solved for external log of scaled microservices.
     ( Note a separate branch to have changes as dockerplayhub doesnt have fluentd )
  
  
  
### 1. NGINX Load Balancer injected to Docker Compose

This branch is to hold nginx loadbalancer to be able to scale the instances of scripService.
To add the load balancer to our Docker Compose system configuration, we create the following nginx.conf
file in the same directory as the docker-compose.yml file:
   refer to nginx.conf
   
   This will configure NGINX to forward the request from port 9090 to http://scripservicecontainer:8080 
   Docker’s embedded DNS server resolves scripservicecontainer ( round robin )
   to resolve the DNS requests based on the service name.
   
 As NGINX service will handle incoming requests & distribute to a scripservicecontainer service, no need to map port 8080
  from the container to host. Remove port mapping config from Docker-Compose file and 
  only expose the port 8080 to linked services.
  
    In order to LOAD the NGINX configuration file we just created, we have to 
       mount it as a volume in the nginx service and add port mappings to the host container for that server. 
    In this example, we configured NGINX to listen on the port 9090, which is why we have to add port mappings for this port.
    
    
### 2. Centralized Logging in scalable microservices + docker

Reference: https://hackernoon.com/monitoring-containerized-microservices-with-a-centralized-logging-architecture-ba6771c1971a

Approach : approach is to gather the logs from each microservice in a central searchable database.
 A microservice should not need to know where its logs are going. The execution environment should handle that. 
 This way, we can change the destination of logs without modifying every single microservice
 [ microservices should log to stdout or stderr]
 
 
  [Service1 Container]
     |
     |
  [Docker Daemon 
      [ daemon.json-->fluentd(logDriver)---]----->  [ fluentd Container -]-------> ELK as Service   
    ]     

    
The logs of each service are forwarded to a log-driver which eventually sends them to a dedicated log-shipping container.
 The shipper can manipulate the logs before persisting them in a store. Finally, the developer can query this datastore to 
 visualize and analyze the logs.

How do the containers ‘know’ to send the logs to a log-driver. What is a log-driver?
  Fluentd ?

####   How logging works in Docker ( hackernoon excerpt )
When the docker daemon runs a container, it sends every event stream from that container to a log-driver. 
By default, it uses the driver specified in the daemon.json file. 

We can specify a different driver for each container during launch.

On receiving a log stream, the log-driver can do whatever it likes. For instance, the default log-driver json-file
persists the logs from each container to a file on the host machine.
The daemon ships with a few log-drivers, but you can add more using plugins.
 Can check the active log-driver and the installed plugins with the command 
 ```docker info``` 
 , then search for “Logging Driver” and “Plugins”.

So it is a best practice for microservices to log to stdout.Delegate the responsibility of log routing to the environment, that is the docker daemon and log-driver. 
  
#### Putting it all together
The docker daemon sends the event stream from each container to the fluentd log-driver which is one of the preinstalled log plugins.
The fluentd log-driver is configured to send the logs to a UDP/TCP address on which the dedicated log-shipping container is listening.
On receiving the logs, the shipper–a container running the fluentd application–parses, aggregates, and sends the logs to an Elasticsearch cluster hosted as a service. Some alternatives to fluentd are Logstash and Filebeat.
Elasticsearch indexes the logs.
The developer then uses Kibana to query Elasticsearch and create cool visualizations from the log data. 
    
    