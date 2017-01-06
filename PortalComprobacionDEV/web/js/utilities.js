function utilities() {
}

function getUnreadNotificationsNumber() {			
	Ext.Ajax.request({
		url: contextrootpath + '/provider/getUnreadNotificationsNumber.action',
		method: 'GET',
		params: {
			lifnr: lifnr
		},
		success: function(response,opts) {
			var element = Ext.get('notifications');
			var responseDecoded = Ext.decode(response.responseText);
			if(responseDecoded.data > 0) { 
				element.update('(' + responseDecoded.data + ')');
			} else {
				element.update('');
			}
		},
		failure: function(response,opts) {
			console.log(response);
		}
	});		
}

var relogin = function() {
	window.location.reload();
};


utilities.handleException = function(proxy, type, action, o, response, args){
	if (type == "remote") {
		Ext.MessageBox.alert("Error", response.errors.reason);
	} else {	
		switch(response.status) {
			case -1: 
				if(loadMask) 
					loadMask.hide();
				Ext.MessageBox.alert("Error", "No se pudo cargar informaciÃ³n del servidor.");break;
			case 0: Ext.MessageBox.alert("AtenciÃ³n", "OcurriÃ³ un problema de comunicaciÃ³n con el servidor favor de contactar a soporte tÃ©cnico");break;
			case 200:
				if(response.responseText.indexOf("login")>=0) {
					Ext.MessageBox.alert(bundle.getMsg('label.message'), bundle.getMsg('label.sessionalert'),relogin);														
				} else {
					msg = Ext.decode(response.responseText);
					Ext.MessageBox.alert(bundle.getMsg('label.message'), msg.msg ? msg.msg : bundle.getMsg('label.nodata'));
				}
				break;
		}			
	}
}

utilities.expiredSession = function(result,request) {
	if(response.responseText.indexOf("login")>=0) {
		Ext.MessageBox.alert("AtenciÃ³n", "Su sesiÃ³n expirÃ³ por seguridad.<br/> DeberÃ¡ iniciar una nueva sesiÃ³n.",relogin);					
	} else {
		Ext.MessageBox.alert("AtenciÃ³n", "No se pudo cargar informaciÃ³n");
	}
}

function windowPassword() {
	var frmChangePass = new Ext.form.FormPanel({
		id:'frmChangePass',
		layout: 'form',
		border: false,
		frame: true,
		bodyStyle: 'padding: 5px 5px 0',
		labelWidth: 200,
		defaults:{		
			width: 300,
			maxLength: 16
		},
		items: [{
			xtype: 'textfield',
			name: 'oldPass',
			fieldLabel: bundle.getMsg('label.oldPassword'),
			inputType: 'password',
			allowBlank: false
		},{
			xtype: 'textfield',
			name: 'email',
			fieldLabel: bundle.getMsg('label.email'),
			vtype: 'email',
			maxLength: 200,
			allowBlank: false
		},{
			xtype: 'textfield',
			name: 'newPass',
			fieldLabel: bundle.getMsg('label.newPassword'),
			inputType: 'password',
			allowBlank: false
		},{
			xtype: 'textfield',
			fieldLabel: bundle.getMsg('label.confirmPassword'),
			inputType: 'password',
			name: 'newPassReview',
			allowBlank: false
		}],
		buttons:[{
			text: bundle.getMsg('label.change'),
			handler: function(){
				var frm = frmChangePass.getForm();
				if(!frm.isValid()) {
					Ext.MessageBox.show({
	   					title: 'AtenciÃ³n',
	   					msg: "Algunos campos tiene valores invÃ¡lidos, favor de corregirlos",
	   					buttons: Ext.MessageBox.OK,
	   					icon: Ext.MessageBox.WARNING
					});
				} else {
					frm.submit({
						url: contextrootpath + '/provider/changePassword.action',
						waitMsg: 'Espere...',
						success: function(form,action){
							Ext.MessageBox.show({
								title: 'Ã‰xito',
								msg: action.result.msg,
								buttons: Ext.MessageBox.OK,
								icon: Ext.MessageBox.INFO
							});
							frm.reset();
							changePassWin.hide();
						},
						failure: function(form,action){
							switch (action.failureType) {
							  case Ext.form.Action.CLIENT_INVALID:
								 Ext.Msg.alert('Error', 'Valores invÃ¡lidos');
								 frm.reset();	
								 changePassWin.hide();
								 break;
							  case Ext.form.Action.CONNECT_FAILURE:
								 Ext.Msg.alert('Error', 'Falla de comunicaciÃ³n con el servidor');
								 frm.reset();			
								 changePassWin.hide();
								 break;
							  case Ext.form.Action.SERVER_INVALID:
								Ext.Msg.alert('Error', action.result.msg);
								frm.reset();			
								changePassWin.hide();
								break;
							  default:
								Ext.Msg.alert('Error',action.result.msg);
							  	frm.reset();		
							  	changePassWin.hide();
							  	break;
							}
						}
					});
						
				}
			}
		},{
			text: bundle.getMsg('label.cancel'),
			handler: function(){
				changePassWin.hide();
			}
		}]
	});

	var changePassWin = new Ext.Window({
		title: bundle.getMsg('label.changepass'),
		frame: true,
		modal: true,
		width: 540,
		closeAction: 'hide',
		autoScroll: true,
		items:[frmChangePass]
	});
	
	changePassWin.show();
}