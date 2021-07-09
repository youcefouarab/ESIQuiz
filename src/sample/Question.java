package sample;


import java.io.Serializable;
import java.util.*;

public abstract class Question implements Serializable {
	private String Question;
	protected ArrayList<Reponse> Reponses = new ArrayList<Reponse>();
	protected boolean Repondu;
	
	public Question(String Question) {
		this.Question=Question;
	}
		
	public abstract float evaluer();
	
	public void setQuestion(String Question) {
		this.Question=Question;
	}
	
	public String getQuestion() {
		return this.Question;
	}
	
	public boolean isRepondu() {
		return this.Repondu;
	}
	
	public void setRepondu(boolean Repondu) {
		this.Repondu=Repondu;
	}

	public ArrayList<Reponse> getReponses() {
		return this.Reponses;
	}

	protected abstract TypeQC getType();


}
