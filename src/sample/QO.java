package sample;


import java.io.Serializable;
import java.util.ArrayList;

public class QO extends Question implements Serializable {
	private String Reponse;
	
	public QO(String Question) {
		super(Question);
	}
	
	public float evaluer() {
		if (Repondu) {
			float note=0;
			//evaluer la question de type QO
			int i=0;
			while ((i<Reponses.size())&&(!Reponse.toUpperCase().equals(Reponses.get(i).getReponse().toUpperCase()))){
				i++;
			}
			if (i<Reponses.size()) note=1;
			return note;
		}
		else return 0;
		
	}	
	
	public void setReponse(String Reponse) {
		this.Reponse=Reponse;
	}
	
	public String getReponse() {
		return this.Reponse;
	}

	@Override
	protected TypeQC getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
