package view;

import java.util.List;
import model.CategoryAndScore;

/**
 * Represents the view for this test grader program.
 */
public interface TestGraderView {

  /**
   * Returns a list of strings representing the student's answers.
   *
   * @param numQuestions the total number of questions in the test that is being taken.
   * @return a list of strings representing the student's answers.
   */
  List<String> getStudentAnswers(int numQuestions);

  /**
   * Outputs the results of the test after the student has taken it.
   */
  void outputResults();

  /**
   * Sets the total number of questions for this test.
   *
   * @param totalQuestions the total number of questions in this test.
   */
  void setTotalQuestions(int totalQuestions);

  /**
   * Sets the number of correct answers for this test.
   *
   * @param totalCorrect the total number of correct answers.
   */
  void setTotalCorrect(int totalCorrect);

  /**
   * Sets the grade of this test.
   *
   * @param grade the grade of this test.
   */
  void setGrade(double grade);

  /**
   * Sets the list of questions answered incorrectly for this test.
   *
   * @param incorrectNumbers the list of questions answered incorrectly.
   */
  void setIncorrectNums(List<Integer> incorrectNumbers);

  /**
   * Sets the graded categories of this test.
   *
   * @param gradedCategories the graded categories for this test
   */
  void setGradedCategories(List<CategoryAndScore> gradedCategories);
}
