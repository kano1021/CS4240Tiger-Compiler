package Utilities;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import IRCode.Label;
import SyntaxTree.ConstantLiteral;
import SyntaxTree.FunctionDeclaration;
import SyntaxTree.OperatorE;
import SyntaxTree.VariableSymbol;

public class NaiveMIPSWriter extends OpCodeWriter {
    private Dictionary<VariableSymbol, VariableRegisterInfo> mvVariableToRegisterMap 
    	= new Hashtable<VariableSymbol, VariableRegisterInfo>();
    private VariableRegisterInfo[] mvRegisterToVariableMap = new VariableRegisterInfo[28];	// [0] will always be null. [1] is reserverd.
    private final int LOAD_IMMEDIATE_REGISTER = 1;
    private final int START_REGISTER = 2;
	
	private List<OpCodeWriter> mvOpCodeWriters = new ArrayList<OpCodeWriter>();

	private MIPSAssemblyTextWriter mWriter = null;
	private PrintWriter mOutput = null;
	
	public NaiveMIPSWriter(
		PrintWriter aOut) {
		mWriter = new MIPSAssemblyTextWriter(aOut);
	}
		
	@Override
	public void EmitCall(
			FunctionDeclaration aFunction,
			VariableSymbol aStoreReturn,
			VariableSymbol[] avArguments) {
		for (int fiIterator = 0; fiIterator < avArguments.length; ++fiIterator)
			mWriter.EmitPush(GetRegister(avArguments[fiIterator]));
		
		mWriter.EmitCall(aFunction.GetName());
		
		if (null != aStoreReturn) {
			Integer fStoreToRegisterBoxed = TryGetRegister(aStoreReturn);
			if (null == fStoreToRegisterBoxed)
			{
				mWriter.EmitLoadImmediate(LOAD_IMMEDIATE_REGISTER, aStoreReturn.GetAddress());
				mWriter.EmitStore(2, LOAD_IMMEDIATE_REGISTER, 0);
			}
			else
				mWriter.EmitMoveRegister(2, fStoreToRegisterBoxed.intValue());
		}
		
		for (int fiIterator = avArguments.length - 1; fiIterator >= 0; --fiIterator)
			mWriter.EmitPop(GetRegister(avArguments[fiIterator]));
	}
	
	private int mLastSeen = 0;
	
	private Integer TryGetRegister(
			VariableSymbol aToLookup) {
		VariableRegisterInfo fRegisterObject = GetRegisterInfo(aToLookup);
		if (VariableRegisterInfo.INVALID_REGISTER == fRegisterObject.CurrentRegister)
			return null;
		
		return new Integer(fRegisterObject.CurrentRegister);
	}
	
	private VariableRegisterInfo GetRegisterInfo(
			VariableSymbol aToLookup)
	{
		VariableRegisterInfo fRegisterObject = mvVariableToRegisterMap.get(aToLookup);
		if (null == fRegisterObject) {
			// Not seen before.
			fRegisterObject = new VariableRegisterInfo(aToLookup);
			mvVariableToRegisterMap.put(aToLookup, fRegisterObject);
		}
		
		fRegisterObject.LastUsed = mLastSeen++;
		return fRegisterObject;
	}
	
