build:
	./gradlew build
run-dist:
	./gradlew installDist
	./build/install/app/bin/app
report:
	./gradlew test jacocoTestReport
checkstyle:
	./gradlew checkstyleMain checkstyleTest

.PHONY: build run-dist report checkstyle
