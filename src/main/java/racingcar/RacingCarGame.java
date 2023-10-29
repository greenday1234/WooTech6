package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import racingcar.domain.Player;
import racingcar.view.GameView;
import racingcar.view.InputView;
import racingcar.view.ResultView;

import java.util.ArrayList;

public class RacingCarGame {

    private InputView inputView;
    private ResultView resultView;
    private GameView gameView;
    private ArrayList<Player> players;
    private int RUN_COUNT;

    //생성자
    public RacingCarGame() {
        inputView = new InputView();
        resultView = new ResultView();
        gameView = new GameView();
        players = new ArrayList<>();
    }

    public void run(){
        inputView.inputName();
        inputCarName();
        inputView.inputRun();
        inputRunCount();

        gameView.gameRunView();
        for(int i=0; i<RUN_COUNT; i++){
            runGame();
            gameView.runView(players);
        }

        resultView.winView(winnerList());
    }

    //이름 입력 및 Player 객체 리스트 생성
    public void inputCarName() {
        String carNames = Console.readLine();
        String[] carArr = carNames.split(",");

        for (String s : carArr) {
            Player player = new Player(s);
            players.add(player);
        }
    }

    public void inputRunCount(){
        RUN_COUNT = Integer.parseInt(Console.readLine());
    }

    public void runGame(){
        for(int i=0; i<players.size(); i++){
            if(randomInt()){
                players.get(i).setRunCount();
            }
        }
    }

    public boolean randomInt(){
        if(Randoms.pickNumberInRange(0,9) >= 4 ){
            return true;
        }
        else{
            return false;
        }
    }

    public ArrayList<Player> winnerList(){
        ArrayList<Player> winners = new ArrayList<>();
        int MAX = findMAX();

        //winners
        for (Player player : players) {
            if(player.getRunCount() == MAX){
                winners.add(player);
            }
        }
        return winners;
    }

    public int findMAX(){
        int MAX = players.get(0).getRunCount();
        //최대값 찾기
        for (Player player : players) {
            if(MAX < player.getRunCount()){
                MAX = player.getRunCount();
            }
        }
        return MAX;
    }
}