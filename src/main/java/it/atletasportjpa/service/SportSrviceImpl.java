package it.atletasportjpa.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.atletasportjpa.model.Sport;
import it.atletasportjpamaven.dao.AtletaDAO;
import it.atletasportjpamaven.dao.EntityManagereUtil;
import it.atletasportjpamaven.dao.SportDAO;

public class SportSrviceImpl implements SportService {

	private SportDAO sportDAO;
	private AtletaDAO atletaDAO;

	@Override
	public void setAtletaDAO(AtletaDAO atletaDAO) {
		this.atletaDAO = atletaDAO;
	}

	@Override
	public void setSportDAO(SportDAO sportDAO) {
		this.sportDAO = sportDAO;
	}

	@Override
	public List<Sport> listAll() throws Exception {
		EntityManager entityManager = EntityManagereUtil.getEntityManager();
		try {
			// injection
			sportDAO.setEntityManager(entityManager);

			// esecuzione metodo
			return sportDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagereUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Sport caricaSingoloSport(Long id) throws Exception {
		EntityManager entityManager = EntityManagereUtil.getEntityManager();
		try {
			// injection
			sportDAO.setEntityManager(entityManager);

			// esecuzione metodo
			return sportDAO.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagereUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Sport sportInstance) throws Exception {
		EntityManager entityManager = EntityManagereUtil.getEntityManager();
		try {

			// è un cambiamento del database,
			// quindi devo iniziare una Transaction
			entityManager.getTransaction().begin();

			// injection
			sportDAO.setEntityManager(entityManager);

			// esecuzione metodo
			sportDAO.update(sportInstance);

			// faccio il commit
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			// faccio rollback se non va a buon fine
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagereUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisciNuovo(Sport sportInstance) throws Exception {
		EntityManager entityManager = EntityManagereUtil.getEntityManager();
		try {

			// è un cambiamento del database,
			// quindi devo iniziare una Transaction
			entityManager.getTransaction().begin();

			// injection
			sportDAO.setEntityManager(entityManager);

			// esecuzione metodo
			sportDAO.insert(sportInstance);

			// faccio il commit
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			// faccio rollback se non va a buon fine
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagereUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public void rimuovi(Long idSportToRemove) throws Exception {
		EntityManager entityManager = EntityManagereUtil.getEntityManager();
		try {

			// è un cambiamento del database,
			// quindi devo iniziare una Transaction
			entityManager.getTransaction().begin();

			// injection
			sportDAO.setEntityManager(entityManager);

			// esecuzione metodo
			sportDAO.delete(sportDAO.get(idSportToRemove));

			// faccio il commit
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			// faccio rollback se non va a buon fine
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagereUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Sport cercaPerDescrizione(String descrizione) throws Exception {
		EntityManager entityManager = EntityManagereUtil.getEntityManager();
		try {
			// injection
			sportDAO.setEntityManager(entityManager);

			// esecuzione metodo
			return sportDAO.findByDescrizione(descrizione);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagereUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Sport> cercaErrori() throws Exception {
		EntityManager entityManager = EntityManagereUtil.getEntityManager();
		try {
			// injection
			sportDAO.setEntityManager(entityManager);

			// esecuzione metodo
			return sportDAO.findMistakes();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagereUtil.closeEntityManager(entityManager);
		}
	}

}
