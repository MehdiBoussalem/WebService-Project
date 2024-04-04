package SportsPredictions.management.web.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Team {
    // D�claration des attributs priv�s de la classe
    private String name;  // Le nom de l'�quipe
    private String level; // Le niveau de l'�quipe 
    private Integer id;   // L'identifiant de l'�quipe

    // Constructeur par d�faut
    public Team() {
    }

    // Constructeur avec tous les attributs
    public Team(String name, String level, Integer id) {
        this.name = name;
        this.level = level;
        this.id = id;
    }

    // Constructeur sans l'identifiant (sera souvent utilis� lors de la cr�ation d'une nouvelle �quipe)
    public Team(String name, String level) {
        this.name = name;
        this.level = level;
        this.id = null;
    }

    // M�thodes d'acc�s (getters) pour les attributs de l'�quipe

    // Getter pour le nom de l'�quipe
    public String getName() {
        return name;
    }

    // Setter pour le nom de l'�quipe
    public void setName(String name) {
        this.name = name;
    }

    // Getter pour le niveau de l'�quipe
    public String getLevel() {
        return level;
    }

    // Setter pour le niveau de l'�quipe
    public void setLevel(String level) {
        this.level = level;
    }

    // Getter pour l'identifiant de l'�quipe
    public Integer getId() {
        return id;
    }

    // Setter pour l'identifiant de l'�quipe
    public void setId(Integer id) {
        this.id = id;
    }

    // M�thode pour g�n�rer une repr�sentation textuelle de l'�quipe
    @Override
    public String toString() {
        return id + "::" + name + "::" + level;
    }
}
