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
            <label  class="col-sm-3 control-label">支付名称<span class="form-required">*</span></label>
              <div class="col-sm-7">
                <input type="text" required maxLength="100" class="form-control" id="name" name="name" data-bind="textinput:model.formEntity.name" 
                placeholder="请输入支付名称">
              </div>
          </div>
	    
	     <div class="form-group">
		    <label for="firstname" class="col-sm-3 control-label center-block">支付类型<span class="form-required">*</span></label>
		      <div class="col-sm-7">
			    <select  class="form-control" data-bind="value:model.formEntity.type">
                  <option value="1">线上支付</option>
                  <option value="2">线下支付</option>
                </select>
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">排序序号</label>
		      <div class="col-sm-7">
			    <input type="text"  maxLength="10" class="form-control" id="payOrder" name = "payOrder" data-bind="textinput:model.formEntity.payOrder"
			       placeholder="请输入排序序号">
			     <span class="help-block">温馨提示：数字越大越靠前</span>
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">备注</label>
		      <div class="col-sm-7">
			    <textarea rows="5" style="max-width: 100%;" class="form-control" id="remarks" name = "remarks" data-bind="textinput:model.formEntity.remarks" ></textarea>
			  </div>
		  </div>
		
		  <div class="form-group">
            <label for="firstname" class="col-sm-3 control-label center-block">状态<span class="form-required">*</span></label>
              <div class="col-sm-7">
                <select class="form-control" data-bind="value:model.formEntity.status">
                      <option value="1">启用</option>
                      <option value="0">禁用</option>
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