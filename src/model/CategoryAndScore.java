package model;

// A pair of integers that represent the number of correct answers and total questions
public class CategoryAndScore {

  String categoryName;
  int correct;
  int total;

  // constructs a CorrectTotalPair given the correct and total numbers
  public CategoryAndScore(String categoryName, int correct, int total) {
    this.categoryName = categoryName;
    this.correct = correct;
    this.total = total;
  }

  // returns category name
  public String getCategoryName() {
    return this.categoryName;
  }

  // returns the correct in the pair
  public int getCorrect() {
    return this.correct;
  }

  // returns the total in the pair
  public int getTotal() {
    return this.total;
  }

  // returns the total in the pair
  public double getPercentage() {
    return 100.0 * ((double) this.correct / (double) this.total);
  }

  protected void increaseCorrectAndTotal() {
    this.correct++;
    this.total++;
  }

  protected void increaseTotal() {
    this.total++;
  }

}