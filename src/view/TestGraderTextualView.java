package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.CategoryAndScore;

/**
 * Represents the view for this test grader program, displaying it textually.
 */
public class TestGraderTextualView implements TestGraderView {

  private final Appendable out;
  private final Scanner scan;
  private Integer totalCorrect;
  private List<Integer> incorrectNumbers;
  private List<CategoryAndScore> gradedCategories;
  private Integer totalQuestions;
  private Double grade;

  /**
   * Constructs the controller for this test grader program.
   * 
   * @param rd represents any input coming from the user.
   * @param ap represents any output sent to the user.
   */
  public TestGraderTextualView(Readable rd, Appendable ap) {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    scan = new Scanner(rd);
    this.out = ap;
  }

  @Override
  public List<String> getStudentAnswers(int numQuestions) {
    int question = 1;
    List<String> studAnswers = new ArrayList<String>();

    while (true) {

      if (question > numQuestions) {
        break;
      }

      try {
        this.out.append("Question " + question + "\n");
      } catch (IOException ioe) {
        throw new IllegalStateException("Append failed.", ioe);
      }

      String nextInput = scan.next();

      if (nextInput.equalsIgnoreCase("A") || nextInput.equalsIgnoreCase("B") ||
          nextInput.equalsIgnoreCase("C") || nextInput.equalsIgnoreCase("D")) {
        studAnswers.add(nextInput);
        question++;
      } else {
        try {
          this.out.append("Please provide a valid answer choice (A, B, C, or D).\n");
        } catch (IOException ioe) {
          throw new IllegalStateException("Append failed.", ioe);
        }
      }
    }

    return studAnswers;
  }

  @Override
  public void outputResults() {

    // ensure that the student has taken the test and the view has the data it needs to
    // output the score
    if (this.totalCorrect == null || this.incorrectNumbers == null
        || this.gradedCategories == null || this.totalQuestions == null
        || this.grade == null) {
      throw new IllegalArgumentException(
          "The student must take the test before the results can be outputted.");
    }

    // output the percent of answers that were correct, total answers correct and total questions
    try {
      this.out.append("\nScore: ");
      this.out.append(Double.toString(roundTwoDecimals(this.grade)));
      this.out.append("\nTotal Correct: ");
      this.out.append(Integer.toString((this.totalCorrect)));
      this.out.append("\nTotal Questions: ");
      this.out.append(Integer.toString(this.totalQuestions));
      this.out.append("\n");
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed.", ioe);
    }

    // print out the scores for each category
    for (CategoryAndScore catAndScore : this.gradedCategories) {
      try {
        this.out.append("\n");
        this.out.append("Category " + catAndScore.getCategoryName() + "\n");
        this.out.append("Score: ");
        this.out.append(Double.toString(roundTwoDecimals(catAndScore.getPercentage())));
        this.out.append("\nTotal Correct: ");
        this.out.append((Integer.toString(catAndScore.getCorrect())));
        this.out.append("\nTotal Questions: ");
        this.out.append(Integer.toString(catAndScore.getTotal()));
        this.out.append("\n");
      } catch (IOException ioe) {
        throw new IllegalStateException("Append failed.", ioe);
      }
    }

    // print out all the incorrect numbers

    try {
      this.out.append("\nQuestions Answered Incorrectly: ");

      for (Integer q : this.incorrectNumbers) {
        this.out.append(Integer.toString(q) + " ");
      }
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed.", ioe);
    }
  }

  // rounds the given double to two decimal places
  private double roundTwoDecimals(double dub) {
    return (double) Math.round(dub * 100) / 100;
  }

  @Override
  public void setTotalQuestions(int totalQuestions) {
    this.totalQuestions = totalQuestions;
  }

  @Override
  public void setTotalCorrect(int totalCorrect) {
    this.totalCorrect = totalCorrect;
  }

  @Override
  public void setGrade(double grade) {
    this.grade = grade;
  }

  @Override
  public void setIncorrectNums(List<Integer> incorrectNumbers) {
    this.incorrectNumbers = incorrectNumbers;
  }

  @Override
  public void setGradedCategories(List<CategoryAndScore> gradedCategories) {
    this.gradedCategories = gradedCategories;
  }
}
