<!-- 新增或修改页 s-->
<div id="editor" class="modal fade" style="width: 100%;" tabindex="-1" aria-labelledby="editorLabel" aria-hidden="true">
  <div class="modal-dialog" style="width: 80%;">
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
		    <label  class="col-sm-2 control-label">标题</label>
		      <div class="col-sm-6">
			    <input type="text" maxLength="300" class="form-control" id="title" name="title" data-bind="textinput:model.formEntity.title" 
			    placeholder="请输入标题">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-2 control-label">内容<span class="form-required">*</span></label>
		      <div class="col-sm-9">
			    <textarea rows="5" style="max-width:100%;" required maxLength="500" class="form-control" id="content" name = "content" data-bind="textinput:model.formEntity.content">
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