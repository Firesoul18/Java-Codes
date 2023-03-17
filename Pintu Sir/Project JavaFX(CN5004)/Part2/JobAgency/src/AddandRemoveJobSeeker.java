import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddandRemoveJobSeeker {
    private static TextField id;
    private static TextField name;
    private static TextField location;
    private static TextArea req;
    private static Label l;

    public static void display() {
        Stage stage = new Stage();
        stage.setTitle("Add or Remove Job Seeker");

        Button button = new Button("Add Job Seeker");
        Button button2 = new Button("Remove Job Seeker");
        Label nameLabel = new Label("Name");
        Label locationLabel = new Label("Location");
        Label skillLabel = new Label("Skills(Seperated With comma(,))");
        Label remLabel = new Label("Enter Id to remove Job Seeker");
        id = new TextField();
        name = new TextField();
        location = new TextField();
        req = new TextArea();
        l = new Label();

        button.setOnAction(e -> addJobSeeker());
        button2.setOnAction(e -> removeJobSeeker());

        VBox layout = new VBox();

        GridPane top = new GridPane();
        HBox lower = new HBox();

        top.setVgap(30);
        top.setAlignment(Pos.CENTER);
        top.setPadding(new Insets(20, 20, 50, 20));

        lower.setAlignment(Pos.CENTER);
        lower.setSpacing(20);

        top.add(nameLabel, 0, 0);
        top.add(name, 1, 0);
        top.add(locationLabel, 0, 1);
        top.add(location, 1, 1);
        top.add(skillLabel, 0, 2);
        top.add(req, 1, 2);
        top.add(button, 1, 3);

        lower.getChildren().addAll(remLabel, id, button2);

        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(top, lower, l);

        Scene scene = new Scene(layout, 700, 700);
        stage.setScene(scene);
        stage.show();
    }

    private static void addJobSeeker() {
        String name = null;
        String location = null;
        ArrayList<String> skills = new ArrayList<String>();
        BufferedWriter jw = null;
        JobSeeker jbv = null;
        try {
            jw = new BufferedWriter(new FileWriter("Text Files/JobSeekers.txt", true));
            name = AddandRemoveJobSeeker.name.getText();
            location = AddandRemoveJobSeeker.location.getText();
            String skillset = AddandRemoveJobSeeker.req.getText();
            String s[] = skillset.split(",");
            Integer num = s.length;
            for (int i = 0; i < num; i++) {
                String skill = s[i].trim();
                skills.add(skill);
            }

            jbv = new JobSeeker(name, skills, location);
        } catch (IOException e) {
            l.setText("Something went wrong..");
            e.printStackTrace();
        }

        try {
            jw.append(jbv.getJSid() + ", " + name + ", " + location + ", " + skills.size() + ", ");
            for (int i = 0; i < skills.size(); i++) {
                jw.append(skills.get(i) + ", ");
            }

            jw.append("\n");
            App.getJobsSeekers().put(jbv.getJSid(), jbv);
            l.setText("New Job Seeker Added Successfully");
        } catch (IOException e) {
            e.printStackTrace();
            l.setText("Something Went Wrong..");
        } finally {
            try {
                jw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void removeJobSeeker() {
        try {
            int id = Integer.parseInt(AddandRemoveJobSeeker.id.getText());
            Object o = App.getJobsSeekers().remove(id);
            if (o == null)
                l.setText("No Job Seeker With This Id found.");
            else {
                l.setText("Deleted Job Seeker: " + ((JobSeeker) o).getName());
                File toChange = new File("Text Files/JobSeekers.txt");
                List<String> list;

                list = Files.lines(toChange.toPath()).filter(line -> !line.startsWith(id + ""))
                        .collect(Collectors.toList());
                Files.write(toChange.toPath(), list, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            }
        } catch (Exception e) {
            l.setText("Something Went Wrong..");
            e.printStackTrace();
        }

    }
}
