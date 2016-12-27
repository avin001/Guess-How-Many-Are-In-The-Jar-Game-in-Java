import java.util.Random;

public class Jar {
  public String mItemType;
  public int mMaxNumOfItems;
  private int mNumberOfTries;
  private int mRandomFillCount;
  private int guess;
  public boolean win;
  
  public Jar() {
    mItemType = "";
    mMaxNumOfItems = 0;
    mNumberOfTries = 0;
    mRandomFillCount = 0;
    guess = 0;
    win = false;
  }
  
  public Jar(String itemType, int maxNumOfItems) {
    mItemType = itemType;
    mMaxNumOfItems = maxNumOfItems;
  }
  
  public void fillJar() {
    Random random = new Random();
    do {
      mRandomFillCount = random.nextInt(mMaxNumOfItems);
    } while (mRandomFillCount == 0);
  }
  
  public String getItemTypeInJar() {
    return mItemType;
  }
  
  public int getMaxNumOfItemsInJar() {
    return mMaxNumOfItems;
  }
  
  public int getActualNumOfItemsInJar() {
    return mRandomFillCount;
  }
  
  public int getNumberOfTriesForRightGuess() {
    return mNumberOfTries;
  }
  
  public int getUserGuess() {
    return guess;
  }
  
  public void validateUserInputForTypeOfItem(String letters) {
    if (letters.length() == 0) {
      throw new IllegalArgumentException("User has not entered any type of item");
    }
    for (char letter : letters.toCharArray()) {
      if (!Character.isLetter(letter)) {
        throw new IllegalArgumentException("Numeric entered instead of item type");
      }
    }
  }
  
  public void validateUserInputForMaxNumOfItemsInJar(String letters) {
    if (letters.length() == 0) {
      throw new IllegalArgumentException("User has not entered a maximum for the type of items in jar.");
    }
    for (char letter : letters.toCharArray()) {
      if (Character.isLetter(letter)) {
        throw new IllegalArgumentException("Letter(s) entered instead of numeric value.");
      }
    }
    if (Integer.parseInt(letters) < 0) {
      throw new IllegalArgumentException("There can't be a negative quantity in your jar.");
    }
    if (Integer.parseInt(letters) == 0) {
      throw new IllegalArgumentException("There can't be a nil (zero) quantity in your jar.");
    }
    if (Integer.parseInt(letters) == 1) {
      throw new IllegalArgumentException("There can't be a single quantity as the maximum in your jar as we cannot play the guess game then.");
    }
  }
  
  private String normalizeGuess(String letters) {
    if (letters.length() == 0) {
      throw new IllegalArgumentException("User has not entered a guess.");
    }
    for (char letter : letters.toCharArray()) {
      if (Character.isLetter(letter)) {
        throw new IllegalArgumentException("Letter(s) entered instead of numeric value.");
      }
    }
    if (Integer.parseInt(letters) < 0) {
      throw new IllegalArgumentException("There can't be a negative quantity in your jar.");
    }
    return letters;
  }
  
  public boolean applyGuess(String letters) {
    letters = normalizeGuess(letters);
    guess = Integer.parseInt(letters);
    if (guess != mRandomFillCount) {
      mNumberOfTries++;
      if (guess > mMaxNumOfItems) {
        System.out.printf("Your guess should be less than %d.%n%n", mMaxNumOfItems);
        mNumberOfTries--;
      } else if (guess > mRandomFillCount) {
      System.out.printf("Your guess: %d is higher than count of %s in the jar.%n%n", guess, mItemType);
      } else if (guess < mRandomFillCount) {
      System.out.printf("Your guess: %d is lower than count of %s in the jar.%n%n", guess, mItemType);
      } 
  } else {
    win = true;
    System.out.printf("%nCongratulations! Your guess of %d %s in the jar is correct!%n", 
                      getActualNumOfItemsInJar(), getItemTypeInJar());
    System.out.printf("It took you %d guess(es) to get it right.%n", mNumberOfTries + 1);
    }
    return win;
  }
  
}