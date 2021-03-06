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
		    <label  class="col-sm-3 control-label">版本号<span class="form-required">*</span></label>
		      <div class="col-sm-6">
			    <input type="text" required maxLength="30" class="form-control" id="versionNo" name="versionNo" data-bind="textinput:model.formEntity.versionNo" 
			    placeholder="请输入版本号">
			  </div>
		  </div>
		  
		  <div class="form-group">
            <label  class="col-sm-3 control-label">更新内容<span class="form-required">*</span></label>
              <div class="col-sm-6">
                <textarea rows="5" style="max-width:100%;" required maxLength="200" class="form-control" id="updateContent" name = "updateContent" data-bind="textinput:model.formEntity.updateContent">
                </textarea>
              </div>
          </div>
          
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">下载地址<span class="form-required">*</span></label>
		      <div class="col-sm-6">
			    <input type="url" required maxLength="200" class="form-control" id="versionUrl" name="versionUrl" data-bind="textinput:model.formEntity.versionUrl" 
			    placeholder="请输入下载地址">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">更新类型</label>
		      <div class="col-sm-6">
			    <select class="form-control" id="status" name="status" data-bind="value:model.formEntity.status">
                    <option value="1">强制更新</option>
                    <option value="0">推荐更新</option>
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