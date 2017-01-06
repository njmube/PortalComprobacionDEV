Ext.onReady(function(){
	Ext.QuickTips.init();
	
	Ext.form.Field.prototype.msgTarget = 'side';
	var form = new Ext.form.FormPanel({
		method: 'POST',
		bodyStyle: 'padding-left: 20px;padding-right: 20px;padding-top: 20px;',		 
		layout: 'form',
		border: false,
		frame: true,		
		items:[{
			layout: 'form',
			border: false,
			items:[{
					xtype: 'textfield',
					fieldLabel: 'Código de Proveedor',
					name: 'providerCode',
					allowBlank: false					
				},{
					xtype: 'textfield',
					fieldLabel: 'Correo electrónico',					
					name: 'email',
					allowBlank: false,
					vtype: 'email'
				}]
		}],
		buttonAlign: 'left',
		buttons:[{
			id: 'sendCode',
			text: 'Solicitar código de seguridad',
			type: 'button',
			handler: function(){
				var frm = form.getForm();
				if(frm.isValid()) {
					frm.submit({
						url: contextrootpath + '/provider/forgotPassword.action',
						waitMsg: 'Solicitando Código...',
						success: function(form,action){
							frm.reset();
							win.close();
							validateCodeWin.show();
							Ext.MessageBox.show({
								title: 'Éxito',
								msg: action.result.msg,
								buttons: Ext.MessageBox.OK,
								icon: Ext.MessageBox.INFO
							});
							
							
						},
						failure: function(form,action){
							switch (action.failureType) {
							  case Ext.form.Action.CLIENT_INVALID:
								 Ext.Msg.alert('Error', 'Valores inválidos');
								 frm.reset();								 
								 break;
							  case Ext.form.Action.CONNECT_FAILURE:
								 Ext.Msg.alert('Error', 'Falla de comunicación con el servidor');
								 frm.reset();								 
								 break;
							  case Ext.form.Action.SERVER_INVALID:
								Ext.Msg.alert('Error', action.result.msg);
								frm.reset();								
								break;
							  default:
								Ext.Msg.alert('Error',action.result.msg);
							  	frm.reset();							  	
							  	break;
							}
						}
					});
				} else {
					Ext.MessageBox.show({
						title: 'Error',
						msg: 'Complete todos los campos',
						buttons: Ext.MessageBox.OK,
						icon: Ext.MessageBox.ERROR
					});
				}
			},
			style: {
				marginLeft : '15px'
			}
		},{
			text: 'Regresar',
			type: 'button',
			handler: function(){
				document.location.href = contextrootpath + '/';
			},
			style: {
				marginLeft : '15px'
			}
		}]
	});
	
	var tokenValidation = new Ext.form.FormPanel({
		method: 'POST',
		bodyStyle: 'padding-left: 20px;padding-right: 20px;padding-top: 20px;',		 
		layout: 'form',
		border: false,
		frame: true,		
		items:[{
			layout: 'form',
			border: false,
			items:[{
					xtype: 'textfield',
					fieldLabel: 'Código de Proveedor',
					name: 'providerCode',
					allowBlank: false,					
				},{
					xtype: 'textfield',
					fieldLabel: 'Correo electrónico',					
					name: 'email',
					allowBlank: false,
					vtype: 'email'
				},{
					xtype: 'textfield',
					fieldLabel: 'Código de seguridad',					
					name: 'token',
					allowBlank: false					
				},{
					xtype: 'textfield',
					name: 'newPass',
					fieldLabel: 'Contraseña nueva',
					inputType: 'password',
					allowBlank: false
				},{
					xtype: 'textfield',
					fieldLabel: 'Confirmar contraseña',
					inputType: 'password',
					name: 'newPassReview',
					allowBlank: false
				}]
		}],
		buttonAlign: 'left',
		buttons:[{			
			text: 'Validar',
			type: 'button',
			handler: function(){
				var frm = tokenValidation.getForm();
				if(frm.isValid()) {
					frm.submit({
						url: contextrootpath + '/provider/validateToken.action',
						waitMsg: 'Enviando Código...',
						success: function(form,action){
							Ext.MessageBox.show({
								title: 'Éxito',
								msg: action.result.msg,
								buttons: Ext.MessageBox.OK,								
								fn: function(){
										document.location.href = contextrootpath + '/';
								},
								icon: Ext.MessageBox.INFO
							});
							frm.reset();							
						},
						failure: function(form,action){
							switch (action.failureType) {
							  case Ext.form.Action.CLIENT_INVALID:
								 Ext.Msg.alert('Error', 'Valores inválidos');
								 frm.reset();								 
								 break;
							  case Ext.form.Action.CONNECT_FAILURE:
								 Ext.Msg.alert('Error', 'Falla de comunicación con el servidor');
								 frm.reset();								 
								 break;
							  case Ext.form.Action.SERVER_INVALID:
								Ext.Msg.alert('Error', action.result.msg);
								frm.reset();								
								break;
							  default:
								Ext.Msg.alert('Error',action.result.msg);
							  	frm.reset();							  	
							  	break;
							}
						}
					});
				} else {
					Ext.MessageBox.show({
						title: 'Error',
						msg: 'Complete todos los campos',
						buttons: Ext.MessageBox.OK,
						icon: Ext.MessageBox.ERROR
					});
				}
			},
			style: {
				marginLeft : '15px'
			}
		},{
			text: 'Regresar',
			type: 'button',
			handler: function(){
				document.location.href = contextrootpath + '/';
			},
			style: {
				marginLeft : '15px'
			}
		}]
	});
	
	var win = Ext.getCmp('forgotWin') || new Ext.Window({
		id: 'forgotWin',
		title: 'Recordar Contraseña',
		frame: true,
		layout: 'form',		
		width: 500,
		modal: false,
		items: [form],
		closable: false
	});
	
	var validateCodeWin = Ext.getCmp('validateCodeWin') || new Ext.Window({
		id : 'validateCodeWin',
		title: 'Validación de código de seguridad',
		frame: true,
		layout: 'form',
		width: 500,
		modal: false,
		items: [tokenValidation],
		closable: false
	});
	
	win.show();	
});