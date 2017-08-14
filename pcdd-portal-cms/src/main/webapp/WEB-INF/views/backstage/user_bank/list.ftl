<!DOCTYPE html>
<html>
	<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>分销管理</title>

    <!-- Bootstrap -->
    <link href="<@full_path path='js/lib/bootstrap-3.3.5/dist/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<@full_path path='css/main.css'/>" rel="stylesheet">
    <link href="<@full_path path='js/lib/cover/jquery.cover.css'/>" rel="stylesheet">
	
	<#include "/views/backstage/common/common_script.ftl"/>
    
    <style>
        .lightTitle {
            color: #2797DA;
        }
    </style>
    
  </head>
  <body>
  	<input type="hidden" id="queryUrl"  value="<@full_path path='backstage/userBankInfos/?act=QUERY' />"  />
  	<input type="hidden" id="deleteUrl"  value="<@full_path path='backstage/userBankInfos/?act=DELETE' />"  />
  	<input type="hidden" id="saveUrl"  value="<@full_path path='backstage/userBankInfos/?act=SAVE' />"  />
  	<input type="hidden" id="managerType"  value="${Session.manager.managerType!}"  />
  	<input type="hidden" id="fullPath"  value="<@full_path path='' />"  />
       <!--提示框 -->
       <#include "/views/backstage/common/common_alert.ftl"/>
       
       <div id="mask" class="mask"></div>
       <div class="container" style="width:100%;">
       		
       	<!--二次确认框  提示框 -->
       	<#include "/views/backstage/common/common_confirm.ftl"/>

       	<!--修改页面 -->
       	<#include "/views/backstage/user_bank/form.ftl"/>
       	<#if userId??>
       	<#else>
		 <div class="row">
			<!--栏目路径-->
                <div class="header">
                  <h1 class="page-title">用户银行卡</h1>
                </div>
                <ul class="breadcrumb">
                    <li><a href="javascript:void(0);">用户管理</a> <span class="divider">/</span></li>
                    <li class="active">用户银行卡</li>
                </ul>
	        
		  </div>		  
		</#if>	
		  <div class="row search"  >
		  	<!--查询-->
		  	<div class="pull-right" >
			  	 <input type="text" class="form-control"  placeholder="请输入账号" data-bind="textinput:model.search.account">
			  	 &nbsp;&nbsp;
			  	 <input type="text" class="form-control"  placeholder="请输入开户姓名" data-bind="textinput:model.search.realName">
			  	 <button type="button" class="btn btn-default btn-sm" data-bind="click: $root.searchByCondition.bind($root)">
	               <span class="glyphicon glyphicon-search"></span> Search
	             </button>
		    </div>
		  </div>

		  <div class="row" style="padding-left:.45em">
						
						<!-- 按钮触发新增框 -->
						<!--<button class="btn btn-primary btn-sm" data-toggle="modal" style="display: none;" data-bind="click:$root.showForm.bind($root)"
						    style="margin: .8em;border-radius: 0; " >
						   <span class="glyphicon glyphicon-plus" style="margin-right:.8em;"></span>添加
						</button>-->
						
						<!-- 按钮触发删除框 -->
						<button class="btn btn-danger btn-sm" data-toggle="modal" 
						   style="margin: .8em;border-radius: 0;display: none;"  
						   data-bind="visible:model.checkItems().length,click:$root.showpop.bind($root,'delete','您确定要银行卡信息吗？',$root.delItem.bind($root)) " >
						   <span class="glyphicon glyphicon-remove" style="margin-right:.8em;"></span>删除
						</button>
		  </div>
		  
			<!--列表数据s-->
			<div class="row"  >
				<div class="col-md-12" >
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
							<th class="c_align" width="4%" >
                                    <label class="check_box_all " for="checkbox" id="check_all" data-bind="click: $root.clickCheckall.bind($root)">
                                    <input type="checkbox" id="checkbox" class="chk_all" />
                                </label>
                                </th>
								<th width="6%">ID</th>
								<th width="8%">账号</th>
								<th width="10%">用户名</th>
								<th width="10%">手机号</th>
								<th width="10%">开户姓名</th>
								<th width="15%">开户银行</th>
								<th width="15%">银行卡号</th>
								<th width="15%">开户地址</th>
								<th width="7%">操作</th>
							</tr>
						</thead>
						<tbody data-bind="foreach: {data: model.dataList, as: 'item'}, visible: model.dataList().length" >
							<tr data-bind="css : { evenTr: $index()%2==1 }">
							   <td class="border_none not_show c_align" >
                                        <label class="check_box"  data-bind="attr: { id: 'chk' + $index(),for: 'checkbox_a' + $index()},click: $root.clickCheckbox.bind($root,$index(),item)">
                                            <input type="checkbox" class="chk_1" data-bind="attr: { id: 'checkbox_a' + $index() }" />
                                        </label>
                                </td>
								<td data-bind="text: item.id"></td>
								<td data-bind="text: item.account"></td>
								<td data-bind="text: item.nickName" ></td>
								<td data-bind="text: item.mobile" ></td>
								<td data-bind="text: item.realName" ></td>
								<td data-bind="text: item.bankName" ></td>
								<td data-bind="text: item.bankNo" ></td>
								<td data-bind="text: item.openCardAddress" ></td>
								<td>
								    <a href="javascript:void(0);" data-bind="click:$root.showForm.bind($root, item)"><span class="glyphicon glyphicon-pencil"></span>编辑</a> &nbsp;
								    <a href="javascritp:void(0);" data-bind="click:$root.showpop.bind($root,'delete','您确定要删除选中的银行卡信息吗？',$root.delItem.bind($root,item.id),1)"><span class="glyphicon glyphicon-trash"></span>删除</a>
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
<script>
    var parentCode = '${code!}';
    var id = "${userId!}";
</script>
<script type="text/javascript" src="<@full_path path='js/backstage/user_bank/service.js'/>" ></script>	
<script type="text/javascript" src="<@full_path path='js/backstage/user_bank/list.js'/>" ></script>	
  </body>
</html>
