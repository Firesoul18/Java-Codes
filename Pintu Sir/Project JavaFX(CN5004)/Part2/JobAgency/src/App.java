import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {

    //Hashmaps for storing jobs and jobSeekers
    private static HashMap<Integer, JobVacency> jobs;
    private static HashMap<Integer, JobSeeker> jobsSeekers;

    public static HashMap<Integer, JobVacency> getJobs() {
        return jobs;
    }

    public static void setJobs(HashMap<Integer, JobVacency> jobs) {
        App.jobs = jobs;
    }

    public static HashMap<Integer, JobSeeker> getJobsSeekers() {
        return jobsSeekers;
    }

    public static void setJobsSeekers(HashMap<Integer, JobSeeker> jobsSeekers) {
        App.jobsSeekers = jobsSeekers;
    }

    public static BufferedReader getJobsreader() {
        return Jobsreader;
    }

    public static void setJobsreader(BufferedReader jobsreader) {
        Jobsreader = jobsreader;
    }

    public static BufferedWriter getJobswriter() {
        return Jobswriter;
    }

    public static void setJobswriter(BufferedWriter jobswriter) {
        Jobswriter = jobswriter;
    }

    public static BufferedReader getJobSeekersreader() {
        return JobSeekersreader;
    }

    public static void setJobSeekersreader(BufferedReader jobSeekersreader) {
        JobSeekersreader = jobSeekersreader;
    }

    public static BufferedWriter getJobSeekerswriter() {
        return JobSeekerswriter;
    }

    public static void setJobSeekerswriter(BufferedWriter jobSeekerswriter) {
        JobSeekerswriter = jobSeekerswriter;
    }


    //This bufferedReader is for taking input from console
    private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

    //These Readers and Writers Read and Write Saved Job Vacencies and Job Seekers
    private static BufferedReader Jobsreader;
    private static BufferedWriter Jobswriter;
    private static BufferedReader JobSeekersreader;
    private static BufferedWriter JobSeekerswriter;
    private static GridPane root;
    private static TextArea area;
    public static void main(String[] args) throws Exception {
        jobsSeekers = new HashMap<>();
        jobs = new HashMap<>();
        try {
            Jobsreader = new BufferedReader(new FileReader("./Text Files/Jobs.txt"));
            Jobswriter = new BufferedWriter(new FileWriter("./Text Files/Jobs.txt", true));
            JobSeekersreader = new BufferedReader(new FileReader("./Text Files/JobSeekers.txt"));
            JobSeekerswriter = new BufferedWriter(new FileWriter("./Text Files/JobSeekers.txt", true));
        } catch (Exception e) {
            e.printStackTrace();
            area.setText("There must have been changes to text files");
        }
        getSavedJobs();     //-> This method gets jobs from Jobs.txt and saves them to the hashmap.
        getSavedJobSeekers();   //->This method get saved job Seekers from text file and saves them to the hashmap
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new GridPane();
        primaryStage.setTitle("Jobs and Job Seekers Agency");
        Button button = new Button("Add or Remove Job");
        Button button2 = new Button("Add or Remove Job Seeker");
        Button showAllJobs = new Button("Show All Jobs");
        Button showAllJobSeekers = new Button("Show All Job Seekers");
        Button createReport = new Button("Create Report File");

        area = new TextArea();

        root.setAlignment(Pos.CENTER);
        root.setVgap(30);
        root.setPadding(new Insets(30,30, 30, 30));

        root.add(showAllJobs, 0, 0);
        root.add(button,0,1);
        root.add(showAllJobSeekers,0,2);
        root.add(button2,0,3);
        root.add(createReport,0,4);
        root.add(area,0,5);

        area.setEditable(false);

        button.setOnAction(e->AddandRemoveJob.display());
        button2.setOnAction(e->AddandRemoveJobSeeker.display());
        showAllJobs.setOnAction(e->showAllJobs());
        showAllJobSeekers.setOnAction(e->showAllJobSeekers());
        createReport.setOnAction(e->generateReport());


        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }




    public static void showAllJobs() {
        Set<Integer> ids = new HashSet<Integer>();
        ids = jobs.keySet();
        if (ids.size() == 0)
            area.setText("No jobs found");
        else {
            Iterator<Integer> itr = ids.iterator();
            area.setText("");

            while (itr.hasNext()) {
                area.appendText(jobs.get(Integer.parseInt("" + itr.next())).printJobDetails());
            }
        }
    }


    public static void showAllJobSeekers() {
        Set<Integer> ids = new HashSet<Integer>();
        ids = jobsSeekers.keySet();
        if (ids.size() == 0)
            area.setText("No jobSeekers found");
        else {
            Iterator<Integer> itr = ids.iterator();
            area.setText("");
            while (itr.hasNext()) {
                area.appendText(jobsSeekers.get(Integer.parseInt("" + itr.next())).printDetails());
            }
        }
    }


    private static void getSavedJobSeekers() {
        try {
            while (JobSeekersreader.ready()) {
                String jobDescription = JobSeekersreader.readLine();
                String[] array = jobDescription.split(", ");
                int Jid = Integer.parseInt(array[0]);
                String name = array[1];
                String location = array[2];
                int i = Integer.parseInt(array[3]);
                ArrayList<String> list = new ArrayList<String>();
                for (; i > 0; i--) {
                    list.add(array[i + 3]);
                }
                JobSeeker jbs = new JobSeeker(Jid, name, list, location);
                jobsSeekers.put(jbs.getJSid(), jbs);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private static void getSavedJobs() {
        try {
            while (Jobsreader.ready()) {
                String jobDescription = Jobsreader.readLine();
                String[] array = jobDescription.split(", ");
                int jId = Integer.parseInt(array[0]);
                String title = array[1];
                String location = array[2];
                int i = Integer.parseInt(array[3]);
                int j = i;
                ArrayList<String> needed = new ArrayList<String>();
                for (; i > 0; i--) {
                    needed.add(array[i + 3]);
                }
                String company = array[4 + j];
                JobVacency jbv = new JobVacency(jId, title, needed, location, company);
                jobs.put(jbv.getJobId(), jbv);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void generateReport() {
        File report = new File("Text Files/Report.txt");
        BufferedReader reader=null, reader2=null;
        BufferedWriter writer=null;
        try {
            reader = new BufferedReader(new FileReader("Text Files/Jobs.txt"));
            reader2 = new BufferedReader(new FileReader("Text Files/JobSeekers.txt"));
            writer = new BufferedWriter(new FileWriter(report));

            while (reader.ready()) {
                String x = reader.readLine();
                String[] xs = x.split(", ");
                writer.append("Eligible Seekers for job id- " + xs[0] + " and title- " + xs[1] + ": \n");
                int i = Integer.parseInt(xs[3]);
                for (; i > 0; i--) {
                    reader2 = new BufferedReader(new FileReader("Text Files/JobSeekers.txt"));
                    while (reader2.ready()) {
                        String seeker = reader2.readLine();
                        String[] seekerData = seeker.split(", ");
                        for (int j = Integer.parseInt(seekerData[3]); j > 0; j--) {
                            if (seekerData[j + 3].equals(xs[i + 3])) {
                                writer.append("Seeker Id: " + seekerData[0] + " Seeker Name: " + seekerData[1] +" Skill: "+seekerData[j+3]+"\n");
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                reader2.close();
                writer.close();
                area.setText("Report Generated Successfully. Check Text Files/Reports file");
            } catch (IOException e) {
                e.printStackTrace();
                area.setText("Couldn't Generate Report. Something Went Wrong");
            }
        }
    }

}
