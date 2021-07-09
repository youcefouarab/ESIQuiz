package sample;


import java.util.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Formation implements Serializable{
    private String Nom ;
    private String Description;
    private LocalDate DateDebut;
    private LocalDate DateFin;
    private ArrayList<Notion> Notions = new ArrayList<Notion>();
    private ArrayList<Quiz> Quizs = new ArrayList<Quiz>();
    private ArrayList<Apprenant> Apprenants = new ArrayList<Apprenant>();

    public Formation(String Nom,String Description,LocalDate DateDebut,LocalDate DateFin)  {
        this.setNom(Nom);
        this.setDescription(Description);
        this.DateDebut=DateDebut;
        this.DateFin=DateFin;
    }

    public void ajoutApprenant(Apprenant a){
        Apprenants.add(a);
    }
    
    public void suppApprenant(int i){
    	Apprenants.remove(i);
    }
    public void ajoutQuiz(Quiz Q){
        Quizs.add(Q);
    }
    
    public void suppQuiz(Quiz Q){
        Quizs.remove(Q);
    }
    
    public ArrayList<Apprenant> getApprenants() {
    	return Apprenants;
    }
    
    public ArrayList<Quiz> getQuizs(){
    	return Quizs;
    }

	public Quiz getQuiz(String Nom) {
		int i=0;
		while ((i<Quizs.size())&&(!Quizs.get(i).getNom().equals(Nom))) {
			i++;
		}
		if (i>=Quizs.size()) return null;
		else return Quizs.get(i);
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public LocalDate getDateDebut() {
		return DateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		DateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return DateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		DateFin = dateFin;
	}

	public ArrayList<Notion> getNotions() {
		return Notions;
	}

	public Notion getNotion(String nom) {
		int i=0;
		while ((i<Notions.size())&&(!Notions.get(i).getNom().equals(nom))) {
			i++;
		}
		if (i>=Notions.size()) return null;
		else return Notions.get(i);
	}

	public Apprenant getApprenant(String login) {
		int i=0;
		while ((i<Apprenants.size())&&(!Apprenants.get(i).getLogin().equals(login))) {
			i++;
		}
		if (i>=Apprenants.size()) return null;
		else return Apprenants.get(i);
	}
	
}

