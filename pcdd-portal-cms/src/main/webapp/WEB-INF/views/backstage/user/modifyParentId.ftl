<!-- 新增或修改页 s-->
<div id="modifyParentId" class="modal fade" tabindex="-1" aria-labelledby="modifyParentIdLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
    
      <div class="alert alert-info">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
          &times;
        </button>
        <h3 class="modal-title " id="modifyParentIdLabel" >
         将线下转移到其他用户名下
        </h3>
      </div>
      
      <div class="modal-body">
        <!--添加的内容-->
        <form class="form-horizontal sh-validate" id="modifyParentIdForm">
           
          <div class="form-group">
              <div class="col-sm-10">
                <input type="text"  maxLength="10" class="form-control" id="parentId" name="parentId" data-bind="textinput:model.modifyParentId.parentId" 
                placeholder="请输入要转移的用户ID">
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
             <button type="button" class="btn btn-primary center-block" data-bind="click:$root.modifyParentIdForm.bind($root)">
               <span class="glyphicon glyphicon-floppy-disk" style="margin-right: .8em;"></span>确定
             </button>
           </div>
         </div>
       </div>
     
     </div><!-- /.modal-content -->
   </div><!-- /.modal -->
</div>          
<!-- 新增或修改数据e -->