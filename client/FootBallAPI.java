package SportsPredictions.management.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.cxf.jaxrs.client.WebClient;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FootBallAPI {
    private static final String API_KEY = "cb0c0788e4dc7aa38e48863c73ff4a5ab8f79480a35d20223891903195704439";
    private static final String COUNTRIES_ENDPOINT = "https://apiv3.apifootball.com/?action=get_countries";
    private static final String LEAGUES_ENDPOINT = "https://apiv3.apifootball.com/?action=get_leagues";
    private static final String TEAMS_ENDPOINT = "https://apiv3.apifootball.com/?action=get_teams";
    private static final String TOP_SCORERS_ENDPOINT = "https://apiv3.apifootball.com/?action=get_topscorers";



    public List<Map<String, String>> getCountries() {
        List<Map<String, String>> countryList = new ArrayList<>();
        WebClient client = WebClient.create(COUNTRIES_ENDPOINT)
                .query("APIkey", API_KEY);

        String response = client.accept(MediaType.APPLICATION_JSON)
                .get(String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(response);
            for (JsonNode countryNode : jsonNode) {
                Map<String, String> country = new HashMap<>();
                country.put("country_id", countryNode.get("country_id").asText());
                country.put("country_name", countryNode.get("country_name").asText());
                countryList.add(country);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countryList;
    }

    public List<Map<String, String>> getLeagues(String country_id) {
        List<Map<String, String>> leagueList = new ArrayList<>();
        WebClient client = WebClient.create(LEAGUES_ENDPOINT)
                .query("APIkey", API_KEY)
                .query("country_id", country_id);

        String response = client.accept(MediaType.APPLICATION_JSON)
                .get(String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(response);
            for (JsonNode leagueNode : jsonNode) {
                Map<String, String> league = new HashMap<>();
                league.put("league_id", leagueNode.get("league_id").asText());
                league.put("league_name", leagueNode.get("league_name").asText());
                league.put("league_season", leagueNode.get("league_season").asText());
                leagueList.add(league);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return leagueList;
    }
    
    public List<Map<String, Object>> getTeams(String leagueID) {
        List<Map<String, Object>> teamList = new ArrayList<>();
        WebClient client = WebClient.create(TEAMS_ENDPOINT)
                .query("APIkey", API_KEY)
                .query("league_id", leagueID);

        String response = client.accept(MediaType.APPLICATION_JSON)
                .get(String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(response);
            for (JsonNode teamNode : jsonNode) {
                Map<String, Object> team = new HashMap<>();
                team.put("team_name", teamNode.get("team_name").asText());
                team.put("team_country", teamNode.get("team_country").asText());
             // Adding list of players for each team
                List<Map<String, String>> players = new ArrayList<>();
                for (JsonNode playerNode : teamNode.get("players")) {
                    Map<String, String> player = new HashMap<>();
                    player.put("player_name", playerNode.get("player_name").asText());
                    player.put("player_goals", playerNode.get("player_goals").asText());
                    
                    players.add(player);
                }
                team.put("players", players);
                teamList.add(team);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teamList;
    }
    
    public List<Map<String, String>> getTopScorers(String leagueID) {
        List<Map<String, String>> topScorersList = new ArrayList<>();
        WebClient client = WebClient.create(TOP_SCORERS_ENDPOINT)
                .query("APIkey", API_KEY)
                .query("league_id", leagueID);

        String response = client.accept(MediaType.APPLICATION_JSON)
                .get(String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(response);
            for (JsonNode topScorerNode : jsonNode) {
                Map<String, String> topScorer = new HashMap<>();
                topScorer.put("player_name", topScorerNode.get("player_name").asText());
                topScorer.put("team_name", topScorerNode.get("team_name").asText());
                topScorer.put("goals", topScorerNode.get("goals").asText());
                topScorersList.add(topScorer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return topScorersList;
    }
   
    public  String getCountryIdByName(String countryName) {
        List<Map<String, String>> countryList = this.getCountries();

        // Parcourir la liste des pays
        for (Map<String, String> country : countryList) {
            if (country.get("country_name").equals(countryName)) {
                return country.get("country_id");
            }
        }

        // Retourner null si le pays n'est pas trouvé
        return null;
    }
    
    public String getLeaguesIdByName(String leagueName, String countryId) {
        // Supposons que vous avez une méthode WebService.getLeagues(String countryId) qui retourne une liste de ligues avec leurs IDs
        List<Map<String, String>> leagueList = this.getLeagues(countryId);

        // Parcourir la liste des ligues
        for (Map<String, String> league : leagueList) {
            if (league.get("league_name").equals(leagueName)) {
                return league.get("league_id");
            }
        }

        // Retourner null si la ligue n'est pas trouvée
        return null;
    }
    
    public List<Map<String, String>> getTeamInfoByName(String teamName, String leagueId) {
        // Supposons que vous avez une méthode WebService.getTeams(String leagueId) qui retourne une liste d'équipes avec leurs informations
        List<Map<String, Object>> teamList = this.getTeams(leagueId);
        List<Map<String, String>> teamInfo = new ArrayList<>();

        // Parcourir la liste des équipes
        for (Map<String, Object> team : teamList) {
            if (team.get("team_name").equals(teamName)) {
                Map<String, String> teamData = new HashMap<>();
                for (Map.Entry<String, Object> entry : team.entrySet()) {
                    teamData.put(entry.getKey(), entry.getValue().toString());
                }
                teamInfo.add(teamData);
            }
        }

        return teamInfo;
    }
    public String predictBestTeam(String teamA, String teamB, String league, String country) {
        // Récupérer l'ID du pays
        String countryId = this.getCountryIdByName(country);
        if (countryId == null) {
            return null;
        }

        // Récupérer l'ID de la ligue
        String leagueId = getLeaguesIdByName(league, countryId);
        if (leagueId == null) {
            return null;
        }

        // Récupérer les informations sur l'équipe A
        List<Map<String, String>> teamAInfo = getTeamInfoByName(teamA, leagueId);
        if (teamAInfo.isEmpty()) {
            return null;
        }

        // Récupérer les informations sur l'équipe B
        List<Map<String, String>> teamBInfo = getTeamInfoByName(teamB, leagueId);
        if (teamBInfo.isEmpty()) {
            return null;
        }

        // Calculer la somme des buts marqués par l'équipe A
        int goalsTeamA = calculateTotalGoals(teamAInfo);

        // Calculer la somme des buts marqués par l'équipe B
        int goalsTeamB = calculateTotalGoals(teamBInfo);

        // Comparer les sommes des buts marqués et retourner le nom de l'équipe avec le plus grand nombre de buts
        if (goalsTeamA > goalsTeamB) {
            return teamA;
        } else if (goalsTeamB > goalsTeamA) {
            return teamB;
        } else {
            return null; // En cas d'égalité du nombre de buts
        }
    }
    private static int calculateTotalGoals(List<Map<String, String>> teamInfo) {
        int totalGoals = 0;

        // Parcours de chaque map dans la liste teamInfo
        for (Map<String, String> team : teamInfo) {
            // Récupération de la liste des joueurs de l'équipe depuis la clé "players"
            String playersJson = team.get("players");

            // Supprimer les caractères de crochet avant et après la chaîne JSON
            playersJson = playersJson.substring(1, playersJson.length() - 1);

            // Séparation de la chaîne JSON en un tableau de joueurs
            String[] playersArray = playersJson.split("\\},\\s*\\{");

            // Parcours de chaque joueur dans le tableau de joueurs
            for (String playerStr : playersArray) {
                // Remplacer les caractères spéciaux de la chaîne JSON pour obtenir des paires clé-valeur
                playerStr = playerStr.replaceAll("[{}]", "");
                String[] playerAttrs = playerStr.split(",\\s*");

                // Extraction des informations sur le joueur
                @SuppressWarnings("unused")
				String playerName = null;
                int playerGoals = 0;

                for (String attr : playerAttrs) {
                    String[] keyValue = attr.split("=");
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();

                    if (key.equals("player_name")) {
                        playerName = value;
                    } else if (key.equals("player_goals")) {
                        playerGoals = Integer.parseInt(value);
                    }
                }

                // Vous pouvez faire quelque chose avec ces informations, par exemple, ajouter les buts du joueur au total
                totalGoals += playerGoals;
            }
        }

        return totalGoals;
    }




    
    public static void main(String[] args) {
    	FootBallAPI api = new FootBallAPI();
    	
    	 // Définition des équipes, de la ligue et du pays
        String teamA = "Manchester City";
        String teamB = "Liverpool";
        String league = "Premier League";
        String country = "England";

        // Appel de la méthode predictBestTeam
        String bestTeam = api.predictBestTeam(teamA, teamB, league, country);

        // Affichage du résultat
        if (bestTeam != null) {
            System.out.println("The best team is: " + bestTeam);
        } else {
            System.out.println("Unable to determine the best team.");
        }    
    }
}

