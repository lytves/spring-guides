# spring-restful-hello-word
Creating basic Spring RESTful application "Hello, World!"

Followed Spring starters guide [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/) which might help
 you to start familiar with Spring Framework  
 
 Was built a service that accept HTTP GET requests at:
 
 > http://localhost:8080/greeting
 
 and
 
 > http://localhost:8080/greeting?name=Username
 
 and responds with a JSON representation:
  
 > {"id":1,"content":"Hello, World!"}
 
 or 
 
 > {"id":2,"content":"Hello, Username!"}
 
 if user do request with **name** parameter
 
 It's a good way to get some basic knowledge of Spring services 