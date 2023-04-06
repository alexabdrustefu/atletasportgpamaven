package it.atletasportjpamaven.dao;

public interface SportDAO extends IBaseDAO<Sport> {
	public Sport findByDescrizione(String descrizione) throws Exception;

}
