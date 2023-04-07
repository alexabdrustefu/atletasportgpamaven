package it.atletasportjpamaven.dao;

import it.atletasportjpa.model.Atleta;

public interface AtletaDAO extends IBaseDAO<Atleta> {
	public Atleta findByIdFetchingSports(Long id);

	public Long sumNumeroMedaglieVinteInSportChiusi();
}
