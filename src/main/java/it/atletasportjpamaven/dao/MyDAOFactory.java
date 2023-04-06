package it.atletasportjpamaven.dao;

public class MyDAOFactory {
	// rendo questo factory SINGLETON
	private static AtletaDAO UTENTE_DAO_INSTANCE = null;
	private static SportDAO SPORT_DAO_INSTANCE = null;

	public static AtletaDAO getAtletaDAOInstance() {
		if (UTENTE_DAO_INSTANCE == null)
			UTENTE_DAO_INSTANCE = new AtletaDAOImpl();
		return UTENTE_DAO_INSTANCE;
	}

	public static SportDAO getSportDAOInstance() {
		if (SPORT_DAO_INSTANCE == null)
			SPORT_DAO_INSTANCE = new SportDAOImpl();
		return SPORT_DAO_INSTANCE;
	}

}
