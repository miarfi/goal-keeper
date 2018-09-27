$(function(){	
	
	
/*-------------------------------------------------------------*/
//Header menu
	switch(menu){
	
	case 'About': 
		$('#about').addClass('active');
		break;
	case 'Contact': 
		$('#contact').addClass('active');
		break;
	case 'All Products': 
		$('#listProducts').addClass('active');
		break;		
	case 'Manage Products': 
		$('#manageProducts').addClass('active');
		break;	
	default: 
		$('#home').addClass('active');
		$('#a_'+menu).addClass('active');
	break;
	}


//var products =[
//	
//	['1', 'ABC'],
//	['2', 'CYX'],
//	['3', 'PQR'],
//	['4', 'MNO']
//]


/*-------------------------------------------------------------*/
//Product data table
var $table = $('#productListTable');

if ($table.length){

	var jsonUrl ='';
	if (window.categoryId ==''){
		jsonUrl = window.contextRoot + '/json/data/all/products'; 	
	}
	else {
		jsonUrl = window.contextRoot + '/json/data/category/'+window.categoryId+'/products';
	}
	
	
	
	//console.log('Inside the table');
	$table.DataTable({
		lengthMenu: [[3,5,10,-1],['3 records', '5 records', '10 records', 'All']],		
		pageLength: 5,
		//data: products
		ajax: {
			url: jsonUrl,
			dataSrc: ''
		},
		columns:[
			{
				data: 'code',	
				mRender: function(data, type, row){
					var str ='';
					str += '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg">';									
					return str;
				}
			},
			{
				data: 'name'				
			},
			{
				data: 'brand'
			},
			{
				data: 'unitPrice',
				mRender: function(data, type, row){
					return '&#8377; '+data
				}
			},
			{
				data: 'quantity',
				mRender: function(data, type, row){
					
					if (data < 1){
						return '<span style:"color:red">Out of stock</span>'; 
					}
					return data;
				}
			},
			{
				data: 'id',
				mRender: function(data, type, row){
					var str ='';
					str += '<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary">'
					+'<span class="glyphicon glyphicon-eye-open">View</span></a> &#160;';
					
					if (row.quantity < 1) {
						str += '<a href="javascript:void(0)" class="btn btn-success disabled">'
							+'<span class="glyphicon glyphicon-shopping-cart">Add to Cart</span></a>';
						
					} else {
						str += '<a href="'+window.contextRoot+'/cart/add/'+data+'/product" class="btn btn-success">'
						+'<span class="glyphicon glyphicon-shopping-cart">Add to Cart</span></a>';
					}
					
					return str;
				}
			}
		]
	})
}


/*-------------------------------------------------------------*/
//dismissing the alert after 3 seconds
var $alert = $('.alert');
if($alert.length) {
	setTimeout(function() {
    	$alert.fadeOut('slow');
	   }, 5000
	);		
}


/*-------------------------------------------------------------*/
//Bootbox alert
//$('.switch input[type="checkbox"]').on('change', function(){
//	var checkbox= $(this);
//	var checked = checkbox.prop('checked');
//	var dMsg = (checked)? 'You want to activate the product':
//		'You want to deactivate the product?';
//	var value = checkbox.prop('value');
//	
//	bootbox.confirm({
//		size: 'medium',
//		title: 'Product Activition & Deactivation',
//		message: dMsg,
//		callback: function(confirmed) {
//			if (confirmed) {
//				console.log(value);
//				bootbox.alert({
//					size: 'medium',
//					title: 'Information',
//					message: 'Your are going to perform operation on product'
//				});
//			} else {
//				checkbox.prop('checked', !checked);
//			}			
//		}
//	});
//});


/*-------------------------------------------------------------*/
//Admin data table
var $adminProductsTable = $('#adminProductsTable');

console.log('==> Js.adminProductTable');
console.log($adminProductsTable.length);

if ($adminProductsTable.length){

	var jsonUrl ='';
	console.log('==> categoryId '+window.categoryId);
//	if (window.categoryId ==''){
		jsonUrl = window.contextRoot + '/json/data/admin/all/products'; 	
//	}
//	else {
//		jsonUrl = window.contextRoot + '/json/data/category/'+window.categoryId+'/products';
//	}
	
	
	
	console.log('==> jsonUrl '+jsonUrl);
	$adminProductsTable.DataTable({
		lengthMenu: [[10,30,50,-1],['10 records', '30 records', '50 records', 'All']],		
		pageLength: 30,
		//data: products
		ajax: {
			url: jsonUrl,
			dataSrc: ''
		},
		columns:[
			{	
				data: 'id'
			},
			{
				data: 'code',	
				mRender: function(data, type, row){
					var str ='';
					str += '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="adminDataTableImg">';									
					return str;
				}
			},
			{
				data: 'name'				
			},
			{
				data: 'quantity',
				mRender: function(data, type, row){
					
					if (data < 1){
						return '<span style:"color:red">Out of stock</span>'; 
					}
					return data;
				}
			},
			{
				data: 'unitPrice',
				mRender: function(data, type, row){
					return '&#8377; '+data
				}
			},

			{
				data: 'active',
				mRender: function(data, type, row){
					var str ='';
					if (data) {
						str += '<label class="switch">'
							 + '<input type="checkbox" checked="checked" value="'+row.id+'"/>'
						     + '<div class="slider"></div></label>';	
					} else {
						str += '<label class="switch">'
							 + '<input type="checkbox" value="'+row.id+'"/>'
						     + '<div class="slider"></div></label>';	
					}
					return str;
				}
					
			},
			{
				data: 'id',
				mRender: function(data, type, row){
					var str ='';
					str += '<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-primary">'
					+'<span class="glyphicon glyphicon-eye-open">Edit</span></a> &#160;';					
					
					return str;
				}
			}
		],
		//Bootbox alert
		initComplete: function(){
			var api = this.api();
			api.$('.switch input[type="checkbox"]').on('change', function(){
				var checkbox= $(this);
				var checked = checkbox.prop('checked');
				var dMsg = (checked)? 'You want to activate the product':
					'You want to deactivate the product?';
				var value = checkbox.prop('value');
				
				bootbox.confirm({
					size: 'medium',
					title: 'Product Activition & Deactivation',
					message: dMsg,
					callback: function(confirmed) {
						if (confirmed) {
							console.log(value);
							
							//Post update product
							var activationUrl = window.contextRoot + '/manage/product/'+ value + '/activation';
							
							$.post(activationUrl, function(data){
								bootbox.alert({
									size: 'medium',
									title: 'Information',
									message: data
								});
								
							})
							

						} else {
							checkbox.prop('checked', !checked);
						}			
					}
				});
			});
			
		}
	})
}



//Validate code for category
var $categoryForm = $('#categoryForm');

if ($categoryForm.length) {
	$categoryForm.validate({
		rules : {
			name : {
				required: true,
				minlength: 2				
			},
			description : {
				required: true				
			}			
		},
		messages: {
			name: {
				required : 'Please add the category name',
				minlength: 'the category name should not be less then 2 characters'
			},
			description: {
				required : 'Please add the category description'
			},
		},
			errorElement: 'em',
			errorPlacement: function(error, element){
				//add the class of help-block
				error.addClass('help-block');
				error.insertAfter(element);
			}
	});		
}




});