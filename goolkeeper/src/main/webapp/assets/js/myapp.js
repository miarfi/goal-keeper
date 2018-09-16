$(function(){	
	
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
	default: 
		$('#home').addClass('active');
		$('#a_'+menu).addClass('active');
	break;
	}


var products =[
	
	['1', 'ABC'],
	['2', 'CYX'],
	['3', 'PQR'],
	['4', 'MNO']
]

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

});