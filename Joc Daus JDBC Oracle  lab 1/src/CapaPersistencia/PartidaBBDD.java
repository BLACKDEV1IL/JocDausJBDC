package CapaPersistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import CapaDomini.Jugador;
import CapaDomini.Partida;

public class PartidaBBDD {
	private ConnectionBBDD connection;

	public PartidaBBDD() throws Exception {
		connection = LoginBBDD.getConnection();
	}

	public void insertGamePartidaBBDD(Jugador player) throws Exception {
		try {
			List<Partida> gameNotSaved = player.getPartidesNoGuardades();
			PreparedStatement setPlayerGame = createGameOnlyPlayer(player);

			for (Partida partida : gameNotSaved) {
				createGameWithPlayer(setPlayerGame, partida);
				setPlayerGame.executeUpdate();
			}
		} catch (SQLException e) {
			throw new Exception("Ups! There's a problem inserting your game");
		}

	}

	private PreparedStatement createGameOnlyPlayer(Jugador player) throws SQLException {
		String query = "INSERT INTO PARTIDA(IDPARTIDA,NOM, DAU1, DAU2) VALUES(?,?,?,?)";
		PreparedStatement setPlayer = connection.prepareStatement(query);
		setPlayer.clearParameters();
		setPlayer.setString(2, player.getNom());
		return setPlayer;
	}

	private void createGameWithPlayer(PreparedStatement userGame, Partida game) throws Exception {
		String query = "SELECT S_IDPARTIDA.NEXTVAL PKPARTIDA FROM DUAL";
		PreparedStatement GeneratePartidaID = connection.prepareStatement(query);
		ResultSet GeneratedIDforPartida = GeneratePartidaID.executeQuery();

		int partidaID;
		if (GeneratedIDforPartida.next())
			partidaID = GeneratedIDforPartida.getInt(1);
		else
			throw new Exception("There's a problem assingning a ID for your game.");

		userGame.setInt(1, partidaID);
		userGame.setInt(3, game.getDau1());
		userGame.setInt(4, game.getDau2());
	}

	void setGamesPlayer(Jugador player) throws SQLException {
		String query = "SELECT * FROM PARTIDA WHERE NOM = ?";
		PreparedStatement selectAllFromPlayer = connection.prepareStatement(query);
		selectAllFromPlayer.clearParameters();
		selectAllFromPlayer.setString(1, player.getNom());
		ResultSet result = selectAllFromPlayer.executeQuery();

		while (result.next()) {
			int dau1 = result.getInt("dau1");
			int dau2 = result.getInt("dau2");
			player.addPartida(dau1, dau2);
		}
		player.setNumPartidesPersistents(result.getRow());
	}

}
