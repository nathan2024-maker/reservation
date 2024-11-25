package entities;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@XmlRootElement(name = "Reservation")
public class Reservation {
    
    private Long id = 0L;
    private Long salleId = 0L;
    private Long utilisateurId = 0L;
    private String sujet = "<indéfini>";
    private LocalDateTime dateDebut = null;
    private LocalDateTime dateFin = null;
    private String statut = "<indéfini>";
    
    // Constructeurs publics
    public Reservation() {}
    
    public Reservation(Long id, Long salleId, Long utilisateurId, String sujet, 
                      LocalDateTime dateDebut, LocalDateTime dateFin, String statut) {
        this.id = id;
        this.salleId = salleId;
        this.utilisateurId = utilisateurId;
        this.sujet = sujet;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
    }
    
    // Getters et Setters avec annotations
    @XmlElement(name = "id", required = true, nillable = true)
    public Long getId() {
        return id;
    }
    
    @XmlElement(name = "salleId", required = true, nillable = true)
    public Long getSalleId() {
        return salleId;
    }
    
    @XmlElement(name = "utilisateurId", required = true, nillable = true)
    public Long getUtilisateurId() {
        return utilisateurId;
    }
    
    @XmlElement(name = "sujet", required = true, nillable = true)
    public String getSujet() {
        return sujet;
    }
    
    @XmlElement(name = "dateDebut", required = true, nillable = true)
    public LocalDateTime getDateDebut() {
        return dateDebut;
    }
    
    @XmlElement(name = "dateFin", required = true, nillable = true)
    public LocalDateTime getDateFin() {
        return dateFin;
    }
    
    @XmlElement(name = "statut", required = true, nillable = true)
    public String getStatut() {
        return statut;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSalleId(Long salleId) {
        this.salleId = salleId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}