package sample;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class Quiz implements Serializable{
    protected String Nom="" ;
    protected LocalDate DateOuvert;
    protected LocalDate DateExp ;
    protected ArrayList<Notion> Notions = new ArrayList<Notion>();

    public Quiz(String Nom,LocalDate DateOuvert,LocalDate DateExp) {
        this.setNom(Nom);
        this.DateOuvert=DateOuvert;
        this.DateExp=DateExp;
    }
    
    public Quiz() {
    	
	}

	public void ajoutNotion(Notion n){
        Notions.add(n);
    }
    
    public void suppNotion(Notion n){
        Notions.remove(n);
    }

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public LocalDate getDateOuvert() {
		return DateOuvert;
	}

	public void setDateOuvert(LocalDate dateOuvert) {
		DateOuvert = dateOuvert;
	}

	public LocalDate getDateExp() {
		return DateExp;
	}

	public void setDateExp(LocalDate dateExp) {
		DateExp = dateExp;
	}
	
	public ArrayList<Notion> getNotions() {
		return this.Notions;
	}
	
	public Notion getNotion(String Nom) {
		int i=0;
		while ((i<Notions.size())&&(Notions.get(i).getNom()!=Nom)) {
			i++;
		}
		if (i>=Notions.size()) return null;
		else return Notions.get(i);
    }
	
	public QuizPerso creerQuizPerso() {
		QuizPerso Q = new QuizPerso(this.Nom,this.DateOuvert,this.DateExp,0,0,false);
		for (Notion n:this.Notions) {
			Notion N = new Notion(n.getNom());
			Q.getNotions().add(N);
			for (Question q:this.getNotion(n.getNom()).getQuestions()) {
				Question Qst = null;
				if (q instanceof QO) Qst = new QO(q.getQuestion());
				else Qst = new QC(q.getQuestion(),q.getType());
				N.getQuestions().add(Qst);
				for(Reponse r:q.getReponses()) {
					Qst.getReponses().add(new Reponse(r.getReponse(),r.isCorrect()));
				}
			}
		}
		return Q;
	}

}
