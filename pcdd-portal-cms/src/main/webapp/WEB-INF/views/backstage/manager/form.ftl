<!-- 新增或修改页 s-->
<div id="editor" class="modal fade" tabindex="-1" aria-labelledby="editorLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
    
	  <div class="alert alert-info">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		  &times;
		</button>
		<h3 class="modal-title " id="editorLabel" >
		  新增
		</h3>
	  </div>
	  
      <div class="modal-body">
        <!--添加的内容-->
	    <form class="form-horizontal sh-validate" id="form">
	      <div class="form-group">
		    <label   class="col-sm-3 control-label">管理员名字:</label>
		      <div class="col-sm-7">
			    <input type="text"  maxLength="50" class="form-control" id="name" name="name" data-bind="textinput:model.formEntity.name" 
			    placeholder="管理员名称">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">密码:</label>
		      <div class="col-sm-7">
			    <input type="password" required maxLength="20" class="form-control" id="passwd" name = "passwd" data-bind="textinput:model.formEntity.passwd"
			       placeholder="请输入密码">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label class="col-sm-3 control-label">权限:</label>
		      <div class="col-sm-7">
			    <ul id="permissions" name="permissions" class="ztree"></ul>
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">状态:</label>
		      <div class="col-sm-7">
			       <select  class="form-control" data-bind="value:model.formEntity.status">
		  			<option value="1">启用</option>
		  			<option value="0">禁用</option>
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