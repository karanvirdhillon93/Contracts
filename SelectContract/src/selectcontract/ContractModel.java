/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectcontract;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author C0373297
 */ 
class ContractModel {
    private ArrayList<Contract> theContracts;
    private ArrayList<Contract> theContractsAll; 
    private int contractCounter;
    private SortedSet<String> originCityList;
    
    public static final int NUMBER_OF_CONTRACT_ATTRIBUTES = 4;
    public static final int INDEX_OF_CONTRACT_ID = 0;
    public static final int INDEX_OF_ORIGIN_CITY = 1;
    public static final int INDEX_OF_DEST_CITY = 2;
    public static final int INDEX_OF_ORDER_ITEM = 3;
    
    public ContractModel() {
        contractCounter = 0;
        theContracts = new ArrayList<>();
        originCityList = new TreeSet<>();
        String fileName = "contracts.txt";
        
        try { // Always wrap fileReader is bufferedReader
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            
            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                String[] tokens = line.split(",", NUMBER_OF_CONTRACT_ATTRIBUTES);
                
                System.out.println("length of tokens: " + tokens.length);
                
                String contractID = tokens[INDEX_OF_CONTRACT_ID];
                String originCity = tokens[INDEX_OF_ORIGIN_CITY];
                String destCity = tokens[INDEX_OF_DEST_CITY];
                String orderItem = tokens[INDEX_OF_ORDER_ITEM];
                
                Contract dataContract = new Contract(contractID, originCity, destCity, orderItem);
                theContracts.add(dataContract);
                theContractsAll = new ArrayList<>(theContracts);
                originCityList.add(originCity); 
                
            }
            originCityList.add("All");
            fileReader.close();
            
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
            
        }
        
    }
    
    boolean foundContracts() {
        if(theContracts.size() > 0)
            return true;
        else
            return false;
    }
    
    public Contract getTheContract() {
        return theContracts.get(contractCounter);
    }
    
    public int getContractCount() {
        int count = 0;
        for(int i=0; i < theContracts.size();i++) {
           count++; 
        } return count;
    }
    
    public int getCurrentContractNum() {
        return contractCounter;
    }
    
    public void nextContract() {
        if(contractCounter < theContracts.size() -1)
            contractCounter++; 
    }
    
    public void prevContract() {
        if(contractCounter >= 0)
            contractCounter--;
    }
    
    public String[] getOriginCityList() {
        String[] a;
        a = originCityList.toArray(new String[originCityList.size()]);
        return a;
    }
    public void updateContractList(String city) {
        theContracts = new ArrayList<>(theContractsAll);
        if (city != "All") {
            theContracts.removeIf(s -> !s.contains(city));
        }
        contractCounter = 0;
    }
        
}

