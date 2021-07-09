package sample;



import java.io.Serializable;

public class Reponse implements Serializable{
    private String Reponse;
    private boolean Correct;
    private boolean Coche=false;

    public Reponse(String Reponse, boolean Correct){
    	this.Reponse=Reponse;
    	this.setCorrect(Correct);
    }
    
    public void Cocher(){
        Coche=true;
    }
    
    public void Decocher(){
        Coche=false;
    }
    
	public String getReponse() {
		return Reponse;
	}
	
	public void setReponse(String reponse) {
		Reponse = reponse;
	}
	
	public boolean isCoche() {
		return Coche;
	}

	public boolean isCorrect() {
		return Correct;
	}

	public void setCorrect(boolean correct) {
		Correct = correct;
	}
	
	
}
