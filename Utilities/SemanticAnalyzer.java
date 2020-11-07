package Utilities;

import java.util.Stack;

import IRCode.*;
import SyntaxTree.*;

public class SemanticAnalyzer {
	private void PrintLine(
		String axLine) {
		System.out.println(axLine);
	}
	
	public int GetErrorCount() {
		return mErrorCount;
	}
	
	private int mErrorCount = 0;
	
	private Stack<FunctionInfo> mvFunctions = new Stack<FunctionInfo>();
	
	public void PushFunction(
		String axName,
		Type aReturnType) {
		FunctionInfo fFunctionInfo = new FunctionInfo(axName);
		fFunctionInfo.SetReturnType(aReturnType);
		mvFunctions.push(fFunctionInfo);
	}
	
	public void PopFunction() {
		mvFunctions.pop();
	}
	
	public void ValidateReturnType(
		IntrinsicTypeE aReturnType) {
		if (!VerifyAssignable(mvFunctions.peek().GetReturnType().GetIntrinsicType(), aReturnType)) {
			PrintLine("Return statement is incompatible with return type.");
		}
	}
	
	private boolean VerifyAssignable(
		Type aTo,
		Type aFrom) {
		// These need to be uncommented after we have resolved array declaration versus array usage (e.g., a[x] = b, a = b, a[x] = b[x], a = b[x]).
//		if (aTo.IsArray() ^ aFrom.IsArray())
//			return false;
//		
//		if (aTo.IsArray() && (((ArrayType)aTo).GetSize() != ((ArrayType)aFrom).GetSize()))
//			return false;
		
		IntrinsicTypeE fTo = aTo.GetUnderlyingType().GetIntrinsicType(),
				fFrom = aFrom.GetUnderlyingType().GetIntrinsicType();
	
		return VerifyAssignable(fTo, fFrom);
	}
	
	private boolean VerifyAssignable(
			IntrinsicTypeE aTo,
			IntrinsicTypeE aFrom)
	{		
		if (IntrinsicTypeE.Float == aTo) {
			// Skip. Float can accept everything.
		} else if (IntrinsicTypeE.Int == aTo) {
			if (IntrinsicTypeE.Float == aFrom) 
				return false;
		} else if (IntrinsicTypeE.Boolean == aTo) {
			switch (aFrom) {
			case Float:
			case Int:
				return false;
			}
		}
		
		return true;
	}
	
	public void ValidateAssignable(
			Type aTo,
			Type aFrom) {
		ValidateAssignable(aTo, aFrom.GetIntrinsicType());
	}
	
	public void ValidateAssignable(
			Type aTo,
			IntrinsicTypeE aFrom) {
		ValidateAssignable(aTo.GetIntrinsicType(), aFrom);
	}
	
	public void ValidateAssignable(
			IntrinsicTypeE aTo,
			IntrinsicTypeE aFrom) {
		if (!VerifyAssignable(aTo, aFrom))
			AddTypeMismatch();
	}
	
	public void PushLoop()
	{
		mvFunctions.peek().PushLoop(null, null);
	}
	
	public void PopLoop() {
		mvFunctions.peek().PopLoop();
	}
	
	public void ValidateInLoop()
	{
		if (0 == mvFunctions.peek().GetLoopCount()) {
			PrintLine("The break statement is not validate outside of a loop.");
			++mErrorCount;
		}
	}
	
	public void AddArgumentCountMismatch() {
		PrintLine("The number of arguments provided is different from the number of arguments needed.");
		++mErrorCount;
	}
	
	public void AddArrayMismatch() {
		PrintLine("The variable and the expression are not both arrays.");
		++mErrorCount;
	}
	
	public void AddExpressionNotBoolean() {
		PrintLine("The expression does not result in a boolean.");
		++mErrorCount;
	}
	
	public void AddTypeMismatch() {
		PrintLine("The types are mismatched.");
		++mErrorCount;
	}
}
