var response;
var arrayItemData = [];
var itemSelectedArray;
var arrayDataFromXml;
var arrayConfigData;
var arrayDocType;
var arrayCeco;
var record;
var ext;
var recordCell;
var fieldNameCell;
var arrayPaymethdData;
var allCecosArray;
var columnIndexCurrent;
var gridCellCurrent;
var arrayHeaderData = [];
var arrayDataFromXml = new Array();
var newValueCurrent;
var columnIndexCurrent; 
var rowIndexCurrent;
var contentsPdf;
var contentsXml;
var unitClass = "";

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
				console.log(o);
				arrayConfigData = o.reader.jsonData.data.configData;
				arrayPaymethdData = o.reader.jsonData.data.paymethd;
			},
			exception: utilities.handleException
		}
	})
});

var solicitudesDS = new Ext.data.JsonStore({
	autoLoad: false,
	root: 'data',
	fields: [
		{name: 'aedat'},
		{name: 'apprdate'},
		{name: 'approver'},
		{name: 'belnr'},
		{name: 'bldat'},
		{name: 'budat'},
		{name: 'bukrs'},
		{name: 'butxt'},
		{name: 'cpudt'},
		{name: 'deptcode'},
		{name: 'dmbtr'},
		{name: 'erdat'},
		{name: 'estatus_a'},
		{name: 'estatus_c'},
		{name: 'expensedoc'},
		{name: 'expenseid'},
		{name: 'expind'},
		{name: 'gjahr'},
		{name: 'kursf'},
		{name: 'langu'},
		{name: 'lifnr'},
		{name: 'name'},
		{name: 'postdate'},
		{name: 'postedby'},
		{name: 'preldoc'},
		{name: 'prelgjahr'},
		{name: 'req_block_icon'},
		{name: 'reqexpdat'},
		{name: 'reqind'},
		{name: 'reqlin'},
		{name: 'status_a'},
		{name: 'status_c'},
		{name: 'subdeptcode'},
		{name: 'tcode'},
		{name: 'texpensedoc'},
		{name: 'userweb'},
		{name: 'usnam'},
		{name: 'vbelnr'},
		{name: 'vgjahr'},
		{name: 'waers'},
		{name: 'wrbtr'}
	],
	proxy: new Ext.data.HttpProxy({
		url: contextrootpath + '/provider/searchDocumentId.action',
		method: 'GET',
		listeners: {
			load: function(thiz,o,options){
				console.log(o);
			},
			exception: utilities.handleException
		}
	})
});

var anticiposDS = new Ext.data.JsonStore({
	autoLoad: false,
	root: 'data.headerDataDto',
	fields: [
		{name: 'admin'},
		{name: 'aedat'},
		{name: 'apprdate'},
		{name: 'approver'},
		{name: 'bldat'},
		{name: 'budat'},
		{name: 'bukrs'},
		{name: 'buktx'},
		{name: 'deptcode'},
		{name: 'deptkostl'},
		{name: 'deptxt'},
		{name: 'erdat'},
		{name: 'ernam'},
		{name: 'erzet'},
		{name: 'expensedoc'},
		{name: 'expensedocdes'},
		{name: 'expenseid'},
		{name: 'expind'},
		{name: 'kursf'},
		{name: 'lifnam'},
		{name: 'lifnr'},
		{name: 'netamtreq'},
		{name: 'netamtexp'},
		{name: 'postdate'},
		{name: 'postedby'},
		{name: 'preldoc'},
		{name: 'prelgjahr'},
		{name: 'reqexpdat'},
		{name: 'reqind'},
		{name: 'stat_descr'},
		{name: 'status'},
		{name: 'subdeptcode'},
		{name: 'subdeptxt'},
		{name: 'tcode'},
		{name: 'userweb'},
		{name: 'waers'},
		{name: 'wwert'},
	],
	proxy: new Ext.data.HttpProxy({
		url: contextrootpath + '/request/getRequestData.action',
		method: 'GET',
		listeners: {
			load: function(thiz,o,options){
				console.log(o);
				arrayHeaderData = o.reader.jsonData.data.headerDataDto;
				response = o.reader.jsonData;
			},
			exception: utilities.handleException
		}
	})
});

