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
		  <!--
		  <div class="form-group">
		    <label for="lastname" class="col-sm-3 control-label">等级图片<span class="form-required">*</span></label>
			  <div class="col-sm-9">
				<div class="pic" >
				  <div style="display:inline" id="productPhotoPics" data-bind="foreach: {data: model.formEntity.levelIcon(), as: 'item'}">
				    <span ><img  data-bind="attr:{src:$root.fmt.imgUrl(item)}" /><em><a href="javascript:void(0)"><img src="<@full_path path='img/close.png' />" width="20" height="20" id="close" data-bind="click:$root.removeImg.bind($root,1,$index())"/></a></em></span>
				  </div>
				  <span data-bind="visible:model.formEntity.levelIcon().length < 1" >
				    <div id="thinThumPhotoPics" class="addPic">
				      <input type="file" onchange="viewModel.upload(this, 'backstage/banner/',1)" accept=".jpg,.jpeg,.bmp,.png" id="file_sequence_id_1" class="fileInput" />
				    </div>
				  </span>
				</div>
			  </div>
		  </div>
		  -->
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">等级名称<span class="form-required">*</span></label>
		      <div class="col-sm-6">
			    <input type="text" disabled="disabled" maxLength="30" class="form-control" id="levelName" name="levelName" data-bind="textinput:model.formEntity.levelName" 
			    placeholder="请输入等级名称">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">充值条件<span class="form-required">*</span></label>
		      <div class="col-sm-6">
			    <input type="text" required maxLength="10" class="form-control" id="rechargeFee" name = "rechargeFee" data-bind="textinput:model.formEntity.rechargeFee"
			       placeholder="请输入充值条件">
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