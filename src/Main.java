import controller.TestGraderController;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.KeyQuestion;
import model.TestGraderModel;
import view.TestGraderTextualView;
import view.TestGraderView;
import view.TestGraderVisualView;

public class Main {

  public static void main(String[] args) {

    KeyQuestion keyquest1 = new KeyQuestion("A", "Math");
    KeyQuestion keyquest2 = new KeyQuestion("A", "Math");
    KeyQuestion keyquest3 = new KeyQuestion("A", "Writing");
    KeyQuestion keyquest4 = new KeyQuestion("A", "Writing");
    KeyQuestion keyquest5 = new KeyQuestion("A", "Reading");
    KeyQuestion keyquest6 = new KeyQuestion("A", "Reading");
    List<KeyQuestion> key = new ArrayList<KeyQuestion>(Arrays.asList(keyquest1, keyquest2, keyquest3, keyquest4, keyquest5, keyquest6));

    TestGraderModel model = new TestGraderModel(key);
//    TestGraderView view = new TestGraderTextualView(new InputStreamReader(System.in),
//        System.out);
    TestGraderView view = new TestGraderVisualView();

    TestGraderController control = new TestGraderController(model, view);

    control.takeTest();
  }
}