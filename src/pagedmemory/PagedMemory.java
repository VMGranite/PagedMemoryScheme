/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagedmemory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Veronica Granite
 */
public class PagedMemory {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
  
        Hashtable<Integer, Boolean> MMT = new Hashtable<>();
        MMT.put(0, true);
        MMT.put(1, true);
        MMT.put(2, true);
        MMT.put(3, true);
        MMT.put(4, true);
        MMT.put(5, true);
        MMT.put(6, true);
        MMT.put(7, true);
        MMT.put(8, true);
        MMT.put(9, true);
        
        System.out.println("Current Main Memory: " + MMT.toString());
        System.out.println("------------");

        /*
        Scanner input = new Scanner(System.in);
        System.out.println("Input 'Job File' Path: ");
        System.out.println("EXAMPLE: D:/Users/Veronica Granite/Documents/NetBeansProjects/PagedMemory/test/Jobs.txt");
        String path = input.nextLine();
         */
        
        ArrayList<Integer> numbersList = new ArrayList<>();
        try {
            for (String line : Files.readAllLines(Paths.get("/Users/veronicagranite/Documents/workspace/PagedMemory/test/Jobs.txt"))) {
                for (String part : line.split("\\s")) {
                    if (!part.equals("[^0-9]")) {
                        Integer i = Integer.valueOf(part);
                        numbersList.add(i);
                    } else {
                        System.out.println("File contains non-numerical characters.");
                        break;
                    }

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(PagedMemory.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("'Job File' is empty, or path location is incorrect.");
        }

        ArrayList<Job> jobTable = new ArrayList<>();
        for (int i = 0; i < numbersList.size(); i++) {
            jobTable.add(new Job(numbersList.get(i), numbersList.get(i + 1), numbersList.get(i + 2)));
            i = i + 2;
        }
        System.out.println("Your Current Job List:" + jobTable);
        
        //Remove jobs that are too large
        for(int i = 0; i < jobTable.size(); i++){
            if(jobTable.get(i).getSize() > 1000){
                    System.out.println("Job of size " + jobTable.get(i).getSize() + " was too large. It has been removed from the Job Table.");
                    jobTable.remove(i);
                }
        }
        
        System.out.println("------------");
        System.out.println("Your Finalized Job List:" + jobTable);
        System.out.println("------------");
        
        //Blank Arraylist to fill each job's PMT
        ArrayList<PMTInfo> PMT = new ArrayList<>();
                
        //Process Jobs until Job Table is empty
        while (jobTable.size() > 0) {
            
            //Checks if jobs are done
            for(int i = 0; i < jobTable.size(); i++){
                /*
                System.out.println(System.currentTimeMillis());
                System.out.println(jobTable.get(i).getStartTime());
                System.out.println(jobTable.get(i).getExecutionTime());
                */
                
                if((jobTable.get(i).getStartTime() + jobTable.get(i).getExecutionTime()) <= System.currentTimeMillis()){
                    resetPMT(jobTable.get(i), MMT);
                    System.out.println("========> Job ID " + jobTable.get(i).getID() + " completed. <========");
                    jobTable.remove(i);
                }
            }
            
            
            for(int i = 0; i < jobTable.size(); i++){
                PMT.clear();
                int freeMemory = checkMemoryAvailable(MMT);
                int currentJobSize = jobTable.get(i).getSize();
                int numberOfPages = 0;
                if(currentJobSize <= freeMemory){
                   
                   
                   if(currentJobSize % 100 != 0){
                       numberOfPages = (currentJobSize / 100) + 1; 
                   }else{
                       numberOfPages = currentJobSize / 100; 
                   }
                   
                   for(int b = 0; b < numberOfPages; b++){
                       int pageFrame = findFreePageFrame(MMT);
                       if(pageFrame != -1){
                           PMT.add(new PMTInfo(b,pageFrame));
                       }else{
                           System.out.println("ERROR: There are no free page frames.");
                       }
                       
                   }
                   
                   jobTable.get(i).setPMT(PMT);
                   System.out.println("- JOB " + " " + i + " - \n" + jobTable.get(i));
                   System.out.println("- PMT - \n" + jobTable.get(i).displayPMT());
                   jobTable.get(i).setStartTime(System.currentTimeMillis());
                   Thread.sleep(1000);
                }else{
                    System.out.println(" - There is not enough free memory at the moment for - JOB " + jobTable.get(i) + " -. Continuing to next Job.\n");
                }
            }
        }
        
        System.out.println("Processing COMPLETE. Program Ended");

    }
    
    public static int checkMemoryAvailable(Hashtable<Integer, Boolean> MMT){
        int freeMemory = 0;
        for(int i = 0; i < MMT.size(); i++){
            if(MMT.get(i) == true){
                freeMemory += 100;
            }
        }
        
        return freeMemory;
    }
    
    public static int findFreePageFrame(Hashtable<Integer, Boolean> MMT){
        int toReturn =-1;
        for(int i = 0; i < MMT.size(); i++){
            if(MMT.get(i)){
                MMT.replace(i, false);
                toReturn = i;
                break;
            }
        }
        
        return toReturn;
    }

   /*
    public static void startTimer(Job job, Hashtable<Integer, Boolean> MMT, int jobIndex, ArrayList<Job> jobTable){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
               resetPMT(job, MMT);
               System.out.println("========> Job " + job.getID() + " completed. <========");
               jobTable.remove(jobIndex);
               //System.out.println(isJob.toString());
               
               
            }
          }, (job.getExecutionTime()));
    } 
    */
   
     public static void resetPMT(Job job, Hashtable<Integer, Boolean> MMT){
        for(int i = 0; i < job.getPMT().size(); i++){
            int index = job.getPMT().get(i).getPageFrameNumber();
            MMT.put(index, true);
        }
    }
    
     
}
