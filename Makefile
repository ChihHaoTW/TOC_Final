LIB_FILE = .:org.json.jar
JFLAGS = -cp $(LIB_FILE)
JC = javac
JVM= java
JAR= jar cvfm
SCHNUM = F74006357
MF_FILE= manifest.mf
FILE=
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java -encoding UTF-8
CLASSES = \
	TocFinal.java

MAIN = TocFinal

default: classes

classes: $(CLASSES:.java=.class)

run: classes
	$(JVM) $(JFLAGS) $(MAIN)

jar: classes
	$(JAR) $(MAIN).jar $(MF_FILE) *.class

build:
	ant -buildfile /home/TOC/ANT/final/build.xml build -Darg $(SCHNUM)

clean:
	$(RM) *.class $(MAIN).jar
