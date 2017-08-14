<!DOCTYPE html>
<html>
	<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>主页</title>

    <!-- Bootstrap -->
    <link href="<@full_path path='js/lib/bootstrap-3.3.5/dist/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<@full_path path='css/main.css'/>" rel="stylesheet">
    <link href="<@full_path path='js/lib/cover/jquery.cover.css'/>" rel="stylesheet">
	
	<#include "/views/backstage/common/common_script.ftl"/>
    
    <style>
    	.form-required {
    		color: red;
		}
		
		.btn-primary {
		    background-color: #1FB5AD;
		    color: #fff;
		    border-color: #1fb5ad;
		}
		
		.form-horizontal .control-label {
		    font-weight: 100;
		    text-align: right;
		}
		
		.form-control {
    		border-radius: 3px; 
		}

		.btn-submit {
			display: none !important;
		}
    </style>
  </head>
  <body style="background-color: #f1f4fb;">
  	<input type="hidden" id="queryUrl"  value="<@full_path path='backstage/other/?act=QUERY' />"  />
  	<input type="hidden" id="deleteUrl"  value="<@full_path path='backstage/other/?act=DELETE' />"  />
  	<input type="hidden" id="saveUrl"  value="<@full_path path='backstage/other/?act=SAVE' />"  />
  	<input type="hidden" id="managerType"  value="${Session.manager.managerType!}"  />
  	
       <!--提示框 -->
       <#include "/views/backstage/common/common_alert.ftl"/>
       
   <div class="container" style="width:100%;">   
	    <div class="row">
		  <!--栏目路径-->
		  <div class="header">
		    <h1 class="page-title">其它设置</h1>
		  </div>
		  <ul class="breadcrumb">
		    <li><a href="javascript:void(0);">系统设置</a> <span class="divider">/</span></li>
		    <li class="active">其它设置</li>
	      </ul>
	    </div>
	    
	    <!--其它设置start-->
		<div class="row" style="padding: 20px;">
			<div class="panel-group" id="otherContent">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#otherContent" 
							   href="#collapseOne">
								<h4>其它设置</h4>
							</a>
						</h4>
					</div>
					<div id="collapseOne" class="panel-collapse collapse in">
						<div class="panel-body">
						
							<form class="form-horizontal sh-validate" id="otherForm">
				                
				                <div class="form-group">
				                    <label class="col-sm-2 control-label">客服QQ<span class="form-required">*</span></label>
				                    <div class="col-sm-4">
				                        <input type="text" name="kefu_qq" placeholder="请输入客服QQ" class="form-control" required maxlength="20"  data-bind="textinput: model.other.kefu_qq" />
				                    </div>
				                </div>
				                
				                <div class="form-group">
				                    <label class="col-sm-2 control-label">微信客服<span class="form-required">*</span></label>
				                    <div class="col-sm-4">
				                        <input type="text" name="kefu_weixin" placeholder="请输入微信客服" class="form-control" required maxlength="20" data-bind="textinput: model.other.kefu_weixin" />
				                    </div>
				                </div>
				                
				                <div class="form-group">
				                    <label class="col-sm-2 control-label">客服官网<span class="form-required">*</span></label>
				                    <div class="col-sm-4">
				                        <input type="text" name="kefu_guanwang" placeholder="请输入客服官网" class="form-control" required maxlength="30" data-bind="textinput: model.other.kefu_guanwang" />
				                    </div>
				                </div>

				                <div class="form-group">
				                    <label class="col-sm-2 control-label">在线人数<span class="form-required">*</span></label>
				                    <div class="col-sm-4">
				                        <input type="text" name="people_num" placeholder="请输入在线人数" class="form-control" required maxlength="30" data-bind="textinput: model.other.people_num" />
				                    </div>
				                </div>

				                
				                <div class="form-group">
				                    <div class="col-sm-offset-2 col-sm-4">
				                        <button type="submit" class="btn btn-primary btn-lg btn-block" >确定</button>
				                        <button class="btn-submit" data-bind="click: updateOther"></button>
				                    </div>
				                </div>
				                
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--其它设置end-->
		
		<!-- 分销设置 -->
		<div class="row" style="padding: 20px;">
            <div class="panel-group" id="biliContent">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#biliContent" 
                               href="#collapseTow">
                                <h4>分销设置</h4>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTow" class="panel-collapse collapse in">
                        <div class="panel-body">
                        
                            <form class="form-horizontal sh-validate" id="biliForm">
                                
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">提现比例<span class="form-required">*</span></label>
                                    <div class="col-sm-4">
                                        <input type="text" name="tixian_bili" placeholder="请输入提现比例" class="form-control" required maxlength="6" min="0" max="1" data-bind="textinput: model.bili.tixian_bili" />
                                        <span class="help-block" style="margin-bottom:0">提示：0~0.99的两位小数</span>
                                    </div>
                                </div>
                                
                                
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">免费提现次数<span class="form-required">*</span></label>
                                    <div class="col-sm-4">
                                          <input type="text" name="tixian_free_count" placeholder="请输入免费提现次数" class="form-control" required maxlength="10" data-bind="textinput: model.bili.tixian_free_count"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">最低提现金额<span class="form-required">*</span></label>
                                    <div class="col-sm-4">
                                          <input type="text" name="tixian_min_fee" placeholder="请输入最低提现金额" class="form-control" required maxlength="10" data-bind="textinput: model.bili.tixian_min_fee"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">为用户赢取金额<span class="form-required">*</span></label>
                                    <div class="col-sm-4">
                                          <input type="text" name="get_all_point" placeholder="请输入为用户赢取金额" class="form-control" required maxlength="10" data-bind="textinput: model.bili.get_all_point"/>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">赚钱比例<span class="form-required">*</span></label>
                                    <div class="col-sm-4">
                                        <input type="text" name="zhuan_qian_bili" placeholder="请输入赚钱比例" class="form-control" required maxlength="6" min="0" max="1" data-bind="textinput: model.bili.zhuan_qian_bili" />
                                        <span class="help-block" style="margin-bottom:0">提示：0~0.99的两位小数</span>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">分销下注次数<span class="form-required">*</span></label>
                                    <div class="col-sm-4">
                                        <input type="text" name="choice_num" placeholder="请输入分销下注次数" class="form-control" required maxlength="30" data-bind="textinput: model.bili.choice_num" />
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-4">
                                        <button type="submit" class="btn btn-primary btn-lg btn-block" >确定</button>
                                        <button class="btn-submit" data-bind="click: updateBili"></button>
                                    </div>
                                </div>
                                
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--分销设置end-->
        
        
        <!--分享设置start-->
		<div class="row" style="padding: 20px;">
            <div class="panel-group" id="shareContent">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#shareContent" 
                               href="#collapseThree">
                                <h4>分享设置</h4>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <form class="form-horizontal sh-validate" id="shareForm">
                                
                                <!--
                                <div class="form-group">
                                  <label for="lastname" class="col-sm-2 control-label">分享图标<span class="form-required">*</span></label>
                                    <div class="col-sm-9">
                                    <input  id="share_logo" name="share_logo" type="hidden"/>
                                      <div class="pic" >
                                        <div style="display:inline" id="productPhotoPics" data-bind="foreach: {data: model.share.share_logo(), as: 'item'}">
                                          <span ><img  data-bind="attr:{src:$root.fmt.imgUrl(item)},click:$root.fmt.biggerShowImg.bind($root,item)" /><em><a href="javascript:void(0)"><img src="<@full_path path='img/close.png' />" width="20" height="20" id="close" data-bind="click:$root.removeImg.bind($root,$index(),1)"/></a></em></span>
                                        </div>
                                        <span data-bind="visible:model.share.share_logo().length < 1" >
                                          <div id="thinThumPhotoPics" class="addPic">
                                            <input type="file" onchange="viewModel.upload(this, 'backstage/other/share_logo/',1)" accept=".jpg,.jpeg,.bmp,.png" id="file_sequence_id_1" class="fileInput" />
                                          </div>
                                        </span>
                                      </div>
                                    </div>
                                </div>
                                -->
                                
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">分享标题<span class="form-required">*</span></label>
                                    <div class="col-sm-4">
                                          <input type="text" name="share_title" placeholder="分享标题" class="form-control" required maxlength="10" data-bind="textinput: model.share.share_title"/>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">分享描述<span class="form-required">*</span></label>
                                    <div class="col-sm-4">
                                          <textarea style="max-width:100%" name="share_content" data-bind="textinput: model.share.share_content" class="form-control" required maxlength="300" ></textarea>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">分享地址<span class="form-required">*</span></label>
                                    <div class="col-sm-4">
                                          <input type="text" name="share_url" placeholder="分享地址" class="form-control" required maxlength="300" data-bind="textinput: model.share.share_url"/>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-4">
                                        <button type="submit" class="btn btn-primary btn-lg btn-block" >确定</button>
                                        <button class="btn-submit" data-bind="click: updateShare"></button>
                                    </div>
                                </div>
                             </form>
                           </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--分享设置end-->
   </div>
     
     
 
	<script type="text/javascript" src="<@full_path path='js/lib/ckeditor/ckeditor.js'/>" ></script>	
	<script type="text/javascript" src="<@full_path path='js/backstage/other/list.js'/>" ></script>	
  </body>
</html>
