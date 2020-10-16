package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.CategoryAndScore;

/**
 * Represents the view for this test grader program, displaying it visually.
 */
public class TestGraderVisualView extends JFrame implements TestGraderView {

  private JPanel testPanel;
  private JPanel resultsPanel;
  private Integer totalCorrect;
  private List<Integer> incorrectNumbers;
  private List<CategoryAndScore> gradedCategories;
  private Integer totalQuestions;
  private Double grade;
  private JLabel currentQ;
  private boolean testComplete;

  public TestGraderVisualView() {

    this.testComplete = false;
    this.setTitle("Test Grader");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.testPanel = new JPanel();

    this.add(this.testPanel);

    this.pack();
  }

  @Override
  public List<String> getStudentAnswers(int numQuestions) {
    this.setBounds(0, 0, 1000, 500);
    this.setVisible(true);

    List<String> studAnswers = new ArrayList<String>();

    JTextField answerBox = new JTextField(10);
    answerBox.setMinimumSize(new Dimension(200, 20));
    JButton submitAnswerButton = new JButton("Submit Answer");
    this.currentQ = new JLabel("Current Question: " + Integer.toString(studAnswers.size() + 1));
    submitAnswerButton.addActionListener((ActionEvent e) -> {

      if (!this.testComplete) {
        String studAns = answerBox.getText();

        if (studAns.equalsIgnoreCase("A") || studAns.equalsIgnoreCase("B") || studAns
            .equalsIgnoreCase("C") || studAns.equalsIgnoreCase("D")) {
          studAnswers.add(studAns);
        } else {
          JOptionPane.showMessageDialog(new JInternalFrame("Error Message", true, true, true, true),
              "Please provide a valid answer choice (A, B, C, or D).");
        }

        answerBox.setText("");
        this.currentQ.setText("Current Question: " + Integer.toString(studAnswers.size() + 1));

      }
    });

    this.testPanel.add(answerBox);
    this.testPanel.add(submitAnswerButton);
    this.testPanel.add(this.currentQ);
    this.currentQ.revalidate();

    while (!this.testComplete) {
      if (studAnswers.size() == numQuestions) {
        this.testComplete = true;
      }

      this.currentQ.revalidate();
    }

    this.currentQ.setText("Test Complete");
    return studAnswers;
  }

  @Override
  public void outputResults() {

    this.currentQ.setText("Test Complete");
    this.resultsPanel = new JPanel();
    this.resultsPanel.setLayout(new BoxLayout(this.resultsPanel, BoxLayout.PAGE_AXIS));

    this.resultsPanel.add(new JLabel("Total Questions: " + Integer.toString(this.totalQuestions)));
    this.resultsPanel.add(new JLabel("Total Correct: " + Integer.toString(this.totalCorrect)));

    String incorrectNumsString = "";
    for (Integer num : this.incorrectNumbers) {
      incorrectNumsString = incorrectNumsString + Integer.toString(num) + " ";
    }

    this.resultsPanel.add(new JLabel("Questions Answered Incorrectly: " + incorrectNumsString));

    for (CategoryAndScore catScore : this.gradedCategories) {

      this.resultsPanel.add(new JLabel(" "));
      this.resultsPanel.add(new JLabel("Category " + catScore.getCategoryName() + ": "));
      this.resultsPanel.add(new JLabel("Number Correct: " + Integer.toString(catScore.getCorrect())));
      this.resultsPanel.add(new JLabel("Total Questions: " + Integer.toString(catScore.getTotal())));
      this.resultsPanel.add(new JLabel("Score: " + Double.toString(catScore.getPercentage())));

    }

    this.remove(this.testPanel);
    this.add(this.resultsPanel);
    this.validate();

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
