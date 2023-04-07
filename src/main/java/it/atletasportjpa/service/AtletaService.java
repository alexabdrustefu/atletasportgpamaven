package it.atletasportjpa.service;

import java.util.List;

import it.atletasportjpa.model.Atleta;
import it.atletasportjpa.model.Sport;
import it.atletasportjpamaven.dao.AtletaDAO;
import it.atletasportjpamaven.dao.SportDAO;

public interface AtletaService {
	public List<Atleta> listAll() throws Exception;

	public Atleta caricaSingoloAtleta(Long id) throws Exception;

	public void aggiorna(Atleta atletaInstance) throws Exception;

	public void inserisciNuovo(Atleta atletaInstance) throws Exception;

	public void rimuovi(Long idAtleta) throws Exception;

	public void aggiungiSport(Atleta atletaEsistente, Sport sportInstance) throws Exception;

	public Long sommaMedaglieVinteInSportChiusi() throws Exception;

	// per injection
	public void setAtletaDAO(AtletaDAO atletaDAO);

	public void setSportDAO(SportDAO sportDAO);

}
