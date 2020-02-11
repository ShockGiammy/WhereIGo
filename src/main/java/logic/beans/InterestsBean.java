package logic.beans;

public class InterestsBean {
	private int[] answares;
	
	public InterestsBean() {
		answares = new int[4];
	}
	
	public void setAnswares(int[] answ) {
		this.answares[0] = answ[0];
		this.answares[1] = answ[1];
		this.answares[2] = answ[2];
		this.answares[3] = answ[3];
	}

	public int[] getAnswares() {
		return this.answares;
	}
}
