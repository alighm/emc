# Virtustream Fibonacci Web Service

The following project demonstrates the fibonacci logic provided as a RESTful Web Service. This application is written in Java and is using Spring Framework. There are 2 different implementations for the Fibonacci logic as part of the **_EmcService_** service component interface.

### Building and Running the Web Service Application
------
In order to run this project locally, you will need Java and Maven.

First you need to build the project:
```sh
$ mvn clean install
```

> This command will build and run all tests. A successful build will show a "BUILD SUCCESS" along with total time spent, finishing time and final memory usage.

At this point, you should now have a build available to execute.Lets check and see if the jar file exist:
```sh
$ ls -alrt target/
```
You should now see **cloud-0.0.1-SNAPSHOT.jar** in the directory listing. To run the application you just need to execute: 
```sh
$ java -jar target/cloud-0.0.1-SNAPSHOT.jar
```
At this point the Web Service is up and running on port **8080**
### Fibonacci API Request
------
By default the application will be running on port 8080. The request must be in json format and as follow:

```sh
$ curl -H "Content-Type: application/json" -X POST -d '{"size":5}' http://localhost:8080/fibonacci
```
The response will be the fibonacci series as big as the size provided:
```sh
$ [0,1,1,2,3]
```
If provided an invalid integer, say -4 for example, the response will be an error along with a response code of **Bad Request (_400_)** 
```sh
* Connected to localhost (::1) port 8080 (#0)
> POST /fibonacci HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.43.0
> Accept: */*
> Content-Type: application/json
> Content-Length: 11
>
* upload completely sent off: 11 out of 11 bytes
< HTTP/1.1 400 Bad Request
< Server: Apache-Coyote/1.1
< Content-Type: application/json;charset=UTF-8
< Transfer-Encoding: chunked
< Date: Sun, 31 Jan 2016 02:07:52 GMT
< Connection: close
<
* Closing connection 0
{"errors":{"size.invalid":"size is lesser than 0. Please provide a positive number"}}
```
### Code Coverage Report
------
In order to build the reporting for code coverage, execute the following command:
```sh
$ mvn cobertura:cobertura
```
After the execution, you should see an **_index.html_** page in target/site/cobertura folder. This index.html page shows all the coverage of test cases in the source code.

![Code Coverage](https://raw.githubusercontent.com/alighm/emc/master/images/Screen%20Shot%202016-01-30%20at%205.52.05%20PM.png)

