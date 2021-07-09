package sample;



import java.io.Serializable;
import java.util.*;

public class Notion implements Serializable{
    private String Nom;
    private ArrayList<Question> Questions= new ArrayList<Question>();
    
    public Notion(String Nom) {
        this.setNom(Nom);
    }
    
    public Question getQuestion(String question) {
		int i=0;
		while ((i<Questions.size())&&(!Questions.get(i).getQuestion().equals(question))) {
			i++;
		}
		if (i>=Questions.size()) return null;
		else return Questions.get(i);
	}
    
    public void ajoutQuestion(Question q){
        Questions.add(q);
    }
    
    public void suppQuestion(Question q){
        Questions.remove(q);
    }
    
	public String getNom() {
		return Nom;
	}
	
	public void setNom(String nom) {
		Nom = nom;
	}

	public ArrayList<Question> getQuestions() {
		return Questions;
	}
	
	
}
