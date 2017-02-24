/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagedmemory;

/**
 *
 * @author Veronica Granite
 */
public class PMTInfo {
    int pageNumber;
    int pageFrameNumber;
    
    PMTInfo(int pN, int pFN){
        this.pageNumber = pN;
        this.pageFrameNumber = pFN;
    }
    
    public void setPageNumber(int pN){
        pageNumber = pN;
    }
    
    public int getPageFrameNumber(){
        return pageFrameNumber;
    }
    
    public String toString(){
        return "Page Number: " + pageNumber + " Page Frame Number: " + pageFrameNumber;
    }
}
