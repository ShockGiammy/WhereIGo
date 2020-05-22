package logic.beans;

import java.util.ArrayList;
import java.util.List;

public class InterestsBean {
	private List<Integer> answares;
	
	public InterestsBean(List<Integer> answ) {
		answares = new ArrayList<>();
		answares.addAll(answ);
	}

	public List<Integer> getAnswares() {
		return this.answares;
	}
}
