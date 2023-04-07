package it.atletasportjpa.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sport")
public class Sport {
	// dichiaro le variabili che saranno collegato alle tabelle del db

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "descrizione")
	private String descrizione;
	@Column(name = "datainizio")
	private LocalDate dataInizio;
	@Column(name = "datafine")
	private LocalDate dataFine;

	// costruttore default
	public Sport() {
		super();
	}

	// costruttore
	public Sport(String descrizione) {
		super();
		this.descrizione = descrizione;
	}

	// costruttore
	public Sport(String descrizione, LocalDate dataInizio) {
		super();
		this.descrizione = descrizione;
		this.dataInizio = dataInizio;
	}

	// costruttore
	public Sport(String descrizione, LocalDate dataInizio, LocalDate dataFine) {
		super();
		this.descrizione = descrizione;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
	}

	// get e set
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}

	public LocalDate getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}
	// toString

	@Override
	public String toString() {
		String dataInizioString = dataInizio != null ? DateTimeFormatter.ofPattern("dd/MM/yyyy").format(dataInizio)
				: " N.D.";
		String dataFineString = dataFine != null ? DateTimeFormatter.ofPattern("dd/MM/yyyy").format(dataFine) : " N.D.";

		return "Sport [id=" + id + ", descrizione=" + descrizione + ", dataInizio=" + dataInizioString + ", dataFine="
				+ dataFineString + "]";
	}

}