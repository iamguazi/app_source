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
		    <label for="firstname" class="col-sm-3 control-label center-block">游戏类型</label>
		      <div class="col-sm-7">
			    <select disabled="disabled" required class="form-control" id="gameType" name="gameType" data-bind="value:model.formEntity.gameType,options:model.gameTypeArrayOfForm,optionsText:'name',optionsValue:'id',event:{change:getAreaTypeByGameType}">
			    </select>
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="firstname" class="col-sm-3 control-label center-block">房间类型</label>
		      <div class="col-sm-7">
			    <select disabled="disabled" required class="form-control" id="areaId" name="areaId" data-bind="value:model.formEntity.areaId,options:model.areaTypes,optionsText:'areaName',optionsValue:'id',event:{focus:$root.areaTypeFocus.bind($root,2)}" >
			    </select>
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="firstname" class="col-sm-3 control-label center-block">比例类型</label>
		      <div class="col-sm-7">
			    <select disabled="disabled" class="form-control" id="biliType" name="biliType" data-bind="value:model.formEntity.biliType">
			    	<option value="1">大小单双</option>
			    	<option value="2">猜数字</option>
			    	<option value="3">特殊玩法</option>
			    </select>
			  </div>
		  </div>
		  
	      <div class="form-group">
		    <label  class="col-sm-3 control-label">比例名称</label>
		      <div class="col-sm-7">
			    <input type="text" disabled="disabled" required maxLength="30" class="form-control" id="biliName" name="biliName" data-bind="textinput:model.formEntity.biliName" 
			    placeholder="请输入比例名称">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">比例<span class="form-required">*</span></label>
		      <div class="col-sm-7">
			    <input type="text"  maxLength="9" class="form-control" id="bili" name="bili" data-bind="textinput:model.formEntity.bili" 
			    placeholder="请输入比例">
			    <span class="help-block">提示：输入整数或小数(小数保留两位小数)</span>
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">最小投注金额</label>
		      <div class="col-sm-7">
			    <input type="text"  maxLength="9" class="form-control" id="minPoint" name="minPoint" data-bind="textinput:model.formEntity.minPoint" 
			    placeholder="请输入最小投注金额">
			    <span class="help-block">提示：输入整数或小数(小数保留两位小数)</span>
			  </div>
		  </div>

		  <div class="form-group">
		    <label  class="col-sm-3 control-label">最大投注金额</label>
		      <div class="col-sm-7">
			    <input type="text"  maxLength="9" class="form-control" id="maxPoint" name="maxPoint" data-bind="textinput:model.formEntity.maxPoint" 
			    placeholder="请输入最小投注金额">
			    <span class="help-block">提示：输入整数或小数(小数保留两位小数)</span>
			  </div>
		  </div>
		
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">中奖和值:</label>
		      <div class="col-sm-7">
			    <input type="text" disabled="disabled" maxLength="100" class="form-control" id="result" name="result" data-bind="textinput:model.formEntity.result" 
			    placeholder="回水比例">
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