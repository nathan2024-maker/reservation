package entities;



import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;




@XmlRootElement(name = "Salle")
public class Salle {
    
    private Long id = 0L;
    private String nom = "<indéfini>";
    private int capacite = 0;
    private List<String> equipements = null;
    private String statut = "<indéfini>";
    
    // Constructeurs publics
    public Salle() {}
    
    public Salle(Long id, String nom, int capacite, List<String> equipements, String statut) {
        this.id = id;
        this.nom = nom;
        this.capacite = capacite;
        this.equipements = equipements;
        this.statut = statut;
    }
    
    // Getters et Setters avec annotations
    @XmlElement(name = "id", required = true, nillable = true)
    public Long getId() {
        return id;
    }
    
    @XmlElement(name = "nom", required = true, nillable = true)
    public String getNom() {
        return nom;
    }
    
    @XmlElement(name = "capacite", required = true, nillable = true)
    public int getCapacite() {
        return capacite;
    }
    
    @XmlElement(name = "equipements", required = true, nillable = true)
    public List<String> getEquipements() {
        return equipements;
    }
    
    @XmlElement(name = "statut", required = true, nillable = true)
    public String getStatut() {
        return statut;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setEquipements(List<String> equipements) {
        this.equipements = equipements;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}