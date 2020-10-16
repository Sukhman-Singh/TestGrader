import controller.TestGraderController;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.TestGraderModel;
import view.TestGraderTextualView;
import view.TestGraderView;
import view.TestGraderVisualView;

public class Main {

  public static void main(String[] args) {

    List<String> key = new ArrayList<String>(Arrays.asList("AB", "AB", "WC", "AA", "CC"));
    List<String> categories = new ArrayList<String>(Arrays.asList("W", "C", "A"));

    TestGraderModel model = new TestGraderModel(key, categories);
    TestGraderView view = new TestGraderTextualView(new InputStreamReader(System.in),
        System.out);
//    TestGraderView view = new TestGraderVisualView();

    TestGraderController control = new TestGraderController(model, view);

    control.takeTest();
  }
}