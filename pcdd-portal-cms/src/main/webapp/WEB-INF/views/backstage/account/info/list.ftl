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
	<style>
    
        .modal-body>iframe {
                height: 400px;
        }
            
        @media screen and (max-device-height: 768px) {
            .modal-body>iframe {
                height: 400px;
            }
        }
        
        @media screen and (min-device-height: 900px) {
            .modal-body>iframe {
                height: 500px;
            }
        }
    </style>
    
  </head>
  <body>
  	<input type="hidden" id="queryUrl"  value="<@full_path path='backstage/accountInfo/?act=QUERY' />"  />
  	<input type="hidden" id="deleteUrl"  value="<@full_path path='backstage/accountInfo/?act=DELETE' />"  />
  	<input type="hidden" id="saveUrl"  value="<@full_path path='backstage/accountInfo/?act=SAVE' />"  />
  	<input type="hidden" id="managerType"  value="${Session.manager.managerType!}"  />
  	<input type="hidden" id="fullPath"  value="<@full_path path='' />"  />
       <!--提示框 -->
       <#include "/views/backstage/common/common_alert.ftl"/>
       
       <div id="mask" class="mask"></div>
       <div class="container" style="width:100%;">
       		
       	<!--二次确认框  提示框 -->
       	<#include "/views/backstage/common/common_confirm.ftl"/>
       	
       	<!--新增或修改-->	
		<#include "/views/backstage/account/info/form.ftl"/>	
		
		<!--转账详情-->
		<#include "/views/backstage/account/info/accountLog.ftl"/>  		
								
		 <div class="row">
				<!--栏目路径-->
						<div class="header">
	            <h1 class="page-title">收款账号管理</h1>
	          </div>
	          <ul class="breadcrumb">
	            <li><a href="javascript:void(0);">资金管理</a> <span class="divider">/</span></li>
	            <li class="active">收款账号管理</li>
            </ul>
		  </div>		  
			
		  <div class="row search"  >
		  	<!--查询-->
		  	<div class="pull-right" >
		  		类型&nbsp;
                <select  class="form-control"  data-bind="value:model.search.accountType">
                    <option value="">所有</option>
                    <option value="1">银行卡</option>
                    <option value="2">支付宝</option>
                    <option value="3">微信</option>
                </select>
		  		&nbsp;&nbsp;
			  	 创建时间&nbsp;
			  	 <input type="text"  id="createTimeBegin" class="form-control laydate-icon" placeholder="起始日期" data-bind="textinput:model.search.createTimeBegin" onclick="laydate()">
                                             至
                 <input type="text"  id="createTimeEnd" class="form-control laydate-icon"  placeholder="结束日期" data-bind="textinput:model.search.createTimeEnd" onclick="laydate()">
                 &nbsp;&nbsp;
                 <input type="text" class="form-control"  placeholder="搜索用户账号" data-bind="textinput:model.search.account">
                 &nbsp;&nbsp;
			  	 <input type="text" class="form-control"  placeholder="搜索开户名" data-bind="textinput:model.search.realName">
			  	 <button type="button" class="btn btn-default btn-sm" data-bind="click: $root.searchByCondition.bind($root)">
	               <span class="glyphicon glyphicon-search"></span> Search
	             </button>
				</div>
		  </div>

		  <div class="row" style="padding-left:.45em">
						
						<!-- 按钮触发新增框 -->
						<button class="btn btn-primary btn-sm" data-toggle="modal" data-bind="click:$root.showForm.bind($root)"
						    style="margin: .8em;border-radius: 0; " >
						   <span class="glyphicon glyphicon-plus" style="margin-right:5px;"></span>新增
						</button>
						
						<!-- 按钮触发删除框 -->
						<button class="btn btn-danger btn-sm" data-toggle="modal" 
						   style="margin: .8em;border-radius: 0;display: none;"  
						   data-bind="visible:model.checkItems().length,click:$root.showpop.bind($root,'delete','您确定要删除选中的广告吗？',$root.delItem.bind($root)) " >
						   <span class="glyphicon glyphicon-trash" style="margin-right:5px;"></span>删除
						</button>
						
		  </div>
		  
			<!--列表数据s-->
			<div class="row"  >
				<div class="col-md-12" >
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th class="c_align" width="5%" >
									<label class="check_box_all " for="checkbox" id="check_all" data-bind="click: $root.clickCheckall.bind($root)">
			    					<input type="checkbox" id="checkbox" class="chk_all" />
			    				</label>
								</th>
								<th width="8%">ID</th>
								<th width="10%">账号</th>
								<th width="6%">类型</th>
								<th width="6%">开户名</th>
								<th width="8%">银行名称</th>
								<th width="17%">开户行</th>
								<th width="10%">二维码</th>
								<th width="12%">创建时间</th>
								<th width="18%">操作</th>
							</tr>
						</thead>
						<tbody data-bind="foreach: {data: model.dataList, as: 'item'}, visible: model.dataList().length" >
							<tr data-bind="css : { evenTr: $index()%2==1 }">
								<td class="border_none not_show c_align" >
										<label class="check_box"  data-bind="attr: { id: 'chk' + $index(),for: 'checkbox_a' + $index()},click: $root.clickCheckbox.bind($root,$index(),item)">
											<input type="checkbox" class="chk_1" data-bind="attr: { id: 'checkbox_a' + $index() }" />
										</label>
								</td>
								<td data-bind="text: item.id,click: $root.showForm.bind($root, item)"></td>
								<td data-bind="text: item.account"></td>
								<td data-bind="text: $root.fmt.accountType(item.accountType),click: $root.showForm.bind($root, item)" ></td>
								<td data-bind="text: item.realName,click: $root.showForm.bind($root, item)"></td>
								<td data-bind="text: item.bankName,click: $root.showForm.bind($root, item)" ></td>
								<td data-bind="text: item.openCardAddress,click: $root.showForm.bind($root, item)" ></td>
                                <td data-bind="html: $root.fmt.img(item.photo),click: $root.fmt.biggerShowImg.bind($root, item.photo)" ></td>
								<td data-bind="text: $root.fmt.date(item.createTime,'yyyy-MM-dd hh:mm'),click: $root.showForm.bind($root, item)" ></td>
								<td>
									<a href="javascritp:void(0);" data-bind="click:$root.showForm.bind($root, item)"><span class="glyphicon glyphicon-pencil"></span>编辑</a> 
								    &nbsp;
                                    <a href="javascritp:void(0);" data-bind="click:$root.showAccountLog.bind($root, item)"><span class="glyphicon glyphicon-eye-open"></span>转账详情</a> 
								    &nbsp;
                                    <a href="javascritp:void(0);" data-bind="click:$root.showpop.bind($root,'delete','您确定要删除该收款账号吗？',$root.delItem.bind($root,item.id),1)"><span class="glyphicon glyphicon-trash"></span>删除</a>
								</td>
							</tr>
						</tbody>
						<tfoot data-bind="visible: !model.dataList().length" >
							<tr>
								<td colspan="100" class="c_align">暂无数据</td>
							</tr>
						</tfoot>
					</table>
			   </div>
			</div>
			<!--列表数据e-->
			<!--分页-->
		    <#include "/views/backstage/common/page.ftl"/>
</div>
<#include "/views/backstage/common/common_script.ftl"/>
<script type="text/javascript" src="<@full_path path='js/backstage/account/info/service.js'/>" ></script>	
<script type="text/javascript" src="<@full_path path='js/backstage/account/info/list.js'/>" ></script>	
  </body>
</html>
