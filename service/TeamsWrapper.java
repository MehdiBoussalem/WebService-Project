package SportsPredictions.management.web.service;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import SportsPredictions.management.web.data.Team;

import java.util.List;

@XmlRootElement(name = "teams")
public class TeamsWrapper {
    private List<Team> teams;

    public TeamsWrapper() {
        // Constructeur par défaut requis par JAXB
    }

    public TeamsWrapper(List<Team> teams) {
        this.teams = teams;
    }

    @XmlElement(name = "team")
    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}