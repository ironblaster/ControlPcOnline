package net.ironblaster.ControlPcOnline.CustomComponentVaadin;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import net.ironblaster.ControlPcOnline.persistence.Persistence;
import net.ironblaster.ControlPcOnline.persistence.Persistence.EMAILSETTING;

public class WindowsetMailSetting extends Window {
	
	public WindowsetMailSetting() {
		this.center();
		//this.setResizable(false);
	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void attach() {
		super.attach();
		
		
		VerticalLayout layout = new VerticalLayout();
		
		
		TextField email = new TextField("email sender");
		TextField password = new TextField("Password");
		TextField serversmtp = new TextField("SMTP server");
		TextField port = new TextField("Port");
		CheckBox auth = new CheckBox("Auth");
		CheckBox ssl = new CheckBox("Require ssl");
		TextField mailReciver = new TextField("email Reciver");
		
		
		
		try {
			email.setValue(Persistence.getEmailSetting().get(EMAILSETTING.EMAIL.getValue()));
			password.setValue(Persistence.getEmailSetting().get(EMAILSETTING.PASSWORD.getValue()));
			serversmtp.setValue(Persistence.getEmailSetting().get(EMAILSETTING.SERVERSMTP.getValue()));
			port.setValue(Persistence.getEmailSetting().get(EMAILSETTING.PORT.getValue()));
			auth.setValue(Boolean.parseBoolean(Persistence.getEmailSetting().get(EMAILSETTING.AUTH.getValue())));
			ssl.setValue(Boolean.parseBoolean(Persistence.getEmailSetting().get(EMAILSETTING.SSLENABLE.getValue())));
			mailReciver.setValue(Persistence.getEmailSetting().get(EMAILSETTING.RECIVEREMAIL.getValue()));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		Button save = new Button("Save");
		save.addClickListener(e->{
			Persistence.saveEmailSetting(email.getValue().trim(),
					password.getValue().trim(),
					serversmtp.getValue().trim(),
					port.getValue().trim(),
					auth.getValue(),
					ssl.getValue(),
					mailReciver.getValue().trim());
		});
		

		
		FormLayout form = new FormLayout();
		
		form.addComponents(email,
							password,
							serversmtp,
							port,
							auth,
							ssl,
							mailReciver,save);
		
		layout.addComponent(form);
		layout.setComponentAlignment(form, Alignment.MIDDLE_CENTER);
		layout.setSizeFull();
		
		this.setContent(form);
	}

}
