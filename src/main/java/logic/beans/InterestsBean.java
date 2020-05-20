package logic.beans;

import java.util.ArrayList;
import java.util.List;

public class InterestsBean {
	private List<Integer> answares;
	
	public InterestsBean() {
		answares = new ArrayList<>();
	}
	
	public void setAnswares(List<Integer> answ) {
		this.answares.addAll(answ);
	}

	public List<Integer> getAnswares() {
		return this.answares;
	}
}
