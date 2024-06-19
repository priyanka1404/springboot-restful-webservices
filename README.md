
postmapping  rest api methods to create user and store in db and how the saved user will be returned to client as been attached as reference pic:

<img width="1016" alt="Screenshot 2024-03-25 at 12 48 31 PM" src="https://github.com/priyanka1404/springboot-restful-webservices/assets/71031840/338d3537-ee1c-44a0-b320-17d2f86038ad">
<img width="1209" alt="Screenshot 2024-03-25 at 12 46 29 PM" src="https://github.com/priyanka1404/springboot-restful-webservices/assets/71031840/690f5e32-d682-4f2d-88bb-c99846a5e589">


#getById @GetMapping
<img width="1224" alt="Screenshot 2024-03-25 at 6 39 08 PM" src="https://github.com/priyanka1404/springboot-restful-webservices/assets/71031840/584019ab-0dc8-4e02-b476-d2d95bb006c9">

//to find all users
<img width="1224" alt="Screenshot 2024-03-25 at 6 39 08 PM" src="https://github.com/priyanka1404/springboot-restful-webservices/assets/71031840/ddeef0ea-9150-4010-8807-030fe2b3dafb">
// to update user info
<img width="1029" alt="Screenshot 2024-03-26 at 12 59 05 PM" src="https://github.com/priyanka1404/springboot-restful-webservices/assets/71031840/f06a652c-7e4e-41e6-a8ca-a0f0701364e8">
<img width="1224" alt="Screenshot 2024-03-26 at 12 58 42 PM" src="https://github.com/priyanka1404/springboot-restful-webservices/assets/71031840/8e8b10d4-e0ab-4246-a67a-88bb52c13a57">

//@deletemapping
<img width="1157" alt="Screenshot 2024-03-26 at 1 34 40 PM" src="https://github.com/priyanka1404/springboot-restful-webservices/assets/71031840/0ead5f90-1d3a-4e92-ad0e-2d90670a0257">
<img width="1028" alt="Screenshot 2024-03-26 at 1 35 05 PM" src="https://github.com/priyanka1404/springboot-restful-webservices/assets/71031840/40a9f6d6-5f12-4cc2-a3dd-59c9b0a0a03e">

#DTO CHANGES
//@POST MAPPING
<img width="1029" alt="Screenshot 2024-03-27 at 5 01 06 PM" src="https://github.com/priyanka1404/springboot-restful-webservices/assets/71031840/761bc893-5e82-48b6-8b98-90df67eceec8">

<img width="1156" alt="Screenshot 2024-03-27 at 5 00 52 PM" src="https://github.com/priyanka1404/springboot-restful-webservices/assets/71031840/f20eb7d3-198b-405d-9f2d-69cec041f41b">


#docker 
docker network

commands:

-> one container communicate with other container in docker network
->deploying spring boot in seperate container and mysql db in seperate container
(deploy them in same docker network to communicate with each other)

Springboot app---->Docker file----(docker build)--->Docker image------(docker run)------>docker img in docker container

(above app in seperate container)


Dockerhub ----(docker pull)----->mysql docker img--------------->mysql docker img in docker container

(above mysql db in seperate container)

1)first we need to pull docker img(mysql) from docker hub and run the container
2)commands
  a)docker pull mysql:latest
  b)docker images  // lists downloaded images in local system
  c)docker ps      // currently running containers
3)create docker network and provide name to deploy two containers
  a)docker network create springboot-db-ntwork
  b)docker network ls  // to check whether it is created or not it will lists all
  ********** output format *********** :
   NETWORK ID     NAME                   DRIVER    SCOPE
   a1bf9dc53f7b   bridge                 bridge    local
   adba6e818300   host                   host      local
   180e032ae5b9   none                   null      local
   bfa4acc4e0ac   springboot-db-ntwork   bridge    local
   *******************************
   -> Driver  type is bridge,in this two containers can communicate each other
   ->scope is local
