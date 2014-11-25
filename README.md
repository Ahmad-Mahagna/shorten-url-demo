# Easy Url Service (Prototype)

Easy url service is a lightweight shorten Url service, based on HTTP REST service.

#Features

  - support standard REST API
  - Simple algorithm and code 
  - uses IMDB (In memory DB )
  - Maven project for easy compilation and packaging
  - Include JSP pages for easier work


### Version
1.0.0


### Installation

You will need the following 

* a web service installed tomcat etc.
* Maven installed
* git
* IDE etc. intellij 

Thats all!



###Usage

#####REST API 

######Generate easy url request.

  Generate easy url let you submit a URL and get back shorten url 
  * A generate easy url request is an HTTP URL of the following form: 
 > POST   http://ip:port/make/json 
  
######request body param  (requires)    
* body text represent the url 
> Content-Type: text/plain.

* Note : currently request body is text/plain will be changed to be application/json (prototype currently)
######output
  output of generate easy url contains the following parameters.

> {"easyUrl":"http://easyUrl.me/yWCgbDo",
> "errorMessage":null,"httpState":200}


 * easyUrl : string represent shortener url. can be null if any error occured.
 * errorMessage :  string represent error message 
 * httpState :  request status e.g. 200 OK , 404 NOT_FOUND , 403 INVALID_URL , 503 INTERNAL_ERROR


   _____________
   
######Fetch original url request.
get original url by given easy url (shorten url) and get back corresponding original url.

* A fetch original url request is an HTTP URL of the following form: (HTTP GET) 
> GET http://ip:port/fetch/json?easyUrl=easyUrlvalue 

######request param (requires) 
easyUrl : Http GET request represent easy url.

######output
  output of fetch original url contains the following parameters.

> {"originUrl":"www.google.com/",
> "httpState":200,"errorMessage":null}


 * originUrl : string represent original url. can be null if any error occured.
 * errorMessage :  string represent error message 
 * httpState :  request status e.g. 200 OK , 404 NOT_FOUND , 403 INVALID_URL , 503 INTERNAL_ERROR

--------

#####UI 

In additional to Rest API we have adding user friendly GUI.
 * for generate easy URL 
 * for fetch original URL 

> GET http://ip:port/ 




### Development

Want to contribute? Great!
please contact us ,we need you :) 


### Todo's / next challange 

 - Write Tests
 - change algorithm
 - improve random generation
 - Add Code Comments
 - Validation on URL ...
 - improve requests ( remove null parameters , adding http status)
 - improve response ( contain also input from user and merge between all type  for make and fetch )
 - make request to generate urls json rather than plain/text to advanced parameters 
 - change UI ( challenge uses only one page ) 

License
----

GNU general public license


####what I have to do to Handle 100000  (large scale)  requests per seconds?

In order to handle this level of traffic The URL shortener service should be more mature and very efficiently. 

* store the data in Nosql database e.g. Redis key/value based rather than IMDB (in memory DB ) 
  this type of database used for quickly storing basic information, it is extremely performant ,efficient  easily scalable.

* processing any request must be quickly (accessing DB , processing business logic ) by caching data , reuse object /services , minimized initialize object / services , business logic should be clear , effcient and very fast.

* load balancing - allow us to add more instances of URL shortener service to handle more requests , application needs to designed to be scalable 

* currently the algorithm based on generate ID then convert it to base 62. we need to improve generate ID process to be more effcient by using UUID and other libraries to make sure that is unique . this algorith required high CPU during   
  processing the requests ,to reduce the amount of resources and processing time. option one is preparing generates easy urls pre-requests then all we need is to pick randomly  generated easy url and link it to in DB.
  option two if we need to save more time while generate easy urls another efficient way to generate easy URL by running    dictionary algorithm for all convention of 5-8 letters and numbers.the solution is based on our service and resources 

* application should be thread safe and each request need to be quickly 

* security issue block any user request more than 3 times in min . etc

* backup data (High avaiability mode ) and remove data after some period of time. ( 3 month)
