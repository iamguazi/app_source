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
		    <label for="firstname" class="col-sm-3 control-label center-block">礼物类型:</label>
		      <div class="col-sm-9">
			    <select class="form-control" id="giftType" name="giftType" data-bind="value:model.formEntity.giftType">
			    	<option value="1">普通</option>
			    	<option value="2">连发</option>
			    	<option value="3">红包</option>
			    </select>
			  </div>
		  </div>
		  
	      <div class="form-group">
		    <label  class="col-sm-3 control-label">礼物名称:</label>
		      <div class="col-sm-9">
			    <input type="text"  maxLength="50" class="form-control" id="giftName" name="giftName" data-bind="textinput:model.formEntity.giftName" 
			    placeholder="请输入礼物名称">
			  </div>
		  </div>
		  
	      <div class="form-group">
		    <label  class="col-sm-3 control-label">所需钻石:</label>
		      <div class="col-sm-9">
			    <input type="text"  maxLength="30" class="form-control" id="needMoney" name="needMoney" data-bind="textinput:model.formEntity.needMoney" 
			    placeholder="请输入所需钻石">
			  </div>
		  </div>
		  
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">序号:</label>
		      <div class="col-sm-9">
			    <input type="text" required maxLength="10" class="form-control" id="orderNo" name = "orderNo" data-bind="textinput:model.formEntity.orderNo"
			       placeholder="请输入序号">
			  </div>
		  </div>
		
		 <div class="form-group">
		    <label  class="col-sm-3 control-label">礼物编号:</label>
		      <div class="col-sm-9">
			    <input type="text" required maxLength="10" class="form-control" id="giftKey" name = "giftKey" data-bind="textinput:model.formEntity.giftKey"
			       placeholder="请输入礼物编号">
			  </div>
		  </div>
		  
         <div class="form-group">
		    <label for="firstname" class="col-sm-3 control-label center-block">状态:</label>
		      <div class="col-sm-9">
			    <select class="form-control" id="status" name="status" data-bind="value:model.formEntity.status">
			    	<option value="1">启用</option>
			    	<option value="0">禁用</option>
			    </select>
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="lastname" class="col-sm-3 control-label">广告图片</label>
			  <div class="col-sm-9">
				<div class="pic" >
				  <div style="display:inline" id="productPhotoPics" data-bind="foreach: {data: model.formEntity.giftPhoto(), as: 'item'}">
				    <span ><img  data-bind="attr:{src:$root.fmt.imgUrl(item)}" /><em><a href="javascript:void(0)"><img src="<@full_path path='img/close.png' />" width="20" height="20" id="close" data-bind="click:$root.removeImg.bind($root,1,$index())"/></a></em></span>
				  </div>
				  <span data-bind="visible:model.formEntity.giftPhoto().length < 1" >
				    <div id="thinThumPhotoPics" class="addPic">
				      <input type="file" onchange="viewModel.upload(this, 'backstage/gift/',1)" accept=".jpg,.jpeg,.bmp,.png" id="file_sequence_id_1" class="fileInput" />
				    </div>
				  </span>
				</div>
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