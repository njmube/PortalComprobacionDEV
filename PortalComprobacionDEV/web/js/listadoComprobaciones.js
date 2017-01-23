var response;
var arrayItemData;
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
var newValueCurrent;
var gridCellCurrent;
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

var cecosArray = new Ext.data.JsonStore({
	id: 'cecosDtoArray',
	fields: ['appdmbtr', 'cost_PERCENT', 'kostl'],
	data: []
});

var dataContableArray = new Ext.data.JsonStore({
	id: 'dataContableArray',
	fields: ['hkont', 'wrbtr', 'mwskz', 'wmwst', 'txt20'],
	data: []
});

var doctypeArray = new Ext.data.JsonStore({
	id: 'doctypeArray',
	fields: ['expenseClass', 'expenseClassDes', 'unit'],
	data: []
});

var cecosDtoArray = new Ext.data.JsonStore({
	id: 'cecosDtoArray',
	fields: ['costcenter', 'descript'],
	data: []
});
 
var dataStore = new Ext.data.Store({
	data: [],
	reader: new Ext.data.ArrayReader({id:'id'},
    ['linea', 'tipodoc', 'clasegasto', 'Office'])
});

var paymethDS = new Ext.data.JsonStore({
	id: 'paymethDS',
	fields: ['paymet', 'paymetdes'],
	data: []
});

var dataContableCombo = new Ext.data.JsonStore({
	id: 'dataContableCombo',
	fields: ['name', 'kostl', 'descript'],
	data: []
});

var detalleComprobacionDS = new Ext.data.JsonStore({
	autoLoad: false,
	root: 'data.itemDataExpenseDtoList',
	fields: [
	   	{name: 'expenseid'},
		{name: 'expensedoc'},
		{name: 'expensedocdes'},
		{name: 'expenseline'},
		{name: 'expensetype'},
		{name: 'expenseclass'},
		{name: 'expenseclassdes'},
		{name: 'available_DMBTR'},
		{name: 'expenquan'},
		{name: 'bukrs'},
		{name: 'buktx'},		
		{name: 'rfc'},
		{name: 'subdeptcode'},
		{name: 'dmbtr'},
		{name: 'subdeptxt'},
		{name: 'deptkostl'},
		{name: 'reqexpdat'},
		{name: 'xmlxstring'},
		{name: 'pdfxstring'},
		{name: 'budat'},
		{name: 'bldat'},
		{name: 'erdat'},
		{name: 'erzet'},
		{name: 'ernam'},
		{name: 'aedat'},
		{name: 'waers_xml'},
		{name: 'waers'},
		{name: 'kursf'},
		{name: 'uuid'},
		{name: 'tcode'},
		{name: 'paymet'},
		{name: 'wrbtr'},
		{name: 'mwskz_xml'},
		{name: 'kostl'},
		{name: 'prelgjahr'},
		{name: 'userweb'},
		{name: 'admin'},
		{name: 'approver'},
		{name: 'apprdate'},
		{name: 'postedby'},
		{name: 'postdate'},
		{name: 'status'},
		{name: 'stat_descr'},
		{name: 'select'},
		{name: 'pdf'},
		{name: 'xml'}
	],
	proxy: new Ext.data.HttpProxy({
		url: contextrootpath + '/expense/getExpenseData.action',
		method: 'GET',
		listeners: {
			load: function(thiz,o,options){
				console.log(o);
				arrayHeaderData = o.reader.jsonData.data.headerExpenseDto;
				arrayItemData = o.reader.jsonData.data.itemDataExpenseDtoList;
				response = o.reader.jsonData;
			},
			exception: utilities.handleException
		}
	})
});

