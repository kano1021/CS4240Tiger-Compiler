package IRCode;

public class LoopInfo {
	public LoopInfo(
		Label aStartLabel,
		Label aEndLabel) {
		mStartLabel = aStartLabel;
		mEndLabel = aEndLabel;
	}
	
	private Label mStartLabel = null;
	private Label mEndLabel = null;
	
	public Label GetEndLabel() {
		return mEndLabel;
	}
}
