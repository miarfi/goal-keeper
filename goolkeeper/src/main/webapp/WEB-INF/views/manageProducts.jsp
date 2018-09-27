<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div class="container">	
	<div class="row">	
	
	<c:if test="${not empty message}">	
		<!-- <div class="row">			
			<div class="col-xs-12 col-md-offset-2 col-md-8">			
				<div class="alert alert-info fade in">${message}</div>				
			</div>
		</div>-->
		<div class="col-xs-12">
			<div class="alert alert-sucess alert-dismissible">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				${message}
			</div>
		</div>
	</c:if>			
				
		<div class="offset-md-2 col-md-8">
			<div class="panel-primary panel">
				
				<div class="panel-heading">
					<h4>Product Management</h4>
				</div>
								
				<div class="panel-body">
					<sf:form class="form-horizonal" 
					modelAttribute="product" 
					action="${contextRoot}/manage/products" 
					method="POST"
					enctype="multipart/form-data">

						<div class="form-group">

							<label class="control-label col-md-4" for="name">Name:</label>
							<div class="col-md-8">
								<sf:input type="text" id="name" path="name" class="form-control" placeholder="Product Name" /> 
								<!-- <em class="help-block">Please enter product name</em> -->
								<sf:errors path="name" cssClass="help-block" element="em"/> 
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="brand">Brand</label>
							<div class="col-md-8">
								<sf:input type="text" id="brand" path="brand" class="form-control" placeholder="Brand Name" /> 
								<!-- <em class="help-block">Please enter brand name</em> -->
								<sf:errors path="brand" cssClass="help-block" element="em"/>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="description">Description</label>
							<div class="col-md-8">
								<sf:textarea id="description" path="description" class="form-control"
									placeholder="Enter your description here!" /> 
								<sf:errors path="description" cssClass="help-block" element="em"/>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="unitPrice">Unit Price</label>
							<div class="col-md-8">
								<sf:input type="number" id="unitPrice" path="unitPrice" class="form-control"
									placeholder="Enter Unit Price" />
								<sf:errors path="unitPrice" cssClass="help-block" element="em"/>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="quantity">Quantity</label>
							<div class="col-md-8">
								<sf:input type="number" id="quantity" path="quantity" class="form-control"
									placeholder="Enter Quantity" />
								<sf:errors path="quantity" cssClass="help-block" element="em"/> 
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="file">Upload a file</label>
							<div class="col-md-8">
								<sf:input type="file" id="file" path="file" class="form-control"/>
								<sf:errors path="file" cssClass="help-block" element="em"/> 
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="categoryId">Category</label>
							<div class="col-md-8">
								<sf:select path="categoryId" id="categoryId" items="${categories}" itemLabel="name" itemValue="id" class="form-control"/>
							
								 <div class="text-right">
									<br/>			
									<sf:hidden path="id"/>
									<sf:hidden path="code"/>
									<sf:hidden path="supplierId"/>
									<sf:hidden path="active"/>	
									<!-- purchases, views -->		
									<c:if test="${product.id == 0 }">
										<div class="text-right">
										<br/>										
										<button type="button" class="btn btn-warning btn-xs" data-toggle="modal" data-target="#myCategoryModal">Add New Category</button>
										</div>
									</c:if>	
								</div>					
							</div>	
						</div>
						
						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<input type="submit" name="submit" value="Save" class="btn btn-primary"/>

							</div>
						</div>
					</sf:form>

				</div>
			</div>
		</div>
	</div>


	<div class="row">

		<div class="col-xs-12 col-md-12">
			<h3>Available Products</h3>
			<hr/>
		</div>
					
		<div class="col-xs-12 col-md-12">
			<div style="overflow:auto">				
			<table id="adminProductsTable" class="table table-striped table-bordered">										
				<thead>
				
					<tr>
						<th>Id</th>	
						<th>&#160;</th>
						<th>Name</th>
						<th>Quantity</th>
						<th>Unit Price</th>
						<th>Active</th>	
						<th>Edit</th>														
					</tr>
				
				</thead>
				
<!-- 				<tbody> -->
<!-- 					<tr> -->
<!-- 						<td>4</td>	 -->
<!-- 						<td> -->
<%-- 							<img class="adminDataTableImg" src="${contextRoot }/resources/images/PRDPQR123WGTX.jpg" alt="Google Pixel" /> --%>
<!-- 						</td> -->
<!-- 						<td>Google Pixel</td> -->
<!-- 						<td>3</td> -->
<!-- 						<td>&#8377; 54000.00/-</td> -->
<!-- 						<td> -->
<!-- 							toggle switch -->
<!--  							<label class="switch"> --> 
<!-- 								<input type="checkbox" checked="checked" value="4"/> -->
<!--  								<div class="slider"></div> --> 
<!--  							</label> --> 
<!-- 						</td>	 -->
<!-- 						<td> -->
<%-- 							<a href="${contextRoot }/manage/4/product" class="btn btn-warning"> --%>
<!-- 								<span class="glyphicon glyphicon-pencil"></span> -->
<!-- 							</a>	 -->
<!-- 						</td>														 -->
<!-- 					</tr>			 -->
<!-- 				</tbody> -->
				
				<tfoot>
				
					<tr>
						<th>Id</th>	
						<th>&#160;</th>
						<th>Name</th>
						<th>Quantity</th>
						<th>Unit Price</th>
						<th>Active</th>	
						<th>Edit</th>						
					</tr>
				
				</tfoot>
			</table>
			</div>
		</div>			
	</div>

	<div class="row">
		<div class="modal fade" id="myCategoryModal" role="dialog">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
				    <!--Modal Header -->
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span>&times;</span>						
						</button>
						<h4 class="modal-title">Add New Category</h4>
					</div>
					<!--Modal Body -->
					<div class="modal-body">
						<sf:form 
						id="categoryForm"
						modelAttribute="category" 
						action="${contextRoot}/manage/category" 
						method="POST"
						class="form-group">
							
							<div class="form-group">
								<label for="category_name" class="control-label col-md-4">Category Name</label>
								<div class="col-md-8">
									<sf:input path="name" id="category_name" type="text" class="form-control"/>
								</div>
							</div>
							<div class="form-group">
								<label for="category_description" class="control-label col-md-4">Category Name</label>
								<div class="col-md-8">
									<sf:textarea path="description" id="category_description" rows="5" class="form-control"/>
								</div>
							</div>
							<div class="form-group">								
								<div class="col-offset-4 col-md-8">
									<input type="submit" value="Add Category" class="btn btn-primary">
								</div>
							</div>
						</sf:form>
					</div>
				</div>
			</div>
		</div>
	</div>


</div>