JBEBT stands for JBoss Ejb Benchmarking Tool. This is a simple application for testing clustered stateless and stateful beans. It contains a server-side beans implementation and a standalone client which remotely invoked those beans.

Build with mvn clean install, then deploy the jboss-ejb/target/jboss-ejb.jar to your jboss instance/instances. To run the client, descend into jbebt-client directory, compile with mvn compile and run with mvn exec:exec. Alternatively, you can run the jar that was built when you installed the application:
(in jboss-client/target directory)
java -cp jbebt-client:[all other dependencies] org.jboss.test.jbebt.client.Client

The whole command to run the client configured for repeated invocation of stateless beans (not creation of stateless beans would be)

mvn exec:exec -Dexec.executable="java" -Dexec.args="-classpath %classpath org.jboss.test.jbebt.client.Client [arguments]"

where possible [arguments] are:
	contest     		invokes creation of [repeat] number of stateful and then stateless beans and measures elapsed time
	creation    		counts to [repeat] with stateful and then stateless beans and measures elapsed time
	contest-s   		invokes creation of [repeat] number of stateful and then stateless beans and measures elapsed time, this option is simultaneous: threads=1 is default
	creation-s  		counts to [repeat] with stateful and then stateless beans and measures elapsed time, this option is simultaneous: threads=1 is default
	stateless   		counts to [repeat] with stateless beans and measures elapsed time
	stateful    		counts to [repeat] with stateful beans and measures elapsed time, good for testing failover (with long sleeptime)
	stateless-c 		invokes creation of [repeat] number of stateless beans and measures elapsed time
	stateful-c  		invokes creation of [repeat] number of stateful beans and measures elapsed time
	repeat=x    		defines how many times will an action be repeated, should be > 0
	sleep=x     		defines how long a delay between two actions should be, in miliseconds, 0 - no delay, should be >= 0
	threads=x   		defines how many threads will be used to generate the load, to get the number of total actions invoked, multiply [repeat] * [threads] or 2 * [repeat] * [threads] for contest and creation options
	verbose     		switches the program into verbose mode - some more output will be generated - good for debug

Note that you should always specify exactly one of the following options: contest, creation, contest-s, creation-s, stateless, statefull, stateless-c, stateful-c

List of dependencies and possibly a script to run the client may come with later commits. jboss-ejb-client.properties is located in jbebt-client/src/main/resources/.

