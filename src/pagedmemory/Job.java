/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagedmemory;

import java.util.ArrayList;

/**
 *
 * @author Veronica Granite
 */
public class Job {
    int jobSize;
    int ID;
    ArrayList<PMTInfo> PMT = new ArrayList<>();
    int executionTime;
    
    Job(int mL,int jS, int eT) {
        this.ID = mL;
        this.jobSize = jS; 
        this.executionTime = eT;
    }
    
    public String toString(){
        return "ID: " + ID + ", Job Size: " + jobSize + ", Execution Time: " + executionTime;
    }
    
    public int getID(){
        return ID;
    }
    
    public int getSize(){
        return jobSize;
    }

    public int getExecutionTime(){
        return executionTime;
    }
    
    public void setPMT(ArrayList<PMTInfo> incomingPMT){
        PMT = incomingPMT;
    }
    
    public String displayPMT(){
        String PMTList = "";
        for(int i = 0; i < PMT.size(); i++){
            PMTList += PMT.get(i) + "\n";
        }
        
        return PMTList;
    }
    
    public ArrayList<PMTInfo> getPMT(){
        return PMT;
    }
    
   
    

}
