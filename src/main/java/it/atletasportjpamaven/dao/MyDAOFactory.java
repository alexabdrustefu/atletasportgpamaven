package it.atletasportjpamaven.dao;

public class MyDAOFactory {
	// rendo questo factory SINGLETON
	private static AtletaDAO Atleta_DAO_INSTANCE = null;
	private static SportDAO SPORT_DAO_INSTANCE = null;

	public static AtletaDAO getAtletaDAOInstance() {
		if (Atleta_DAO_INSTANCE == null)
			Atleta_DAO_INSTANCE = new AtletaDAOImpl();
		return Atleta_DAO_INSTANCE;
	}

	public static SportDAO getSportDAOInstance() {
		if (SPORT_DAO_INSTANCE == null)
			SPORT_DAO_INSTANCE = new SportDAOImpl();
		return SPORT_DAO_INSTANCE;
	}

}
