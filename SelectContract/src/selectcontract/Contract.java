/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectcontract;

/**
 *
 * @author C0373297
 */
public class Contract {
    String contractID;
    String originCity;
    String destCity;
    String orderItem;
    
    
    public Contract(String id, String origin, String dest, String item) {
        contractID = id;
        originCity = origin;
        destCity = dest;
        orderItem = item;
    }
    
    public String getContractID(){
        return contractID;
    }
    public String getOriginCity() {
        return originCity;
    }
    public String getDestCity() {
        return destCity;
    }
    public String getOrderItem() {
        return orderItem;
    }

    boolean contains(String city) {
        if (originCity == city)
            return true;
        else return false;
    }
}
