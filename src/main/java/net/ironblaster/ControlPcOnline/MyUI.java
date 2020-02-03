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
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import net.ironblaster.ControlPcOnline.CustomComponentVaadin.WindowAddPc;
import net.ironblaster.ControlPcOnline.CustomComponentVaadin.WindowsetMailSetting;
import net.ironblaster.ControlPcOnline.ejb.Schedule;
import net.ironblaster.ControlPcOnline.networkCommand.Ping;
import net.ironblaster.ControlPcOnline.persistence.Persistence;
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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Grid<PcList> computer = new Grid<>();
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	
    	
    	
    	
    	
        VerticalLayout layout = new VerticalLayout();
        
        Button aggiungiPc = new Button("Aggiungi Nuovo PC");
        
        
        aggiungiPc.addClickListener(e->{
        	
        	addWindow(new WindowAddPc());
        	
        });
        
        
        
        Button eseguiPing = new Button("Ping");
        eseguiPing.addClickListener(e->{
        	
        	if(computer.getSelectedItems().isEmpty()) {
        		Notification.show("selezionare un Ip da pingare");
        		return;}

	          
	         if(Ping.isReachable(computer.getSelectedItems().iterator().next().getIp())) {
	        	 Notification.show("ip raggiungibile",Type.HUMANIZED_MESSAGE).setDelayMsec(4000);
	         }else {
	        	 Notification.show("ip NON raggiungibile",Type.ERROR_MESSAGE);
	         }


        	
        	
        	
        });
        
        
        
        Button rimuovi = new Button("rimuovi");
        rimuovi.addClickListener(e->{
        	if(computer.getSelectedItems().isEmpty()) {
        		Notification.show("selezionare un Ip da rimuovere");
        		return;}
        	
        	
        	Persistence.removeToListIpbyIp(computer.getSelectedItems().iterator().next().getIp());
        	computer.setItems(Persistence.getListPc());
        	
        });
        
        
        Button carica =  new Button("Carica in schedule");
        
        carica.addClickListener(e->{
        	try {
        	Schedule.reloadSchedule();
        	Notification.show("Complete Reload");
        	}catch (Exception ex) {
				Notification.show("error",ex.getMessage(),Type.ERROR_MESSAGE);
			}
        });
        
        
        
       Button NotifiSetting = new Button("Impostazioni email");
        NotifiSetting.addClickListener(e->{
        	addWindow(new WindowsetMailSetting());
        });
        
        
       
        
        
        HorizontalLayout bottoni = new HorizontalLayout();
        bottoni.addComponents(aggiungiPc,rimuovi,eseguiPing,carica,NotifiSetting);
        
        
        
        
        
        
        GridContextMenu<PcList> gridMenu = new GridContextMenu<>(computer);
        gridMenu.addGridBodyContextMenuListener(this::updateGridBodyMenu);
        computer.addColumn(PcList::getIp).setCaption("Ip");
        computer.addColumn(PcList::getName).setCaption("Nome");
        computer.setSizeFull();
        computer.setSelectionMode(SelectionMode.SINGLE);
       
        
        
        computer.setItems(Persistence.getListPc());
        
        
        
        
        this.addWindowOrderUpdateListener(e->{
        	computer.setItems(Persistence.getListPc());
        	
        });      
        
        
        
       
        
        

        
        
        
        
        Label lastexecution = new Label();
        lastexecution.setCaptionAsHtml(true);
        lastexecution.setCaption("<b>ultima esecuzione:</b> "+Util.longToFormattedDate(Persistence.getLastScheduleExecution()));
        
        
        Label nextExe = new Label();
        nextExe.setCaptionAsHtml(true);
        nextExe.setCaption("<b>prossima esecuzione: </b>"+Util.longToFormattedDate(Persistence.getTask().getOra().getTimeInMillis()));
        
        
        
        
        HorizontalLayout schedule = new HorizontalLayout();
        schedule.addComponents(lastexecution,nextExe);
        
        layout.addComponents(bottoni,schedule,computer);
        layout.setComponentAlignment(computer, Alignment.MIDDLE_CENTER);
        
        setContent(layout);
        
        
        
        
    }
    
    
    
    private void updateGridBodyMenu(GridContextMenuOpenEvent<PcList> event) {
        //event.getContextMenu().removeItems();
        if (event.getItem() != null) {
            event.getContextMenu().addItem("Rimuovi", VaadinIcons.CLOSE, selectedItem -> {
            	//TODO AGGIUNGERE PULSANTI SIA DI TEST PING CHE DI RIMOZIONE DA PERSISTENZA
            	Persistence.removeToListIpbyIp(event.getItem().getIp());
            	computer.setItems(Persistence.getListPc());
              /*  userData.remove(event.getItem());
                grid.setItems(userData);*/
            });
        } else {
        	
        	 event.getContextMenu().addItem("Rimuovi2", VaadinIcons.CLOSE, selectedItem -> {
             	//TODO AGGIUNGERE PULSANTI SIA DI TEST PING CHE DI RIMOZIONE DA PERSISTENZA
             	Persistence.removeToListIpbyIp(event.getItem().getIp());
             	computer.setItems(Persistence.getListPc());
               /*  userData.remove(event.getItem());
                 grid.setItems(userData);*/
             });
          /*  event.getContextMenu().addItem("Add row", VaadinIcons.PLUS, selectedItem -> {
               userData.add(new UserData("John", "Doe", "john.doe[at]lost.com"));
                grid.setItems(userData);
            });*/
        }
    }
    
    


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
    }
}
