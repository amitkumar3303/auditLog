# auditLog

1. Take the project in STS of Intellij
2. Run the application
3. use postman for making post call
4. request body for post call e.g

{
"path":"C:\\Users\\Amit_Kumar100\\Downloads\\smallProject\\logFile.txt"
}
5. you can copy the logFile.txt from git project  path
 src\test\resources
6. in step 4 give the same path where you put the logFile.txt
7.URL to put in post call:http://localhost:8080/audit/auditvalidationlog

8.You wil get the response in postman.
9. In order to check the value in DB use below url
http://localhost:8080/h2-console/
10. for password use:   sa
11. run query: select * from AUDIT_LOG ;

For further clarification mail me:
amitkumar3303@gmail.com
Linkedin:
https://www.linkedin.com/in/amit-kumar-3303
