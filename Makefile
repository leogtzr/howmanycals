.DEFAULT_GOAL := create

create:
	ant -file "build.xml" -Dnb.internal.action.name=rebuild clean jar

clean:
	ant -file "build.xml" -Dnb.internal.action.name=rebuild clean

run:
	java -XX:+PrintCommandLineFlags -XX:+PrintClassHistogram -Dlogback.configurationFile=logback.xml \
		--enable-preview -jar "dist/howmanycals.jar" "muscle_gain.properties"

