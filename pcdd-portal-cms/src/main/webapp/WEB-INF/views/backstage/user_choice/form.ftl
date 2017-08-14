<!-- 新增或修改页 s-->
<div id="editor" class="modal fade" tabindex="-1" aria-labelledby="editorLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
    
	  <div class="alert alert-info">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		  &times;
		</button>
		<h3 class="modal-title " id="editorLabel" >
		  详情
		</h3>
	  </div>
	  
      <div class="modal-body">
        <!--添加的内容-->
	    <form class="form-horizontal" id="form">
		  
		  <div class="form-group">
		    <label for="lastname" class="col-sm-3 control-label">用户头像</label>
			  <div class="col-sm-7">
				<div class="pic" >
				  <div style="display:inline" id="productPhotoPics" data-bind="foreach: {data: model.formEntity.userPhoto(), as: 'item'}">
				    <span ><img  data-bind="attr:{src:$root.fmt.img4Null(item)},click:$root.fmt.biggerShowImg.bind($root, item)" /></span>
				  </div>
				</div>
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">账号:</label>
		      <div class="col-sm-7">
			    <input type="text"  maxLength="50" disabled class="form-control" id="account" name="account" data-bind="value:model.formEntity.account" 
			    placeholder="请输入账号">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">昵称:</label>
		      <div class="col-sm-7">
			    <input type="text"  maxLength="50" disabled class="form-control" id="nickName" name="nickName" data-bind="value:model.formEntity.nickName" 
			    placeholder="请输入昵称">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">游戏类型:</label>
		      <div class="col-sm-7">
			    <input type="text"  maxLength="50" disabled class="form-control" id="gameType" name="gameType" data-bind="value:$root.fmt.gameType(model.formEntity.gameType())" 
			    placeholder="请输入游戏类型">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">房间类型:</label>
		      <div class="col-sm-7">
			    <input type="text"  maxLength="50" disabled class="form-control" id="areaName" name="areaName" data-bind="value:model.formEntity.areaName" 
			    placeholder="请输入房间名称">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">房间名称:</label>
		      <div class="col-sm-7">
			    <input type="text"  maxLength="50" disabled class="form-control" id="roomName" name="roomName" data-bind="value:model.formEntity.roomName" 
			    placeholder="请输入房间类型">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">下注金额:</label>
		      <div class="col-sm-7">
			    <input type="text"  maxLength="50" disabled class="form-control" id="point" name="point" data-bind="value:$root.fmt.currency(model.formEntity.point(),{places: 2,symbol: ''})" 
			    placeholder="请输入下注金额">
			  </div>
		  </div>

		  <div class="form-group">
		    <label  class="col-sm-3 control-label">账户余额:</label>
		      <div class="col-sm-7">
			    <input type="text"  maxLength="50" disabled class="form-control" id="accountPoint" name="accountPoint" data-bind="value:$root.fmt.currency(model.formEntity.accountPoint(),{places: 2,symbol: ''})" 
			   >
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