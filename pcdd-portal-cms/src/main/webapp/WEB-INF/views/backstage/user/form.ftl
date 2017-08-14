<!-- 新增或修改页 s-->
<div id="editor" class="modal fade" tabindex="-1" aria-labelledby="editorLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
    
	  <div class="alert alert-info">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		  &times;
		</button>
		<h3 class="modal-title " id="editorLabel" data-bind="text:model.formEntity.id()?'编辑':'新增'">
		</h3>
	  </div>
	  
      <div class="modal-body">
        <!--添加的内容-->
	    <form class="form-horizontal sh-validate" id="form">
	    
	     <div class="form-group">
		    <label for="lastname" class="col-sm-3 control-label">头像</label>
			  <div class="col-sm-7">
				<div class="pic" >
				  <div style="display:inline" id="productPhotoPics" data-bind="foreach: {data: model.formEntity.userPhoto(), as: 'item'}">
				    <span ><img  data-bind="attr:{src:$root.fmt.imgUrl(item)}" /><em><a href="javascript:void(0)"><img src="<@full_path path='img/close.png' />" width="20" height="20" id="close" data-bind="click:$root.removeImg.bind($root,1,$index())"/></a></em></span>
				  </div>
				  <span data-bind="visible:model.formEntity.userPhoto().length < 1" >
				    <div id="thinThumPhotoPics" class="addPic">
				      <input type="file" onchange="viewModel.upload(this, 'backstage/user/',1)" accept=".jpg,.jpeg,.bmp,.png" id="file_sequence_id_1" class="fileInput" />
				    </div>
				  </span>
				</div>
			  </div>
		  </div>
		  
	      <div class="form-group">
		    <label  class="col-md-3 control-label">账号<span class="form-required">*</span>:</label>
		      <div class="col-md-7">
			    <input type="text"  maxLength="50" required class="form-control" id="account" name = "account" data-bind="textinput:model.formEntity.account,attr:{readonly:model.formEntity.id()>0?'readonly':false}"
			       placeholder="请输入账号">
			  </div>
		  </div>
		  
	      <div class="form-group" data-bind="visible:!model.formEntity.id()">
		    <label  class="col-md-3 control-label">密码<span class="form-required">*</span>:</label>
		      <div class="col-md-7">
			    <input type="password" required maxLength="50" class="form-control" id="password" name = "password" data-bind="textinput:model.formEntity.password"
			       placeholder="请输入密码">
			  </div>
		  </div>
		  
	      <div class="form-group">
		    <label  class="col-md-3 control-label">昵称:</label>
		      <div class="col-md-7">
			    <input type="text"   maxLength="50" class="form-control" id="nickName" name = "nickName" data-bind="textinput:model.formEntity.nickName"
			       placeholder="请输入昵称">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-md-3 control-label">性别:</label>
		      <div class="col-md-7">
			    <label class="checkbox-inline">
						<input type="radio" name="sex"  value="1" data-bind="checked:model.formEntity.sex"> 男
					</label>
					<label class="checkbox-inline">
						<input type="radio" name="sex"  value="2" data-bind="checked:model.formEntity.sex"> 女 
					</label>
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-md-3 control-label">等级:</label>
		      <div class="col-md-7">
			    <select class="form-control" required id="level" name="level" data-bind="value:model.formEntity.level,options:model.levelOptions,optionsText:'levelName',optionsValue:'id',event:{focus:function(e){ if(!viewModel.model.levelOptions().length)Lobibox.notify('info',{msg:'请选配置等级数据'});}}">
			    </select>
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-md-3 control-label">手机号:</label>
		      <div class="col-md-7">
			    <input type="text"   maxLength="50" class="form-control" id="mobile" name = "mobile" data-bind="textinput:model.formEntity.mobile"
			       placeholder="请输入手机号">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-md-3 control-label">状态:</label>
		      <div class="col-md-7">
			    <select class="form-control" id="status" name="status" data-bind="value:model.formEntity.status">
			    	<option value="1">正常</option>
			    	<option value="0">拉黑</option>
			    </select>
			  </div>
		  </div>
		  
	    </form>
      </div>
	 
	   <div class="modal-footer" style="border-top:0;">
	     <div class="row"  >
	       <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 col-xs-offset-4 col-sm-offset-4 col-lg-offset-4 col-md-offset-4" >
		     <button type="button" class="btn btn-default center-block" data-dismiss="modal">
			    关闭
			 </button>
		   </div>
		   <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" >
		     <button type="button" class="btn btn-primary center-block" data-bind="click:$root.beforeSubmit.bind($root)">
			   <span class="glyphicon glyphicon-floppy-disk" style="margin-right: .8em;"></span>保存
			 </button>
		   </div>
	     </div>
	   </div>
	 
     </div><!-- /.modal-content -->
   </div><!-- /.modal -->
</div>			
<!-- 新增或修改数据e -->