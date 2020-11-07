package IRCode;

import java.util.*;

import SyntaxTree.*;

public class FunctionInfo {
	private String mxName = null;
	private int mNextID = 0;
	
	public FunctionInfo(
		String axName) {
		mxName = axName;
	}
	
	public String GetName() {
		return mxName;
	}
	
	public String NextLabelID() {
		return String.format("%s:", mNextID++);
	}
	
	private Type mReturnType = null;
	
	public Type GetReturnType() {
		return mReturnType;
	}
	
	public Type SetReturnType(
		Type aReturnType) {
		mReturnType = aReturnType;
		return aReturnType;
	}
	
	private Stack<LoopInfo> mvLoops = new Stack<LoopInfo>();
	
	public void PushLoop(
		Label aStartLabel,
		Label aEndLabel)
	{
		mvLoops.push(new LoopInfo(aStartLabel, aEndLabel));
	}
	
	public Label PopLoop() {
		return mvLoops.pop().GetEndLabel();
	}
	
	public int GetLoopCount() {
		return mvLoops.size();
	}
	
	public Label PeekLoopEndLabel() {
		return mvLoops.peek().GetEndLabel();
	}
}
