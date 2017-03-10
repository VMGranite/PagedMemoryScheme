/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagedmemory;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author veronicagranite
 */
public class JobTable {
    //declare access modifier when you don't want them public
    private static JobTable instance;
    
    private ArrayList<Job> currentStateOfJobTable;
    private Hashtable<Integer, Boolean> MMT;
    
    private JobTable(){
        currentStateOfJobTable = new ArrayList();
        MMT = new Hashtable<>();
    }
    
    public static JobTable getInstance(){
        if(instance == null){
            instance = new JobTable();
        }
        
        return instance;
    }
    
    public ArrayList<Job> getCurrentStateOfJobTable(){
        return currentStateOfJobTable;
    }
    
    public Hashtable<Integer, Boolean> getMMT(){
        return MMT;
    }
    
    
}
