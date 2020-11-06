tiger: main
	java TigerCompiler phase1-part1-checkin.tiger

main: TigerCompiler.class
	$(MAKE) -C ANTLR
	javac -cp antlr-4.7.2-complete.jar *.java

TigerCompiler.class: TigerCompiler.java

clean: 
	rm *.class
	rm TigerParser.java
	rm TigerLexer.java

deliverables: TigerCompiler.java TigerParser.java TigerLexer.java
	cp TigerCompiler.java ./Deliverables/
	cp TigerParser.java ./Deliverables/
	cp TigerParser.g4 ./Deliverables/
	cp TigerLexer.java ./Deliverables/
	cp TigerLexer.g4 ./Deliverables/
		