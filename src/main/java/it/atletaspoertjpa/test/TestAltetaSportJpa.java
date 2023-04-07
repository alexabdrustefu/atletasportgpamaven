package it.atletaspoertjpa.test;

import java.time.LocalDate;
import java.util.List;

import it.atletasportjpa.model.Atleta;
import it.atletasportjpa.model.Sport;
import it.atletasportjpa.model.StatoAtleta;
import it.atletasportjpa.service.AtletaService;
import it.atletasportjpa.service.MyServiceFactory;
import it.atletasportjpa.service.SportService;
import it.atletasportjpamaven.dao.EntityManagereUtil;

public class TestAltetaSportJpa {

	public static void main(String[] args) {
		AtletaService atletaServiceInstance = MyServiceFactory.getAtletaServiceInstance();
		SportService sportServiceInstance = MyServiceFactory.getSportServiceInstance();
		try {
			inizializzaSport(sportServiceInstance);

			System.out.println("Gli elementi nella tabella sport sono: " + sportServiceInstance.listAll().size());

			System.out.println("Gli elementi nella tabella atleta sono: " + atletaServiceInstance.listAll().size());
			// ###########################################################################################

			// TEST InserisciNuovoAtleta
			inserimentoAtleta(atletaServiceInstance);
			System.out.println("Gli elementi nella tabella atleta sono: " + atletaServiceInstance.listAll().size());
			// ###########################################################################################

			// TEST collegaSportEAtletaEsistenti
			collegamentoAtletaASport(atletaServiceInstance, sportServiceInstance);
			System.out.println("Gli elementi nella tabella atleta sono: " + atletaServiceInstance.listAll().size());
			System.out.println("Gli elementi nella tabella sport sono: " + sportServiceInstance.listAll().size());
			// ###########################################################################################

//			// TEST deleteAtleta
//			cancellazioneAtleta(atletaServiceInstance);
//			System.out.println("Gli elementi nella tabella atleta sono: " + atletaServiceInstance.listAll().size());
			// ###########################################################################################

//			// TEST updateAtleta
//			updateAtleta(atletaServiceInstance);
//			System.out.println("Gli elementi nella tabella atleta sono: " + atletaServiceInstance.listAll().size());
			// ###########################################################################################

//			// TEST deleteSport
//			cancellazioneSport(sportServiceInstance);
//			System.out.println("Gli elementi nella tabella sport sono: " + sportServiceInstance.listAll().size());
			// ###########################################################################################

//			// TEST updateSport
//			updateSport(sportServiceInstance);
//			System.out.println("Gli elementi nella tabella sport sono: " + sportServiceInstance.listAll().size());
			// ###########################################################################################

//			// TEST trovaErrori
//			trovaErrori(sportServiceInstance);
//			System.out.println("Gli elementi nella tabella sport sono: " + sportServiceInstance.listAll().size());
			// ###########################################################################################

//			// TEST sommaMedaglieInSportChiusi
//			sommaMedaglie(atletaServiceInstance);
//			System.out.println("Gli elementi nella tabella atleta sono: " + atletaServiceInstance.listAll().size());
			testModificaStatoUtente(atletaServiceInstance);
			System.out.println("Gli elementi nella tabella atleta sono: " + atletaServiceInstance.listAll().size());

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			// chiudo tutte le connessioni e rilascioil main
			EntityManagereUtil.shutdown();
		}
	}

	// ###########################################################################################
	// METODO CHE MI INIZIALIZZA SPORT
	private static void inizializzaSport(SportService sportServiceInstance) throws Exception {
		if (sportServiceInstance.cercaPerDescrizione("Calcio") == null)
			sportServiceInstance.inserisciNuovo(new Sport("Calcio"));
		if (sportServiceInstance.cercaPerDescrizione("Basket") == null)
			sportServiceInstance.inserisciNuovo(new Sport("Basket"));
		if (sportServiceInstance.cercaPerDescrizione("Nuoto") == null)
			sportServiceInstance.inserisciNuovo(new Sport("Nuoto"));
		if (sportServiceInstance.cercaPerDescrizione("Pallavolo") == null)
			sportServiceInstance.inserisciNuovo(new Sport("Pallavolo"));
	}

	// ###########################################################################################
	// METODO CHE MI INSERISCE UN NUOVO ATLETA NEL DB
	private static void inserimentoAtleta(AtletaService atletaServiceInstance) throws Exception {
		System.out.println("-----TEST testInserisciAtleta INIZIO-----");
		Atleta nuovoAtleta = new Atleta("alessandro", "stefu", LocalDate.of(2002, 10, 06), "1", 15);
		Atleta secondoAtleta = new Atleta("mario", "rossi", LocalDate.of(2001, 10, 06), "3", 6);
		atletaServiceInstance.inserisciNuovo(nuovoAtleta);
		atletaServiceInstance.inserisciNuovo(secondoAtleta);
		if (nuovoAtleta.getId() == null) {
			throw new RuntimeException("testInserisciNuovoAtleta FALLITO: atleta non inserito.");
		}
		System.out.println("-----TEST testInserisciNuovoAtleta FINE-----");
	}
	// ###########################################################################################

