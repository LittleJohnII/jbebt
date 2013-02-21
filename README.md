JBEBT stands for JBoss Ejb Benchmarking Tool. This is a simple application for testing clustered stateless and stateful beans. It contains a server-side beans implementation and a standalone client which remotely invoked those beans.

Build with mvn clean install, then deploy the jboss-ejb/target/jboss-ejb.jar to your jboss instance. To run the client, descend into jbebt-client directory, compile with mvn compile and run with mvn exec:exec. Alternatively, you can run the jar that was built when you installed the application:
(in jboss-client/target directory)
java -cp jbebt-client:[all other dependencies] org.jboss.test.jbebt.client.Client

List of "all other dependencies" and possibly a script to run the client may come with later commits. jboss-ejb-client.properties is located in jbebt-client/src/main/resources/.
