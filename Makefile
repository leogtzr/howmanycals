.DEFAULT_GOAL := create

create:
	ant -file "build.xml" -Dnb.internal.action.name=rebuild clean jar

clean:
	ant -file "build.xml" -Dnb.internal.action.name=rebuild clean

run:
	ant -file "build.xml" -Dnb.internal.action.name=run run
