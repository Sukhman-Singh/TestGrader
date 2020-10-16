package controller;

import model.TestGraderModel;
import view.TestGraderView;

/**
 * Represents the controller for this test grader program.
 */
public class TestGraderController {

  private TestGraderModel model;
  private TestGraderView view;


  public TestGraderController(TestGraderModel model, TestGraderView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.model = model;
    this.view = view;
  }

  public void takeTest() {

    // make the student take the test
    model.gradeTest(view.getStudentAnswers(model.getTotalQuestions()));

    // set all the fields of the view using the results of the test
    view.setTotalQuestions(model.getTotalQuestions());
    view.setTotalCorrect(model.getTotalCorrect());
    view.setIncorrectNums(model.getIncorrectNumbers());
    view.setGradedCategories(model.getGradedCategories());
    view.setGrade(model.getGrade());

    // output the results
    view.outputResults();
  }
}