	private int GetRegister(
		VariableSymbol aToLookup) {
		VariableRegisterInfo fRegisterObject = GetRegisterInfo(aToLookup);
		
		if (VariableRegisterInfo.INVALID_REGISTER == fRegisterObject.CurrentRegister) {
			// Not already in registers.
			for (int fiIterator = START_REGISTER; fiIterator < mvRegisterToVariableMap.length; ++fiIterator) {
				if (null == mvRegisterToVariableMap[fiIterator])
				{
					// Found an open register.
					fRegisterObject.CurrentRegister = fiIterator;
					mvRegisterToVariableMap[fiIterator] = fRegisterObject;
					return fRegisterObject.CurrentRegister;
				}
			}
			
			// No open registers. Find the oldest variable.
			VariableRegisterInfo fVRI = mvRegisterToVariableMap[1];
			int fOldestValue = fVRI.LastUsed,
				fOldestIndex = 1;
			for (int fiIterator = START_REGISTER + 1; fiIterator < mvRegisterToVariableMap.length; ++fiIterator) {
				fVRI = mvRegisterToVariableMap[fiIterator];
				if (fOldestValue > fVRI.LastUsed)
				{
					fOldestValue = fVRI.LastUsed;
					fOldestIndex = fiIterator;
				}
			}
			
			fVRI = mvRegisterToVariableMap[fOldestIndex];
			mWriter.EmitLoadImmediate(LOAD_IMMEDIATE_REGISTER, fVRI.Variable.GetAddress());
			mWriter.EmitStore(fVRI.CurrentRegister, LOAD_IMMEDIATE_REGISTER, 0);

			fVRI.CurrentRegister = VariableRegisterInfo.INVALID_REGISTER;
						
			mvRegisterToVariableMap[fOldestIndex] = fRegisterObject;
			fRegisterObject.CurrentRegister = fOldestIndex;
			mWriter.EmitLoadImmediate(LOAD_IMMEDIATE_REGISTER, fVRI.Variable.GetAddress());
			mWriter.EmitLoad(LOAD_IMMEDIATE_REGISTER, 0, fRegisterObject.CurrentRegister);
		}
		
		return fRegisterObject.CurrentRegister;
	}
	
	@Override
	public void EmitBranchEqual(
			VariableSymbol aToTest,
			int aEqualTo, 
			Label aBranchTo) {
		mWriter.EmitBranchEqual(GetRegister(aToTest), aEqualTo, aBranchTo);
	}

	@Override
	public void EmitComment(String axComment) {
		mWriter.EmitComment(axComment);
	}

	@Override
	public void EmitJump(Label aLabel) {
	    mWriter.EmitJump(aLabel);
	}

	@Override
	public void EmitLabel(Label aLabel) {
	    mWriter.EmitLabel(aLabel);
	}

	@Override
	public void EmitMove(VariableSymbol aSource, VariableSymbol aOffset, VariableSymbol aDestination) {
	    mWriter.EmitMove(GetRegister(aSource), GetRegister(aOffset), GetRegister(aDestination));
	}

	@Override
	public void EmitOperation(
			OperatorE aOperator,
			VariableSymbol aLHS,
			VariableSymbol aRHS,
			VariableSymbol aStore) {
		mWriter.EmitOperation(aOperator, GetRegister(aLHS), GetRegister(aRHS), GetRegister(aStore));
	}

	@Override
	public void EmitOperation(OperatorE aOperator, VariableSymbol aLHS, String axRHS, VariableSymbol aStore) {
	        String fxOperation = OperandToString(aOperator);
		if (null == fxOperation)
			throw new UnsupportedOperationException("Operator not found.");
		
		fxOperation += String.format(" $%d, %s, $%d", GetRegister(aLHS), axRHS, GetRegister(aStore));
		//mWriter.EmitOperation(aOperator, aLHS, axRHS, aStore);
		throw new UnsupportedOperationException();
	}

	@Override
	public void EmitReturn(VariableSymbol aReturnValue) {
	        String fxOperation = "ret";
		
		if (null != aReturnValue)
			fxOperation += String.format(" $%d", GetRegister(aReturnValue));
		
		mWriter.EmitReturn(fxOperation);
	}

	@Override
	public void EmitSet(VariableSymbol aDestination, String axOffset, VariableSymbol aSource) {
		mWriter.EmitSet(GetRegister(aDestination), axOffset, GetRegister(aSource));
	}

	@Override
	public void EmitSet(VariableSymbol aDestination, String axOffset, ConstantLiteral aSource) {
		//PrintLine(String.format("set $%d, %s, %s", GetRegister(aDestination), axOffset, aSource.GetStringValue()));
		throw new UnsupportedOperationException();
	}
		
	private static String OperandToString(//here we convert operand to string
			OperatorE aOperand) {
		switch (aOperand) {
			case Add: return "add";
			case And: return "and";
			case Subtract: return "sub";
			case Multiply: return "mul";
			case Divide: return "div";
			case Power: return "pow";	
			case Equal: return "eq";
			case NotEqual: return "neq";			
			case LessThan: return "lt";	
			case LessThanOrEqualTo: return "lteq";			
			case GreaterThan: return "gt";
			case GreaterThanOrEqualTo: return "gteq";
			case Or: return "or";
		}
		
		return null;
	}
}