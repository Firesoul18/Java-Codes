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

public class AddandRemoveJob {
    private static TextField id;
    private static TextField title;
    private static TextField location;
    private static TextArea req;
    private static TextField company;
    private static Label l;

    public static void display() {
        Stage stage = new Stage();
        stage.setTitle("Add or Remove Job");

        Button button = new Button("Add Job");
        Button button2 = new Button("Remove Job");
        Label titleLabel = new Label("Title");
        Label locationLabel = new Label("Location");
        Label reqLabel = new Label("Required Skills(Seperated With comma(,))");
        Label remLabel = new Label("Enter Id to remove Job");
        Label cLabel = new Label("Company");
        l = new Label("");
        id = new TextField();
        title = new TextField();
        location = new TextField();
        req = new TextArea();
        company = new TextField();

        VBox layout = new VBox();

        GridPane top = new GridPane();
        HBox lower = new HBox();

        top.setVgap(30);
        top.setAlignment(Pos.CENTER);
        top.setPadding(new Insets(20, 20, 50, 20));

        lower.setAlignment(Pos.CENTER);
        lower.setSpacing(20);

        button.setOnAction(e -> addJob());
        button2.setOnAction(e -> removeJob());

        top.add(titleLabel, 0, 0);
        top.add(title, 1, 0);
        top.add(locationLabel, 0, 1);
        top.add(location, 1, 1);
        top.add(cLabel, 0, 2);
        top.add(company, 1, 2);
        top.add(reqLabel, 0, 3);
        top.add(req, 1, 3);
        top.add(button, 1, 4);

        lower.getChildren().addAll(remLabel, id, button2);

        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(top, lower, l);

        Scene scene = new Scene(layout, 700, 700);
        stage.setScene(scene);
        stage.show();
    }

    private static void addJob() {
        String title;
        String location;
        String company;
        ArrayList<String> requiredSkills = new ArrayList<String>();
        BufferedWriter jw=null;
        try {
            jw = new BufferedWriter(new FileWriter("Text Files/Jobs.txt",true));
        } catch (IOException e) {
            l.setText("Something went wrong..");
            e.printStackTrace();
        }

        title = AddandRemoveJob.title.getText();
        location = AddandRemoveJob.location.getText();
        company = AddandRemoveJob.company.getText();
        String skillset = AddandRemoveJob.req.getText();
        String skills[] = skillset.split(",");
        Integer num = skills.length;
        for (int i = 0; i < num; i++) {
            String skill = skills[i].trim();
            requiredSkills.add(skill);
        }

        JobVacency jbv = new JobVacency(title, requiredSkills, location, company);

        try {
            jw.append(jbv.getJobId() + ", " + title + ", " + location + ", " + requiredSkills.size() + ", ");
            for (int i = 0; i < requiredSkills.size(); i++) {
                jw.append(requiredSkills.get(i) + ", ");
            }

            jw.append(company + "\n");
        } catch (IOException e) {
            e.printStackTrace();
            l.setText("Something Went Wrong..");
        }
        finally{
            try {
                jw.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        App.getJobs().put(jbv.getJobId(), jbv);
        l.setText("New Job Added Successfully");
    }

    private static void removeJob() {
        int id = Integer.parseInt(AddandRemoveJob.id.getText());
        Object o = App.getJobs().remove(id);
        if (o == null)
            l.setText("No Job With This Id found.");
        else {
            l.setText("Deleted job: " + ((JobVacency) o).getTitle());
            File toChange = new File("Text Files/Jobs.txt");
            List<String> list;
            try {
                list = Files.lines(toChange.toPath()).filter(line -> !line.startsWith(id + ""))
                        .collect(Collectors.toList());
                        Files.write(toChange.toPath(), list, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException e) {
                l.setText("Something Went Wrong..");
                e.printStackTrace();
            }
            
        }
    }
}
