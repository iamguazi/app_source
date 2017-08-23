<!-- 新增或修改页 s-->
<div id="recharge" class="modal fade" tabindex="-1" aria-labelledby="rechargeLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
    
	  <div class="alert alert-info">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		  &times;
		</button>
		<h3 class="modal-title " id="rechargeLabel" >
		  充值
		</h3>
	  </div>
	  
      <div class="modal-body">
        <!--添加的内容-->
	    <form class="form-horizontal sh-validate" id="rechargeForm">
	      <div class="form-group">
		    <label  class="col-sm-3 control-label">充值用户:</label>
		      <div class="col-sm-7">
			    <input type="text"  maxLength="50" disabled class="form-control" id="nickName" name="nickName" data-bind="textinput:model.recharge.nickName" 
			    placeholder="请输入充值用户">
			  </div>
		  </div>
		  
	      <div class="form-group">
		    <label  class="col-sm-3 control-label">充值账号:</label>
		      <div class="col-sm-7">
			    <input type="text" disabled maxLength="30" class="form-control" id="account" name="account" data-bind="textinput:model.recharge.account" 
			    placeholder="请输入充值账号">
			  </div>
		  </div>
		  
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">充值金额:</label>
		      <div class="col-sm-7">
			    <input type="text" required maxLength="10" class="form-control" id="rechargePoint" name = "rechargePoint" data-bind="textinput:model.recharge.rechargePoint"
			       placeholder="请输入充值金额">
			       <span class="help-block">单位：元宝</span>
			  </div>
		  </div>

		  <div class="form-group">
		    <label  class="col-sm-3 control-label">备注:</label>
		      <div class="col-sm-7">
			    <input type="text" required maxLength="100" class="form-control" id="remark" conname = "remark" data-bind="textinput:model.recharge.remark"
			       placeholder="扣款可输入备注">
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
		     <button type="button" class="btn btn-primary center-block" data-bind="click:$root.rechargeForm.bind($root)">
			   <span class="glyphicon glyphicon-floppy-disk" style="margin-right: .8em;"></span>确定
			 </button>
		   </div>
	     </div>
	   </div>
	 
     </div><!-- /.modal-content -->
   </div><!-- /.modal -->
</div>			
<!-- 新增或修改数据e -->