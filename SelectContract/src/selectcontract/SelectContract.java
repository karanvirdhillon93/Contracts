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
public class SelectContract {
      public static void main(String[] args) {
        // TODO code application logic here
        ContractView theView = new ContractView();
        ContractModel theModel = new ContractModel();
        ContractController theController;
        theController = new ContractController(theView, theModel);
        theView.setVisible(true);
    }
}
