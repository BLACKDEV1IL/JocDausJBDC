package CapaPersistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import CapaDomini.Jugador;

public class JugadorBBDD {

	private ConnectionBBDD connection;
	private PartidaBBDD gameDB;

	public JugadorBBDD() throws Exception {
		connection = LoginBBDD.getConnection();
		gameDB = new PartidaBBDD();
	}

	public void insertPlayerFinal(Jugador player) throws Exception {
		try {
			boolean insertedPlayer = insertPlayerToDB(player);
			if (!insertedPlayer)
				throw new Exception("Player could not be added");
			insertGameJugadorBBDD(player);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Player could not be added");
		}
	}

	public void insertGameJugadorBBDD(Jugador player) throws Exception {
		try {
			if (isThereAPlayerNamed(player.getNom()))
				gameDB.insertGamePartidaBBDD(player);

		} catch (SQLException e) {
			throw new Exception("Player does not exist");
		}

	}

	public boolean isThereAPlayerNamed(String name) throws Exception {
		try {
			ResultSet queryPlayer = selectPlayer(name);
			return queryPlayer.next();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Player not found");
		}
	}

	private Jugador getPlayerFromDB(String name) throws Exception {
		ResultSet result = selectPlayer(name);
		if (!result.next())
			throw new Exception("Player could not be retrieved from database");
		
		String nomJugador = result.getString("nom");
		return new Jugador(nomJugador);
	}
	
	public Jugador searchForPlayer(String name) throws Exception{
	    try{
	      Jugador player = getPlayerFromDB(name);
	      gameDB.setGamesPlayer(player);
	      return player;
	    }
	    catch(SQLException e){
	      throw new Exception("Player was not found");
	    }
	  }

	private ResultSet selectPlayer(String name) throws SQLException {
		String query = "SELECT NOM FROM JUGADOR WHERE NOM=?";
		PreparedStatement querySelectPlayer = connection.prepareStatement(query);
		querySelectPlayer.setString(1, name);
		return querySelectPlayer.executeQuery();
	}

	public boolean insertPlayerToDB(Jugador player) throws SQLException {
		String query = "INSERT INTO JUGADOR(NOM) VALUES(?)";
		PreparedStatement queryInsertPlayer = connection.prepareStatement(query);
		queryInsertPlayer.clearParameters();
		queryInsertPlayer.setString(1, player.getNom());
		return queryInsertPlayer.executeUpdate() == 1;

	}
	
	

}
