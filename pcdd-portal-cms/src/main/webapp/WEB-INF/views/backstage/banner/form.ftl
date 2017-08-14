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
		    <label for="firstname" class="col-sm-3 control-label center-block">广告位置<span class="form-required">*</span></label>
		      <div class="col-sm-7">
			    <select class="form-control" id="bannerPlace" name="bannerPlace" data-bind="value:model.formEntity.bannerPlace">
			    	<option value="1">app</option>
			    	<option value="2">网站首页</option>
			    </select>
			  </div>
		  </div>
		  
	      <div class="form-group">
		    <label  class="col-sm-3 control-label">广告名称<span class="form-required">*</span></label>
		      <div class="col-sm-7">
			    <input type="text"  maxLength="30" class="form-control" id="bannerName" name="bannerName" data-bind="textinput:model.formEntity.bannerName" 
			    placeholder="请输入广告名称">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">序号<span class="form-required">*</span></label>
		      <div class="col-sm-7">
			    <input type="text" required maxLength="10" class="form-control" id="bannerOrder" name = "bannerOrder" data-bind="textinput:model.formEntity.bannerOrder"
			       placeholder="请输入序号">
			  </div>
		  </div>
		
         <div class="form-group">
		    <label for="firstname" class="col-sm-3 control-label center-block">状态<span class="form-required">*</span></label>
		      <div class="col-sm-7">
			    <select class="form-control" id="status" name="status" data-bind="value:model.formEntity.status">
			    	<option value="1">启用</option>
			    	<option value="0">禁用</option>
			    </select>
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="firstname" class="col-sm-3 control-label center-block">是否跳转<span class="form-required">*</span></label>
		      <div class="col-sm-7">
			    	<label class="checkbox-inline">
						<input type="radio" name="isGo"  value="1" data-bind="checked:model.formEntity.isGo,click:$root.showGoType.bind($root,1)"> 是
					</label>
					<label class="checkbox-inline">
						<input type="radio" name="isGo"  value="0" data-bind="checked:model.formEntity.isGo,click:$root.showGoType.bind($root,0)"> 否 
					</label>
			  </div>
		  </div>
		  
		  <div class="form-group" data-bind="visible:model.formEntity.isGo()==1">
		    <label for="firstname" class="col-sm-3 control-label center-block">地址</label>
		      <div class="col-sm-7">
			    <input type="text"  maxLength="200" class="form-control" id="url" name = "url" data-bind="textinput:model.formEntity.url"
			       placeholder="请输入跳转地址">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="lastname" class="col-sm-3 control-label">广告图片</label>
			  <div class="col-sm-7">
				<div class="pic" >
				  <div style="display:inline" id="productPhotoPics" data-bind="foreach: {data: model.formEntity.bannerImgurl(), as: 'item'}">
				    <span ><img  data-bind="attr:{src:$root.fmt.imgUrl(item)}" /><em><a href="javascript:void(0)"><img src="<@full_path path='img/close.png' />" width="20" height="20" id="close" data-bind="click:$root.removeImg.bind($root,1,$index())"/></a></em></span>
				  </div>
				  <span data-bind="visible:model.formEntity.bannerImgurl().length < 1" >
				    <div id="thinThumPhotoPics" class="addPic">
				      <input type="file" onchange="viewModel.upload(this, 'backstage/banner/',1)" accept=".jpg,.jpeg,.bmp,.png" id="file_sequence_id_1" class="fileInput" />
				    </div>
				  </span>
				</div>
			  </div>
		  </div>
		  
		  <div class="form-group" >
            <label for="firstname" class="col-sm-3 control-label center-block">内容</label>
              <div class="col-sm-8">
                <textarea id="content" name ="content" ></textarea> 
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