package CapaAplicacio;

import CapaPersistencia.LoginBBDD;

public class LoginController {
	public void login(String user, String pass) throws Exception{
		LoginBBDD.login(user, pass);
	}
}
