# crude
A CRUD Editor for JPA

## Building
In the CrudeExample folder execute:

    mvn package

## Running the application
In the CrudeExample folder execute the following command(s):

    java -cp target/CrudeExample-1.0-SNAPSHOT.jar:target/lib/* uk.me.eastmans.editor.CrudeEditor listEntities

    java -cp target/CrudeExample-1.0-SNAPSHOT.jar:target/lib/* uk.me.eastmans.editor.CrudeEditor listEntity Consumption

    java -cp target/CrudeExample-1.0-SNAPSHOT.jar:target/lib/* uk.me.eastmans.editor.CrudeEditor listEntity Owner

    java -cp target/CrudeExample-1.0-SNAPSHOT.jar:target/lib/* uk.me.eastmans.editor.CrudeEditor listEntity Artefact

## CrudeWeb

Within the CrudeWeb folder we have an embedded tomcat that uses servlets and JSP files to 
display information. We can now start to expand this by adding the functionality of the 
metadata evaluator

To build and run the tomcat webapp, use:

    mvn compile assembly:single
    java -jar target/CrudeWeb-jar-with-dependencies.jar

You can check the web app by using the following URLs
    
    http://localhost:8080/CrudeWeb/index.jsp
    http://localhost:8080/CrudeWeb/hello
    http://localhost:8080/CrudeWeb/entities

The last URL should show you the list of Entity types loaded into the JPA layer

## Things yet to be done
