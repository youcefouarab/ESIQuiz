package sample;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class QuizPerso extends Quiz implements Serializable{
    
	private float TauxAccomp;
    private float TauxReussite;
    private boolean Soumis;
    
	public QuizPerso(String Nom, LocalDate DateOuvert, LocalDate DateExp, float TauxAccomp, float TauxReussite, boolean Soumis) {
		super(Nom, DateOuvert, DateExp);
		this.TauxAccomp=TauxAccomp;
		this.TauxReussite=TauxReussite;
		this.Soumis=Soumis;
	}

	public void calcTauxAccomp(){
		float cmp=0,ttl=0;
		for (Notion n : this.Notions
		) {
			for (Question q:n.getQuestions()
			) {
				ttl++;
				if(q.isRepondu()==true){
					cmp++;
				}
			}
		}
		this.TauxAccomp=((float)cmp/(float)ttl)*100;
	}
	
	public void calcTauxReussite(){
		float cmp=0,ttl=0;
		for (Notion n : this.Notions
		) {
			for (Question q:n.getQuestions()
			) {
				ttl++;
				if(q.isRepondu()==true){
					cmp+=q.evaluer();
				}
			}
		}
		this.TauxReussite=((float)cmp/(float)ttl)*100;
	}

	public float getTauxAccomp() {
		return TauxAccomp;
	}

	public float getTauxReussite() {
		return TauxReussite;
	}

	public boolean estSoumis() {
		return Soumis;
	}

	public void soumettre() {
		//soumettre un quiz a evaluation et afficher son taux de reussite
		Soumis=true;
		calcTauxAccomp();
		calcTauxReussite();
		
	}

	public void setNotions(ArrayList<Notion> Notions) {
		this.Notions=Notions;
		
	}
}
