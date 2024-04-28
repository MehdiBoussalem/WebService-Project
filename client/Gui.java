package SportsPredictions.management.client;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import SportsPredictions.management.web.data.League;
import SportsPredictions.management.web.data.Team;

import java.awt.Desktop;
import java.awt.Font;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class Gui {
    private JFrame frame;

    public void createWindow() {
        // Création d'une instance de JFrame avec le titre spécifié
        frame = new JFrame("Ma Fenêtre");
        frame.setSize(1440, 500);

        // Définition de l'opération par défaut lorsque la fenêtre est fermée
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Création d'une étiquette "REST"
        JLabel restLabel = new JLabel("REST");
        restLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Définir la police avec une taille de 24
        restLabel.setBounds(100, 50, 100, 30); // Définir les coordonnées x, y, largeur et hauteur

        // Création des boutons
        JButton createLeagueButton = new JButton("Create League");
        createLeagueButton.setBounds(50, 100, 150, 30); // Définir les coordonnées x, y, largeur et hauteur

        JButton createTeamButton = new JButton("create Team");
        createTeamButton.setBounds(50, 150, 150, 30); // Définir les coordonnées x, y, largeur et hauteur

        JButton getTeamsButton = new JButton("Get Teams");
        getTeamsButton.setBounds(50, 200, 150, 30); // Définir les coordonnées x, y, largeur et hauteur

        
     // Création d'un ActionListener pour le bouton "addLeagueButton"
        createLeagueButton.addActionListener(e -> {
            // Demander le nom de la ligue
            String leagueName = JOptionPane.showInputDialog(frame, "Enter the league name:");
            if (leagueName != null && !leagueName.isEmpty()) {
                // Demander le sport
                String sport = JOptionPane.showInputDialog(frame, "Enter the sport:");
                if (sport != null && !sport.isEmpty()) {
                    // Demander le pays
                    String country = JOptionPane.showInputDialog(frame, "Enter the country:");
                    if (country != null && !country.isEmpty()) {
                        // Demander le niveau
                        String level = JOptionPane.showInputDialog(frame, "Enter the level:");
                        if (level != null && !level.isEmpty()) {
                            // Créer l'objet League
                            League league = new League(leagueName, sport, country, level);

                            // Appel de la méthode addLeague
                            WebService ws = new WebService();
                            String uri = ws.addLeague(league);

                            if (uri != null) {
                                // Si la méthode retourne un URI, ouvrez le lien dans le navigateur
                                    try {
										Desktop.getDesktop().browse(new URI(uri));
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (URISyntaxException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
                                
                            }

                            } else {
                                // Si la méthode retourne null, affichez un message d'erreur dans une boîte de dialogue
                                JOptionPane.showMessageDialog(frame, "Error", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            
        });
        
        createTeamButton.addActionListener(e -> {
            WebService ws = new WebService();

            // Demander le nom de l'équipe
            String teamName = JOptionPane.showInputDialog(frame, "Enter the team name:");
            if (teamName != null && !teamName.isEmpty()) {
                // Demander le niveau de l'équipe
                String teamLevel = JOptionPane.showInputDialog(frame, "Enter the team level:");
                if (teamLevel != null && !teamLevel.isEmpty()) {
                    // Demander l'ID de la ligue
                    String leagueIdString = JOptionPane.showInputDialog(frame, "Enter the league ID:");
                    if (leagueIdString != null && !leagueIdString.isEmpty()) {
                        try {
                            Integer leagueId = Integer.parseInt(leagueIdString);
                            
                            // Appel de la méthode addTeamToLeague
                            String uri = ws.addTeamToLeague(leagueId, teamName, teamLevel);

                            if (uri != null) {
                                // Afficher un message indiquant que l'équipe a été créée
                                JOptionPane.showMessageDialog(frame, "Team created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                // Si la méthode retourne null, affichez un message d'erreur dans une boîte de dialogue
                                JOptionPane.showMessageDialog(frame, "Error: Unable to add team to league.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException ex) {
                            // En cas d'erreur de format de l'ID de la ligue
                            JOptionPane.showMessageDialog(frame, "Error: Invalid league ID.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

            
        getTeamsButton.addActionListener(e -> {
            // Demander l'ID de la ligue
            String leagueIdString = JOptionPane.showInputDialog(frame, "Enter the league ID:");
            if (leagueIdString != null && !leagueIdString.isEmpty()) {
                try {
                    Integer leagueId = Integer.parseInt(leagueIdString);
                    
                    // Appel de la méthode getTeamsInLeague
                    WebService ws = new WebService();
                    String teamWebServiceUrl = ws.getTeamsInLeague(leagueId);
                    
                    // Ouvrir la page des équipes dans la ligue spécifique dans le navigateur
                    try {
                        Desktop.getDesktop().browse(new URI(teamWebServiceUrl));
                    } catch (IOException | URISyntaxException ex) {
                        // En cas d'erreur lors de l'ouverture du lien, affichez un message d'erreur
                        JOptionPane.showMessageDialog(frame, "Error: Unable to open the link in the browser.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    // En cas d'erreur de format de l'ID de la ligue
                    JOptionPane.showMessageDialog(frame, "Error: Invalid league ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });




        
        
        
        
        
        
        
        
        // Création d'une étiquette "API" avec une police et une taille spécifiées
        JLabel apiLabel = new JLabel("API");
        apiLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Définir la police avec une taille de 24
        apiLabel.setBounds(1175, 50, 100, 30);
        
        // Création d'un bouton "getTOPSc"
        JButton getTOPSc = new JButton("Get Top Scorers");
        getTOPSc.setBounds(1100, 100, 200, 30); // Définir les coordonnées x, y, largeur et hauteur 
        
        // Création d'un bouton "API"
        JButton predict = new JButton("Predict Winner");
        predict.setBounds(1100, 150, 200, 30); // Définir les coordonnées x, y, largeur et hauteur 
        
        
     // Création d'un ActionListener pour le bouton "getTOPSc"
        getTOPSc.addActionListener(e -> {
            // Demander le pays
            String country = JOptionPane.showInputDialog(frame, "Enter the country name:");
            if (country != null && !country.isEmpty()) {
                // Demander la ligue
                String league = JOptionPane.showInputDialog(frame, "Enter the league name:");
                if (league != null && !league.isEmpty()) {
                    // Obtenir les meilleurs buteurs
                    String result = getTopScorer(country, league);
                    // Afficher le résultat dans une boîte de dialogue
                    JOptionPane.showMessageDialog(frame, result, "Top Scorers", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
     // Création d'un ActionListener pour le bouton "predict"
        predict.addActionListener(e -> {
            // Demander les équipes, le pays et la ligue
            String teamA = JOptionPane.showInputDialog(frame, "Enter the name of team A:");
            if (teamA != null && !teamA.isEmpty()) {
                String teamB = JOptionPane.showInputDialog(frame, "Enter the name of team B:");
                if (teamB != null && !teamB.isEmpty()) {
                    String country = JOptionPane.showInputDialog(frame, "Enter the country:");
                    if (country != null && !country.isEmpty()) {
                        String league = JOptionPane.showInputDialog(frame, "Enter the league:");
                        if (league != null && !league.isEmpty()) {
                            // Obtenir la prédiction
                            String result = getPrediction(teamA, teamB, country, league);
                            // Afficher le résultat dans une boîte de dialogue
                            JOptionPane.showMessageDialog(frame, result, "Prediction", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });
        

        // Création d'une étiquette "SOAP" 
        JLabel soapLabel = new JLabel("SOAP");
        soapLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Définir la police avec une taille de 24
        soapLabel.setBounds(600, 50, 100, 30);
        

        // Création des boutons
        JButton createLeagueButtonSOAP = new JButton("Create League");
        createLeagueButtonSOAP.setBounds(575, 100, 150, 30); // Définir les coordonnées x, y, largeur et hauteur

        JButton createTeamButtonSOAP = new JButton("create Team");
        createTeamButtonSOAP.setBounds(575, 150, 150, 30); // Définir les coordonnées x, y, largeur et hauteur

        JButton getTeamsButtonSOAP = new JButton("Get Teams");
        getTeamsButtonSOAP.setBounds(575, 200, 150, 30); // Définir les coordonnées x, y, largeur et hauteur

        
        
     // Création d'un ActionListener pour le bouton "createLeagueButton"
        createLeagueButtonSOAP.addActionListener(e -> {
            // Demander le nom de la ligue
            String leagueName = JOptionPane.showInputDialog(frame, "Enter the league name:");
            if (leagueName != null && !leagueName.isEmpty()) {
                // Demander le sport
                String sport = JOptionPane.showInputDialog(frame, "Enter the sport:");
                if (sport != null && !sport.isEmpty()) {
                    // Demander le pays
                    String country = JOptionPane.showInputDialog(frame, "Enter the country:");
                    if (country != null && !country.isEmpty()) {
                        // Demander le niveau
                        String level = JOptionPane.showInputDialog(frame, "Enter the level:");
                        if (level != null && !level.isEmpty()) {
                            // Créer l'objet League
                            League league = new League(leagueName,sport,country,level);
                          ;

                            // Appel de la méthode addLeagueSOAP du service WebService
                            WebService ws = new WebService();
                            String uri = ws.addLeagueSOAP(league);

                            if (uri != null) {
                                // Si la méthode retourne un URI, ouvrir le lien dans le navigateur
                                try {
                                    Desktop.getDesktop().browse(new URI(uri));
                                } catch (IOException | URISyntaxException ex) {
                                    ex.printStackTrace();
                                    JOptionPane.showMessageDialog(frame, "Error: Unable to open the link in the browser.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                // Si la méthode retourne null, afficher un message d'erreur dans une boîte de dialogue
                                JOptionPane.showMessageDialog(frame, "Error: Failed to add league.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            }
        });


        // Ajout des composants à la fenêtre
        frame.add(restLabel);
        frame.add(createLeagueButton);
        frame.add(createTeamButton);
        frame.add(getTeamsButton);
        
        frame.add(apiLabel);
        frame.add(getTOPSc);
        frame.add(predict);
        
        frame.add(soapLabel);
        frame.add(createLeagueButtonSOAP);
        frame.add(createTeamButtonSOAP);
        frame.add(getTeamsButtonSOAP);        
        // Définition du layout manager comme null pour positionner les composants manuellement
        frame.setLayout(null);

        // Rendre la fenêtre visible
        frame.setVisible(true);
    }


    public String getTopScorer(String country, String league) {
    	FootBallAPI api = new FootBallAPI();
        String countryID = api.getCountryIdByName(country);
        if (countryID == null) {
            return "Error: The country '" + country + "' does not exist or there is a spelling error.";
        }

        String leagueID = api.getLeaguesIdByName(league, countryID);
        if (leagueID == null) {
            return "Error: The league '" + league + "' does not exist or there is a spelling error.";
        }

        List<Map<String, String>> topScorersList = api.getTopScorers(leagueID);
        if (topScorersList == null) {
            return "Error: No results found for top scorers in the league '" + league + "'.";
        }
        StringBuilder displayTextBuilder = new StringBuilder();

        // Parcours de la liste des meilleurs buteurs
        int position = 1;
        for (Map<String, String> topScorer : topScorersList) {
            String playerName = topScorer.get("player_name");
            String teamName = topScorer.get("team_name");
            String goals = topScorer.get("goals");

            // Ajout des informations formatées à la chaîne de texte
            displayTextBuilder.append(position).append(", ");
            displayTextBuilder.append("Name: ").append(playerName).append(", ");
            displayTextBuilder.append("Team: ").append(teamName).append(", ");
            displayTextBuilder.append("Goals: ").append(goals).append("\n");

            position++;
        }

        // Convertir la chaîne de texte en une seule chaîne
        return displayTextBuilder.toString();
    }


    public String getPrediction(String teamA,String teamB,String country,String league) {
    	String displayText = null;
    	FootBallAPI api = new FootBallAPI();
    	displayText = api.predictBestTeam(teamA, teamB, league, country);
    	if (displayText==null) {
    		return "Error the country , league or teams dont exist !";
    	}
    	
    	displayText = "The predicted winner is : " + displayText;
    	
    	return displayText;
		
	}
	}
	
 	

