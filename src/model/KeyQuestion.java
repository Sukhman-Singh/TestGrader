package model;

// represents a question in the answer key and contains the answer and category it is
public class KeyQuestion {

  String answer;
  String category;

  // constructs a KeyQuestion using the provided answer and question
  public KeyQuestion(String answer, String category) {
    this.category = category;
    this.answer = answer;
  }

  // returns the answer to this question
  public String getAnswer() {
    return this.answer;
  }

  // returns category of this question
  public String getCategory() {
    return this.category;
  }
}
