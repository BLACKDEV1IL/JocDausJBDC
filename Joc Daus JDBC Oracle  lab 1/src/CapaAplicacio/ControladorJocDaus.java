package CapaAplicacio;

import java.util.ArrayList;
import java.util.List;

import CapaAplicacio.DTO.JugadorDTO;
import CapaAplicacio.DTO.PartidaDTO;
import CapaDomini.Dau;
import CapaDomini.Jugador;
import CapaDomini.Partida;
import CapaPersistencia.JugadorBBDD;

public class ControladorJocDaus {

	private Dau dau1, dau2;
	private final int CARES_DAUS = 6;
	private Jugador jugador;

	public ControladorJocDaus() {
		dau1 = new Dau(CARES_DAUS);
		dau2 = new Dau(CARES_DAUS);
		jugador = new Jugador("Anonim");
	}

	public void jugar() {
		int tirada1 = this.tirarDau(dau1);
		int tirada2 = this.tirarDau(dau2);
		jugador.addPartida(tirada1, tirada2);
	}

	private int tirarDau(Dau dau) {
		dau.llenca();
		return dau.valorCara();
	}

	public JugadorDTO getJugadorDTO() {
		return new JugadorDTO(jugador);
	}

	public String getNomJugador() {
		return jugador.getNom();
	}

	public PartidaDTO getPartidaEnCurs() {
		return new PartidaDTO(jugador.getPartidaEnCurs());
	}

	public double guanyadesPercent() {
		return jugador.nombreGuanyades() / (float) jugador.nombrePartides() * 100;
	}

	public void nouJugador(String nom) throws Exception {
		// Si el nom �s "Anonim" no cal fer res
		if (!nom.equalsIgnoreCase("Anonim")) {
			jugador = new Jugador(nom);
		}
		if (checkName(nom)) {
			JugadorBBDD jugador = new JugadorBBDD();
			if (jugador.isThereAPlayerNamed(nom)) {
				this.jugador = new JugadorBBDD().searchForPlayer(nom);
			}
		}
	}

	public List<PartidaDTO> getPartides() {
		List<Partida> partides = jugador.getPartides();
		List<PartidaDTO> result = new ArrayList<>();
		for (Partida p : partides) {
			result.add(new PartidaDTO(p));
		}
		return result;
	}

	public void acabarJoc() throws Exception {
		if (checkName(getNomJugador())) {
			if (!new JugadorBBDD().isThereAPlayerNamed(getNomJugador())) {
				new JugadorBBDD().insertPlayerFinal(jugador);
			}
			new JugadorBBDD().insertGameJugadorBBDD(jugador);

		}
	}

	private boolean checkName(String name) {
		return !name.equalsIgnoreCase("Anonim");
	}
}
