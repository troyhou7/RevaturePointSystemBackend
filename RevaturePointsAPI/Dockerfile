FROM centos
#speciffy a base image (background environment)
#create an environment that has centos in it

COPY . /workspace
#copy everything in current directory of this docker file
#put it in the virtual env under root directory /workspace

WORKDIR /workspace
#when you write commands, what directory they will execute in

RUN yum install -y maven
#any commands you need to run while building the image

WORKDIR /workspace/build/libs

EXPOSE 8080
#Allows container to be accessed on that port
#Should be port your app actually runs on

ENTRYPOINT ["java","-jar","RevaturePointsAPI-0.0.1-SNAPSHOT.jar"]
#the command that will execute when you create an instance of this image
#usually this would be the command to start the application
