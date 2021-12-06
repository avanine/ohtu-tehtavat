package ohtu;

public class TennisGame {

    private int playerOneScore = 0;
    private int playerTwoScore = 0;
    private String[] scoreStrings = new String[] {"Love", "Fifteen", "Thirty", "Forty"};
    private String playerOneName;
    private String playerTwoName;

    public TennisGame(String player1Name, String player2Name) {
        this.playerOneName = player1Name;
        this.playerTwoName = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == playerOneName) {
            playerOneScore += 1;
        } else {
            playerTwoScore += 1;
        }
    }

    private String equalScore() {
        if (playerOneScore < 4) {
            return scoreStrings[playerOneScore] + "-All";
        } else {
            return "Deuce";
        }
    }

    private String scoreGreaterThanFour() {
        int scoreDifference = playerOneScore - playerTwoScore;

            if (scoreDifference == 1) {
                return "Advantage player1";
            } else if (scoreDifference == -1) {
                return "Advantage player2";
            } else if (scoreDifference >= 2) {
                return "Win for player1";
            } else {
                return "Win for player2";
            }
    }

    public String getScore() {
        if (playerOneScore == playerTwoScore) {
            return equalScore();
        } else if (playerOneScore >= 4 || playerTwoScore >= 4) {
            return scoreGreaterThanFour();
        } else {
            return scoreStrings[playerOneScore] + "-" + scoreStrings[playerTwoScore];
        }
    }
}