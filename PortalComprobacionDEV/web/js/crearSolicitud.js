Ext.onReady(function(){
	var lang;
	var params = Ext.urlDecode(window.location.search.substring(1));
	lang = params.language ? params.language : 'es';
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side';
	
	bundle = new Ext.i18n.Bundle({bundle:'messages', path: contextrootpath + '/resources/language', lang: lang});
	bundle.onReady(function(){
		
		var sociedadDS = new Ext.data.JsonStore({
			autoLoad: true,
			root: 'data.configData',
			fields:[
				{name: 'bukrs'},
				{name: 'butxt'},
				{name: 'docTypes'}
			],
			proxy: new Ext.data.HttpProxy({
				url: contextrootpath + '/provider/getConfDataForExpen.action', 
				method: 'GET',
				listeners: {
					load: function(thiz,o,options){
						Ext.getCmp('deptcode').setValue(o.reader.jsonData.data.deptcode);
						Ext.getCmp('subdeptcode').setValue(o.reader.jsonData.data.subdeptcode);
					},
					exception : utilities.handleException
				}
			})
		});
		
		var tipoDocumentoStore = new Ext.data.JsonStore({
			root: 'data.expenseDocConf',
			fields: ['expenseDoc','expenseDocAbre','expenseDocDesc'],
			autoLoad: true,
			proxy: new Ext.data.HttpProxy({ url: contextrootpath + '/provider/getConfDataForExpen.action', method: 'GET' })
		});

		var razonSocialField = new Ext.form.ComboBox({
			triggerAction: 'all',
			id: 'cmbRazonSocial',
			name: 'cmbRazonSocial',
			store: sociedadDS,
			fieldLabel: bundle.getMsg('label.company'),
			emptyText: bundle.getMsg('label.emptycompany'),
			valueField: 'bukrs',
			displayField: 'butxt',
			hiddenName: 'hdnRazonSocial',
			mode: 'local',
			allowBlank: false,
			width: 250
		});
		
		var tipoDocumentoField = new Ext.form.ComboBox({
			triggerAction: 'all',
			id: 'cmbTipoDocumento',
			name: 'cmbTipoDocumento',
			store: tipoDocumentoStore,
			fieldLabel: bundle.getMsg('label.documenttype'),
			emptyText: bundle.getMsg('label.emptydocumenttype'),
			valueField: 'expenseDoc',
			displayField: 'expenseDocDesc',
			hiddenName: 'hdnTipoDocumento',
			mode: 'local',
			allowBlank: false,
			width: 250
		});
		
		var requestDate = new Ext.form.DateField({
			fieldLabel: bundle.getMsg('label.travelrequestdate'),
			id: 'requestDate',
	        name: 'requestDate',
			allowBlank: false,
			width: 130,
			format: 'Y-m-d'
		});
		
		var createdDate = new Ext.form.DateField({
			fieldLabel: bundle.getMsg('label.travelrequestdatecreate'),
			id: 'createdDate',
			name: 'createdDate',
			allowBlank: false,
			width: 130,
			format: 'Y-m-d',
			readOnly: true
		});
		
		var commentsField = new Ext.form.TextArea({
			fieldLabel: bundle.getMsg('label.comments'),
			id: 'comments',
			name: 'comments',
			allowBlank: false,
			anchor: '90%',
	        height: 150
		});
		requestDate.setValue(new Date());
		createdDate.setValue(new Date());
		
		var formCrearSolicitud = new Ext.form.FormPanel({
			title: bundle.getMsg('label.travelform'),
			id: 'crearSolicitudesForm',
			method: 'POST',
			bodyStyle: 'padding: 10px 10px 0 10px;',
			labelWidth: 150,
			frame: true,
			defaults: {
				msgTarget: 'side',
				anchor: '95%'
			},
			border: false,
			renderTo: 'formCrearSolicitud',
			items:[{
				layout: 'form',
				border: false,
				items:[
				       razonSocialField,
				       tipoDocumentoField,
				       {
				    	   layout: 'hbox',
				    	   border: false,
				    	   defaults:{margins:'0 10 0 0'},
				    	   items: [{			    		   
				    		   layout: 'form',
				    		   border: false,
				    		   items:[requestDate]
				    	   },{			    		   
				    		   layout: 'form',
				    		   border: true,
				    		   labelWidth: 100,
				    		   items:[createdDate]
				    	   }]
				       },
				       commentsField,
				       {
				    	   xtype: 	'hidden',
			               name: 	'waers',
			               id: 		'waers'
			           },
			           {
			        	   xtype:	'hidden',
			               name: 	'deptcode',
			               id: 		'deptcode'
			           },
			           {
			        	   xtype: 	'hidden',
			        	   name: 	'subdeptcode',
			        	   id: 		'subdeptcode'
			           },
					   {
						   xtype: 	'hidden',
			        	   name: 	'createdBy',
			        	   id: 		'createdBy'
					   }
				      ]
			}],
			buttonAlign: 'left',
			buttons:[{
				id: 'enviar',
				text: bundle.getMsg('label.send'),
				type: 'button',
				handler: enviarSolicitud,
				style: {
					marginLeft : '15px'
				}
			}]
		});
		
		function enviarSolicitud() {
			var frm = Ext.getCmp('crearSolicitudesForm').getForm();
			if(frm.isValid()) {
				Ext.Ajax.request({
					    url: contextrootpath + '/sessionStatus.action',
					    success: function(result,request) {
						    if(result.responseText.indexOf("login")>=0) {
							    Ext.MessageBox.alert(bundle.getMsg('label.sessionalerttitle'), bundle.getMsg('label.sessionalert'),relogin);
						    } else {
							    frm.submit({
								    url: contextrootpath + '/travel/saveTravelData.action',
								    waitMsg: bundle.getMsg('label.sendingtravelrequest'),
								    success: function(form,action){
									    Ext.MessageBox.show({
										    title: bundle.getMsg('label.success'),
										    msg: action.result.msg,
										    buttons: Ext.MessageBox.OK,
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
							}
					    },
					    failure: utilities.expiredSession
				});
			
			} else {
			    Ext.MessageBox.show({
					title: bundle.getMsg('label.alerttitle'),
					msg: bundle.getMsg('label.alert'),
					icon: Ext.MessageBox.INFO,
					buttons: Ext.MessageBox.OK
				});
			}
		}
		
	}); //end bundle
	
}); //end onReady
