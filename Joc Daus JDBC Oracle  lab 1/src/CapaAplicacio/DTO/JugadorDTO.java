package CapaAplicacio.DTO;

import CapaDomini.Jugador;

public class JugadorDTO {
	private String nom;

	public JugadorDTO(Jugador jugador) {
		this.nom = jugador.getNom();

	}

	public String getNom() {
		return this.nom;
	}

}
