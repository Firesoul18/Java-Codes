import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Main {
    //Hashmaps for storing jobs and jobSeekers
    private static HashMap<Integer, JobVacency> jobs;
    private static HashMap<Integer, JobSeeker> jobsSeekers;

    //This bufferedReader is for taking input from console
    private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

    //These Readers and Writers Read and Write Saved Job Vacencies and Job Seekers
    private static BufferedReader Jobsreader;
    private static BufferedWriter Jobswriter;
    private static BufferedReader JobSeekersreader;
    private static BufferedWriter JobSeekerswriter;

    //this function adds a JobVacency
    public static void addJob() throws IOException {
        String title;
        String location;
        String company;
        ArrayList<String> requiredSkills = new ArrayList<String>();

        System.out.print("Enter Job Title: ");
        title = sc.readLine();
        System.out.print("Enter Location: ");
        location = sc.readLine();
        System.out.print("Enter Company Name: ");
        company = sc.readLine();

        System.out.print("Enter Number of required skills: ");
        Integer num = null;
        while (num == null) {
            try {
                num = Integer.parseInt(sc.readLine());
            } catch (Exception e) {
                System.out.print("Enter Correct Number: ");
            }
        }
        for (int i = 0; i < num; i++) {
            System.out.print("Enter Skill Number" + (i + 1) + " : ");
            String skill = sc.readLine();
            requiredSkills.add(skill);
        }

        JobVacency jbv = new JobVacency(title, requiredSkills, location, company);
        Jobswriter.append(jbv.getJobId() + ", " + title + ", " + location + ", " + requiredSkills.size() + ", ");
        for (int i = 0; i < requiredSkills.size(); i++) {
            Jobswriter.append(requiredSkills.get(i) + ", ");
        }
        Jobswriter.append(company + "\n");
        jobs.put(jbv.getJobId(), jbv);
        System.out.println("Job Added Successfully");

    }

    public static void removeJob() throws IOException {
        System.out.println("Enter Job id: ");
        int id = Integer.parseInt(sc.readLine());
        Object o = jobs.remove(id);
        if (o == null)
            System.out.println("No Job With This Id found.");
        else {
            System.out.println("Deleted job: " + ((JobVacency) o).getTitle());
            File toChange = new File("Text Files/Jobs.txt");
            List<String> list = Files.lines(toChange.toPath()).filter(line -> !line.startsWith(id + ""))
                    .collect(Collectors.toList());
            Files.write(toChange.toPath(), list, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }

    public static void showAllJobs() {
        Set<Integer> ids = new HashSet<Integer>();
        ids = jobs.keySet();
        if (ids.size() == 0)
            System.out.println("No jobs found");
        else {
            Iterator<Integer> itr = ids.iterator();

            while (itr.hasNext()) {
                System.out.println(jobs.get(Integer.parseInt("" + itr.next())).printJobDetails());
            }
        }
    }

    public static void showAllJobSeekers() {
        Set<Integer> ids = new HashSet<Integer>();
        ids = jobsSeekers.keySet();
        if (ids.size() == 0)
            System.out.println("No jobSeekers found");
        else {
            Iterator<Integer> itr = ids.iterator();

            while (itr.hasNext()) {
                System.out.println(jobsSeekers.get(Integer.parseInt("" + itr.next())).printDetails());
            }
        }
    }

    public static void addJobSeeker() throws IOException {
        String name;
        String location;
        ArrayList<String> skills = new ArrayList<String>();

        System.out.print("Enter Name: ");
        name = sc.readLine();
        System.out.print("Enter Location: ");
        location = sc.readLine();

        System.out.print("Enter Number of skills: ");
        Integer num = null;
        while (num == null) {
            try {
                num = Integer.parseInt(sc.readLine());
            } catch (Exception e) {
                System.out.print("Enter Correct Number: ");
            }
        }
        for (int i = 0; i < num; i++) {
            System.out.print("Enter Skill Number " + (i + 1) + ": ");
            String skill = sc.readLine();
            skills.add(skill);
        }

        JobSeeker jbs = new JobSeeker(name, skills, location);

        JobSeekerswriter.append(jbs.getJSid() + ", " + name + ", " + location + ", " + skills.size() + ", ");
        for (int i = 0; i < skills.size(); i++) {
            if (i < skills.size() - 1)
                JobSeekerswriter.append(skills.get(i) + ", ");
            else
                JobSeekerswriter.append(skills.get(i) + "\n");
        }

        jobsSeekers.put(jbs.getJSid(), jbs);
        System.out.println("JobSeeker Added Successfully");
    }

    public static void removeJobSeeker() throws IOException {
        System.out.print("Enter Id of JobSeeker to remove: ");
        int id = Integer.parseInt(sc.readLine());
        JobSeeker js = jobsSeekers.remove(id);
        if (js == null) {
            System.out.println("No JobSeeker found for this id");
        } else {
            System.out.println("JobSeeker " + js.getName() + " Removed Successfully");
            File toChange = new File("Text Files/JobSeekers.txt");
            List<String> list = Files.lines(toChange.toPath()).filter(line -> !line.startsWith(id + ""))
                    .collect(Collectors.toList());
            Files.write(toChange.toPath(), list, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        }

    }

    public static void main(String[] args) throws IOException {
        jobsSeekers = new HashMap<>();
        jobs = new HashMap<>();
        try {
            Jobsreader = new BufferedReader(new FileReader("./Text Files/Jobs.txt"));
            Jobswriter = new BufferedWriter(new FileWriter("./Text Files/Jobs.txt", true));
            JobSeekersreader = new BufferedReader(new FileReader("./Text Files/JobSeekers.txt"));
            JobSeekerswriter = new BufferedWriter(new FileWriter("./Text Files/JobSeekers.txt", true));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There must have been changes to text files");
        }

        getSavedJobs();     //-> This method gets jobs from Jobs.txt and saves them to the hashmap.
        getSavedJobSeekers();   //->This method get saved job Seekers from text file and saves them to the hashmap
        printMenu();        //-> this method prints the menu to the screen and calls other functions according to the input
        Jobsreader.close();
        JobSeekersreader.close();
        Jobswriter.close();
        JobSeekerswriter.close();
        sc.close();
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

    public static void printMenu() throws IOException {
        int selectedValue = 99;
        loop: while (true) {
            System.out.println("*******************");
            System.out.println("Job Agency");
            System.out.println("*******************");
            System.out.println("\nPress 1 to See All Jobs");
            System.out.println("Press 2 to Add New Job");
            System.out.println("Press 3 to Delete a Job");
            System.out.println("Press 4 to see all Job Seekers");
            System.out.println("Press 5 to Add A New Job Seeker");
            System.out.println("press 6 to Delete a Job Seeker");
            System.out.println("Press 7 to Generate Reports");
            System.out.println("Press 0 to Exit");
            System.out.print("\nEnter A Button: ");

            try {
                selectedValue = Integer.parseInt(sc.readLine());
            } catch (Exception e) {
                System.out.println("\nEnter Correct number...");
            }

            switch (selectedValue) {
                case 0:
                    break loop;
                case 1:
                    showAllJobs();  // show all jobs
                    break;
                case 2:
                    addJob();   // add new Job to the file and hashmap
                    break;
                case 3:
                    removeJob();    //removes job from the file and hashmap
                    break;
                case 4:
                    showAllJobSeekers();    //shows all job seekers
                    break;
                case 5:
                    addJobSeeker();     //add job seeker to the file and hashmap
                    break;
                case 6:
                    removeJobSeeker();      //removes job seeker from the file and hashmap
                    break;
                case 7:
                    generateReports();      //generates reports and saves them to a text file (Report.txt)
                    break;
                default:
                    System.out.println("Enter Correct Number...");
                    break;
            }
        }
    }

    public static void generateReports() {
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}