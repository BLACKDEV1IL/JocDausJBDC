package CapaAplicacio.DTO;



import CapaDomini.Jugador;

public class JugadorDTO {
	private String nom;
    //private List<PartidaDTO> partides=new ArrayList<PartidaDTO>();
    
    public JugadorDTO(Jugador jugador){
    	this.nom = jugador.getNom();
    	//initPartides(jugador.getPartides());
    }

	/*private void initPartides(List<Partida> partides) {
		// TODO Auto-generated method stub
		for (Partida p:partides){
			this.partides.add(new PartidaDTO(p));
		}
	}*/
	

}
