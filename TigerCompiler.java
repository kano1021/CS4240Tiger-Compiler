// Generated from TigerParser.g4 by ANTLR 4.7.2

import java.io.*;
import java.util.List;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import ANTLR.*;
import SyntaxTree.*;
import Utilities.*;
import Visitors.ProgramVisitor;

/**
 * This class provides an empty implementation of {@link TigerParserListener},
 * which can be extended to create a listener which only needs to handle a subset
 * of the available methods.
 */
public class TigerCompiler {
	public static void main(String avArgs[]) {
		try {
			String fxFileNameWithoutExtension = avArgs[0].replaceFirst("[.][^.]+$", "");
			
			FileReader fReader = new FileReader(avArgs[0]);
			TigerLexer fTigerLexer = new TigerLexer(CharStreams.fromReader(fReader));
			TokenStream fTokenStream = new CommonTokenStream(fTigerLexer);
			
			TigerParser fParser = new TigerParser(fTokenStream);
			ProgramVisitor fProgramVisitor = new ProgramVisitor(SymbolTable.GetGlobal());
			LetStatement fTigerProgram = fProgramVisitor.visit(fParser.tiger_program());
			
			SemanticAnalyzer fSemanticAnalyzer = new SemanticAnalyzer();
			fSemanticAnalyzer.PushFunction("main", new IntrinsicType(IntrinsicTypeE.Void));
			fTigerProgram.AnalyzeSemantics(null, fSemanticAnalyzer);
			if (0 == fSemanticAnalyzer.GetErrorCount())
			{
				System.out.println("No semantic errors.");
				BackendManager fBackend = new BackendManager();
				FileWriter fFileWriter = new FileWriter(fxFileNameWithoutExtension + ".ir");
				PrintWriter fIRPrintWriter = new PrintWriter(fFileWriter);
				IRWriter fIRWriter = new IRWriter(fIRPrintWriter);
				fBackend.AddOpCodeWriter(fIRWriter);
				
				fFileWriter = new FileWriter(fxFileNameWithoutExtension + ".naive");
				PrintWriter fNaivePrintWriter = new PrintWriter(fFileWriter);
				NaiveMIPSWriter fNaive = new NaiveMIPSWriter(fNaivePrintWriter);
				fBackend.AddOpCodeWriter(fNaive);

				fFileWriter = new FileWriter(fxFileNameWithoutExtension + ".cfg");
				CFGMIPSAccumulator fCFG = new CFGMIPSAccumulator();
				fBackend.AddOpCodeWriter(fCFG);

				fTigerProgram.Compile(fBackend);
				
				fIRPrintWriter.flush();
				fIRPrintWriter.close();
				
				fNaivePrintWriter.flush();
				fNaivePrintWriter.close();
				
				fCFG.ConvertToBasicBlocks();
				PrintWriter fCFGPrintWriter = new PrintWriter(fFileWriter);
				fCFGPrintWriter.flush();
				fCFGPrintWriter.close();
			}
			
			fTigerProgram.PrintSymbolTable(0);
		}
		catch (FileNotFoundException feFNE) {
			System.err.println("The input file was not found.");
		}
		catch (IOException feIO) {
			System.err.println("There was an IO problem.");
		}
	}
}