var detalleAnticipoDS = new Ext.data.JsonStore({
	autoLoad: false,
	root: 'data.itemDataDtoList',
	fields: [
	   	{name: 'admin'},
		{name: 'apprdate'},
		{name: 'approver'},
		{name: 'belnr'},
		{name: 'bldat'},
		{name: 'budat'},
		{name: 'bukrs'},
		{name: 'dmbtr'},
		{name: 'erdat'},
		{name: 'erzet'},
		{name: 'expenseid'},
		{name: 'gjahr'},
		{name: 'kursf'},
		{name: 'mark'},
		{name: 'postdate'},
		{name: 'postedby'},
		{name: 'reqlin'},
		{name: 'status'},
		{name: 'vbelnr'},
		{name: 'vgjahr'},
		{name: 'waers'},
		{name: 'wrbtr'}
	],
	proxy: new Ext.data.HttpProxy({ 
		url: contextrootpath + '/request/getRequestData.action',
		method: 'GET',
		listeners: {
			load: function(thiz,o,options){
				console.log(o);
				arrayItemData = o.reader.jsonData.data.itemDataDtoList;
				response = o.reader.jsonData;
			},
			exception: utilities.handleException
		}
	})
});
 
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	Ext.form.Field.prototype.msgTarget = 'side';	
    var lang;
	var params = Ext.urlDecode(window.location.search.substring(1));
	lang = params.language ? params.language : 'es';
	bundle = new Ext.i18n.Bundle({bundle:'messages', path: contextrootpath + '/resources/language', lang: lang});
	bundle.onReady(function(){
		
		var razonSocialField = new Ext.form.ComboBox({
			triggerAction: 'all',
			id: 'cmbRazonSocial',
			name: 'cmbRazonSocial',
			store: sociedadDS,
			fieldLabel: bundle.getMsg('label.company'),
			emptyText: bundle.getMsg('label.emptycompany'),
			valueField: 'bukrs',
			displayField: 'butxt',
			mode: 'local',
			allowBlank: false,
			width: 250
		});
		
		var fechaIni = new Ext.form.DateField({
			fieldLabel: bundle.getMsg('label.travelrequestdate'),
			id: 'fechaIni',
			name: 'fechaIni',		
			allowBlank: false,
			width: 130
		});
		
		var fechaFin = new Ext.form.DateField({
			fieldLabel: bundle.getMsg('label.travelrequestdateto'),		
			id: 'fechaFin',		
			name: 'fechaFin',		
			allowBlank: false,
			width: 130
		});
		
		var estadoCmb = new Ext.form.ComboBox({
			id:'cmbEstado',
			name: 'cmbEstado',
			fieldLabel: bundle.getMsg('label.status'),
			store : new Ext.data.SimpleStore({
				idProperty: 'status',
				fields: ['id','desc'],
				data:[
					  ['00',bundle.getMsg('cmb.all')],
					  ['01',bundle.getMsg('cmb.pending')],
					  ['02',bundle.getMsg('cmb.approval')],
					  ['03',bundle.getMsg('cmb.approved_advancepaymentdoc')],
					  ['04',bundle.getMsg('cmb.paid_posted')],
					  ['05',bundle.getMsg('cmb.rejected')],
					  ['06',bundle.getMsg('cmb.canceled')]
				      ]
			}),
			typeAhead: true,
			triggerAction: 'all',
			mode: 'local',
			forceSelection: true,
			allowBlank: false,
			selectOnFocus: true,
			valueField: 'id',
			value: '00',
			displayField: 'desc',
			width: 250
		});
		
		var btnListarSolicitudes = new Ext.Button({
			text: bundle.getMsg('label.filter'),
			handler: listarSolicitudes,
			width: 100
		});
		
		var solicitudesGrd = new Ext.grid.GridPanel({
			id: 'solicitudesGrd',
			store: solicitudesDS,
			columns: [new Ext.grid.RowNumberer(),
			          {header: bundle.getMsg('label.travelrequestnumber'), width: 45, dataIndex: 'expenseid', sortable: true, align: 'center', menuDisabled: true},
			    	  {header: bundle.getMsg('label.advancepaymentnumber'), width: 45, dataIndex: 'reqlin', sortable: true, align: 'center', menuDisabled: true},
			    	  {header: bundle.getMsg('label.creationdate'), width: 45, dataIndex: 'reqexpdat', sortable: true, align: 'center', menuDisabled: true},
			    	  {header: bundle.getMsg('label.postdate'), width: 45, dataIndex: 'budat', sortable: true, align: 'center', menuDisabled: true},
			    	  {header: bundle.getMsg('label.amount'), width: 45, dataIndex: 'wrbtr', sortable: true, align: 'center', menuDisabled: true},
			    	  {header: bundle.getMsg('label.currency'), width: 45, dataIndex: 'waers', sortable: true, align: 'center', menuDisabled: true},
			    	  {header: bundle.getMsg('label.statusdescription'), width: 45, dataIndex: 'estatus_a', sortable: true, align: 'center', menuDisabled: true},
					  {header: bundle.getMsg('label.approvedby'), width: 45, dataIndex: 'approver', sortable: true, align: 'center', menuDisabled: true},
					  {header: bundle.getMsg('label.paymentrequest'), width: 45, dataIndex: 'preldoc', sortable: true, align: 'center', menuDisabled: true},
					  {header: bundle.getMsg('label.postedby'), width: 45, dataIndex: 'postedby', sortable: true, align: 'center', menuDisabled: true},
					  {header: bundle.getMsg('label.paymentdocument'), width: 45, dataIndex: 'vbelnr', sortable: true, align: 'center', menuDisabled: true},
					  {header: bundle.getMsg('label.selection'), width: 45, sortable : false, align: 'center', dataIndex: 'select', xtype: 'checkcolumn'}
					  ],
		    viewConfig: {forceFit: true},		
			border: false,
			stripeRows: true,
			singleCheckSelect: true,
			height: 300,
			columnLines: true,
			autoScroll: true,
			loadMask: true,
			selModel: new Ext.grid.RowSelectionModel({singleSelect: true})
		});
		var firstDate = new Date();
		firstDate = new Date(firstDate.getFullYear(),firstDate.getMonth(),1);
		fechaIni.setValue(firstDate);
		fechaFin.setValue(new Date());
		
		var formSolicitud = new Ext.form.FormPanel({
			title: bundle.getMsg('label.advancepaymentslist'),
			id: 'solicitudForm',
			method: 'POST',
			bodyStyle: 'padding: 10px 10px 0 10px;',
			frame: true,
			defaults: {
				msgTarget: 'side',
				anchor: '100%',
				labelWidth: 150
			},
			border: false,
			renderTo: 'formListaSolicitud',
			items:[{
				layout: 'form',
				border: false,
				items:[
				       razonSocialField,
				       {
				    	   layout: 'hbox',
				    	   border: false,
				    	   defaults:{margins:'0 10 0 0'},
				    	   items: [{			    		   
				    		   layout: 'form',
				    		   border: false,
				    		   items:[fechaIni]
				    	   },{			    		   
				    		   layout: 'form',
				    		   border: true,
				    		   labelWidth: 25,			    		   
				    		   items:[fechaFin]
				    	   }]
				       },
				       estadoCmb,
					   btnListarSolicitudes,
					   solicitudesGrd
				       ]
			}],
			buttonAlign: 'left',
			buttons:[{
				id: 'crearAnticipo',
				text: bundle.getMsg('label.createadvancepayment'),
				type: 'button',
				handler: crearAnticipo,
				style: {
					marginLeft : '15px',
					width: 130
				}
			},{
				id: 'modificarAnticipo',
				text: bundle.getMsg('label.modifyadvancepayment'),
				type: 'button',
				handler: modificarAnticipo,
				style: {
					marginLeft : '15px',
					width: 130
				}
			}]
		});

		function listarSolicitudes(){
			var frm = Ext.getCmp('solicitudForm').getForm();
			if(frm.isValid()){
				solicitudesDS.removeAll();
				solicitudesDS.load({
					params: {
						cmbRazonSocial: Ext.getCmp('cmbRazonSocial').getValue(), 
						fechaIni: Ext.getCmp('fechaIni').getValue().format('Y-m-d'),
						fechaFin: Ext.getCmp('fechaFin').getValue().format('Y-m-d'),
						cmbEstado: Ext.getCmp('cmbEstado').getValue()
					}
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
		
		function crearAnticipo(){
			var solicitudes = Ext.getCmp('solicitudesGrd').getStore();
			var record;
			solicitudes.each(function(rec){
				if(rec.data.select){
					record = rec;
				}
			});

			if(!record){
				Ext.Msg.alert('Aviso','Por favor seleccione una solicitud de viaje del listado');
				return;
			}

			if (record.data.status == 2 || record.data.status == 3){
				Ext.Msg.alert('Aviso','No es posible generar un anticipo para la solicitud seleccionada');
				return;
			}
	
			var numSolicitudField = new Ext.form.TextField({
				id: 'numSolicitud',
				name: 'numSolicitud',
				fieldLabel: bundle.getMsg('label.travelrequestnumber'),
				allowBlank: false,
				width: 200,
				readOnly: true
			});
			
			var numAnticipoField = new Ext.form.TextField({
				id: 'numAnticipo',
				name: 'numAnticipo',
				fieldLabel: bundle.getMsg('label.advancepaymentnumber'),
				allowBlank: true,
				width: 200,
				readOnly: true
			});
		
			var fecDocumentoDate = new Ext.form.DateField({
				fieldLabel: bundle.getMsg('label.expensedocumentdate'),
				id: 'fecDocumento',
				name: 'fecDocumento',
				allowBlank: false,
				width: 200,
				format: 'Y-m-d'
			});
			fecDocumentoDate.setValue(new Date());
		
			var fecSolicitudDate = new Ext.form.DateField({
				fieldLabel: bundle.getMsg('label.travelrequestdate'),
				id: 'fecSolicitud',
				name: 'fecSolicitud',		
				allowBlank: false,
				width: 200,
				format: 'Y-m-d',
				hidden: true
			});
		
			var monedaField = new Ext.form.ComboBox({
				triggerAction: 'all',
				name: 'moneda',
				fieldLabel: bundle.getMsg('label.currency'),
				emptyText: bundle.getMsg('label.emptycurrency'),
				store: new Ext.data.SimpleStore({
					idProperty: 'status',
					fields: ['Currency'],
					data:[
						['MXN'],
						['USD'],
						['EUR'],
						['CAD'],
						['JPY'],
						['AUD'],
						['BRL'],
						['CNY'],
						['CZK'],
						['GBP'],
						['INR']
						]
				}),
				mode: 'local',
				typeAhead: true,
				forceSelection: true,
				displayField: 'Currency',
				valueField: 'Currency',
				selectOnFocus: true,
				triggerAction: 'all',
				width: 200
			});
		
			var importeAnticipoField = new Ext.form.TextField({
				id: 'importeAnticipo',
				name: 'importeAnticipo',
				fieldLabel: bundle.getMsg('label.amountrequest'),
				allowBlank: false,
				width: 200
			});

			var btnEnviarAnticipo = new Ext.Button({
				text: bundle.getMsg('label.send'),
				id: 'enviarAnticipo',
				handler: enviarAnticipo,
				width: '75px'
			});

			var anticipoFormPanel = Ext.getCmp('anticipoFormPanel') || new Ext.FormPanel({
				title: bundle.getMsg('label.createadvancepayment'),
				id: 'anticipoFormPanel',
				method: 'POST',
				bodyStyle: 'padding: 10px 10px 0 10px;',
				frame: true,
				defaults: {
					msgTarget: 'side',
					anchor: '100%',
					labelWidth: 200,
				},
				border: false,
				buttonAlign: 'left',
				items:[{
					layout: 'column',
					border: false,
					items:[{
							layout: 'form',
							columnWidth: .5,
							border: false,
							items:[
								numSolicitudField,
								numAnticipoField,
								fecDocumentoDate,
								fecSolicitudDate,
								monedaField,
								importeAnticipoField,
								btnEnviarAnticipo
							]
					}]
				}]
			});
			
			var winAnticipo = Ext.getCmp('winAnticipo') || new Ext.Window({
				id: 'winAnticipo',
				title: bundle.getMsg('label.createadvancepayment') + ' ' + record.data.expenseid,
				closeAction:'destroy',
				width: 1050,
				resizable: false,
				items: [anticipoFormPanel],
				listeners:{
					beforeclose:function(win) {
						Ext.getCmp("winAnticipo").destroy();
	                    //Ext.getCmp("winAnticipo").close();
						return true;
					}
				}
			});
			winAnticipo.show();
		
			var params = {};
			params.bukrs = record.data.bukrs;
			params.expenseId = record.data.expenseid;
	
			Ext.Ajax.request({
				url: contextrootpath + '/request/getRequestData.action',
				method: 'GET',
				params: params,
				success: function(result, request) {
					if(Ext.util.JSON.decode(result.responseText).success == false){
						Ext.MessageBox.show({
							title: 'Aviso',
							msg: Ext.util.JSON.decode(result.responseText).msg,
							buttons: Ext.MessageBox.OK,
							icon: Ext.MessageBox.WARNING
						});
					} else {
						response = Ext.util.JSON.decode(result.responseText);
						var anticipoFrm = Ext.getCmp('anticipoFormPanel').getForm();
						anticipoFrm.findField('numSolicitud').setValue(record.data.expenseid);
						anticipoFrm.findField('numAnticipo').setValue("");
						anticipoFrm.findField('fecDocumento').setValue(new Date());
						anticipoFrm.findField('fecSolicitud').setValue(response.data.headerDataDto.erdat);
						anticipoFrm.findField('moneda').setValue(response.data.itemDataDtoList[0].waers);
					}
				},
				failure: function(result, request) {
					switch (result.failureType) {
						case Ext.form.Action.CLIENT_INVALID:
								Ext.MessageBox.show({
									title: 'Aviso',
									msg: "Algunos valores se enviaron incorrectamente",
									buttons: Ext.MessageBox.OK,
									icon: Ext.MessageBox.WARNING
								});
								break;
						case Ext.form.Action.CONNECT_FAILURE:
								Ext.MessageBox.show({
									title: 'Error crítico',
									msg: "Comunicación con el servidor falló",
									buttons: Ext.MessageBox.OK,
									icon: Ext.MessageBox.ERROR
								});
								break;
						default:
							if(result.failureType!=undefined){
								Ext.MessageBox.show({
									title: 'ERROR',
									msg: result.responseText,
									buttons: Ext.MessageBox.OK,
									icon: Ext.MessageBox.ERROR
								});
							}
					}
				}
			});
		}
	
		function modificarAnticipo(){
			var solicitudes = Ext.getCmp('solicitudesGrd').getStore();
			var record;
			solicitudes.each(function(rec){
				if(rec.data.select){
					record = rec;
				}
			});

			if(!record){
				Ext.Msg.alert('Aviso','Por favor seleccione una solicitud de viaje del listado');
				return;
			}

			if (record.data.status == 2 || record.data.status == 3){
				Ext.Msg.alert('Aviso','No es posible generar un anticipo para la solicitud seleccionada');
				return;
			}
	
			var numSolicitudField = new Ext.form.TextField({
				id: 'numSolicitud',
				name: 'numSolicitud',
				fieldLabel: bundle.getMsg('label.travelrequestnumber'),
				allowBlank: false,
				width: 200,
				readOnly: true
			});
			
			var numAnticipoField = new Ext.form.TextField({
				id: 'numAnticipo',
				name: 'numAnticipo',
				fieldLabel: bundle.getMsg('label.advancepaymentnumber'),
				allowBlank: false,
				width: 200,
				readOnly: true
			});
		
			var fecDocumentoDate = new Ext.form.DateField({
				fieldLabel: bundle.getMsg('label.expensedocumentdate'),
				id: 'fecDocumento',
				name: 'fecDocumento',
				allowBlank: false,
				width: 200,
				format: 'Y-m-d'
			});
			fecDocumentoDate.setValue(new Date());
		
			var fecSolicitudDate = new Ext.form.DateField({
				fieldLabel: bundle.getMsg('label.travelrequestdate'),
				id: 'fecSolicitud',
				name: 'fecSolicitud',		
				allowBlank: false,
				width: 200,
				format: 'Y-m-d',
				hidden: true
			});
		
			var monedaField = new Ext.form.ComboBox({
				triggerAction: 'all',
				name: 'moneda',
				fieldLabel: bundle.getMsg('label.currency'),
				emptyText: bundle.getMsg('label.emptycurrency'),
				store: new Ext.data.SimpleStore({
					idProperty: 'status',
					fields: ['Currency'],
					data:[
						['MXN'],
						['USD'],
						['EUR'],
						['CAD'],
						['JPY'],
						['AUD'],
						['BRL'],
						['CNY'],
						['CZK'],
						['GBP'],
						['INR']
						]
				}),
				mode: 'local',
				typeAhead: true,
				forceSelection: true,
				displayField: 'Currency',
				valueField: 'Currency',
				selectOnFocus: true,
				triggerAction: 'all',
				width: 200
			});
		
			var importeAnticipoField = new Ext.form.TextField({
				id: 'importeAnticipo',
				name: 'importeAnticipo',
				fieldLabel: bundle.getMsg('label.amountrequest'),
				allowBlank: false,
				width: 200
			});

			var btnEnviarAprobacion = new Ext.Button({
				text: bundle.getMsg('label.sendapproved'),
				id: 'enviarAprobacion',
				handler: enviarAprobacion,
				width: '75px'
			});

			var anticipoFormPanel = Ext.getCmp('anticipoFormPanel') || new Ext.FormPanel({
				title: bundle.getMsg('label.modifyadvancepayment'),
				id: 'anticipoFormPanel',
				method: 'POST',
				bodyStyle: 'padding: 10px 10px 0 10px;',
				frame: true,
				defaults: {
					msgTarget: 'side',
					anchor: '100%',
					labelWidth: 200,
				},
				border: false,
				buttonAlign: 'left',
				items:[{
					layout: 'column',
					border: false,
					items:[{
							layout: 'form',
							columnWidth: .5,
							border: false,
							items:[
							    numSolicitudField,
								numAnticipoField,
								fecDocumentoDate,
								fecSolicitudDate,
								monedaField,
								importeAnticipoField,
								btnEnviarAprobacion
							]
					}]
				}]
			});
			
			var winAnticipo = Ext.getCmp('winAnticipo') || new Ext.Window({
				id: 'winAnticipo',
				title: bundle.getMsg('label.modifyadvancepayment') + ' ' + record.data.expenseid,
				closeAction:'destroy',
				width: 1050,
				resizable: false,
				items: [anticipoFormPanel],
				listeners:{
					beforeclose:function(win) {
						Ext.getCmp("winAnticipo").destroy();
	                    //Ext.getCmp("winAnticipo").close();
						return true;
					}
				}
			});
			winAnticipo.show();
		
			var params = {};
			params.bukrs = record.data.bukrs;
			params.expenseId = record.data.expenseid;
	
			Ext.Ajax.request({
				url: contextrootpath + '/request/getRequestData.action',
				method: 'GET',
				params: params,
				success: function(result, request) {
					if(Ext.util.JSON.decode(result.responseText).success == false){
						Ext.MessageBox.show({
							title: 'Aviso',
							msg: Ext.util.JSON.decode(result.responseText).msg,
							buttons: Ext.MessageBox.OK,
							icon: Ext.MessageBox.WARNING
						});
					} else {
						response = Ext.util.JSON.decode(result.responseText);
						var anticipoFrm = Ext.getCmp('anticipoFormPanel').getForm();
						anticipoFrm.findField('numSolicitud').setValue(record.data.expenseid);
						anticipoFrm.findField('numAnticipo').setValue(response.data.itemDataDtoList[0].reqlin);
						anticipoFrm.findField('fecDocumento').setValue(new Date());
						anticipoFrm.findField('fecSolicitud').setValue(response.data.headerDataDto.erdat);
						anticipoFrm.findField('moneda').setValue(response.data.itemDataDtoList[0].waers);
						anticipoFrm.findField('importeAnticipo').setValue(response.data.itemDataDtoList[0].wrbtr);
					}
				},
				failure: function(result, request) {
					switch (result.failureType) {
						case Ext.form.Action.CLIENT_INVALID:
								Ext.MessageBox.show({
									title: 'Aviso',
									msg: "Algunos valores se enviaron incorrectamente",
									buttons: Ext.MessageBox.OK,
									icon: Ext.MessageBox.WARNING
								});
								break;
						case Ext.form.Action.CONNECT_FAILURE:
								Ext.MessageBox.show({
									title: 'Error crítico',
									msg: "Comunicación con el servidor falló",
									buttons: Ext.MessageBox.OK,
									icon: Ext.MessageBox.ERROR
								});
								break;
						default:
							if(result.failureType!=undefined){
								Ext.MessageBox.show({
									title: 'ERROR',
									msg: result.responseText,
									buttons: Ext.MessageBox.OK,
									icon: Ext.MessageBox.ERROR
								});
							}
					}
				}
			});
		}
		
		function enviarAprobacion(){
			var frm = Ext.getCmp('anticipoFormPanel').getForm();
			if(frm.isValid()){
				Ext.Ajax.request({
					url: contextrootpath + '/sessionStatus.action',
					success: function(result,request) {
						if(result.responseText.indexOf("login")>=0) {
							Ext.MessageBox.alert('Alerta', 'La sesión expiró, favor de ingresar nuevamente',relogin);					
						} else {
							response.data.itemDataDtoList[0].wrbtr = frm.findField('importeAnticipo').getValue();
							response.data.itemDataDtoList[0].bldat = frm.findField('fecDocumento').getValue().format('Y-m-d');
							response.data.itemDataDtoList[0].budat = frm.findField('fecSolicitud').getValue().format('Y-m-d');
							response.data.headerDataDto.waers = frm.findField('moneda').getValue();
							response.data.itemDataDtoList[0].waers = frm.findField('moneda').getValue();
							response.data.itemDataDtoList[0].status = "02";
							var waitBox = Ext.MessageBox.wait(bundle.getMsg('label.sendingadvancepayment'), 'Please Wait...');
							Ext.Ajax.request({
								url: contextrootpath + '/request/saveRequestData.action',
								method: 'POST',
								params: response.data,
								jsonData: response.data,
								beforeSend:function(){
									//Ext.getCmp('enviarAnticipo').disable();
							    },
								success: function(result,request){
									var res = Ext.util.JSON.decode(result.responseText);
									if(res.success){
										Ext.MessageBox.show({
											title: bundle.getMsg('label.success'),
											msg: res.msg,
											buttons: Ext.MessageBox.OK,
											icon: Ext.MessageBox.INFO
										});
									} else {
										Ext.MessageBox.show({
											title: 'Atención',
											msg: res.msg,
											buttons: Ext.MessageBox.OK,
											icon: Ext.MessageBox.ERROR
										});
									}
									frm.reset();
									//Ext.getCmp('enviarAnticipo').enable();
									Ext.getCmp('winAnticipo').close();
								},
								failure: function(result,request){
									switch (action.failureType) {
										case Ext.form.Action.CLIENT_INVALID:
												Ext.MessageBox.show({
													title: 'Atención',
													msg: 'Valores inválidos',
													buttons: Ext.MessageBox.OK,
													icon: Ext.MessageBox.ERROR
												});
												frm.reset();
												break;
										case Ext.form.Action.CONNECT_FAILURE:
												Ext.MessageBox.show({
													title: 'Atención',
													msg: 'Falla de comunicación con el servidor',
													buttons: Ext.MessageBox.OK,
													icon: Ext.MessageBox.ERROR
												});
												frm.reset();
												break;
										case Ext.form.Action.SERVER_INVALID:
												Ext.MessageBox.show({
													title: 'Atención',
													msg: action.result.msg,
													buttons: Ext.MessageBox.OK,
													icon: Ext.MessageBox.ERROR
												});
												frm.reset();
												break;
										default:
												Ext.MessageBox.show({
													title: 'Atención',
													msg: action.result.msg,
													buttons: Ext.MessageBox.OK,
													icon: Ext.MessageBox.ERROR
												});
												frm.reset();
												break;
									}
									//Ext.getCmp('enviarAnticipo').enable();
								}
							});
						}
					},
					failure: utilities.expiredSession
				});
			
			} else {
				Ext.MessageBox.show({
					title: 'Mensaje',
					msg: 'Complete todos los campos requeridos',
					buttons: Ext.MessageBox.OK,
					icon: Ext.MessageBox.WARNING
				});
				//Ext.getCmp('enviarAnticipo').enable();
			}
		}
		
		function enviarAnticipo(){
			var frm = Ext.getCmp('anticipoFormPanel').getForm();
			if(frm.isValid()){
				Ext.Ajax.request({
					url: contextrootpath + '/sessionStatus.action',
					success: function(result,request) {
						if(result.responseText.indexOf("login")>=0) {
							Ext.MessageBox.alert('Alerta', 'La sesión expiró, favor de ingresar nuevamente',relogin);					
						} else {
							response.data.itemDataDtoList[0].wrbtr = frm.findField('importeAnticipo').getValue();
							response.data.itemDataDtoList[0].bldat = frm.findField('fecDocumento').getValue().format('Y-m-d');
							response.data.itemDataDtoList[0].budat = frm.findField('fecSolicitud').getValue().format('Y-m-d');
							response.data.headerDataDto.waers = frm.findField('moneda').getValue();
							response.data.itemDataDtoList[0].waers = frm.findField('moneda').getValue();
							var waitBox = Ext.MessageBox.wait(bundle.getMsg('label.sendingadvancepayment'), 'Please Wait...');
							Ext.Ajax.request({
								url: contextrootpath + '/request/saveRequestData.action',
								method: 'POST',
								params: response.data,
								jsonData: response.data,
								beforeSend:function(){
									Ext.getCmp('enviarAnticipo').disable();
							    },
								success: function(result,request){
									var res = Ext.util.JSON.decode(result.responseText);
									if(res.success){
										Ext.MessageBox.show({
											title: bundle.getMsg('label.success'),
											msg: res.msg,
											buttons: Ext.MessageBox.OK,
											icon: Ext.MessageBox.INFO
										});
									} else {
										Ext.MessageBox.show({
											title: 'Atención',
											msg: res.msg,
											buttons: Ext.MessageBox.OK,
											icon: Ext.MessageBox.ERROR
										});
									}
									frm.reset();
									Ext.getCmp('enviarAnticipo').enable();
									Ext.getCmp('winAnticipo').close();
								},
								failure: function(result,request){
									switch (action.failureType) {
										case Ext.form.Action.CLIENT_INVALID:
												Ext.MessageBox.show({
													title: 'Atención',
													msg: 'Valores inválidos',
													buttons: Ext.MessageBox.OK,
													icon: Ext.MessageBox.ERROR
												});
												frm.reset();
												break;
										case Ext.form.Action.CONNECT_FAILURE:
												Ext.MessageBox.show({
													title: 'Atención',
													msg: 'Falla de comunicación con el servidor',
													buttons: Ext.MessageBox.OK,
													icon: Ext.MessageBox.ERROR
												});
												frm.reset();
												break;
										case Ext.form.Action.SERVER_INVALID:
												Ext.MessageBox.show({
													title: 'Atención',
													msg: action.result.msg,
													buttons: Ext.MessageBox.OK,
													icon: Ext.MessageBox.ERROR
												});
												frm.reset();
												break;
										default:
												Ext.MessageBox.show({
													title: 'Atención',
													msg: action.result.msg,
													buttons: Ext.MessageBox.OK,
													icon: Ext.MessageBox.ERROR
												});
												frm.reset();
												break;
									}
									Ext.getCmp('enviarAnticipo').enable();
								}
							});
						}
					},
					failure: utilities.expiredSession
				});
			
			} else {
				Ext.MessageBox.show({
					title: 'Mensaje',
					msg: 'Complete todos los campos requeridos',
					buttons: Ext.MessageBox.OK,
					icon: Ext.MessageBox.WARNING
				});
				Ext.getCmp('enviarAnticipo').enable();
			}
		}

	}); //end bundle
	
}); //end onReady
