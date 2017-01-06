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
			},
			exception: utilities.handleException
		}
	})
});

var partidasAbiertasDS = new Ext.data.JsonStore({
	autoLoad: false,
	root: 'abierta',
	fields: [
		{name: 'augbl'},
		{name: 'augdt'},
		{name: 'belnr'},
		{name: 'blart'},
		{name: 'budat'},
		{name: 'dmbtr'},
		{name: 'gjahr'},
		{name: 'waers'},
		{name: 'xblnr'},
		{name: 'zfbdt'}
    ],
	proxy: new Ext.data.HttpProxy({ 
		url: contextrootpath + '/provider/getAccStatus.action', 
		method: 'GET',
		listeners: {
			load: function(thiz,o,options){
				console.log(o);
			},
			exception: utilities.handleException
		}
	})
});

var partidasCompensadasDS = new Ext.data.JsonStore({
	autoLoad: false,
	root: 'compensada',
	fields: [
		{name: 'augbl'},
		{name: 'augdt'},
		{name: 'belnr'},
		{name: 'blart'},
		{name: 'budat'},
		{name: 'dmbtr'},
		{name: 'gjahr'},
		{name: 'waers'},
		{name: 'xblnr'},
		{name: 'zfbdt'}
    ],
	proxy: new Ext.data.HttpProxy({ 
		url: contextrootpath + '/provider/getAccStatus.action', 
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
    var lang;
	var params = Ext.urlDecode(window.location.search.substring(1));
	lang = params.language ? params.language : 'es';
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side';
	
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
		
		var btnListarPartidas = new Ext.Button({
			text: bundle.getMsg('label.filter'),
			handler: listarPartidas,
			width: 100
		});
		
		var frmEstadoCuenta = new Ext.form.FormPanel({
			title: bundle.getMsg('label.accountstatement'),
			id: 'frmEstadoCuenta',
			method: 'POST',
			bodyStyle: 'padding: 10px 10px 0 10px;',
			frame: true,
			buttonAlign: 'left',
			defaults: {
				msgTarget: 'side',
				anchor: '100%',
				labelWidth: 150
			},
			border: false,
			renderTo: 'formEstadoCuenta',
			items:[{
				layout: 'form',
				border: false,
				items:[
				       razonSocialField,
					   btnListarPartidas
				]
			}]
		});
		
		var gridPartidaAbie = new Ext.grid.GridPanel({
			store: partidasAbiertasDS,
			title: 'Partidas abiertas',
			renderTo: 'gridPartidaAbie',
			columns: [new Ext.grid.RowNumberer(),
			          {header: bundle.getMsg('label.documentnumber'), width: 100, dataIndex: 'belnr', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.fiscalyear'), width: 100, dataIndex: 'gjahr', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.documentclass'), width: 100, dataIndex: 'blart', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.postingdate'), width: 100, dataIndex: 'budat', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.duedate'), width: 100, dataIndex: 'zfbdt', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.reference'), width: 100, dataIndex: 'xblnr', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.amount'), width: 100, dataIndex: 'dmbtr', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.currency'), width: 100, dataIndex: 'waers', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.compensationdoc'), width: 100, dataIndex: 'augbl', sortable : true, align: 'center'},
					  {header: bundle.getMsg('label.compensationdate'), width: 100, dataIndex: 'augdt', sortable : true, align: 'center'}	
			          ],
			viewConfig: {forceFit: true},		
			border: false,
			stripeRows: true,
			height: 250,
			columnLines: true,
			autoScroll: true,
			loadMask: true,
			selModel: new Ext.grid.RowSelectionModel({singleSelect: true})
		});
		
		var gridPartidaComp = new Ext.grid.GridPanel({
			store: partidasCompensadasDS,
			title: 'Partidas compensadas',
			renderTo: 'gridPartidaComp',
			columns: [new Ext.grid.RowNumberer(),
			          {header: bundle.getMsg('label.documentnumber'), width: 100, dataIndex: 'belnr', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.fiscalyear'), width: 100, dataIndex: 'gjahr', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.documentclass'), width: 100, dataIndex: 'blart', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.postingdate'), width: 100, dataIndex: 'budat', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.reference'), width: 100, dataIndex: 'xblnr', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.amount'), width: 100, dataIndex: 'dmbtr', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.currency'), width: 100, dataIndex: 'waers', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.compensationdoc'), width: 100, dataIndex: 'augbl', sortable : true, align: 'center'},
					  {header: bundle.getMsg('label.compensationdate'), width: 100, dataIndex: 'augdt', sortable : true, align: 'center'},
					  {header: bundle.getMsg('label.duedate'), width: 100, dataIndex: 'zfbdt', sortable : true, align: 'center'}
			          ],
			viewConfig: {forceFit: true},		
			border: false,
			stripeRows: true,
			height: 250,
			columnLines: true,
			autoScroll: true,
			loadMask: true,
			selModel: new Ext.grid.RowSelectionModel({singleSelect: true})
		});
		
		function listarPartidas() {
			var frm = Ext.getCmp('frmEstadoCuenta').getForm();	
			if(frm.isValid()){
				partidasAbiertasDS.removeAll();
				partidasCompensadasDS.removeAll();
				partidasAbiertasDS.load({
					params:{
						cmbRazonSocial: Ext.getCmp('cmbRazonSocial').getValue()
					}
				});
				partidasCompensadasDS.load({
					params:{
						cmbRazonSocial: Ext.getCmp('cmbRazonSocial').getValue()
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
		
	}); //end bundle
	
}); //end onReady