	private static void collegamentoAtletaASport(AtletaService atletaServiceInstance, SportService sportServiceInstance)
			throws Exception {
		System.out.println("-----TEST testCollegaAtletaASportEsistente INIZIO-----");

		Sport sportEsistenteSuDB = sportServiceInstance.cercaPerDescrizione("Calcio");
		if (sportEsistenteSuDB == null) {
			throw new RuntimeException("testInserisciNuovoAtleta FALLITO: sport inesistente");
		}

		List<Atleta> listaAtleti = atletaServiceInstance.listAll();
		if (listaAtleti.size() < 1) {
			throw new RuntimeException("testInserisciNuovoAtleta FALLITO: Non ci sono atleti nel DB");
		}
		Atleta atletaEsistente = listaAtleti.get(0);
		atletaServiceInstance.aggiungiSport(atletaEsistente, sportEsistenteSuDB);
		Atleta altetaReloaded = atletaServiceInstance.caricaSingoloAtleta(atletaEsistente.getId());
		if (altetaReloaded.getSports().size() != 1)
			throw new RuntimeException("testInserisciNuovoAtleta FALLITO: sport non collegato");
		System.out.println("-----TEST testCollegaAtletaASportEsistente FINE-----");

	}

	// ###########################################################################################
	// METODO CHE CANCELLA UN ATLETA DAL DB
	private static void cancellazioneAtleta(AtletaService atletaServiceInstance) throws Exception {
		System.out.println("-----TEST testDeleteAtleta INIZIO-----");
		List<Atleta> listaAtletiPrimaDellaRimozione = atletaServiceInstance.listAll();
		if (listaAtletiPrimaDellaRimozione.size() < 1)
			throw new RuntimeException("testDeleteAtleta FALLITO: non sono presenti atleti in lista.");

		// inserisco un atleta per poi eliminarlo
		Atleta nuovoAtleta = new Atleta("mario", "bianchi", LocalDate.of(2005, 11, 9), "2", 8);
		atletaServiceInstance.inserisciNuovo(nuovoAtleta);
		if (nuovoAtleta.getId() == null) {
			throw new RuntimeException("testDeleteAtleta FALLITO: atleta non inserito.");
		}
		atletaServiceInstance.rimuovi(nuovoAtleta.getId());
		List<Atleta> listaAtletiDopoDellaRimozione = atletaServiceInstance.listAll();
		if (listaAtletiPrimaDellaRimozione.size() != listaAtletiDopoDellaRimozione.size())
			throw new RuntimeException("testDeleteAtleta FALLITO: atleta non rimosso.");
		System.out.println("-----TEST testDeleteAtleta FINE-----");

	}

	// ###########################################################################################
	// METODO CHE MI AGGIORNA UN ELEMENTO NEL DB
	private static void updateAtleta(AtletaService atletaServiceInstance) throws Exception {
		System.out.println("-----TEST testUpdateAtleta INIZIO-----");
		List<Atleta> listaAtleti = atletaServiceInstance.listAll();
		if (listaAtleti.size() < 1)
			throw new RuntimeException("testDeleteAtleta FALLITO: non sono presenti atleti in lista.");
		Atleta atletaDaAggiornare = listaAtleti.get(0);
		String nuovoNome = "giacomo";
		atletaDaAggiornare.setNome(nuovoNome);
		atletaServiceInstance.aggiorna(atletaDaAggiornare);
		System.out.println(atletaDaAggiornare);
		System.out.println("-----TEST testUpdateAtleta FINE-----");
	}

	// ###########################################################################################
	// METODO CHE SI OCCUPA CON LA CANCELLAZIONE DI UNO SPORT
	private static void cancellazioneSport(SportService sportServiceInstance) throws Exception {
		System.out.println("-----TEST testDeleteSport INIZIO-----");
		List<Sport> listaSportPrimaDellaRimozione = sportServiceInstance.listAll();
		if (listaSportPrimaDellaRimozione.size() < 1)
			throw new RuntimeException("testDeleteSport FALLITO: non sono presenti sport in lista.");

		// inserisco un atleta per poi eliminarlo
		Sport nuovoSport = new Sport("Corsa");
		sportServiceInstance.inserisciNuovo(nuovoSport);
		if (nuovoSport.getId() == null) {
			throw new RuntimeException("testDeleteSport FALLITO: atleta non inserito.");
		}
		sportServiceInstance.rimuovi(nuovoSport.getId());
		List<Sport> listaSportDopoDellaRimozione = sportServiceInstance.listAll();
		if (listaSportPrimaDellaRimozione.size() != listaSportDopoDellaRimozione.size())
			throw new RuntimeException("testDeleteSport FALLITO: sport non rimosso.");
		System.out.println("-----TEST testDeleteSport FINE-----");

	}

