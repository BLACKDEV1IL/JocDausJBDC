package CapaAplicacio.DTO;

import CapaDomini.Partida;

public class PartidaDTO {
	
	private int dau1, dau2;
	private boolean resultat;
	
	public PartidaDTO(Partida p){
		this.dau1= p.getDau1();
		this.dau2= p.getDau2();
		this.resultat= p.getGuanyada();
	}

	public String getDau1() {
		return Integer.toString(dau1);
	}

	public String getDau2() {
		return Integer.toString(dau2);
	}

	public String getResultat() {
		String resultat;
        if (this.resultat) {
            resultat = "guanyada";
        } else {
            resultat = "perduda";
        }
        return "Dau1: " + dau1 + ", Dau2: " + dau2 + ", Resultat: " + resultat;
    }

}
