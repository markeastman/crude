# crude
A CRUD Editor for JPA

## Building
In the CrudeExample folder execute
mvn package

## Running the application
In the CrudeExample folder execute the following command(s)

java -cp target/CrudeExample-1.0-SNAPSHOT.jar:target/lib/* uk.me.eastmans.editor.CrudeEditor listEntities

java -cp target/CrudeExample-1.0-SNAPSHOT.jar:target/lib/* uk.me.eastmans.editor.CrudeEditor listEntity Consumption

java -cp target/CrudeExample-1.0-SNAPSHOT.jar:target/lib/* uk.me.eastmans.editor.CrudeEditor listEntity Owner

## Things yet to be done

* At the moment the domian and populating the domain is contained within this app,
we need to move it to another separate jar file and have the main app 
connect to it so that we can run the editor against any domain config and jar file.
* Next step is to start using the metaData to work on what columns we can display and how to navigate the columns. We
could let the app take http GET, POST etc. requests do manipulate the domain 