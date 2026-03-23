package trivia;

import printer.ConsolePrinter;
import printer.Printer;

import java.util.*;

public class Game implements IGame {
   public static final int NB_QUESTION = 50;
   public static final int BOARD_SIZE = 12;
   public static final int WINNING_PURSE = 6;

   private final Map<Category, LinkedList<String>> questionsByCategory = new HashMap<>();
   private final List<Player> players = new ArrayList<>();

   private int currentPlayerIndex = 0;
   private boolean isGettingOutOfPenaltyBox;
   private final Printer printer = new ConsolePrinter();

   public Game() {
      initQuestions();
   }

   private void initQuestions() {
      for (Category category : Category.values()) {
         LinkedList<String> questions = new LinkedList<>();
         for (int i = 0; i < NB_QUESTION; i++) {
            questions.addLast(category.getLabel() + " Question " + i);
         }
         questionsByCategory.put(category, questions);
      }
   }

   public boolean add(String playerName) {
      players.add(new Player(playerName));
      printer.print(playerName + " was added");
      printer.print("They are player number " + players.size());
      return true;
   }

   private Player getCurrentPlayer() {
      return players.get(currentPlayerIndex);
   }

   public void roll(int roll) {
      Player player = getCurrentPlayer();
      printer.print(player + " is the current player");
      printer.print("They have rolled a " + roll);

      if (player.isInPenaltyBox()) {
         if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;
            printer.print(player + " is getting out of the penalty box");
            movePlayerAndAsk(player, roll);
         } else {
            printer.print(player + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
         }
      } else {
         movePlayerAndAsk(player, roll);
      }
      if(isGettingOutOfPenaltyBox){
         isGettingOutOfPenaltyBox = false;
         player.setInPenaltyBox(false);
      }
   }

   private void movePlayerAndAsk(Player player, int roll) {
      player.move(roll, BOARD_SIZE);
      printer.print(player + "'s new location is " + player.getPlace());
      askCurrentQuestion();
   }

   public void askCurrentQuestion() {
      Category category = Category.fromPosition(getCurrentPlayer().getPlace());
      printer.print("The category is " + category.getLabel());

      String question = questionsByCategory.get(category).removeFirst();
      printer.print(question);
   }

   public boolean handleCorrectAnswer() {
      Player player = getCurrentPlayer();

      if (player.isInPenaltyBox() && !isGettingOutOfPenaltyBox) {
         nextPlayer();
         return true;
      }

      printer.print("Answer was correct!!!!");
      player.addGold();
      printer.print(player + " now has " + player.getPurse() + " Gold Coins.");

      boolean winner = didPlayerWin();
      nextPlayer();

      return winner;
   }

   public boolean wrongAnswer() {
      Player player = getCurrentPlayer();
      printer.print("Question was incorrectly answered");
      printer.print(player + " was sent to the penalty box");
      player.setInPenaltyBox(true);

      nextPlayer();
      return true;
   }

   private void nextPlayer() {
      currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
   }

   private boolean didPlayerWin() {
      return !(getCurrentPlayer().getPurse() == WINNING_PURSE);
   }

}