	// ###########################################################################################
	// METODO CHE SI OCCUPA CON L'AGGIORNAMENTO DI UNO SPORT
	private static void updateSport(SportService sportServiceInstance) throws Exception {
		System.out.println("-----TEST testUpdateAtleta INIZIO-----");
		List<Sport> listaSport = sportServiceInstance.listAll();
		if (listaSport.size() < 1)
			throw new RuntimeException("testUpdateSport FALLITO: non sono presenti sport in lista.");
		Sport sportDaAggiornare = listaSport.get(3);
		String nuovaDescrizione = "Corsa";
		sportDaAggiornare.setDescrizione(nuovaDescrizione);
		sportServiceInstance.aggiorna(sportDaAggiornare);
		System.out.println(sportDaAggiornare);
		System.out.println("-----TEST testUpdateAtleta FINE-----");
	}

	// ###########################################################################################
	// METODO CHE MI TROVA GLI ERRORI COME AD ESEMPIO UN ATLETA CHE INIZIA DOPO LA
	// DATA FINE
	private static void trovaErrori(SportService sportServiceInstance) throws Exception {
		System.out.println("-----TEST testTrovaErrori INIZIO-----");
		List<Sport> listaSport = sportServiceInstance.listAll();
		if (listaSport.size() < 1)
			throw new RuntimeException("testTrovaErrori FALLITO: non sono presenti sport in lista.");
		Sport sportConErrore = new Sport("Palestra", LocalDate.now(), LocalDate.of(2000, 12, 1));
		sportServiceInstance.inserisciNuovo(sportConErrore);
		if (sportConErrore.getId() == null) {
			throw new RuntimeException("testTrovaErrori FALLITO: sportConErrore non inserito");
		}
		List<Sport> listaErrori = sportServiceInstance.cercaErrori();
		if (listaErrori.size() < 1) {
			throw new RuntimeException("testTrovaErrori FALLITO: non sono presenti errori");
		}
		System.out.println(listaErrori);
		sportServiceInstance.rimuovi(sportConErrore.getId());
		List<Sport> listaSportDopoRimozione = sportServiceInstance.listAll();
		if (listaSport.size() != listaSportDopoRimozione.size())
			throw new RuntimeException("testTrovaErrori FALLITO: sport non rimosso.");
		System.out.println("-----TEST testTrovaErrori FINE-----");

	}

	// ###########################################################################################
	// MI SOMMA LE MEDAGLIE DI UN ATLETA CHIUSO
	private static void sommaMedaglie(AtletaService atletaServiceInstance) throws Exception {
		System.out.println("-----TEST testSommaMedaglieInSportChiusi INIZIO-----");
		List<Atleta> listaAtleti = atletaServiceInstance.listAll();
		if (listaAtleti.size() < 1)
			throw new RuntimeException("testSommaMedaglieInSportChiusi FALLITO: non sono presenti atleti in lista.");
		Long sommaMedaglie = atletaServiceInstance.sommaMedaglieVinteInSportChiusi();
		System.out.println("La somma delle medaglie vinte in sport chiusi è: " + sommaMedaglie);
		System.out.println("-----TEST testSommaMedaglieInSportChiusi FINE-----");
	}

	// ###########################################################################################
	// fine metodi per i test
	private static void testModificaStatoUtente(AtletaService atletaServiceINstance) throws Exception {
		System.out.println(".......testModificaStatoUtente inizio.............");

		// mi creo un utente inserendolo direttamente su db
		Atleta nuovoAtleta = new Atleta("francesco", "colatei", LocalDate.of(2002, 10, 06), "9", 15);
		Atleta secondoAtleta = new Atleta("leonardo", "morelli", LocalDate.of(2002, 1, 06), "5", 17);
		atletaServiceINstance.inserisciNuovo(nuovoAtleta);
		atletaServiceINstance.inserisciNuovo(secondoAtleta);
		if (nuovoAtleta.getId() == null || secondoAtleta.getId() == null)
			throw new RuntimeException("testModificaStatoUtente fallito: utente non inserito ");

		// proviamo a passarlo nello stato ATTIVO ma salviamoci il vecchio stato
		StatoAtleta vecchioStato = nuovoAtleta.getStato();
		StatoAtleta vecchioStatosecondoAtleta = secondoAtleta.getStato();
		nuovoAtleta.setStato(StatoAtleta.PRATICANTE);
		secondoAtleta.setStato(StatoAtleta.DISABILITATO);
		atletaServiceINstance.aggiorna(nuovoAtleta);
		atletaServiceINstance.aggiorna(secondoAtleta);

		if (nuovoAtleta.getStato().equals(vecchioStato) || secondoAtleta.getStato().equals(vecchioStatosecondoAtleta))
			throw new RuntimeException("testModificaStatoUtente fallito: modifica non avvenuta correttamente ");

		System.out.println(".......testModificaStatoUtente fine: PASSED.............");
	}
}
