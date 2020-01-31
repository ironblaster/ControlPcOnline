package net.ironblaster.ControlPcOnline.CustomComponentVaadin;


import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

import net.ironblaster.ControlPcOnline.persistence.pojo.Config;

import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class WindowAddPc extends Window{
	
	public WindowAddPc() {
		
		this.center();
		this.setCaption("Aggiungi Computer");
		
		
		
	}
	
	
	
	
	@Override
	public void attach() {
		super.attach();
		
		
		TextField ip = new TextField("IP macchina");
		TextField nome = new TextField("Nome macchina");
		
		
		
		VerticalLayout layout = new VerticalLayout();
		
		
		HorizontalLayout textlay = new HorizontalLayout();
		textlay.addComponents(ip,nome);
		textlay.setComponentAlignment(ip,Alignment.MIDDLE_CENTER);
		textlay.setComponentAlignment(nome,Alignment.MIDDLE_CENTER);
		
		
		
		Button salva = new Button("Salva");
		salva.setClickShortcut(KeyCode.ENTER);
		
		
		salva.addClickListener(e->{
			
			try {
				
				Config.addInListIP(ip.getValue().trim(), nome.getValue().trim());
			//TODO AGGIUNGERE SALVATAGGIO DEL NUOVO PC
			
			
			this.close();
			}
			catch (Exception ex) {
				Notification.show("errore salvataggio",ex.getMessage(),Type.ERROR_MESSAGE);
				}
		});
		
		
		
		
		layout.addComponents(textlay,salva);
		layout.setComponentAlignment(salva, Alignment.MIDDLE_RIGHT);
		layout.setComponentAlignment(textlay, Alignment.MIDDLE_CENTER);
		this.setContent(layout);
	}

}
