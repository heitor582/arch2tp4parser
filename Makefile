#!make

JAVAC = javac
JAVA  = java
MAIN  = Main
all: run

compile:
	javac Main.java

run: compile
	java Main

clean:
	rm -f *.class