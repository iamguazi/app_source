<!-- 新增或修改页 s-->
<div id="editor" class="modal fade" tabindex="-1" aria-labelledby="editorLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
    
	  <div class="alert alert-info">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		  &times;
		</button>
		<h3 class="modal-title " id="editorLabel" data-bind="text:model.formEntity.id()>0?'编辑':'新增'">
		  
		</h3>
	  </div>
	  
      <div class="modal-body">
        <!--添加的内容-->
	    <form class="form-horizontal  sh-validate" id="form">
		  
		  <div class="form-group">
            <label for="firstname" class="col-sm-3 control-label center-block">游戏类型<span class="form-required">*</span></label>
              <div class="col-sm-7">
                <select required class="form-control" id="gameType" name="gameType" data-bind="event:{change:changeGameType},value:model.formEntity.gameType,options:model.gameTypeArrayOfForm,optionsText:'name',optionsValue:'id'">
                </select>
              </div>
          </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">期数<span class="form-required">*</span></label>
		      <div class="col-sm-7">
			    <input type="text" required maxLength="30"  class="form-control" id="gameNum" name="gameNum" data-bind="textinput:model.formEntity.gameNum" 
			    placeholder="请输入期数">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">开奖号码(数字)<span class="form-required">*</span></label>
		      <div class="col-sm-7">
			    <input type="text" required maxLength="10"  class="form-control" id="gameResult" name="gameResult" data-bind="textinput:model.formEntity.gameResult" 
			    placeholder="请输入开奖号码">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">开奖号码(含式子)<span class="form-required">*</span></label>
		      <div class="col-sm-9">
			    <input type="text" required maxLength="100" style="width:30%;display: inline-block;" class="form-control"  name="gameResultDesc1" data-bind="textinput:model.formEntity.gameResultDesc1" 
			    placeholder="请输入开奖因子">
			    <div style="width:2%;display: inline-block;">
			         +
			    </div>
			    <input type="text" required maxLength="100" style="width:30%;display: inline-block;" class="form-control"  name="gameResultDesc2" data-bind="textinput:model.formEntity.gameResultDesc2" 
			    placeholder="请输入开奖因子">
			    <div style="width:2%;display: inline-block;">
                     +
                </div>
                <br/>
                <input type="text" required maxLength="100" style="width:30%;display: inline-block;" class="form-control"  name="gameResultDesc3" data-bind="textinput:model.formEntity.gameResultDesc3" 
                placeholder="请输入开奖因子">
			    <div style="width:2%;display: inline-block;margin-top:5px;">
                     =
                </div>
			    <input type="text" required maxLength="100" style="margin-top:5px;width:30%;display: inline-block;" class="form-control" name="gameResultDesc4" data-bind="textinput:model.formEntity.gameResultDesc4" 
			    placeholder="请输入开奖结果">
			  </div>
		  </div>
		  
		  <div class="form-group">
		    <label  class="col-sm-3 control-label">开奖类型<span class="form-required">*</span></label>
		      <div class="col-sm-7">
			    <input type="text" required maxLength="30"  class="form-control" id="resultType" name="resultType" data-bind="textinput:model.formEntity.resultType" 
			    placeholder="请输入开奖类型">
			  </div>
		  </div>
		  
		  <div class="form-group" >
		    <label  class="col-sm-3 control-label">开奖时间<span class="form-required">*</span></label>
		      <div class="col-sm-7">
			    <input onclick="laydate({format:'YYYY-MM-DD hh:mm:ss',istime:true,choose:function(datas){viewModel.model.formEntity.openTime = datas;}})" type="text" required class="form-control" id="openTime" name="openTime" data-bind="textinput:model.formEntity.openTime" 
			    placeholder="请选择开奖时间">
			  </div>
		  </div>
		  
		  <div class="form-group" style="display:none;">
		    <label  class="col-sm-3 control-label">总下注金额<span class="form-required">*</span></label>
		      <div class="col-sm-7">
			    <input type="text" disabled="disabled" maxLength="11"  class="form-control" id="totalPoint" name="totalPoint" data-bind="textinput:model.formEntity.totalPoint" 
			    placeholder="请输入总下注金额">
			  </div>
		  </div>

		  <!--<div class="form-group">
		    <label  class="col-sm-3 control-label">是否派奖<span class="form-required">*</span></label>
		      <div class="col-sm-7">
			    <select  class="form-control" id="isGive" name="isGive" data-bind="value:model.formEntity.isGive">
                    <option value="0">否</option>
    			    <option value="1">是</option>
                </select>
			  </div>
		  </div>-->
		  
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