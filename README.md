#How to build and run application

cd to project root folder
mvn clean install
mvn spring-boot:run


#This application is a Spring Boot App running on H2 Database, exposes the below endPoints 

Add Invoice POST http://localhost:8080/invoices
View All invoices GET http://localhost:8080/invoices
View Invoice GET http://localhost:8080/invoices/{invoiceId}

#To Add invoices please see sample request xml/json

{
"client":"SampleClientJSON",
"vatRate":15,
"LineItems":[{"quantity":2,"description":"desc","unitPrice":15.99}, 
     {"quantity":7,"description":"description","unitPrice":2.79}]
 }  
 
 <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
 <invoice>
     <client>SampleClientXML</client>
     <vatRate>15</vatRate>
     <lineItems>
         <quantity>2</quantity>
         <description>desc</description>
         <unitPrice>15.99</unitPrice>
     </lineItems>
     <lineItems>
         <quantity>7</quantity>
         <description>description</description>
         <unitPrice>2.79</unitPrice>
     </lineItems>
 </invoice>

  