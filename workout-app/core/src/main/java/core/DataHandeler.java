package core;

import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class DataHandeler {
    
    public List<Workout> loadData(String filename){
        Scanner scanner;
        try {
            scanner = new Scanner(new File("workout-app/core/src/main/java/core/datastorage/" + filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("File not found.");   
        }

        //Collecting lines from file and adding to array workouts
        String title = scanner.nextLine();
        List<Workout> workouts = new ArrayList<>();

        while (scanner.hasNext()) {
            Workout w = new Workout(scanner.nextLine());
            workouts.add(w);
        }

        scanner.close();
        return workouts;
    }

    public void saveData(String filename, List<Workout> workouts){

        PrintWriter writer;
        try{
            writer = new PrintWriter(filename);
        }catch (FileNotFoundException e){
            try {
                //trying to create file if the file dont exist
                writer = new PrintWriter(new File("workout-app/core/src/main/java/core/datastorage/" + filename));
            } catch (FileNotFoundException e1) {
                throw new IllegalArgumentException("File not found and file could not be created"); 
            }
        }

        //Writing the data
        writer.println("Data");
        for (Workout w: workouts){
            writer.println(w.getDescription());
        }

        writer.flush();
        writer.close();
    }
}
