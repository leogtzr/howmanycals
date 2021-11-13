@echo off

ant -file "build.xml" -Dnb.internal.action.name=rebuild clean jar

exit /b 0

