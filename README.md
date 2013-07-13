JBEBT stands for JBoss EJB Benchmarking Tool.

It is a simple application for testing remote invocations on @Clustered @Stateless and @Stateful beans with JBoss AS. To use it with EAP, you'll have to set up your EAP repositories and change version.jboss.as attribute to the version you desire to use.

To build it, invoke "mvn clean install" from the root directory of the project. This will compile, build and package the application. A functional jbebt-ejb.jar will appear in ./jbebt-ejb/target which you can deploy into your installation of AS or EAP. There are two ways to run the client part of the application:

1, Use the packaged jbebt-client.jar which will appear in the ./jbebt-client/target directory after invoking "mvn clean install". The best way to run it, is to use the maven exec plugin, like this:
    mvn exec:exec -Dexec.executable="java" -Dexec.args="-classpath %classpath org.jboss.test.jbebt.client.Client [arguments]"
where [arguments] is a list of arguments of your choice. These and their descriptions are printed if you run the application without arguments or you can look them up in help file: ./jbebt-client/src/main/resources/help.txt. It is also possible to run the jar file without the use of maven exec plugin, like this:
    java -cp jbebt-client:[dependencies] org.jboss.test.jbebt.client.Client [arguments]
in which case you'll have to provide jars on which the application depends. The best way to look these up is to go through the pom file of jbebt-client.

2, Assemble and run jbebt-client-jar-with-dependencies.jar with the use of maven assembly plugin. To do this, traverse into ./jbebt-client/ directory and invoke "mvn assembly:single". A packaged jar with all dependencies will appear in the target directory, which you can run simply:
    java -jar jbebt-cliet-jar-with-dependencies.jar [arguments]