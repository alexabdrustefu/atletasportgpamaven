package it.atletasportjpamaven.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.atletasportjpa.model.Atleta;

public class AtletaDAOImpl implements AtletaDAO {

	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Atleta> list() throws Exception {

		return entityManager.createQuery("from Atleta", Atleta.class).getResultList();
	}

	@Override
	public Atleta get(Long id) throws Exception {

		return entityManager.find(Atleta.class, id);
	}

	@Override
	public void update(Atleta atletaInstance) throws Exception {
		if (atletaInstance == null)
			throw new Exception("Problema valore input");
		atletaInstance = entityManager.merge(atletaInstance);
	}

	@Override
	public void insert(Atleta atletaInstance) throws Exception {
		if (atletaInstance == null)
			throw new Exception("Problema valore input");
		entityManager.persist(atletaInstance);
	}

	@Override
	public void delete(Atleta atletaInstance) throws Exception {
		if (atletaInstance == null)
			throw new Exception("Problema valore input");
		entityManager.remove(entityManager.merge(atletaInstance));

	}

	@Override
	public Atleta findByIdFetchingSports(Long id) {
		TypedQuery<Atleta> query = entityManager
				.createQuery("select a from Atleta a left join fetch a.sports s where a.id = :idAtleta", Atleta.class);
		query.setParameter("idAtleta", id);
		return query.getResultList().stream().findFirst().orElse(null);
	}

	@Override
	public Long sumNumeroMedaglieVinteInSportChiusi() {
		Long somma = null;
		Query query = entityManager.createQuery(
				"select sum(a.numeroMedaglieVinte) from Atleta a join a.sports s where s.dataFine is not null");
		somma = (Long) query.getSingleResult();
		return somma;
	}

}
