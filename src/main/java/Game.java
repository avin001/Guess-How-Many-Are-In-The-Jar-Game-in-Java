public class Game {
    public static void main(String[] args) {
      
      Jar jar = new Jar();
      Prompter prompter = new Prompter(jar);
      prompter.displayWelcomeMessage();
      prompter.displayAdminSettingsMessage();
      String itemType = prompter.promptAdminForTypeOfItem();
      int maxNumOfItems = prompter.promptAdminForMaxNumberOfItems();
      jar = new Jar(itemType, maxNumOfItems);
      prompter.provideStartGameSummary();
      prompter.play();
    }
}
