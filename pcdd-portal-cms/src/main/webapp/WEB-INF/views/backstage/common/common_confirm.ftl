<!-- 二次确认页面 s-->
<div class="modal fade" id="delete" tabindex="-1" 
  aria-labelledby="deleteLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
	  <div  class="alert alert-danger">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		  &times;
		</button>
		<h3 class="modal-title " id="deleteLabel" >
		   删除
		</h3>
	  </div>
	  <div class="modal-body">
	  	<!--添加的内容-->
		 <span id="deleteIdsMsg">已选中: <span style="color:#d9534f;font-size:18px;font-weight:bold;" data-bind="text:model.checkItems().length"></span> 条记录，</span><span id="deleteMsg"></span>
	  </div>
	  <div class="modal-footer" style="border-top:0;">
	    <div class="row"  >
		  <div class="col-lg-2 col-md-2 col-sm-2  col-xs-2 col-lg-offset-4 col-md-offset-4 col-sm-offset-4 col-xs-offset-4" >
		    <button type="button" class="btn btn-default center-block"  data-dismiss="modal">
			   关闭
			</button>
		  </div>
		  <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 " >
		    <button type="button" class="btn btn-danger center-block" id="deleteBtn">
			  删除
			</button>
		  </div>
	    </div>
	  </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal -->
</div>								
<!-- 二次确认页面e -->
								
