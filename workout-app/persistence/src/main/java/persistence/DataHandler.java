package persistence;

public class DataHandler {
    
    private String mvnDir = System.getProperty("user.dir");
    private String filepath = mvnDir + "/../persistence/src/main/resources/persistence/datastorage/";

    // public List<Workout> loadData(String filename){
    //     Scanner scanner;
    //     try {
    //         scanner = new Scanner(new File(filepath + filename));
    //     } catch (FileNotFoundException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //         throw new IllegalArgumentException("");
    //     }

        //Collecting lines from file and adding to array workouts
    //     String title = scanner.nextLine();
    //     List<Workout> workouts = new ArrayList<>();

    //     while (scanner.hasNext()) {
    //         Workout w = new Workout(scanner.nextLine());
    //         workouts.add(w);
    //     }

    //     scanner.close();
    //     return workouts;
    // }

    // public void saveData(String filename, List<Workout> workouts){

    //     PrintWriter writer;
    //     try{
    //         writer = new PrintWriter(filepath + filename);
    //     }catch (FileNotFoundException e){
    //         try {
    //             //trying to create file if the file dont exist
    //             writer = new PrintWriter(new File(filepath + filename));
    //         } catch (FileNotFoundException e1) {
    //             throw new IllegalArgumentException("File not found and file could not be created"); 
    //         }
    //     }

    //     //Writing the data
    //     writer.println("Data");
    //     for (Workout w: workouts){
    //         writer.println(w.getWorkoutInput());
    //     }

    //     writer.flush();
    //     writer.close();
    // }
}
