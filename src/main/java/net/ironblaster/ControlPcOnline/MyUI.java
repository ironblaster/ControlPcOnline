package net.ironblaster.ControlPcOnline;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.contextmenu.GridContextMenu;
import com.vaadin.contextmenu.GridContextMenu.GridContextMenuOpenListener.GridContextMenuOpenEvent;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;

import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import net.ironblaster.ControlPcOnline.CustomComponentVaadin.WindowAddPc;
import net.ironblaster.ControlPcOnline.persistence.pojo.Config;
import net.ironblaster.ControlPcOnline.sessionPojo.PcList;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
	Grid<PcList> computer = new Grid<>();
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	
    	
    	
    	
    	
        VerticalLayout layout = new VerticalLayout();
        
        Button aggiungiPc = new Button("Aggiungi Nuovo PC");
        
        
        aggiungiPc.addClickListener(e->{
        	
        	addWindow(new WindowAddPc());
        	
        });
        
        GridContextMenu<PcList> gridMenu = new GridContextMenu<>(computer);
        gridMenu.addGridBodyContextMenuListener(this::updateGridBodyMenu);
        computer.addColumn(PcList::getIp).setCaption("Ip");
        computer.addColumn(PcList::getName).setCaption("Nome");
        computer.setSizeFull();
        computer.setSelectionMode(SelectionMode.SINGLE);
       
        
        
        computer.setItems(Config.getListPc());
        
        
        
        
        this.addWindowOrderUpdateListener(e->{
        	computer.setItems(Config.getListPc());
        	
        });      
        
        
        
        
        
        
        
        layout.addComponents(aggiungiPc,computer);
        layout.setComponentAlignment(computer, Alignment.MIDDLE_CENTER);
        
        setContent(layout);
        
        
        
        
    }
    
    
    
    private void updateGridBodyMenu(GridContextMenuOpenEvent<PcList> event) {
        event.getContextMenu().removeItems();
        if (event.getItem() != null) {
            event.getContextMenu().addItem("Remove row", VaadinIcons.CLOSE, selectedItem -> {
            	//TODO AGGIUNGERE PULSANTI SIA DI TEST PING CHE DI RIMOZIONE DA PERSISTENZA
            	
              /*  userData.remove(event.getItem());
                grid.setItems(userData);*/
            });
        } else {
            event.getContextMenu().addItem("Add row", VaadinIcons.PLUS, selectedItem -> {
             /*   userData.add(new UserData("John", "Doe", "john.doe[at]lost.com"));
                grid.setItems(userData);*/
            });
        }
    }
    
    


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
