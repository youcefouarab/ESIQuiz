package sample;


import java.util.*;
import java.io.Serializable;
import java.lang.String;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Apprenant extends Compte implements Serializable{
	private String Nom="";
	private String Prenom;
	private LocalDate DateNaissance;
	private String Adresse;
	private float Moyenne;
	private ArrayList<QuizPerso> Quizs = new ArrayList<QuizPerso>();
	
	public Apprenant() {
		super("","");
	}
		
	public Apprenant(String Login, String Mdp, String Nom, String Prenom, LocalDate DateNaissance, String Adresse, float Moyenne) {
		super(Login, Mdp);
		this.setNom(Nom);
		this.Prenom=Prenom;
		this.Adresse=Adresse;
		this.Moyenne=Moyenne;
		this.DateNaissance=DateNaissance;
       
	}
	
	public void modifMdp(String Mdp) {
		this.Mdp=Mdp;
	}
	
	
	
	
	
	public void affichActivite() {
		//afficher pour chaque quiz ses infos
		if (this.Quizs.size()==0) {
			System.out.println("Aucune activite!");
		} else {
			for (int i=0;i<this.Quizs.size();i++) {
				System.out.println("Quiz: "+this.Quizs.get(i).getNom());
				System.out.println("Date d'ouverture: "+this.Quizs.get(i).getDateOuvert().toString());
				System.out.println("Date d'expiration: "+this.Quizs.get(i).getDateExp().toString());
				this.Quizs.get(i).calcTauxAccomp();
				System.out.println("Taux d'accomplissement: "+this.Quizs.get(i).getTauxAccomp());
				this.Quizs.get(i).calcTauxReussite();
				System.out.println("Taux de reussite: "+this.Quizs.get(i).getTauxReussite());
			}
		}
	}
	
	

	public void modifQuiz(int Id) {
		//modifier ses reponses dans un quiz
		/*if (this.Quizs[Id].Soumis) {
			System.out.println("Vous ne pouvez pas modifier ce quiz car il est soumis!");
		} else {
			//affichages des questions et reponses avec possibilite de modification
		}*/
		
	}
	
	public ArrayList<QuizPerso> getQuizs() {
		return this.Quizs;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	public LocalDate getDateNaissance() {
		return DateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		DateNaissance = dateNaissance;
	}

	public String getAdresse() {
		return Adresse;
	}

	public void setAdresse(String adresse) {
		Adresse = adresse;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}
	
	public void calcMoyenne(){
		this.setMoyenne(0);
		float ac=0; int tot=0;
		if(Quizs.size()!=0) {
			for (QuizPerso q:  Quizs
			) {
				if ((q.getDateExp().compareTo(LocalDate.now())<=0)||(q.estSoumis())) {
					ac+=(float)q.getTauxReussite();
					tot++;
				}
				
			}
			if (tot==0) this.setMoyenne(0);
			else this.setMoyenne((float)ac/(float)tot);
		}
		
	}

	public float getMoyenne() {
		return Moyenne;
	}

	public void setMoyenne(float moyenne) {
		Moyenne = moyenne;
	}

	public QuizPerso getQuiz(String nom) {
		int i=0;
		while ((i<Quizs.size())&&(!Quizs.get(i).getNom().equals(nom))) {
			i++;
		}
		if (i>=Quizs.size()) return null;
		else return Quizs.get(i);
	}


}
