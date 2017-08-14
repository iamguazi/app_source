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
		    <label  class="col-sm-3 control-label">Key值:</label>
		      <div class="col-sm-7" >
		          <input type="text" required maxLength="100" class="form-control" id="paramsValue" name="paramsValue" data-bind="textinput:model.formEntity.paramsValue" 
                placeholder="请输入Key值">
			  </div>
		  </div>
		  
	      <div class="form-group">
		    <label  class="col-sm-3 control-label">备注:</label>
		      <div class="col-sm-7">
			    <input type="text" disabled="disabled" maxLength="100" class="form-control" id="paramsRemarks" name="paramsRemarks" data-bind="textinput:model.formEntity.paramsRemarks" 
			    placeholder="请输入备注内容">
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