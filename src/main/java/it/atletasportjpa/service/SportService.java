package it.atletasportjpa.service;

import java.util.List;

import it.atletasportjpa.model.Sport;
import it.atletasportjpamaven.dao.AtletaDAO;
import it.atletasportjpamaven.dao.SportDAO;

public interface SportService {

	public List<Sport> listAll() throws Exception;

	public Sport caricaSingoloSport(Long id) throws Exception;

	public void aggiorna(Sport sportInstance) throws Exception;

	public void inserisciNuovo(Sport sportInstance) throws Exception;

	public void rimuovi(Long idSportToRemove) throws Exception;

	public Sport cercaPerDescrizione(String descrizione) throws Exception;

	public List<Sport> cercaErrori() throws Exception;

	// per injection
	public void setAtletaDAO(AtletaDAO atletaDAO);

	public void setSportDAO(SportDAO sportDAO);

}
