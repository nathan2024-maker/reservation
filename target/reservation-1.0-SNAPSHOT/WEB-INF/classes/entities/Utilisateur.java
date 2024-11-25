package entities;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Utilisateur")
public class Utilisateur {
    
    private Long id = 0L;
    private String nom = "<indéfini>";
    private String email = "<indéfini>";
    private String role = "<indéfini>";
    
    // Constructeurs publics
    public Utilisateur() {}
    
    public Utilisateur(Long id, String nom, String email, String role) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.role = role;
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
    
    @XmlElement(name = "email", required = true, nillable = true)
    public String getEmail() {
        return email;
    }
    
    @XmlElement(name = "role", required = true, nillable = true)
    public String getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }
}