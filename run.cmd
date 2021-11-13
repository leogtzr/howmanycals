@echo off
:: leogtzr

java -XX:+PrintCommandLineFlags -XX:+PrintClassHistogram -Dlogback.configurationFile=logback.xml --enable-preview ^
	-jar "dist/howmanycals.jar" "muscle_gain.properties"

exit /b 0
