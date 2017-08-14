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
		    <label  class="col-sm-3 control-label">商户id<span class="form-required">*</span></label>
		      <div class="col-sm-6">
			    <input type="text" required maxLength="50" class="form-control" id="mchId" name="mchId" data-bind="textinput:model.formEntity.mchId" 
			    placeholder="请输入商户id">
			  </div>
		  </div>
		  
		  <div class="form-group">
            <label  class="col-sm-3 control-label">商户key<span class="form-required">*</span></label>
              <div class="col-sm-6">
                <textarea rows="5" style="max-width:100%;" required maxLength="120" class="form-control" id="mchKey" name = "mchKey" data-bind="textinput:model.formEntity.mchKey">
                </textarea>
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