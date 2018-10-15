// Http import (Required for all Node JS files)
var http = require('http');

// File System Import
var fs = require("fs");

// URL import
var url = require('url');

// Creating a server
http.createServer( function (request, response) {  
   // Parsing the request containing file name
   var pathname = url.parse(request.url).pathname;
   
   // printing the File Names
   console.log("Request for " + pathname + " received.");
   
   // Reading the content from the file
   fs.readFile(pathname.substr(1), function (err, data) {
      if (err) {
         console.log(err);
         // HTTP Status: 404 : NOT FOUND
         // Content Type: text/html
         response.writeHead(404, {'Content-Type': 'text/html'});
      }else {	
         //Page found	  
         // HTTP Status: 200 : OK
         // Content Type: text/html
         response.writeHead(200, {'Content-Type': 'text/html'});	
         
         // Writing the content of the file to response body
         response.write(data.toString());		
      }
      // Ending the Response
      response.end();
   });   
}).listen(8080);