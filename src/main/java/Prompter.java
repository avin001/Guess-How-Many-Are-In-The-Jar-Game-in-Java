import java.util.Scanner;

public class Prompter {
  private Jar mJar;
  Scanner scanner = new Scanner(System.in);
  
  public Prompter(Jar jar) {
    mJar = jar;
  }
  
  public void displayWelcomeMessage() {
    // The following code just prints a formatted welcome message to the user
    System.out.println("\n");
    String welcomeMessage = "Welcome to the game:  GUESS HOW MANY IN THE JAR !!!";
    System.out.println(welcomeMessage);
    for (char letter: welcomeMessage.toCharArray()) {
      System.out.print("=");
    }
    System.out.println("\n");
  }
  
  public void displayAdminSettingsMessage() {
    // The following code just prints a formatted message for game settings
    String adminMessage = "Please provide admin settings for the game.";
    System.out.println(adminMessage);
    System.out.println("\n");
    String titleAdminSettings = "ADMIN SETTINGS";
    System.out.println(titleAdminSettings);
    for (char letter: titleAdminSettings.toCharArray()) {
      System.out.print("=");
    }
    System.out.println("\n");
  }
  
  public String promptAdminForTypeOfItem() {
    // The following code prompts the user for providing the type of item in the jar
    boolean isAcceptable = false;
    do {
      System.out.print("Please provide the type of item you would want in your jar: ");
      String typeOfItemInJar = scanner.nextLine();
      try {
        mJar.validateUserInputForTypeOfItem(typeOfItemInJar);
        isAcceptable = true;
        mJar.mItemType = typeOfItemInJar;
      } catch (IllegalArgumentException iae) {
        System.out.printf("%s. Please try again. %n", iae.getMessage());
      }
    } while (!isAcceptable);
    return mJar.getItemTypeInJar();    
  }
  
   public int promptAdminForMaxNumberOfItems() {
    // The following code prompts the user for providing the maximum number of items in the jar
    boolean isAcceptable = false;
    do {
      System.out.printf("What is the maximum number of %s your jar should contain: ", mJar.getItemTypeInJar());
      String maxNumOfItemsInJar = scanner.nextLine();
      try {
        mJar.validateUserInputForMaxNumOfItemsInJar(maxNumOfItemsInJar);
        isAcceptable = true;
        mJar.mMaxNumOfItems = Integer.parseInt(maxNumOfItemsInJar);
      } catch (IllegalArgumentException iae) {
        System.out.printf("%s. Please try again. %n", iae.getMessage());
      }
    } while (!isAcceptable);
    return mJar.getMaxNumOfItemsInJar();
  }
  
  public void provideStartGameSummary() {
    // The following code just prints a game summary to the user
    System.out.println("\n\n");
    String startGameMessage = "STARTING GAME!!!";
    System.out.println(startGameMessage);
    for (char letter: startGameMessage.toCharArray()) {
      System.out.print("=");
    }
    System.out.println("\n");
    System.out.printf("As per your admin settings, a jar to hold %s has been created such that the jar can hold a maximum of %d %s.%n%n", mJar.getItemTypeInJar(), mJar.getMaxNumOfItemsInJar(), mJar.getItemTypeInJar());
    System.out.printf("Your goal is to guess how many %s are in the jar. Your guess should be between 1 and %d.%n%n", mJar.getItemTypeInJar(), mJar.getMaxNumOfItemsInJar());
  }
  
  public void promptForGuess() {
    boolean isValidGuess = false;
    String guessAsString = "";
    System.out.printf("Please provide a guess.%n");
    while(!isValidGuess) {
      System.out.print("Guess:  ");
      try {
        guessAsString = scanner.nextLine();
        mJar.applyGuess(guessAsString);
        isValidGuess = true;
    } catch (IllegalArgumentException iae) {
        System.out.println("Exception thrown:  " + iae.getMessage());
      }
    } 
  }
  
  public void play() {
    mJar.fillJar();
    while (!mJar.win) {
      promptForGuess();
    }
  } 
}
 
   