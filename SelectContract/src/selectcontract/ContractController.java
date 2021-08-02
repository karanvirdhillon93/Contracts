/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectcontract;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
/**
 *
 * @author C0373297
 */
class ContractController {
    private ContractView theView;
    private ContractModel theModel;
    
    public ContractController(ContractView view, ContractModel model) {
        theView = view;
        theModel = model;
        
        this.theView.addPrevListener(new PrevButtonListener());
        this.theView.addBidListener(new BidButtonListener());
        this.theView.addNextListener(new NextButtonListener());
        this.theView.addComboBoxListener(new ComboListener());
        this.theView.setOriginCityList(theModel.getOriginCityList());
        setUpDisplay();
    }
    
    private void setUpDisplay() {
        try {
            if(theModel.foundContracts()) {
                Contract c = theModel.getTheContract();
                theView.setContractID(c.getContractID());
                theView.setDestCity(c.getDestCity());
                theView.setOriginCity(c.getOriginCity());
                theView.setOrderItem(c.getOrderItem());
                
            } else {
                theView.setContractID("???");
                theView.setDestCity("???");
                theView.setOriginCity("???");
                theView.setOrderItem("???");
            }
        } catch (Error ex) {
            //provide an error message to the console output.
            System.out.println(ex);
            theView.displayErrorMessage("Error: there was a problem setting the contract.\n"
                                           +" Contracts number: " + theModel.getCurrentContractNum());
            
        }
        
        theView.updateContractViewPanel(theModel.getCurrentContractNum(), theModel.getContractCount());
    }
    
    class PrevButtonListener implements ActionListener {
        @Override
        
        public void actionPerformed(ActionEvent e) {
            /*if the currently displayed contract is the first in the list of 
            contracts,then you cannot view a non-existent contract behind it. */
            if(theModel.getCurrentContractNum() == 0) {
                return;
            }
            try {
                //retrieve the contract behind the  currently displayed contract.
                theModel.prevContract();
            } catch (Exception ex) {
                System.out.println(ex);
                theView.displayErrorMessage("Error: there was a problem setting a previous contract.\n");
            }
            setUpDisplay();
            
        }
    }
    
    class NextButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            /*if the currently displayed contract is the first in the list of 
            contracts,then you cannot view a non-existent contract behind it. */
            if(theModel.getCurrentContractNum() == 2) {
                return;
            }
            try {
                //retrieve the contract behind the  currently displayed contract.
                theModel.nextContract();
            } catch (Exception ex) {
                System.out.println(ex);
                theView.displayErrorMessage("Error: there was a problem setting next contract.\n");
            }
            setUpDisplay();
            
        }
    }
    
    class BidButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ConfirmBid cb;
                cb = new ConfirmBid(theView, true, theModel.getTheContract());
                cb.setLocationRelativeTo(null);
                cb.setVisible(true);
            } catch (Exception ex) {
                System.out.println(ex);
                theView.displayErrorMessage("Error: the number entered must be integers. \n");
            }
        }
    }
    
    class ComboListener implements ItemListener {
        
        @Override
        public void itemStateChanged(ItemEvent e) {
            System.out.println(e.getItem().toString());
            if(e.getStateChange() == ItemEvent.SELECTED) {
                String selectedCity = e.getItem().toString();
                System.out.println(selectedCity);
                theModel.updateContractList(selectedCity);
                setUpDisplay();
            }
        }
    }
}
