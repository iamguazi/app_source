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
		    <label for="firstname" class="col-sm-3 control-label center-block">游戏类型<span class="form-required">*</span></label>
		      <div class="col-sm-7">
			    <select required disabled = "disabled" class="form-control" id="gameType" name="gameType" data-bind="value:model.formEntity.gameType,options:model.gameTypeArrayOfForm,optionsText:'name',optionsValue:'id',event:{change:getAreaTypeByGameType}">
			    </select>
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="firstname" class="col-sm-3 control-label center-block">房间类型<span class="form-required">*</span></label>
		      <div class="col-sm-7">
			    <select disabled = "disabled" required class="form-control" id="areaId" name="areaId" data-bind="value:model.formEntity.areaId,options:model.areaTypes,optionsText:'areaName',optionsValue:'id',event:{focus:$root.areaTypeFocus.bind($root,2)}" >
			    </select>
			  </div>
		  </div>
		  <!--
		  <div class="form-group">
		    <label for="lastname" class="col-sm-3 control-label">房间图标<span class="form-required">*</span></label>
			  <div class="col-sm-7">
				<div class="pic" >
				  <div style="display:inline" id="productPhotoPics" data-bind="foreach: {data: model.formEntity.roomPhoto(), as: 'item'}">
				    <span ><img  data-bind="attr:{src:$root.fmt.imgUrl(item)},click:$root.fmt.biggerShowImg.bind($root,item)" /><em><a href="javascript:void(0)"><img src="<@full_path path='img/close.png' />" width="20" height="20" id="close" data-bind="click:$root.removeImg.bind($root,1,$index())"/></a></em></span>
				  </div>
				  <span data-bind="visible:model.formEntity.roomPhoto().length < 1" >
				    <div id="thinThumPhotoPics" class="addPic">
				      <input type="file" onchange="viewModel.upload(this, 'backstage/roominfo/',1)" accept=".jpg,.jpeg,.bmp,.png" id="file_sequence_id_1" class="fileInput" />
				    </div>
				  </span>
				</div>
			  </div>
		  </div>
		  -->
		  
	      <div class="form-group">
		    <label  class="col-sm-3 control-label">房间名称<span class="form-required">*</span></label>
		      <div class="col-sm-7">
			    <input type="text" required maxLength="30" class="form-control" id="roomName" name="roomName" data-bind="textinput:model.formEntity.roomName" 
			    placeholder="请输入房间名称">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">机器人数量<span class="form-required">*</span></label>
		      <div class="col-sm-3">
			    <input type="text" required maxLength="10" class="form-control" id="rebotMinCount" name = "rebotMinCount" data-bind="textinput:model.formEntity.rebotMinCount"
			       placeholder="输入最小数量">
			  </div>
			  <label  class="col-sm-1 control-label">至</label>
			  <div class="col-sm-3">
			       <input type="text" required maxLength="10" class="form-control" id="rebotMaxCount" name = "rebotMaxCount" data-bind="textinput:model.formEntity.rebotMaxCount"
			       placeholder="输入最大数量">
			  </div>
		  </div>
			    
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">人数上限<span class="form-required">*</span></label>
		      <div class="col-sm-7">
			    <input type="text" required maxLength="10" class="form-control" id="peopleMaxCount" name = "peopleMaxCount" data-bind="textinput:model.formEntity.peopleMaxCount"
			       placeholder="请输入人数上限">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">房间最小人数</label>
		      <div class="col-sm-7">
			    <input type="text"  maxLength="10" class="form-control" id="minPeopleCount" name = "minPeopleCount" data-bind="textinput:model.formEntity.minPeopleCount"
			       placeholder="请输入房间最小人数">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">单注最低金额<span class="form-required">*</span></label>
		      <div class="col-sm-7">
			    <input type="text" required maxLength="10" class="form-control" id="perMinPoint" name = "perMinPoint" data-bind="textinput:model.formEntity.perMinPoint"
			       placeholder="请输入单注最低金额">
			    <span class="help-block">提示：输入整数或小输(小数保留两位小数)</span>
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">单注最高金额<span class="form-required">*</span></label>
		      <div class="col-sm-7">
			    <input type="text" required maxLength="10" class="form-control" id="perMaxPoint" name = "perMaxPoint" data-bind="textinput:model.formEntity.perMaxPoint"
			       placeholder="请输入单注最高金额">
			    <span class="help-block">提示：输入整数或小输(小数保留两位小数)</span>
			  </div>
		  </div>	  
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">总注封顶金额<span class="form-required">*</span></label>
		      <div class="col-sm-7">
			    <input type="text" required maxLength="10" class="form-control" id="allMaxPoint" name = "allMaxPoint" data-bind="textinput:model.formEntity.allMaxPoint"
			       placeholder="请输入总注封顶金额">
			    <span class="help-block">提示：输入整数或小输(小数保留两位小数)</span>
			  </div>
		  </div>
		  
		   <div class="form-group">
            <label  class="col-sm-3 control-label">房间密码<span class="form-required">*</span></label>
              <div class="col-sm-7">
                <input type="text" required maxLength="100" class="form-control" id="password" name = "password" data-bind="textinput:model.formEntity.password"
                   placeholder="请输房间密码">
                <span class="help-block">提示：-1表示无密码</span>
              </div>
          </div>
		
		<div class="form-group">
		    <label for="firstname" class="col-sm-3 control-label center-block">状态<span class="form-required">*</span></label>
		      <div class="col-sm-7">
			    <select disabled = "disabled" class="form-control" id="status" name="status" data-bind="value:model.formEntity.status">
			    	<option value="1">启用</option>
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
			 <button class="ladda-button btn btn-primary center-block" data-style="expand-right"  data-bind="click:$root.beforeSubmit.bind($root)"><span class="ladda-label"><span class="glyphicon glyphicon-floppy-disk" style="margin-right: .8em;">保存</span></span></button>
		   </div>
	     </div>
	   </div>
	 
     </div><!-- /.modal-content -->
   </div><!-- /.modal -->
</div>			
<!-- 新增或修改数据e -->