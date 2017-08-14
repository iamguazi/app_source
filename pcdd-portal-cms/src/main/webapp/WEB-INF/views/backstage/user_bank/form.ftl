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
            <label  class="col-md-3 control-label">真实姓名<span class="form-required">*</span>:</label>
              <div class="col-md-7">
                <input type="text"  maxLength="50" required class="form-control" id="realName" name = "realName" data-bind="textinput:model.formEntity.realName"
                   placeholder="请输入真实姓名">
              </div>
          </div>
          
          <div class="form-group" >
            <label  class="col-md-3 control-label">银行卡名称<span class="form-required">*</span>:</label>
              <div class="col-md-7">
                <input type="text" required maxLength="50" class="form-control" id="bankName" name = "password" data-bind="textinput:model.formEntity.bankName"
                   placeholder="请输入银行卡名称">
              </div>
          </div>
          
          <div class="form-group">
            <label  class="col-md-3 control-label">银行卡号<span class="form-required">*</span>:</label>
              <div class="col-md-7">
                <input type="text" required digits maxLength="25" class="form-control" id="bankNo" name = "bankNo" data-bind="textinput:model.formEntity.bankNo"
                   placeholder="请输入银行卡号">
              </div>
          </div>

          <div class="form-group">
            <label  class="col-md-3 control-label">开户地址<span class="form-required">*</span>:</label>
              <div class="col-md-7">
                <input type="text" required  maxLength="100" class="form-control" id="openCardAddress" name = "openCardAddress" data-bind="textinput:model.formEntity.openCardAddress"
                   placeholder="请输入开户地址">
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
             <button type="button" class="btn btn-primary center-block" data-bind="click:$root.beforeSubmit.bind($root)">
               <span class="glyphicon glyphicon-floppy-disk" style="margin-right: .8em;"></span>保存
             </button>
           </div>
         </div>
       </div>
     
     </div><!-- /.modal-content -->
   </div><!-- /.modal -->
</div>          
<!-- 新增或修改数据e -->