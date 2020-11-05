import controller.TestGraderController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.KeyQuestion;
import model.TestGraderModel;
import view.TestGraderTextualView;
import view.TestGraderView;
import view.TestGraderVisualView;

public class Main {

  public static void main(String[] args) {

    String nameOfAnimeFile;

    // check that there is one user input for the name of the answer key file
    try {
      nameOfAnimeFile = args[0];
    } catch (IndexOutOfBoundsException ioobe) {
      throw new IllegalArgumentException("Please input the file name for the answer key.");
    }

    List<KeyQuestion> answerKey = new ArrayList<KeyQuestion>();

    // try to parse the file
    try {
      File keyFile = new File(nameOfAnimeFile);
      Scanner myReader = new Scanner(keyFile);
      KeyQuestion tempKeyQuest;

      while (myReader.hasNextLine()) {
        String line = myReader.nextLine();
        tempKeyQuest = new KeyQuestion(line.substring(0,1), line.substring(2));
        answerKey.add(tempKeyQuest);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("Answer key was not found.");
      e.printStackTrace();
    }

    TestGraderModel model = new TestGraderModel(answerKey);
    TestGraderView view = new TestGraderTextualView(new InputStreamReader(System.in),
        System.out);
//    TestGraderView view = new TestGraderVisualView();

    TestGraderController control = new TestGraderController(model, view);

    control.takeTest();
  }
}