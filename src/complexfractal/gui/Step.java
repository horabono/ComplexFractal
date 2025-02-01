package complexfractal.gui;

public enum Step {
	START(":10"),
	PAGE_BACK(":5"),
	STEP_BACK(":2"),
	RESET("100"),
	STEP_FORWARD("x2"),
	PAGE_FORWARD("x5"),
	END("x10");
	
	public final String mark;
	
	private Step(String mark) {
		this.mark = mark;
	}
}
