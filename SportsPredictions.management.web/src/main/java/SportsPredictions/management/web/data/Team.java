package SportsPredictions.management.web.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Team {
    // Déclaration des attributs privés de la classe
    private String name;  // Le nom de l'équipe
    private String level; // Le niveau de l'équipe 
    private Integer id;   // L'identifiant de l'équipe

    // Constructeur par défaut
    public Team() {
    }

    // Constructeur avec tous les attributs
    public Team(String name, String level, Integer id) {
        this.name = name;
        this.level = level;
        this.id = id;
    }

    // Constructeur sans l'identifiant (sera souvent utilisé lors de la création d'une nouvelle équipe)
    public Team(String name, String level) {
        this.name = name;
        this.level = level;
        this.id = null;
    }

    // Méthodes d'accès (getters) pour les attributs de l'équipe

    // Getter pour le nom de l'équipe
    public String getName() {
        return name;
    }

    // Setter pour le nom de l'équipe
    public void setName(String name) {
        this.name = name;
    }

    // Getter pour le niveau de l'équipe
    public String getLevel() {
        return level;
    }

    // Setter pour le niveau de l'équipe
    public void setLevel(String level) {
        this.level = level;
    }

    // Getter pour l'identifiant de l'équipe
    public Integer getId() {
        return id;
    }

    // Setter pour l'identifiant de l'équipe
    public void setId(Integer id) {
        this.id = id;
    }

    // Méthode pour générer une représentation textuelle de l'équipe
    @Override
    public String toString() {
        return id + "::" + name + "::" + level;
    }
}
