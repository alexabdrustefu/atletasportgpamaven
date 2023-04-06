package it.atletasportspa.service;

import java.util.List;

import it.atletasportjpamaven.dao.AtletaDAO;
import it.atletasportjpamaven.dao.SportDAO;

public interface AtletaService {
	public List<Atleta> listAll() throws Exception;

	public Atleta caricaSingoloAtleta(Long id) throws Exception;

	public void aggiorna(Atleta atletaInstance) throws Exception;

	public void inserisciNuovo(Atleta atletaInstance) throws Exception;

	public void rimuovi(Long idAtleta) throws Exception;

	public void aggiungiSport(Atleta atletaEsistente, Sport sportInstance) throws Exception;

// per injection
	public void setAtletaDAO(AtletaDAO atletaDAO);

	public void setSportDAO(SportDAO sportDAO);

}
