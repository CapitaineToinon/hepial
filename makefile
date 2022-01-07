PWD=.
SRC=$(PWD)/src
BIN=$(PWD)/bin
LIB=$(PWD)/lib

JAVA=java
JAVAC=javac
JFLEX=jflex
JAVACUP=$(LIB)/java-cup-11a.jar
CLASSPATH=$(JAVACUP):$(SRC)

FILE_FLEX=$(SRC)/main/main.flex
FILE_CUP=$(SRC)/main/main.cup
FILE_JAVA_NAME=Lexer

all: sym.class parser.class $(FILE_JAVA_NAME).class

$(FILE_JAVA_NAME).java : $(FILE_FLEX)
	$(JFLEX) $(FILE_FLEX)

sym.java parser.java: $(FILE_CUP)
	$(JAVA) -jar $(JAVACUP) -destdir $(SRC)/main $(FILE_CUP)

%.class: %.java
	$(JAVAC) -d $(BIN) -cp $(CLASSPATH) $(SRC)/main/$<

clean:
	rm -rf $(BIN)/* $(SRC)/**/*.class $(SRC)/main/parser.java $(SRC)/main/sym.java $(SRC)/main/$(FILE_JAVA_NAME).java $(SRC)/main/$(FILE_JAVA_NAME).java~
