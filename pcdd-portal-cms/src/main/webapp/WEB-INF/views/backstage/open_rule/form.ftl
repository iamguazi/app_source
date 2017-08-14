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
		    <label for="firstname" class="col-sm-3 control-label center-block">下注类型</label>
		      <div class="col-sm-7">
			    <input type="text" disabled="disabled" required maxLength="30" class="form-control" id="typeName" name="typeName" data-bind="textinput:model.formEntity.typeName" />
			  </div>
		  </div>
		  
		  <div class="form-group">
            <label  class="col-sm-3 control-label">下注范围1:<span class="form-required">*</span></label>
              <div class="col-sm-3">
                <input type="text"  maxLength="9" class="form-control" id="start1" name="start1" data-bind="textinput:model.formEntity.start1" 
                placeholder="请输入起始金额">
              </div>
              <div class="col-sm-1 conterFlag">
                    ~
              </div>
              <div class="col-sm-3">
                <input type="text"  maxLength="9" class="form-control" id="end1" name="end1" data-bind="textinput:model.formEntity.end1" 
                placeholder="请输入截至金额">
              </div>
          </div>

		  <div class="form-group">
		    <label  class="col-sm-3 control-label">赔率1<span class="form-required">*</span></label>
		      <div class="col-sm-7">
			    <input type="text"  maxLength="9" class="form-control" id="bili1" name="bili1" data-bind="textinput:model.formEntity.bili1" 
			    placeholder="请输入赔率1">
			    <span class="help-block">提示：输入整数或小数(小数保留两位小数)</span>
			  </div>
		  </div>

          <div class="form-group">
            <label  class="col-sm-3 control-label">下注范围2:<span class="form-required">*</span></label>
              <div class="col-sm-3">
                <input type="text"  maxLength="9" class="form-control" id="start2" name="start2" data-bind="textinput:model.formEntity.start2" 
                placeholder="请输入起始金额">
              </div>
              <div class="col-sm-1 conterFlag">
                    ~
              </div>
              <div class="col-sm-3">
                <input type="text"  maxLength="9" class="form-control" id="end2" name="end2" data-bind="textinput:model.formEntity.end2" 
                placeholder="请输入截至金额">
              </div>
          </div>

          <div class="form-group">
            <label  class="col-sm-3 control-label">赔率2<span class="form-required">*</span></label>
              <div class="col-sm-7">
                <input type="text"  maxLength="9" class="form-control" id="bili2" name="bili2" data-bind="textinput:model.formEntity.bili2" 
                placeholder="请输入赔率2">
                <span class="help-block">提示：输入整数或小数(小数保留两位小数)</span>
              </div>
          </div>

          <div class="form-group">
            <label  class="col-sm-3 control-label">下注范围3:<span class="form-required">*</span></label>
              <div class="col-sm-3">
                <input type="text"  maxLength="9" class="form-control" id="start3" name="start3" data-bind="textinput:model.formEntity.start3" 
                placeholder="请输入起始金额">
              </div>
              <div class="col-sm-1 conterFlag">
                    ~
              </div>
              <div class="col-sm-3">
                <input type="text"  maxLength="9" class="form-control" id="end3" name="end3" data-bind="textinput:model.formEntity.end3" 
                placeholder="请输入截至金额">
              </div>
          </div>

          <div class="form-group">
            <label  class="col-sm-3 control-label">赔率3<span class="form-required">*</span></label>
              <div class="col-sm-7">
                <input type="text"  maxLength="9" class="form-control" id="bili3" name="bili3" data-bind="textinput:model.formEntity.bili3" 
                placeholder="请输入赔率3">
                <span class="help-block">提示：输入整数或小数(小数保留两位小数)</span>
              </div>
          </div>
          
          <div class="form-group">
            <label  class="col-sm-3 control-label">下注范围4:<span class="form-required">*</span></label>
              <div class="col-sm-3">
                <input type="text"  maxLength="9" class="form-control" id="start4" name="start4" data-bind="textinput:model.formEntity.start4" 
                placeholder="请输入起始金额">
              </div>
              <div class="col-sm-1 conterFlag">
                    ~
              </div>
              <div class="col-sm-3">
                <input type="text"  maxLength="9" class="form-control" id="end4" name="end4" data-bind="textinput:model.formEntity.end4" 
                placeholder="请输入截至金额">
              </div>
          </div>

          <div class="form-group">
            <label  class="col-sm-3 control-label">赔率4<span class="form-required">*</span></label>
              <div class="col-sm-7">
                <input type="text"  maxLength="9" class="form-control" id="bili4" name="bili4" data-bind="textinput:model.formEntity.bili4" 
                placeholder="请输入赔率4">
                <span class="help-block">提示：输入整数或小数(小数保留两位小数)</span>
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