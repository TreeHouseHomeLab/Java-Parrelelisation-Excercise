JCC = javac
JAVA = java
JAVADOC = javadoc

JFLAGS = -d bin/ -g
SP = -sourcepath src/
CP = -cp bin/

.SUFFIXES: .java .class

.java.class:
	$(JCC) $(SP) $(CP) $(JFLAGS) $*.java

CLASSES =  \
			src/Timer.java \
			src/TreeData.java \
			src/SunlightAnalysis.java \
			src/ParallelAnalysis.java \
			src/map.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) bin/*.class

cleandocs:
	$(RM) doc/*

docs:
	$(JAVADOC) -d doc/ $(SP) src/$(PACKAGE)/*.java
