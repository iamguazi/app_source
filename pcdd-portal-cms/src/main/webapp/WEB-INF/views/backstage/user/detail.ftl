<!-- 新增或修改页 s-->
<div id="detail" class="modal fade" tabindex="-1" aria-labelledby="detailLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
    
	  <div class="alert alert-info">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		  &times;
		</button>
		<h3 class="modal-title " id="detailLabel" >
		 详情
		</h3>
	  </div>
	  
      <div class="modal-body">
        <!--添加的内容-->
	    <form class="form-horizontal" id="detailForm">
	    
	     <div class="form-group">
		    <label for="lastname" class="col-sm-3 control-label">头像</label>
			  <div class="col-sm-7">
				<div class="pic" >
				  <div style="display:inline"  data-bind="foreach: {data: model.formEntity.detailUserPhoto(), as: 'item'}">
				    <span ><img  data-bind="attr:{src:$root.fmt.img4Null(item)},click:$root.fmt.biggerShowImg.bind($root, item)" /></span>
				  </div>
				</div>
			  </div>
		  </div>
		  
	      <div class="form-group">
		    <label  class="col-md-3 control-label">账号:</label>
		      <div class="col-md-7">
			    <input type="text"  disabled maxLength="50" required class="form-control" id="account" name = "account" data-bind="textinput:model.formEntity.account"
			       placeholder="请输入账号">
			  </div>
		  </div>
		  
	      <div class="form-group">
		    <label  class="col-md-3 control-label">昵称:</label>
		      <div class="col-md-7">
			    <input type="text" disabled  maxLength="50" class="form-control" id="nickName" name = "nickName" data-bind="textinput:model.formEntity.nickName"
			       placeholder="请输入昵称">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-md-3 control-label">性别:</label>
		      <div class="col-md-7">
			    <label class="checkbox-inline">
						<input disabled type="radio" name="sex"  value="1" data-bind="checked:model.formEntity.sex"> 男
					</label>
					<label class="checkbox-inline">
						<input disabled type="radio" name="sex"  value="2" data-bind="checked:model.formEntity.sex"> 女 
					</label>
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-md-3 control-label">等级:</label>
		      <div class="col-md-7">
			    <input type="text" disabled  maxLength="50" class="form-control" id="levelName" name = "levelName" data-bind="textinput:model.formEntity.levelName"
			       placeholder="请输入等级">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-md-3 control-label">手机号:</label>
		      <div class="col-md-7">
			    <input type="text" disabled  maxLength="50" class="form-control" id="mobile" name = "mobile" data-bind="textinput:model.formEntity.mobile"
			       placeholder="请输入手机号">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-md-3 control-label">状态:</label>
		      <div class="col-md-7">
			    <select disabled class="form-control" id="status" name="status" data-bind="value:model.formEntity.status">
			    	<option value="1">正常</option>
			    	<option value="0">拉黑</option>
			    </select>
			  </div>
		  </div>
		  
	    </form>
      </div>
	 
	   <div class="modal-footer" style="border-top:0;">
	     <div class="row"  >
	       <div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 col-xs-offset-3 col-sm-offset-3 col-lg-offset-3 col-md-offset-3" >
		     <button type="button" class="btn btn-default center-block btn-block" data-dismiss="modal">
			   	返回
			 </button>
		   </div>
	     </div>
	   </div>
	 
     </div><!-- /.modal-content -->
   </div><!-- /.modal -->
</div>			
<!-- 新增或修改数据e -->