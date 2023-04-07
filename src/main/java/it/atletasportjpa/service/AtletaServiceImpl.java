package it.atletasportjpa.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.atletasportjpa.model.Atleta;
import it.atletasportjpa.model.Sport;
import it.atletasportjpamaven.dao.AtletaDAO;
import it.atletasportjpamaven.dao.EntityManagereUtil;
import it.atletasportjpamaven.dao.SportDAO;

public class AtletaServiceImpl implements AtletaService {
	private AtletaDAO atletaDao;
	private SportDAO sportDao;

	@Override
	public void setAtletaDAO(AtletaDAO atletaDAO) {
		this.atletaDao = atletaDAO;
	}

	@Override
	public void setSportDAO(SportDAO sportDAO) {
		this.sportDao = sportDAO;
	}

	@Override
	public List<Atleta> listAll() throws Exception {
		EntityManager entityManager = EntityManagereUtil.getEntityManager();
		try {
			// injection
			atletaDao.setEntityManager(entityManager);

			// esecuzione metodo
			return atletaDao.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagereUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Atleta caricaSingoloAtleta(Long id) throws Exception {
		EntityManager entityManager = EntityManagereUtil.getEntityManager();
		try {
			// injection
			atletaDao.setEntityManager(entityManager);

			// esecuzione metodo
			return atletaDao.findByIdFetchingSports(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagereUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Atleta atletaInstance) throws Exception {
		EntityManager entityManager = EntityManagereUtil.getEntityManager();
		try {

			// è un cambiamento del database,
			// quindi devo iniziare una Transaction
			entityManager.getTransaction().begin();

			// injection
			atletaDao.setEntityManager(entityManager);

			// esecuzione metodo
			atletaDao.update(atletaInstance);

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
	public void inserisciNuovo(Atleta atletaInstance) throws Exception {
		EntityManager entityManager = EntityManagereUtil.getEntityManager();
		try {

			// è un cambiamento del database,
			// quindi devo iniziare una Transaction
			entityManager.getTransaction().begin();

			// injection
			atletaDao.setEntityManager(entityManager);

			// esecuzione metodo
			atletaDao.insert(atletaInstance);

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
	public void rimuovi(Long idAtleta) throws Exception {
		EntityManager entityManager = EntityManagereUtil.getEntityManager();
		try {

			// è un cambiamento del database,
			// quindi devo iniziare una Transaction
			entityManager.getTransaction().begin();

			// injection
			atletaDao.setEntityManager(entityManager);

			// esecuzione metodo
			atletaDao.delete(atletaDao.get(idAtleta));

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
	public void aggiungiSport(Atleta atletaEsistente, Sport sportInstance) throws Exception {
		EntityManager entityManager = EntityManagereUtil.getEntityManager();
		try {

			// è un cambiamento del database,
			// quindi devo iniziare una Transaction
			entityManager.getTransaction().begin();

			// injection
			atletaDao.setEntityManager(entityManager);

			// 'attacco' alla sessione di hibernate i due oggetti
			// così jpa capisce che se è già presente quel ruolo non deve essere inserito

			atletaEsistente = entityManager.merge(atletaEsistente);
			sportInstance = entityManager.merge(sportInstance);

			atletaEsistente.getSports().add(sportInstance);
			// l'update non viene richiamato a mano in quanto
			// risulta automatico, infatti il contesto di persistenza
			// rileva che atletaEsistente ora è dirty vale a dire che una sua
			// proprieta ha subito una modifica (vale anche per i Set ovviamente)

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
	public Long sommaMedaglieVinteInSportChiusi() throws Exception {
		EntityManager entityManager = EntityManagereUtil.getEntityManager();
		try {
			// injection
			atletaDao.setEntityManager(entityManager);

			// esecuzione metodo
			return atletaDao.sumNumeroMedaglieVinteInSportChiusi();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagereUtil.closeEntityManager(entityManager);
		}
	}
}
