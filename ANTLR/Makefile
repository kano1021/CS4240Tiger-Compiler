main: TigerLexer.class TigerParser.class
	javac -cp antlr-4.7.2-complete.jar *.java

TigerLexer.class: TigerLexer.java

TigerLexer.java: TigerLexer.g4
	java -jar ../antlr-4.7.2-complete.jar TigerLexer.g4

TigerParser.class: TigerParser.java

TigerParser.java: TigerParser.g4
	java -jar ../antlr-4.7.2-complete.jar -visitor TigerParser.g4

clean: 
	rm *.class
	rm TigerParser.java
	rm TigerLexer.java

deliverables: TigerRecognizer.java TigerParser.java TigerLexer.java
	cp TigerRecognizer.java ./Deliverables/
	cp TigerParser.java ./Deliverables/
	cp TigerParser.g4 ./Deliverables/
	cp TigerLexer.java ./Deliverables/
	cp TigerLexer.g4 ./Deliverables/
		