4)we need to run docker img in docker container
  a)docker run --name mysqldb --network springboot-mysql-net  -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=employeedb -d mysql

   # docker run // to run the img in container
   # --name mysqldb  //providing the name of the container
   #--network springboot-mysql-net //providing name of the network
   #-e // for environment variable
   #-d // to run the container in background,d for detached
   #mysql //which is an image name

   b) docker ps //to check whether container is running in background or not
   c) docker logs -f mysqldb // to check logs of mysqldb
   d)we need to login(access) to mysql db in docker container
   e)docker ps//provides docker id eg:1b154e6ff72e
   f) docker exec -it 1b15 bash // it will give bash in docker container
   g)mysql -u  root -p  // enter given password:root
   h)show databases:
   ***********output **********
   mysql> show databases;
   +--------------------+
   | Database           |
   +--------------------+
   | employeedb         |
   | information_schema |
   | mysql              |
   | performance_schema |
   | sys                |
   +--------------------+
   5 rows in set (0.07 sec)

   ******************************




   strep 2:
   - use springboot-restful-webservices
   -build a  jar file
   ->create Dockerfile in root folder
   -> provide the instructions in Dockerfile to build docker image
   -> before building the docker image from docker file,we need to configure springboot application
   it should connect with mysql db in a docker container
   ->how to use profiles to configure spring boot application to deploy in docker environment

   ##How to use profile in springboot application
   ->to build dockerfile  to build docker image
   ->we need to configure  as per docker environment
   -> we need to create one more profile(application.properties ) in springboot application and we need to  activate it
   -> copy application.properties and paste it in resource folder again and change the name to
   application-docker.properties as per the environment
   ->existed config is localhost related now change according to docker environment and provide the container name(mysqldb)

   -> Docker ps // to check the containers
   *******output************
 CONTAINER ID   IMAGE          COMMAND                  CREATED        STATUS        PORTS                               NAMES
 1b154e6ff72e   mysql          "docker-entrypoint.s…"   4 hours ago    Up 4 hours    3306/tcp, 33060/tcp                 mysqldb
*********************************************

#change the below  local config to docker config:
spring.datasource.url:jdbc:mysql://localhost:3306/user_management

#as per docker config:
spring.datasource.url:jdbc:mysql://mysqldb:3306/employeedb
change password to root

# to activate the profile:
->Go to application.properties file
spring.profiles.active=docker
-> the maven  build the project




**********************************
   ## How to build the image
   Commands:
   ->#open terminal go to  docker file location and execute docker build commands

1)open the docker file location in terminal
2) cd springboot-restful-webservices(application)
3)docker build -t springboot-restful-webservices . // to build docker image,-t for tag,. represents current working directory
4)docker images // lists all  available images in machines with all info like(tag-version),size,creation time
5)docker build -t springboot-restful-webservices:0.1.RELEASE . // to change tag info
6)docker images // to check the images


  ************************************
## # how to run the docker img in docker container
   1)docker images
   2) docker ps // to check running containers
   3)docker run --network springboot-db-ntwork  --name springboot-db<img width="1369" alt="Screenshot 2024-06-19 at 5 28 42 PM" src="https://github.com/priyanka1404/springboot-restful-webservices/assets/71031840/a738643a-4f08-4b21-8f1a-d3ff39a15aaa">
-container -p 8003:8080 -d springboot-restful-webservices

    -p //to map container port with   host port
     ******output*******
     95c06376a3d41dac6d75942544e1c2dbc5b3aabbff86cd2ca06dc377a2eea174




***********************************************

4) docker logs -f springboot-restful-webservices // to tail logs'

or

   //4) docker logs -f 95c0 //to see docker logs,-f to tail the logs

   6)docker ps // to check whether container is running or not
   7)  docker stop 3375 //to stop docker container

   *****************


   ####crud operations#########
   post:http://localhost:8003/api/users


####crud operations output screenshots:###


<img width="853" alt="Screenshot 2024-06-19 at 5 28 05 PM" src="https://github.com/priyanka1404/springboot-restful-webservices/assets/71031840/33c3af7e-5f5c-47e9-b524-c76359f80700">
<img width="769" alt="Screenshot 2024-06-19 at 5 25 43 PM" src="https://github.com/priyanka1404/springboot-restful-webservices/assets/71031840/7c132840-20d4-4cf4-a10e-bf9b74a38ac3">
<img width="769" alt="Screenshot 2024-06-19 at 4 36 56 PM" src="https://github.com/priyanka1404/springboot-restful-webservices/assets/71031840/faa2b041-8d35-4ec6-b14d-b9bf5e1643fd">
<img width="767" alt="Screenshot 2024-06-19 at 4 35 02 PM" src="https://github.com/priyanka1404/springboot-restful-webservices/assets/71031840/49ffc528-9f4e-484d-804d-c7a41ae1a6ea">

<img width="1369" alt="Screenshot 2024-06-19 at 5 28 42 PM" src="https://github.com/priyanka1404/springboot-restful-webservices/assets/71031840/81336bf6-4af9-43f0-ab09-36f514855473">




















