package SportsPredictions.management.web.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class League {
    // Attributs privés de la classe League
    private String name;        // Le nom de la ligue
    private String sport;       // Le sport de la ligue (par exemple, football, basketball, etc.)
    private String country;     // Le pays où la ligue est basée
    private String division;    // La division de la ligue (par exemple, première division, deuxième division, etc.)
    private Integer id;         // L'identifiant de la ligue
    private List<Team> teams;   // Une liste des équipes appartenant à cette ligue

    // Constructeur par défaut
    public League() {
        this.teams = new ArrayList<>();
    }
 // Constructeur avec tous les attributs
    public League(String name, String sport, String country, String division, Integer id) {
        this.name = name;
        this.sport = sport;
        this.country = country;
        this.division = division;
        this.id = id;
        this.teams = new ArrayList<>();
    }
 // Constructeur sans l'identifiant (sera souvent utilisé lors de la création d'une nouvelle ligue)
    public League(String name, String sport, String country, String division) {
        this.name = name;
        this.sport = sport;
        this.country = country;
        this.division = division;
        this.id = null;
        this.teams = new ArrayList<>();
    }
    // Méthodes d'accès (getters) et de modification (setters) pour les attributs de la ligue
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement(name = "team")
    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
 // Méthode pour ajouter une équipe à la ligue
    public void addTeam(Team team) {
        this.teams.add(team);
    }
 // Méthode pour supprimer une équipe de la ligue
    public void removeTeam(Team team) {
        this.teams.remove(team);
    }
 // Méthode pour obtenir une représentation textuelle de la ligue
    @Override
    public String toString() {
        StringBuilder teamsStr = new StringBuilder();
        for (Team team : teams) {
            teamsStr.append(team.toString()).append("\n");
        }
        return id + "::" + name + "::" + sport + "::" + country + "::" + division + "\n" + teamsStr.toString();
    }
}
