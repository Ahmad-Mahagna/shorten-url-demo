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
 - improve requests ( remove null parameters ) 
 - make request to generate urls json rather than plain/text to advanced parameters 
 - change UI ( challenge uses only one page ) 

License
----

GNU general public license
