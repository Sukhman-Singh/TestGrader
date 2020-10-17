package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the model for this test grader program.
 */
public class TestGraderModel {

  private List<KeyQuestion> key;
  private List<String> categories;
  private Integer totalCorrect;
  private List<CategoryAndScore> gradedCategories;
  private List<Integer> incorrectNums;

  /**
   * Constructs a model for this test grader program.
   *
   * @param key        the answer key for this test grader. The format for the key will be
   *                   *Category*Question*
   */
  public TestGraderModel(List<KeyQuestion> key) {

    this.key = key;
    this.categories = createCategories(this.key);
  }

  // creates a list of categories using a key (list of keyquestions)
  private List<String> createCategories(List<KeyQuestion> key) {
    ArrayList<String> tempCategories = new ArrayList<String>();

    for (KeyQuestion keyquest : key) {
      if (!tempCategories.contains(keyquest.getCategory())) {
        tempCategories.add(keyquest.getCategory());
      }
    }

    return tempCategories;
  }

  /**
   * Grades this test with the provided student answers. Assigns the total correct answers field and
   * the grade categories field when this method is run.
   *
   * @param answers the student's answers
   */
  public void gradeTest(List<String> answers) {
    // check that the number of answers matches the number of questions
    if (answers.size() != this.key.size()) {
      throw new IllegalArgumentException(
          "The number of answers provided must match the number of questions in this test.");
    }

    // keep track of the total number of correct answers and all incorrect questions
    int correctAns = 0;
    List<Integer> tempIncorrectNums = new ArrayList<Integer>();

    // for each category, create a correct/total pair
    List<CategoryAndScore> tempGradeCategories = new ArrayList<CategoryAndScore>();
    for (String cat : this.categories) {
      tempGradeCategories.add(new CategoryAndScore(cat, 0, 0));
    }

    for (int i = 0; i < this.key.size(); i++) {

      // if the answer is correct
      if (this.key.get(i).getAnswer().equalsIgnoreCase(answers.get(i))) {
        tempGradeCategories
            .get(getIndexOfCategory(this.key.get(i).getCategory(), tempGradeCategories))
            .increaseCorrectAndTotal();
        correctAns++;

        // if the question is incorrect
      } else {
        // update the category that this question pertains to
        tempGradeCategories
            .get(getIndexOfCategory(this.key.get(i).getCategory(), tempGradeCategories))
            .increaseTotal();
        // add it to the list of incorrect numbers
        tempIncorrectNums.add(i + 1);
      }
    }

    // update all the fields
    this.totalCorrect = correctAns;
    this.incorrectNums = tempIncorrectNums;
    this.gradedCategories = tempGradeCategories;
  }

  // gets the index of the category in the provided list of categories
  private int getIndexOfCategory(String category, List<CategoryAndScore> categories) {
    for (int i = 0; i < categories.size(); i++) {
      if (category.equals(categories.get(i).getCategoryName())) {
        return i;
      }
    }

    throw new IllegalArgumentException(
        "The provided category is not within the provided list of categories.");
  }

  /**
   * Returns the list questions that were incorrect.
   *
   * @return the list questions that were incorrect
   */
  public List<Integer> getIncorrectNumbers() {
    if (this.incorrectNums == null) {
      throw new IllegalArgumentException(
          "You have to grade the test before getting the incorrect numbers.");
    }

    return this.incorrectNums;
  }

  /**
   * Returns the list of categories in this test.
   *
   * @return the list of categories in this test
   */
  public List<String> getCategories() {
    return this.categories;
  }

  /**
   * Returns the total number of questions in this test.
   *
   * @return the total number of questions in this test
   */
  public int getTotalQuestions() {
    return this.key.size();
  }

  /**
   * Returns the total number of questions answered correctly.
   *
   * @return the total number of questions answered correctly
   */
  public int getTotalCorrect() {
    if (this.totalCorrect == null) {
      throw new IllegalArgumentException(
          "You have to grade the test before getting the total number correct.");
    }

    return this.totalCorrect;
  }

  /**
   * Returns the percentage of questions answered correctly as a double rounded to the second
   * decimal place.
   *
   * @return the percentage of questions correct represented as a double
   */
  public double getGrade() {
    if (this.totalCorrect == null) {
      throw new IllegalArgumentException(
          "You have to grade the test before getting the grade.");
    }

    return 100.0 * ((double) this.getTotalCorrect() / (double) this.getTotalQuestions());
  }

  /**
   * Returns a list of graded categories of this test.
   *
   * @return a list of graded categories of this test
   */
  public List<CategoryAndScore> getGradedCategories() {
    if (this.gradedCategories == null) {
      throw new IllegalArgumentException(
          "You have to grade the test before getting the graded categories.");
    }

    return this.gradedCategories;
  }
}
