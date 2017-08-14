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
		    <label  class="col-sm-3 control-label center-block">类型</label>
		      <div class="col-sm-7">
			    <select  required class="form-control" id="accountType" name="accountType" data-bind="value:model.formEntity.accountType,event:{change:showAccountType}">
                    <option value="1">银行卡</option>
                    <option value="2">支付宝</option>
                    <option value="3">微信</option>
                </select>
			  </div>
		  </div>
		  
	      <div class="form-group">
		    <label  class="col-sm-3 control-label">账号</label>
		      <div class="col-sm-7">
			    <input type="text"  required maxLength="100" class="form-control" id="account" name="account" data-bind="textinput:model.formEntity.account" 
			    placeholder="请输入账号">
			  </div>
		  </div>

	      <div class="form-group">
		    <label  class="col-sm-3 control-label">开户名</label>
		      <div class="col-sm-7">
			    <input type="text"  maxLength="100" class="form-control" id="realName" name="realName" data-bind="textinput:model.formEntity.realName" 
			    placeholder="请输入开户名">
			  </div>
		  </div>

	      <div class="form-group">
		    <label  class="col-sm-3 control-label">银行名称</label>
		      <div class="col-sm-7">
			    <input type="text"  maxLength="100" class="form-control" id="bankName" name="bankName" data-bind="textinput:model.formEntity.bankName" 
			    placeholder="请输入银行名称">
			  </div>
		  </div>

	      <div class="form-group">
		    <label  class="col-sm-3 control-label">开户行</label>
		      <div class="col-sm-7">
			    <input type="text"  maxLength="100" class="form-control" id="openCardAddress" name="openCardAddress" data-bind="textinput:model.formEntity.openCardAddress" 
			    placeholder="请输入开户行">
			  </div>
		  </div>
		  
		  <div class="form-group">
            <label for="lastname" class="col-sm-3 control-label">二维码</label>
              <div class="col-sm-7">
                <div class="pic" >
                  <div style="display:inline" id="photoPics" data-bind="foreach: {data: model.formEntity.photo(), as: 'item'}">
                    <span ><img  data-bind="attr:{src:$root.fmt.imgUrl(item)},click:$root.fmt.biggerShowImg.bind($root, item)" /><em><a href="javascript:void(0)"><img src="<@full_path path='img/close.png' />" width="20" height="20" id="close" data-bind="click:$root.removeImg.bind($root,1,$index())"/></a></em></span>
                  </div>
                  <span data-bind="visible:model.formEntity.photo().length < 1" >
                    <div id="thinThumPhotoPics" class="addPic">
                      <input type="file" onchange="viewModel.upload(this, 'backstage/account/',1)" accept=".jpg,.jpeg,.bmp,.png" id="file_sequence_id_1" class="fileInput" />
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
			 <button class="ladda-button btn btn-primary center-block" data-style="expand-right"  data-bind="click:$root.beforeSubmit.bind($root)"><span class="ladda-label"><span class="glyphicon glyphicon-floppy-disk" style="margin-right: .8em;">保存</span></span></button>
		   </div>
	     </div>
	   </div>
	 
     </div><!-- /.modal-content -->
   </div><!-- /.modal -->
</div>			
<!-- 新增或修改数据e -->