var comprobacionesDS = new Ext.data.JsonStore({
	autoLoad: false,
	root: 'data',
	fields: [
	   	{name: 'appdmbtr'},
		{name: 'bldat'},
		{name: 'budat'},
		{name: 'bukrs'},
		{name: 'deptcode'},
		{name: 'dmbtr'},
		{name: 'expensedoc'},
	   	{name: 'expenseid'},
		{name: 'lifnam'},
		{name: 'lifnr'},
		{name: 'saldo'},
		{name: 'stat_descr'},
		{name: 'status'},
		{name: 'subdeptcode'},
		{name: 'waers'},
		{name: 'select'}
	],
	proxy: new Ext.data.HttpProxy({ 
		url: contextrootpath + '/provider/getExpenseStatement.action', 
		method: 'GET',
		listeners: {
			load: function(thiz,o,options){
				console.log(o);
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
		
		var expenseidField = new Ext.form.TextField({
			id: 'expenseid',
			name: 'expenseid',
			fieldLabel: bundle.getMsg('label.expensenumber'),
			allowBlank: true,
			width: 250
		});
		
		var btnListarComprobaciones = new Ext.Button({
			text: bundle.getMsg('label.filter'),
			handler: listarComprobaciones,
			width: 100
		});
		
		var comprobacionesGrd = new Ext.grid.GridPanel({
			id: 'comprobacionesGrd',
			store: comprobacionesDS,
			columns: [new Ext.grid.RowNumberer(),
			          {header: bundle.getMsg('label.vendornumber'), hidden: true, width: 45, dataIndex: 'lifnr', sortable: true, align: 'center', menuDisabled: true},
					  {header: bundle.getMsg('label.vendorname'), hidden: true, width: 45, dataIndex: 'lifnam', sortable: true, align: 'center', menuDisabled: true},
					  {header: bundle.getMsg('label.expensenumber'), width: 45, dataIndex: 'expenseid', sortable: true, align: 'center', menuDisabled: true},
					  {header: bundle.getMsg('label.documenttype'), width: 45, dataIndex: 'expensedoc', sortable: true, align: 'center', menuDisabled: true},
					  {header: bundle.getMsg('label.documentdate'), width: 45, dataIndex: 'bldat', sortable: true, align: 'center', menuDisabled: true},
					  {header: bundle.getMsg('label.postdate'), width: 45, dataIndex: 'budat', sortable: true, align: 'center', menuDisabled: true},
					  {header: bundle.getMsg('label.expenseamount'), width: 45, dataIndex: 'dmbtr', sortable: true, align: 'center', menuDisabled: true},
					  {header: bundle.getMsg('label.approvedamount'), width: 45, dataIndex: 'appdmbtr', sortable: true, align: 'center', menuDisabled: true},
					  {header: bundle.getMsg('label.balance'), width: 45, dataIndex: 'saldo', sortable: true, align: 'center', menuDisabled: true},
					  {header: bundle.getMsg('label.statusdescription'), width: 45, dataIndex: 'stat_descr', sortable: true, align: 'center', menuDisabled: true},
					  {header: bundle.getMsg('label.departmentcode'), width: 45, dataIndex: 'deptcode', sortable: true, align: 'center', menuDisabled: true},
					  {header: bundle.getMsg('label.subdepartmentcode'), width: 45, dataIndex: 'subdeptcode', sortable: true, align: 'center', menuDisabled: true},
					  {header: bundle.getMsg('label.selection'), width: 45, sortable : false, align: 'center', dataIndex: 'select', xtype: 'checkcolumn'}
					  ],
		    viewConfig: {forceFit: true},		
			border: false,
			stripeRows: true,
			singleCheckSelect: true,
			height: 275,
			columnLines: true,
			autoScroll: true,
			loadMask: true,
			selModel: new Ext.grid.RowSelectionModel({singleSelect: true})
		});
		var firstDate = new Date();
		firstDate = new Date(firstDate.getFullYear(),firstDate.getMonth(),1);
		fechaIni.setValue(firstDate);
		fechaFin.setValue(new Date());
		
		var formComprobacion = new Ext.form.FormPanel({
			title: bundle.getMsg('label.expenseslist'),
			id: 'comprobacionForm',
			method: 'POST',
			bodyStyle: 'padding: 10px 10px 0 10px;',
			frame: true,
			defaults: {
				msgTarget: 'side',
				anchor: '100%',
				labelWidth: 150
			},
			border: false,
			renderTo: 'formListaComprobacion',
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
					   expenseidField,
					   btnListarComprobaciones,
					   comprobacionesGrd
				       ]
			}],
			buttonAlign: 'left',
			buttons:[{
				id: 'editarComprobacion',
				text: bundle.getMsg('label.editexpense'),
				type: 'button',
				handler: editarComprobacion,
				style: {
					marginLeft : '15px',
					width: 130
				}
			},{
				id: 'enviarAprobacion',
				text: bundle.getMsg('label.sendapproved'),
				type: 'button',
				handler: enviarAprobacion,
				style: {
					marginLeft : '15px',
					width: 130
				}
			}]
		});
		
		function listarComprobaciones(){
			var frm = Ext.getCmp('comprobacionForm').getForm();	
			if(frm.isValid()){
				comprobacionesDS.removeAll();
				comprobacionesDS.load({
					params: {
						cmbRazonSocial: Ext.getCmp('cmbRazonSocial').getValue(), 
						fechaIni: Ext.getCmp('fechaIni').getValue().format('Y-m-d'),
						fechaFin: Ext.getCmp('fechaFin').getValue().format('Y-m-d'),
						cmbEstado: Ext.getCmp('cmbEstado').getValue(),
						expenseid: Ext.getCmp('expenseid').getValue()
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
		
		function editarComprobacion(){
			var comprobaciones = Ext.getCmp('comprobacionesGrd').getStore();
			comprobaciones.each(function(rec){
				if(rec.data.select) {
					record = rec;
				}
			});

			if(!record) {
				Ext.Msg.alert('Aviso','Por favor seleccione una comprobación del listado');
				return;
			}

			if (record.data.status != "01") {
				Ext.Msg.alert('Aviso','No es posible editar la comprobación seleccionada');
				return;
			}

			numcomps = arrayConfigData.length;
			for(var ic = 0;ic<numcomps;ic++){
				if (arrayConfigData[ic].bukrs === record.data.bukrs){
					doctypeArray.loadData(arrayConfigData[ic].docTypes);
					allCecosArray = arrayConfigData[ic].cecosDto;	    
				}
			}
		
			var numComprobacionField = new Ext.form.TextField({
				id: 'numcomprobacion',
				name: 'numcomprobacion',
				fieldLabel: bundle.getMsg('label.expensenumber'),
				allowBlank: false,
				width: 150
			});
		
			var requestDate = new Ext.form.DateField({
				fieldLabel: bundle.getMsg('label.expensedocumentdate'),
				id: 'requestDate',
				name: 'requestDate',
				allowBlank: false,
				width: 150
			});
		
			var createdDate = new Ext.form.DateField({
				fieldLabel: bundle.getMsg('label.postdate'),
				id: 'createdDate',
				name: 'createdDate',
				allowBlank: false,
				width: 150
			});
		
			var commentsField = new Ext.form.TextArea({
				id: 'comments',
				name: 'comments',
				fieldLabel: bundle.getMsg('label.expensecomments'),
				allowBlank: false,
				anchor: '60% 15%',
				height: 50,
				hidden: true
			});
			
			var btnSendComprobacion = new Ext.Button({
				text: bundle.getMsg('label.sendexpense'),
				id: 'enviarComprobacion',
				handler: sendComprobacion,
				width: 150
			});
		
			var btnSaveComprobacion = new Ext.Button({
				text: bundle.getMsg('label.saveexpense'),
				id: 'guardarComprobacion',
				handler: saveComprobacion,
				width: 150
			});

			var detalleComprobacionesGrd = new Ext.grid.GridPanel({
				id: 'detalleComprobacionesGrd',
				store: detalleComprobacionDS,
				columns: [new Ext.grid.RowNumberer(),
						  {header: 'Linea', width: 50, dataIndex: 'expenseline', sortable: true, align: 'center'},
						  {header: 'Tipo de Doc.', width: 100, dataIndex: 'expensetype', sortable: true, align: 'center'},
						  {header: 'id Clase Gasto', width: 100, dataIndex: 'expenseclass', sortable: true, align: 'center', hidden: true},
						  {header: 'cantidad', width: 100, dataIndex: 'expenquan', sortable: true, align: 'center', hidden: true},
						  {header: 'Clase Gasto', width: 100, dataIndex: 'expenseclassdes', sortable: true, align: 'center'},
						  {header: 'Importe', width: 100, dataIndex: 'wrbtr', sortable: true, align: 'center'},
						  {header: 'Moneda', width: 50, dataIndex: 'waers_xml', sortable: true, align: 'center'},
						  {header: 'Sociedad', width: 100, dataIndex: 'bukrs', sortable: true, align: 'center'},
						  {header: 'Ind. IVA', width: 100, dataIndex: 'mwskz_xml', sortable: true, align: 'center'},
						  {header: 'Forma Pago', width: 100, dataIndex: 'paymet', sortable: true, align: 'center'},
						  {header: 'Fecha Doc.', width: 100, dataIndex: 'bldat', sortable: true, align: 'center'},
						  {header: 'Tipo Cambio.', width: 100, dataIndex: 'kursf', sortable: true, align: 'center'},
						  {header: 'Centro Coste', width: 100, dataIndex: 'kostl', sortable: true, align: 'center'},
						  {header: 'UUID', width: 100, dataIndex: 'uuid', sortable: true, align: 'center'},
						  {header: 'RFC', width: 100, dataIndex: 'rfc', sortable: true, align: 'center'},
						  {header: 'pdfxstring', width: 100, dataIndex: 'pdfxstring', sortable: true, align: 'center', hidden: true},
						  {header: 'xmlxstring', width: 100, dataIndex: 'xmlxstring', sortable: true, align: 'center', hidden: true},
						  {header: '# Fact.', width: 100, dataIndex: 'numfact', sortable: true, align: 'center', hidden: true},
						  {header: 'XML', width: 15, dataIndex: 'xml', sortable: true, align: 'center'},
						  {header: 'PDF', width: 15, dataIndex: 'pdf', sortable: true, align: 'center'},
						  {header: 'Importe MXN', width: 100, dataIndex: 'dmbtr', sortable: true, align: 'center', hidden: true},
						  {header: 'Disponible MXN', width: 100, dataIndex: 'available_DMBTR', sortable: true, align: 'center', hidden: true},
						  {header: 'Selección', width: 100, sortable : false, align: 'center', dataIndex: 'select', xtype: 'checkcolumn'}
						  ],
				viewConfig: {forceFit: true},		
				border: false,
				stripeRows: true,
				singleCheckSelect: true,
				height: 200,
				columnLines: true,
				autoScroll: true,
				loadMask: true,
				selModel: new Ext.grid.RowSelectionModel({singleSelect: true}),
				listeners: {
					viewready: function(){
						var comprobaciones = Ext.getCmp('comprobacionesGrd').getStore();
						comprobaciones.each(function(rec){
							if(rec.data.select) {
								record = rec;
							}
						});
						this.store.load({
							params: {
								bukrs: record.data.bukrs,
								expenseid: record.data.expenseid
							}
						});
					}
				}
			});

			var formListaComprobacion = Ext.getCmp('listaComprobacionesForm') || new Ext.form.FormPanel({
				title: bundle.getMsg('label.editexpense'),
				id: "listaComprobacionesForm",
				method: 'POST',
				bodyStyle: 'padding-left: 20px;padding-right: 20px;padding-top: 20px;',
				frame: true,
				defaults: {
					msgTarget: 'side',
					anchor: '95%',
					labelWidth: 150
				},
				border: false,
				layout: 'form',
				renderTo: 'formListaComprobacion',
				items:[{
					layout: 'column',
					border: false,
					items:[{
							layout: 'form',
							columnWidth: .5,
							border: false,
							items:[
								numComprobacionField,
								requestDate,
								createdDate,
								commentsField
							]
					}]
				}],
				buttonAlign: 'left',
				buttons:[{
					id: 'showAgregarLinea',
					text: bundle.getMsg('label.addline'),
					type: 'button',
					handler: showAgregarLinea,
					width: 150
				},{
					id: 'removerLinea',
					text: bundle.getMsg('label.removeline'),
					type: 'button',
					width: 150,
					listeners: {
						click: function(){
							var detalleComprobaciones = Ext.getCmp('detalleComprobacionesGrd').getStore();
							var recordDetalleComprobacion;
							detalleComprobaciones.each(function(rec){
								if(rec.data.select) {
									recordDetalleComprobacion = rec;
									record = rec;
								}
							});
						
							if(!recordDetalleComprobacion){
								Ext.Msg.alert('Aviso','Por favor seleccione una línea de la lista');
								return;
							}
							detalleComprobaciones.remove(recordDetalleComprobacion);
						}
					}
				},{
					id: 'showDistribuirCeco',
					text: bundle.getMsg('label.addcostcenters'),
					type: 'button',
					handler: showDistribuirCeco,
					width: 150
				},{
					id: 'showDatowContables',
					text: bundle.getMsg('label.addaccountingdata'),
					type: 'button',
					handler: showDatowContables,
					width: 150
				}]
			});
			Ext.getCmp('numcomprobacion').setValue(record.data.expenseid);
			
			if (record.data.status == 1) {
				Ext.getCmp('requestDate').setValue(new Date());
				Ext.getCmp('createdDate').setValue(new Date());
			} else {
				Ext.getCmp('requestDate').setValue(record.data.budat);
				Ext.getCmp('createdDate').setValue(record.data.bldat);
			}
			
			var winComprobacion = Ext.getCmp('winComprobacion') || new Ext.Window({
				id: 'winComprobacion',
				title: bundle.getMsg('label.editexpense') + ' ' + record.data.expenseid,
				frame: true,
				layout: 'form',
				closeAction: 'destroy',
				width: 1300,
				modal: true,
				resizable: false,
				items: [formListaComprobacion, 
				        detalleComprobacionesGrd,
						{
				    	   layout: 'hbox',
				    	   border: false,
				    	   defaults: {margins:'10 10 10 10'},
				    	   items: [{			    		   
				    		   layout: 'form',
				    		   border: false,
				    		   items: [btnSendComprobacion]
				    	   },{			    		   
				    		   layout: 'form',
				    		   border: false,
				    		   items: [btnSaveComprobacion]
				    	   }]
				       }],
				listeners: {
					beforeclose: function(win) {
						Ext.getCmp("winComprobacion").destroy();
						return true;
					}
				}
			});
			//Ext.getCmp('detalleComprobacionesGrd').getView().refresh();
			
			winComprobacion.show();
			
			var params = {};

		}
		
		function showAgregarLinea(){
			Ext.apply(Ext.form.VTypes, {
				xmlFile: function(v) {
					return /^.*.(xml|XML)$/.test(v);
				},
				xmlFileText: 'Debe Ingresar un XML'
			});
				
			numcomps = arrayConfigData.length;
		    for(var ic = 0;ic<numcomps;ic++){
				if (arrayConfigData[ic].bukrs === record.data.bukrs){
					doctypeArray.loadData(arrayConfigData[ic].docTypes);
		        }
		    }
		
			var docTypeCmb = new Ext.form.ComboBox({
				id:"docType",
				name: 'docType',
				fieldLabel: bundle.getMsg('label.documenttype'),
				emptyText: bundle.getMsg('label.emptydocumenttype'),
				store: new Ext.data.SimpleStore({
					idProperty: 'status',
					fields: ['id','desc'],
					data:[
						  ['01','CFDI'],
						  ['02','No CFDI'],
						  ['03','Extranjero']
						  ]
				}),
				typeAhead: true,
				triggerAction: 'all',
				mode: 'local',
				forceSelection: true,
				allowBlank: false,
				selectOnFocus: true,
				valueField: 'id',
				displayField: 'desc',
				width: 250,
				listeners: {
					select: function(combo, record, index) {
						if (combo.getValue() == 1) {
							Ext.getCmp('quantity').setVisible(true);
							Ext.getCmp('quantity').setValue("1");
							Ext.getCmp('rfc').setVisible(false);
							Ext.getCmp('numfact').setVisible(false);
							Ext.getCmp('monto').setVisible(false);
							Ext.getCmp('iva').setVisible(false);
							Ext.getCmp('money').setVisible(false);
							Ext.getCmp('metodopago').setVisible(true);
							Ext.getCmp('cargarxml').setVisible(true);
							Ext.getCmp('cargarpdf').setVisible(true);
						}
							
						if (combo.getValue() == 2) {
							Ext.getCmp('quantity').setVisible(true);
							Ext.getCmp('quantity').setValue("1");
							Ext.getCmp('rfc').setVisible(true);
							Ext.getCmp('rfc').setValue("");
							Ext.getCmp('rfc').setReadOnly(false);
							Ext.getCmp('numfact').setVisible(true);
							Ext.getCmp('monto').setVisible(true);
							Ext.getCmp('iva').setVisible(true);
							Ext.getCmp('money').setVisible(true);
							Ext.getCmp('metodopago').setVisible(true);
							Ext.getCmp('cargarxml').setVisible(false);
							Ext.getCmp('cargarpdf').setVisible(true);
						}
						
						if (combo.getValue() == 3) {
							Ext.getCmp('quantity').setVisible(true);
							Ext.getCmp('quantity').setValue("1");
							Ext.getCmp('rfc').setVisible(true);
							Ext.getCmp('rfc').setValue("XEXX010101000");
							Ext.getCmp('rfc').setReadOnly(true);
							Ext.getCmp('numfact').setVisible(true);
							Ext.getCmp('monto').setVisible(true);
							Ext.getCmp('iva').setVisible(true);
							Ext.getCmp('money').setVisible(true);
							Ext.getCmp('metodopago').setVisible(true);
							Ext.getCmp('cargarxml').setVisible(false);
							Ext.getCmp('cargarpdf').setVisible(true);
						}
					}
				}
			});
		
			var gastosCmb = new Ext.form.ComboBox({
				id: "gastos",
				name: 'gastos',
				fieldLabel: bundle.getMsg('label.expenseclass'),
				emptyText: bundle.getMsg('label.emptyexpenseclass'),
				store: doctypeArray,
				typeAhead: true,
				triggerAction: 'all',
				mode: 'local',
				forceSelection: true,
				allowBlank: false,
				selectOnFocus: true,
				valueField: 'expenseClass',
				displayField: 'expenseClassDes',
				width: 250,
				hiddenName: 'unit',
				listeners: {
					select: function(combo, record, index) {
						unitClass = record.data.unit;
					}
				}
			});
			
			var cargarxml = new Ext.form.TextField({
				fieldLabel: bundle.getMsg('label.xmlfile'),
				id: 'cargarxml',
				name: 'cargarxml',
				regex: /^.*\.(xml|XML)$/,
                vtype: 'xmlFile', 
				regexText: 'Solo archivos XML',
                inputType: 'file',
                msgTarget: 'side',
            	blankText: '<div style="color:red;">validation message</div>',
                width: 350
			});
			
			var cargarpdf = new Ext.form.TextField({
				fieldLabel: bundle.getMsg('label.pdffile'),
				id: 'cargarpdf',
				name: 'cargarpdf',
				regex: /^.*\.(pdf|PDF)$/,
				regexText: 'Solo archivos PDF',
				inputType: 'file',
                width: 350
			});
		
			var quantityField = new Ext.form.TextField({
				fieldLabel: bundle.getMsg('label.quantity'),
				id: 'quantity',
				name: 'quantity',
				allowBlank: false,
				width: 180,
				hidden: true
			});
		
			var moneyCmb = new Ext.form.ComboBox({
				id: "money",
				name: 'money',
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
				typeAhead: true,
				triggerAction: 'all',
				mode: 'local',
				forceSelection: true,
				allowBlank: true,
				selectOnFocus: true,
				valueField: 'Currency',
				displayField: 'Currency',
				width: 180,
				hidden: true
			});
		
			var metodoPagoCmb = new Ext.form.ComboBox({
				id: "metodopago",
				name: "metodopago",
				fieldLabel: bundle.getMsg('label.paymentmethod'),
				emptyText: bundle.getMsg('label.emptypaymentmethod'),
				store: paymethDS,
				typeAhead: true,
				triggerAction: 'all',
				mode: 'local',
				forceSelection: true,
				allowBlank: false,
				selectOnFocus: true,
				valueField: 'paymet',
				displayField: 'paymetdes',
				width: 250
			});
		
			var rfcField = new Ext.form.TextField({
				fieldLabel: bundle.getMsg('label.rfc'),
				id: 'rfc',
				name: 'rfc',
				allowBlank: true,
				width: 180,
				hidden: true,
				minLength: 12,
				maxLength: 13
			});
		
			var numFactField = new Ext.form.TextField({
				fieldLabel: bundle.getMsg('label.invoicenumber'),
				id: "numfact",
				name: 'numfact',
				allowBlank: true,
				width: 180,
				hidden: true
			});
		
			var montoField = new Ext.form.TextField({
				fieldLabel: bundle.getMsg('label.amount'),
				id: "monto",
				name: 'monto',
				allowBlank: true,
				width: 180,
				hidden: true
			});
		
			var ivaCmb = new Ext.form.ComboBox({
				id: "iva",
				name: 'iva',
				fieldLabel: bundle.getMsg('label.tax'),
				emptyText: bundle.getMsg('label.emptytax'),
				store: new Ext.data.SimpleStore({
					idProperty: 'status',
					fields: ['IVA'],
					data:[
						  ['16.00'],
						  ['0.00']
						  ]
				}),
				typeAhead: true,
				triggerAction: 'all',
				mode: 'local',
				forceSelection: true,
				allowBlank: true,
				selectOnFocus: true,
				valueField: 'IVA',
				displayField: 'IVA',
				width: 180,
				hidden: true
			});

			var btnAddLineToTable = new Ext.Button({
				text: bundle.getMsg('label.add'),
				id: 'addLineToTable',
				handler: addLineToTable,
				width: '75px'
			});

			var anticipoFormPanel = Ext.getCmp('anticipoFormPanel') || new Ext.FormPanel({
				title: bundle.getMsg('label.addline'),
				id: 'anticipoFormPanel',
				method: 'POST',
				bodyStyle: 'padding: 10px 10px 0 10px;',
				frame: true,
				defaults: {
					msgTarget: 'side',
					anchor: '205%',
					labelWidth: 150,
				},
				border: false,
				buttonAlign: 'left',
				width: '1300',
				height: 550,
				fileUpload: true,
				items:[{
					layout: 'column',
					border: false,
					items:[{
							layout: 'form',
							columnWidth: .5,
							border: false,
							items:[
								docTypeCmb,
								gastosCmb,
								cargarxml,
								cargarpdf,
								quantityField,
								moneyCmb,
								metodoPagoCmb,
								rfcField,
								numFactField,
								montoField,
								ivaCmb,
								btnAddLineToTable
							]
					}]
				}]
			});
			paymethDS.loadData(arrayPaymethdData);
			
			Ext.getCmp('docType').setValue("00");
			hideInputText();
			Ext.getCmp("anticipoFormPanel").getForm().reset();
		
			winAnticipo = Ext.getCmp('winAnticipo') || new Ext.Window({
				id: 'winAnticipo',
				title: bundle.getMsg('label.addline'),
				width: 600,
				height: 550,
				resizable: false,
				items: [anticipoFormPanel]
			});		
			winAnticipo.show();

		}
		
		function getBase64Xml(file1){
			var reader = new FileReader();
			var fileUpload = file1.files[0];
			reader.onload = function(event){
				contentsXml = btoa(event.target.result);
			};
			reader.readAsBinaryString(fileUpload);
		}
		
		function getBase64Pdf(file1){
			var reader = new FileReader();
			var fileUpload = file1.files[0];
			reader.onload = function(event){
				contentsPdf = btoa(event.target.result);
			};
			reader.readAsBinaryString(fileUpload);
		}
		
		function functionOne(){
			var pdfFile = document.getElementById('cargarpdf');
			getBase64Pdf(pdfFile);
	    };
 
		function functionTwo(params){
			console.log("pdf new " + contentsPdf);
		};

		function addLineToTable(){
			var confirmExtensionXml=/^.*.(xml|XML)$/.test(Ext.getCmp('cargarxml').getValue());
			var confirmExtensionPDF=/^.*.(pdf|PDF)$/.test(Ext.getCmp('cargarpdf').getValue());

			if((Ext.getCmp('cargarxml').getValue() == "" || Ext.getCmp('cargarpdf').getValue() == "") && Ext.getCmp('docType').getValue() == "01"){
				msg = "";
				if (Ext.getCmp('cargarxml').getValue() == "") msg = "Debe Ingresar Xml" + "\n";
				if (Ext.getCmp('cargarpdf').getValue() == "") msg = msg + "Debe Ingresar PDF";
				alert(msg);
				return false;
			}
			
			if(Ext.getCmp('cargarpdf').getValue() == "" && Ext.getCmp('docType').getValue() != "01"){				 
				msg = "Debe Ingresar PDF";
				alert(msg);
				return false;
			}
			
			if(!confirmExtensionXml && Ext.getCmp('cargarxml').getValue() != ""){				 
				msg = "Debe Subir archivo en formato XML";
				alert(msg);
				return false;
			}
			
			if(!confirmExtensionPDF && Ext.getCmp('cargarpdf').getValue() != ""){				 
			    msg = "Debe subir archivo en formato PDF";
				alert(msg);
				return false;
			}
			
			numcomps = arrayConfigData.length;
		    for(var ic = 0;ic<numcomps;ic++){
				if (arrayConfigData[ic].bukrs === record.data.bukrs){
					arrayDocType = arrayConfigData[ic].docTypes;
		        }
			}
				
			TopicRecord = Ext.data.Record.create([
			{name: 'expenseline', mapping: 'expenseline'},
			{name: 'expenseclass', mapping: 'expenseclass'},
			{name: 'expenseclassdes', mapping: 'expenseclassdes'},
			{name: 'expenquan', mapping: 'expenquan'},
			{name: 'wrbtr', mapping: 'wrbtr'},
			{name: 'waers_xml', mapping: 'waers_xml'},
			{name: 'mwskz_xml', mapping: 'mwskz_xml'},
			{name: 'paymet', mapping: 'paymet'},
			{name: 'bldat', mapping: 'bldat'},
			{name: 'bukrs', mapping: 'bukrs'},
			{name: 'kostl', mapping: 'kostl'},
			{name: 'uuid', mapping: 'uuid'},
			{name: 'rfc', mapping: 'rfc'},
			{name: 'numfact', mapping: 'numfact'},
			{name: 'xml', mapping: 'xml'},
			{name: 'pdf', mapping: 'pdf'},
			{name: 'xmlxstring', mapping: 'xmlxstring'},
			{name: 'pdfxstring', mapping: 'pdfxstring'},
			{name: 'dmbtr', mapping: 'dmbtr'},
			{name: 'available_DMBTR', mapping: 'available_DMBTR'}
			]);
			
	        arrayDataFromXml = "";
	        var paymetValue = Ext.getCmp('metodopago').getValue();
	        var expenseClassDesValue = Ext.getCmp('gastos').getRawValue();
	        var expenseClassValue = Ext.getCmp('gastos').getValue();
	        var doctypeValue = Ext.getCmp('docType').getValue();
	        var pdfValue = Ext.getCmp('cargarpdf').getValue();
	        var xmlValue = Ext.getCmp('cargarxml').getValue();
	        var quantityValue = Ext.getCmp('quantity').getValue();
	        var bukrs = response.data.headerExpenseDto.bukrs;
	           
	        if(confirmExtensionXml){
				var xmlFile = document.getElementById('cargarxml');
	            getBase64Xml(xmlFile);
	            var pdfFile = document.getElementById('cargarpdf');
	            getBase64Pdf(pdfFile);
	            var formData = new FormData();
	            formData.append("file", $("#cargarxml")[0].files[0]);
	            $.ajax({
					url: contextrootpath + '/provider/getFieldsFromXml.action',
	                type: 'POST',
	                beforeSend: function(){
					
	                },
	                contentType: false,
	                error: function(jqXHR, textStatus, errorThrown){
							 
					},
	                data: formData,
	                cache: false,
	                contentType: false,
	                processData: false,
	                success: function (data){
						arrayDataFromXml = data.data;
						var str = "" + (parseInt(arrayItemData[arrayItemData.length-1].expenseline)+1);
						var pad = "0000"
						expenseLineString = pad.substring(0, pad.length - str.length) + str
						var params = {
							expenseline: expenseLineString,
							expensetype: doctypeValue,
							expenseclass: expenseClassValue,
					        expenseclassdes: expenseClassDesValue,
					        expenquan: quantityValue,
					        wrbtr: arrayDataFromXml.wrbtr,
					        mwskz_xml: arrayDataFromXml.mwskz,
					        uuid: arrayDataFromXml.uuid,
					        paymet: paymetValue,
					        waers_xml: arrayDataFromXml.waers,
					        rfc: arrayDataFromXml.rfc,
					        pdf: pdfValue == ""? "No":"Si",
					        xml: xmlValue == "" ? "No":"Si",
					        bldat: arrayDataFromXml.bldat.split("T")[0],					         		
					        kursf: arrayDataFromXml.kursf,
					        numfact: arrayDataFromXml.numFact,
					        bukrs: bukrs,
					        kostl: "",
					        costdist: "",
					        pdfxstring: contentsPdf,
					        xmlxstring: contentsXml,
					        unit: unitClass
					    };
						myNewRecord = new TopicRecord(params);
						detalleComprobacionDS.add(myNewRecord);
						arrayItemData.push(params);
						arrayDataFromXml = "";
			 		}
			    });
			}else{
				var todaysDate = new Date();
 				var str = "" + (parseInt(arrayItemData[arrayItemData.length-1].expenseline)+1);
				var pad = "0000";
				expenseLineString = pad.substring(0, pad.length - str.length) + str
 				function convertDate(date){
					var yyyy = date.getFullYear().toString();
					var mm = (date.getMonth()+1).toString();
					var dd = date.getDate().toString();
				    var mmChars = mm.split('');
					var ddChars = dd.split('');
				    return yyyy + '-' + (mmChars[1]?mm:"0"+mmChars[0]) + '-' + (ddChars[1]?dd:"0"+ddChars[0]);
				}
						
				var paymetValue = Ext.getCmp('metodopago').getValue();
	            var expenseClassDesValue = Ext.getCmp('gastos').getRawValue();
	            var expenseClassValue = Ext.getCmp('gastos').getValue();
	            var doctypeValue = Ext.getCmp('docType').getValue();
	            var pdfValue = Ext.getCmp('cargarpdf').getValue();
	            var xmlValue = Ext.getCmp('cargarxml').getValue();
	            var quantityValue = Ext.getCmp('quantity').getValue();
	            var bukrs = response.data.headerExpenseDto.bukrs; 	
	            var rfcValue = Ext.getCmp('rfc').getValue();
	            var numfactValue = Ext.getCmp('numfact').getValue();
	            var ivaValue = Ext.getCmp('iva').getValue();
	            var montoValue = Ext.getCmp('monto').getValue();
	            var moneyValue = Ext.getCmp('money').getValue();
	        	var pdfFile = document.getElementById('cargarpdf');
	     		getBase64Pdf(pdfFile);   
	            $.ajax({
					url: contextrootpath+'/provider/getFieldsFromXml.action',
	                type: 'POST',
	            	beforeSend: function(){
						var pdfFile = document.getElementById('cargarpdf');
	     				getBase64Pdf(pdfFile); 
	                },
					data: formData,
	                cache: false,
	                contentType: false,
	                processData: false,
	            	complete: function(){
						var params = {
							expenseline: expenseLineString,
						    expensetype: doctypeValue,				        					         		 
						    pdf: pdfValue == "" ? "No":"Si",
						    xml: xmlValue == "" ? "No":"Si",
						    paymet: paymetValue,
						    expenquan: quantityValue,
						    rfc: rfcValue,
						    uuid: "",
						    expenseclass: expenseClassValue,
						    expenseclassdes: expenseClassDesValue,
						    numfact: numfactValue,
						    mwskz_xml: ivaValue,
						    wrbtr: montoValue,
						    waers_xml: moneyValue,
						    bukrs: bukrs,
						    bldat: convertDate(todaysDate),
						    unit: unitClass, 
						    kostl: "",
						    costdist:"",
							pdfxstring: ""
						};
						params.pdfxstring = contentsPdf;
						myNewRecord = new TopicRecord(params);
						detalleComprobacionDS.add(myNewRecord);
						arrayItemData.push(params);
	            	}
	           	});
			}
			arrayDataFromXml = "";
			Ext.getCmp('detalleComprobacionesGrd').getView().refresh();
			winAnticipo.close();
		}
		
		function printLineInTable(arrayDataFromXml){
				 
		}
		
		function showDistribuirCeco(){
			var detalleComprobacionesGrd = Ext.getCmp('detalleComprobacionesGrd').getStore();
			var record;
			var Cecosnulled = [
			        {kostl: '', cost_PERCENT: '', appdmbtr:''},
				    {kostl: '', cost_PERCENT: '', appdmbtr:''},
				    {kostl: '', cost_PERCENT: '', appdmbtr:''},
				    {kostl: '', cost_PERCENT: '', appdmbtr:''},
				    {kostl: '', cost_PERCENT: '', appdmbtr:''},
				    {kostl: '', cost_PERCENT: '', appdmbtr:''},
				    {kostl: '', cost_PERCENT: '', appdmbtr:''},
				    {kostl: '', cost_PERCENT: '', appdmbtr:''},
				    {kostl: '', cost_PERCENT: '', appdmbtr:''},
				    {kostl: '', cost_PERCENT: '', appdmbtr:''}
			]
			detalleComprobacionesGrd.each(function(rec){
				if(rec.data.select){
					record = rec;
					itemSelectedArray = arrayItemData[detalleComprobacionesGrd.indexOf(record)];
					if(itemSelectedArray.costdist === undefined || itemSelectedArray.costdist == "")
						expenseidCurrent = cecosArray.loadData(Cecosnulled);
					else{
						$.each(itemSelectedArray.costdist,function(index,val){
						
						});
						var lengthCecos = itemSelectedArray.costdist.length;
						if(lengthCecos<10){
							var countCecos;
							for(countCecos = lengthCecos;countCecos<10;countCecos++){
								itemSelectedArray.costdist.push({kostl: '', cost_PERCENT: '', appdmbtr:''});
							}
							cecosArray.loadData(itemSelectedArray.costdist);
						}else cecosArray.loadData(itemSelectedArray.costdist);
					}
				}
			});
				
		    if(!record){
				Ext.Msg.alert('Aviso','Por favor seleccione una comprobación del listado');
				return;
			}
				
			var cecoFormPanel = Ext.getCmp('cecoFormPanel') || new Ext.FormPanel({
					id: 'cecoFormPanel',
					bodyStyle:'padding:15px 0px 10px 5px',
				    anchor: '100% 100%',    
				    autoScroll:true,
				    autoHeight: true,
				    fileUpload: true,
				    frame: true,
				    border: false,
				    items: [{
				        layout:'column',
				        border: false,
				        anchor: '100%',             
				        items:[{
				    		columnWidth: .20,
			                layout: 'form',
			                border: false,
			                labelWidth: 70,
			                defaultType: 'textfield',
			                defaults: {			                	
			                    labelStyle: 'margin-left: 10px;'              
			                },  
				    		items: [{
				    			id:"comprobacionIdCeco",
				    			fieldLabel: '# Compr', name: 'comprobacionIdCeco', width: 100
				    		}]
				    	},{
				    		columnWidth: .15,
			                layout: 'form',
			                border: false,
			                labelWidth: 60,
			                defaultType: 'textfield',
			                defaults: {			                	
			                    labelStyle: 'margin-left: 10px;'              
			                },  
				    		items: [{
				    			id: "lineaComprobacionCeco",
				    			fieldLabel: 'Línea', name: 'lineaComprobacionCeco', width: 60
				    		}]
				    	},{
				    		columnWidth: .22,
			                layout: 'form',
			                border: false,
			                labelWidth: 100,
			                defaultType: 'textfield',
			                defaults: {			                	
			                    labelStyle: 'margin-left: 10px;'              
			                },  
				    		items: [{
				    			id: "clasegatosComprobacionCeco",
				    			fieldLabel: 'Clase Gastos', name: 'clasegatosComprobacionCeco', width: 80
				    		}]
				    	},{
				    		columnWidth: .21,
			                layout: 'form',
			                border: false,
			                labelWidth: 60,
			                defaultType: 'textfield',
			                defaults: {			                	
			                    labelStyle: 'margin-left: 10px;',
			                    value: "100"              
			                },  
				    		items: [{
				    			id: "importeComprobacionCeco",
				    			fieldLabel: 'Importe', name: 'importeComprobacionCeco', width: 80
				    		}]
				    	},{
				    		columnWidth: .17,
			                layout: 'form',
			                border: false,
			                labelWidth: 10,
			                defaultType: 'textfield',
			                defaults: {			                	
			                    labelStyle: 'margin-left: 10px;'              
			                },  
				    		items: [{
				    			id: "moneyCeco",
				    			name: 'moneyCeco', width: 60
				    		}]
				    	},{
				    		columnWidth: .7,
				    		layout: 'form',
				    		defaults: {
				    			anchor: '-20',
								labelStyle: 'margin-left: 5px;'
					    	},
					   		items: [{	
					   			xtype: 'editorgrid',
					   			anchor: '100% 100%',
					   		    id:'cecoGrd',
					   		    name:'cecoGrd',
					   		    autoScroll: true,
					   		    forceLayout:true,
					   		    viewConfig: {forceFit: true},		
								border: false,			
								stripeRows: true,
								singleCheckSelect: true,
								store: cecosArray, 
								height: 400,
								columnLines: true,
								autoScroll: true,
								loadMask: true,
								selModel: new Ext.grid.RowSelectionModel({singleSelect: true}),
					   		    columns:[
					   		        new Ext.grid.RowNumberer(),
					   		         {
					                   header: 'Centro Coste', width: 45, sortable: true, dataIndex: 'kostl', menuDisabled: true,
					                    editor: new Ext.form.ComboBox({
									    displayField: 'descript',
									    //displayField: 'name',
									    valueField: 'kostl',
									    store: dataContableCombo,
									    typeAhead: true,
									    mode: 'local',
									    forceSelection: true,
									    triggerAction: 'all',
									    emptyText: 'Select a state...',
									    selectOnFocus: true
									    })
					                },
				             		//{header: 'Centro Coste', width: 45, sortable: true, dataIndex: 'kostl', menuDisabled: true},
				             		{header: '% Distr.', width: 45, sortable: true, dataIndex: 'cost_PERCENT', menuDisabled: true,editor:new Ext.form.TextField({  
                    						allowBlank : false ,
                    						listeners: {
										       	change: function(field, newVal, oldVal){
									     		        newValueCurrent=newVal;
									     		        fieldNameCell= gridCellCurrent.getColumnModel().getDataIndex(columnIndexCurrent+1);					     		       
									     		       var rowCurrent=gridCellCurrent.getStore().getAt(rowIndexCurrent);
									     		       rowCurrent.set(fieldNameCell, mathRound(parseFloat(newVal)* parseFloat(Ext.getCmp("importeComprobacionCeco").getValue())/100));
									     		       	rowIndexCurrent=rowIndexCurrent+1;	

										  		},
										  		specialkey: function(field, e){
										            console.table("char1 "+e.getKey()); //this is don't work
										            if(e.getKey()==e.ENTER){
										            	fieldNameCell= gridCellCurrent.getColumnModel().getDataIndex(columnIndexCurrent+1);
										            	var rowCurrent=gridCellCurrent.getStore().getAt(rowIndexCurrent);
										            	fieldNameCellPercentage= gridCellCurrent.getColumnModel().getDataIndex(columnIndexCurrent);
									     		       var data = rowCurrent.get(fieldNameCellPercentage);
									     		       rowCurrent.set(fieldNameCell,mathRound(parseFloat(field.getValue())* parseFloat(Ext.getCmp("importeComprobacionCeco").getValue())/100));	
									     		       rowIndexCurrent=rowIndexCurrent+1;
										            }
										        }
									    	},

									        changeHandler: function(field, newValue) {
									           
									        }

                								}),listeners: {
										    change : function(field, newValue,o ,e) {
									        var text = field.value;
									    }
									}

				             		},				             		 
				             		{header: 'Importe', width: 45, sortable: true, dataIndex: 'appdmbtr', menuDisabled: true,editor:new Ext.form.TextField({
				             			allowBlank : false,

				             		}) }
				             		
				                ],listeners: {
							        cellclick: function(grid, rowIndex, columnIndex, e) {
								        //var zRec = iView.getRecord(iRowEl);}

								        recordCell = grid.getStore().getAt(rowIndex);  // Get the Record
								        columnIndexCurrent=columnIndex;
								        gridCellCurrent=grid;
									    rowIndexCurrent=rowIndex;
								        
								    },
								    specialkey: function(field, e){
										            console.table("char1 "+e.getKey()); //this is don't work
										            if(e.getKey()==e.ENTER){
										            	fieldNameCell= gridCellCurrent.getColumnModel().getDataIndex(columnIndexCurrent+1);
										            	var rowCurrent=gridCellCurrent.getStore().getAt(rowIndexCurrent);
										            	fieldNameCellPercentage= gridCellCurrent.getColumnModel().getDataIndex(columnIndexCurrent);
									     		       var data = rowCurrent.get(fieldNameCellPercentage);
									     		       
									     		       rowCurrent.set(fieldNameCell,(parseFloat(field.getValue())* parseFloat(Ext.getCmp("importeComprobacionCeco").getValue())/100));	
										            }
										        }
							    }		   							   	    
					   		}]



				    	},{
				    		columnWidth: .2,
			                layout: 'form',
			                border: false,
			                labelWidth: 80,
			                defaultType: 'textfield',
			                defaults: {
			                    labelStyle: 'margin-left: 10px;'              
			                },  
				    		items: [{
				    		   	xtype : 'button',
								text : '% iguales',
								handler : equalsPercentage,
								defaults : {
									anchor : '10',
									labelStyle : 'margin-left: 15px;'
								},
								width : 86
										 
				    		}]
				    	},{
				    		columnWidth: .5,
			                layout: 'form',
			                border: false,
			                labelWidth: 80,
			                defaultType: 'textfield',
			                defaults: {
			                    labelStyle: 'margin-left: 10px;'              
			                },  
				    		items: [{
				    		   	xtype : 'button',
								text : 'Enviar',
								handler : addDistribuirCeco,
								defaults : {
									anchor : '10',
									labelStyle : 'margin-left: 15px;'
								},
								width : 86
										 
				    		}]
				    	},{
				    		columnWidth: .5,
			                layout: 'form',
			                border: false,
			                labelWidth: 80,
			                defaultType: 'textfield',
			                defaults: {
			                    labelStyle: 'margin-left: 10px;'              
			                },  
				    		items: [{
				    		   	xtype : 'button',
								text : 'Cancelar',
								handler : showDistribuirCeco,
								defaults : {
									anchor : '10',
									labelStyle : 'margin-left: 15px;'
								},
								width : 86
				        }]
				    }]
				}]
			});
			dataContableCombo.loadData(allCecosArray);
			winCeco = Ext.getCmp('winCeco') || new Ext.Window({
				id: 'winCeco',					
				closeAction: 'destroy',
				width: '950',		
				items: [cecoFormPanel]
			});
			Ext.getCmp('comprobacionIdCeco').setValue(Ext.getCmp('numcomprobacion').getValue());
			Ext.getCmp('lineaComprobacionCeco').setValue(itemSelectedArray.expenseline);
			Ext.getCmp('importeComprobacionCeco').setValue(itemSelectedArray.wrbtr);
			Ext.getCmp('moneyCeco').setValue(itemSelectedArray.waers_xml);
			Ext.getCmp('clasegatosComprobacionCeco').setValue(itemSelectedArray.expenseclassdes);
			winCeco.show();
		}
		
		getSelectedRowIndex =  function(grid){
			var r = grid.getSelectionModel().getSelection();
			var s = grid.getStore();
			return s.indexOf(r[0]);
        }
		
		function mathRound(num){
			return Math.round(num*100)/100;
		}
		
		function addDistribuirCeco(){
			var cecoArrayCurrent = [];
			var cecoGrd = Ext.getCmp('cecoGrd').getStore();
			var sumPercentege = 0;
			cecoGrd.each(function(rec){
				if(rec.data.kostl != ""){
					sumPercentege = parseFloat(rec.data.cost_PERCENT)+sumPercentege;
					cecoArrayCurrent.push(rec.data);
				}
			});
			
			if(Math.round(sumPercentege) != 100 ){
				Ext.Msg.alert('Aviso','La distribución debe sumar 100%');
				return;
			}
			var detalleComprobacionesGrd = Ext.getCmp('detalleComprobacionesGrd').getStore();
			var record2;
			detalleComprobacionesGrd.each(function(rec){
				if(rec.data.select){
					record2 = rec;
					itemSelectedArray = arrayItemData[detalleComprobacionesGrd.indexOf(record2)];		 
					arrayItemData[detalleComprobacionesGrd.indexOf(record2)].costdist = [];	
					arrayItemData[detalleComprobacionesGrd.indexOf(record2)].costdist = cecoArrayCurrent;
				}
			});
			arrayItemData[detalleComprobacionesGrd.indexOf(record2)].kostl = cecoArrayCurrent[0].kostl;
			TopicRecord = Ext.data.Record.create([
			{name: 'expenseline', mapping: 'expenseline'},
			{name: 'expenseclass', mapping: 'expenseclass'},
			{name: 'expenseclassdes', mapping: 'expenseclassdes'},
			{name: 'expenquan', mapping: 'expenquan'},
			{name: 'wrbtr', mapping: 'wrbtr'},
			{name: 'waers_xml', mapping: 'waers_xml'},
			{name: 'mwskz_xml', mapping: 'mwskz_xml'},
			{name: 'paymet', mapping: 'paymet'},
			{name: 'bldat', mapping: 'bldat'},
			{name: 'bukrs', mapping: 'bukrs'},
			{name: 'kostl', mapping: 'kostl'},
			{name: 'uuid', mapping: 'uuid'},
			{name: 'rfc', mapping: 'rfc'},
			{name: 'numfact', mapping: 'numfact'},
			{name: 'xml', mapping: 'xml'},
			{name: 'pdf', mapping: 'pdf'},
			{name: 'xmlxstring', mapping: 'xmlxstring'},
			{name: 'pdfxstring', mapping: 'pdfxstring'},
			{name: 'dmbtr', mapping: 'dmbtr'},
			{name: 'available_DMBTR', mapping: 'available_DMBTR'}
			]);
			myNewRecord = new TopicRecord(arrayItemData[detalleComprobacionesGrd.indexOf(record2)]);
			detalleComprobacionDS[detalleComprobacionesGrd.indexOf(record2)] = myNewRecord;		
			Ext.getCmp('detalleComprobacionesGrd').getView().refresh();
			$.each(arrayItemData[detalleComprobacionesGrd.indexOf(record2)].costdist,function(index,val){

			});
			winCeco.close();
		}
		
		function equalsPercentage(){
			var cecoGrd = Ext.getCmp('cecoGrd').getStore();
			var count = 0;
			cecoGrd.each(function(rec){
				if(rec.data.kostl != ""){
					count++;
				}
			});
			var percentageEqual = 100/count;
			cecoGrd.each(function(rec){
				if(count>0){
					row = cecoGrd.getAt(count-1);
					row.set("cost_PERCENT", mathRound(percentageEqual));
					row.set("appdmbtr", mathRound((parseFloat(Ext.getCmp("importeComprobacionCeco").getValue())*percentageEqual)/100));
					count--;
				}
			});
		}

		function showDatowContables(){
			var detalleComprobacionesGrd = Ext.getCmp('detalleComprobacionesGrd').getStore();
			var record;
			detalleComprobacionesGrd.each(function(rec){
				if(rec.data.select){
					record = rec;
					itemSelectedArray = arrayItemData[detalleComprobacionesGrd.indexOf(record)];				 
					dataContableArray.loadData(itemSelectedArray.accdata);
				}
			});
				
			if(!record) {
				Ext.Msg.alert('Aviso','Por favor seleccione una comprobación del listado');
				return;
			}
			
			var datosContablesFormPanel = Ext.getCmp('datosContablesFormPanel') || new Ext.FormPanel({
					id: 'datosContablesFormPanel',
					bodyStyle:'padding:15px 0px 10px 5px',
				    anchor: '100% 100%',    
				    autoScroll:true,
				    autoHeight: true,
				    fileUpload: true,
				    frame: true,
				    border: false,
				    items: [{
				        layout:'column',
				        border: false,
				        anchor: '100%',             
				        items:[{
				    		columnWidth: .20,
			                layout: 'form',
			                border: false,
			                labelWidth: 70,
			                defaultType: 'textfield',
			                defaults: {			                	
			                    labelStyle: 'margin-left: 10px;'              
			                },  
				    		items: [{
				    			id:"solicitudIdData",
				    			fieldLabel: '# Solicitud', name: 'solicitudIdData', width: 100
				    		}]
				    	},{
				    		columnWidth: .15,
			                layout: 'form',
			                border: false,
			                labelWidth: 60,
			                defaultType: 'textfield',
			                defaults: {			                	
			                    labelStyle: 'margin-left: 10px;'              
			                },  
				    		items: [{
				    			id: "lineaData",
				    			fieldLabel: 'Línea', name: 'linea', width: 60
				    		}]
				    	},{
				    		columnWidth: .22,
			                layout: 'form',
			                border: false,
			                labelWidth: 100,
			                defaultType: 'textfield',
			                defaults: {			                	
			                    labelStyle: 'margin-left: 10px;'              
			                },  
				    		items: [{
				    			id: "clasegastoData",
				    			fieldLabel: 'Clase Gastos', name: 'clasegastoData', width: 80
				    		}]
				    	},{
				    		columnWidth: .21,
			                layout: 'form',
			                border: false,
			                labelWidth: 60,
			                defaultType: 'textfield',
			                defaults: {			                	
			                    labelStyle: 'margin-left: 10px;'              
			                },  
				    		items: [{
				    			id: "importeData",
				    			fieldLabel: 'Importe', name: 'importeData', width: 80
				    		}]
				    	},{
				    		columnWidth: .18,
			                layout: 'form',
			                border: false,
			                labelWidth: 10,
			                defaultType: 'textfield',
			                defaults: {			                	
			                    labelStyle: 'margin-left: 10px;'              
			                },  
				    		items: [{
				    			id: "moneyData",
				    			name: 'moneyData', width: 60
				    		}]
				    	},{
				    		columnWidth: .7,
				    		layout: 'form',
				    		defaults: {
				    			anchor: '-20',
								labelStyle: 'margin-left: 5px;'
					    	},
					   		items: [{	
					   			xtype: 'grid',
					   			anchor: '100% 100%',
					   		    id:'datosContablesGrd',
					   		    name:'datosContablesGrd',
					   		    autoScroll: true,
					   		    forceLayout:true,
					   		    viewConfig: {forceFit: true},		
								border: false,			
								stripeRows: true,
								singleCheckSelect: true,
								store: dataContableArray, 
								autoHeight: true,
								columnLines: true,
								autoScroll: true,
								loadMask: true,
								selModel: new Ext.grid.RowSelectionModel({singleSelect: true}),
					   		    columns:[
					   		        new Ext.grid.RowNumberer(),
				             		{header: 'Libro Mayor', width: 45, sortable: true, dataIndex: 'hkont', menuDisabled: true},
				             		{header: 'Importe', width: 45, sortable: true, dataIndex: 'wrbtr', menuDisabled: true},				             		 
				             		{header: 'Iva', width: 45, sortable: true, dataIndex: 'mwskz', menuDisabled: true},				             		 
				             		{header: 'Importe impuesto', width: 45, sortable: true, dataIndex: 'wmwst', menuDisabled: true},				             		 
				             		{header: 'Texto', width: 45, sortable: true, dataIndex: 'txt20', menuDisabled: true}
				            ]		   							   	    
					   	}]
				    }]
			    }]
			});
				
			var winDatos = Ext.getCmp('winDatos') || new Ext.Window({
				id: 'winDatos',					
				closeAction: 'destroy',
				width: '950',		
				items: [datosContablesFormPanel]
			});
			Ext.getCmp('solicitudIdData').setValue(Ext.getCmp('numcomprobacion').getValue());
			Ext.getCmp('lineaData').setValue(itemSelectedArray.expenseline);
			Ext.getCmp('importeData').setValue(itemSelectedArray.wrbtr);
			Ext.getCmp('moneyData').setValue(itemSelectedArray.waers_xml);
			Ext.getCmp('clasegastoData').setValue(itemSelectedArray.expenseclassdes);
			winDatos.show();
		}
		
		function enviarDataContable(){
			
		}
		
		function hideInputText(){
			Ext.getCmp('quantity').setVisible(true);									      	 
			Ext.getCmp('rfc').setVisible(false);
			Ext.getCmp('numfact').setVisible(false);
			Ext.getCmp('monto').setVisible(false);
	        Ext.getCmp('iva').setVisible(false);
			Ext.getCmp('metodopago').setVisible(true);
			Ext.getCmp('cargarxml').setVisible(true);
			Ext.getCmp('cargarpdf').setVisible(true);
		}
		
		function enviarAprobacion(){
			var comprobaciones = Ext.getCmp('comprobacionesGrd').getStore();
			var record;
			comprobaciones.each(function(rec){
				if(rec.data.select){
					record = rec;
				}
			});

			if(!record){
				Ext.Msg.alert('Aviso','Por favor seleccione una comprobación del listado');
				return;
			}
		}
		
		function saveComprobacion(){
			var frm = Ext.getCmp('listaComprobacionesForm').getForm();
			var flatWithoutCecos = 0;
            $.each(arrayItemData,function(i,item){
				if(item.costdist == ""){
					Ext.Msg.alert('Aviso','Debe agregar centros de coste');
					flatWithoutCecos = 1;
					return;
				}
			});
			
			if(flatWithoutCecos == 1){
				return;
			}
		
			if(response.data.headerExpenseDto.status === "02"){
				Ext.Msg.alert('Aviso','No es posible Guardar esta comprobación');
				return;
			}

			var detalleComprobacionesGrd = Ext.getCmp('detalleComprobacionesGrd').getStore();
			newErzet = response.data.headerExpenseDto.erzet.split(".");
			response.data.headerExpenseDto.erzet = newErzet[0];
			params = {};
			params.headerExpenseDto = response.data.headerExpenseDto;
			params.headerTextList = response.data.headerTextList;
			params.itemDataExpenseDtoList = arrayItemData;

			Ext.Ajax.request({
				url: contextrootpath + '/sessionStatus.action',
				success: function(result,request) {
					if(result.responseText.indexOf("login")>=0) {
						Ext.MessageBox.alert('Alerta', 'La sesión expiró, favor de ingresar nuevamente',relogin);					
					} else {
						response = [];
						arrayItemData = [];
						itemSelectedArray = [];
						arrayDataFromXml = [];
						arrayDocType = [];
						arrayCeco = [];
						record = [];
						contentsPdf = "";
						contentsXml = "";
						var waitBox = Ext.MessageBox.wait(bundle.getMsg('label.savingexpense'), 'Please Wait...');
						Ext.Ajax.request({
							url: contextrootpath + '/expense/saveExpenseData.action',
							method: 'POST',
							params: params,
							jsonData: params,
							beforeSend:function(){
								Ext.getCmp('enviarComprobacion').disable();
								Ext.getCmp('guardarComprobacion').disable();
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
								Ext.getCmp('enviarComprobacion').enable();
								Ext.getCmp('guardarComprobacion').enable();
								Ext.getCmp('winComprobacion').close();
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
								Ext.getCmp('enviarComprobacion').enable();
								Ext.getCmp('guardarComprobacion').enable();
							}
						});
					}
				},
				failure: utilities.expiredSession
			});
		}
	
		function sendComprobacion(){
			var frm = Ext.getCmp('listaComprobacionesForm').getForm();
			var flatWithoutCecos = 0;
			$.each(arrayItemData, function(i,item){
				if(item.costdist == ""){
					Ext.Msg.alert('Aviso','Debe agregar centros de coste');
					flatWithoutCecos = 1;
					return;
				}
			});
		
			if(flatWithoutCecos == 1){
				return;
			}
			
			response.data.headerExpenseDto.status = "02";
			var detalleComprobacionesGrd = Ext.getCmp('detalleComprobacionesGrd').getStore();
			newErzet = response.data.headerExpenseDto.erzet.split(".");
			response.data.headerExpenseDto.erzet = newErzet[0];
			params = {};
			params.headerExpenseDto = response.data.headerExpenseDto;
			params.headerTextList = response.data.headerTextList;
			params.itemDataExpenseDtoList = arrayItemData;
			
			Ext.Ajax.request({
				url: contextrootpath + '/sessionStatus.action',
				success: function(result,request) {
					if(result.responseText.indexOf("login")>=0) {
						Ext.MessageBox.alert('Alerta', 'La sesión expiró, favor de ingresar nuevamente',relogin);					
					} else {
						response = [];
						arrayItemData = [];
						itemSelectedArray = [];
						arrayDataFromXml = [];
						arrayDocType = [];
						arrayCeco = [];
						record = [];
						contentsPdf = "";
						contentsXml = "";
						var waitBox = Ext.MessageBox.wait(bundle.getMsg('label.sendingexpense'), 'Please Wait...');
						Ext.Ajax.request({
							url: contextrootpath + '/expense/saveExpenseData.action',
							method: 'POST',
							params: params,
							jsonData: params,
							beforeSend:function(){
								Ext.getCmp('enviarComprobacion').disable();
							    Ext.getCmp('guardarComprobacion').disable();
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
								Ext.getCmp('enviarComprobacion').enable();
							    Ext.getCmp('guardarComprobacion').enable();
								Ext.getCmp('winComprobacion').close();
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
								Ext.getCmp('enviarComprobacion').enable();
							    Ext.getCmp('guardarComprobacion').enable();
							}
						});
					}
				},
				failure: utilities.expiredSession
			});
		}
		
	}); //end bundle
	
}); //end onReady
