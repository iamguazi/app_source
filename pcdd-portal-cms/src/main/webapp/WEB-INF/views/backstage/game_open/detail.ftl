<!-- 新增或修改页 s-->
<div id="detail" class="modal fade" tabindex="-1" aria-labelledby="detailLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
    
	  <div class="alert alert-info">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		  &times;
		</button>
		<h3 class="modal-title " id="detailLabel" >
		  详情
		</h3>
	  </div>
	  
      <div class="modal-body">
        <!--添加的内容-->
	    <form class="form-horizontal" id="detailForm">
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">游戏类型:</label>
		      <div class="col-sm-7">
			    <input type="text"  maxLength="50" disabled class="form-control"  name="gameType" data-bind="value:$root.fmt.gameType(model.formEntity.gameType())" 
			    placeholder="请输入游戏类型">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">期数:</label>
		      <div class="col-sm-7">
			    <input type="text"  maxLength="50" disabled class="form-control"  name="gameNum" data-bind="value:model.formEntity.gameNum" 
			    placeholder="请输入期数">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">开奖号码(数字):</label>
		      <div class="col-sm-7">
			    <input type="text"  maxLength="50" disabled class="form-control"  name="gameResult" data-bind="value:model.formEntity.gameResult" 
			    placeholder="请输入开奖号码">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">开奖号码(含式子):</label>
		      <div class="col-sm-7">
			    <input type="text"  maxLength="50" disabled class="form-control"  name="gameResultDesc" data-bind="value:model.formEntity.gameResultDesc" 
			    placeholder="请输入开奖号码">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">开奖类型:</label>
		      <div class="col-sm-7">
			    <input type="text"  maxLength="50" disabled class="form-control"  name="resultType" data-bind="value:model.formEntity.resultType" 
			    placeholder="请输入开奖类型">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">总下注金额:</label>
		      <div class="col-sm-7">
			    <input type="text"  maxLength="50" disabled class="form-control"  name="totalPoint" data-bind="value:$root.fmt.currency(model.formEntity.totalPoint(),{places: 2,symbol: ''})" 
			    placeholder="请输入总下注金额">
			  </div>
		  </div>
	    </form>
      </div>
	 
	   <div class="modal-footer" style="border-top:0;">
	     <div class="row"  >
	       <div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 col-xs-offset-3 col-sm-offset-3 col-lg-offset-3 col-md-offset-3" >
		     <button type="button" class="btn btn-default center-block btn-block" data-dismiss="modal">
			  返回
			 </button>
		   </div>
	     </div>
	   </div>
	 
     </div><!-- /.modal-content -->
   </div><!-- /.modal -->
</div>			
<!-- 新增或修改数据e -->