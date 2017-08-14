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
    
    
    
  </head>
  <body>
    <input type="hidden" id="queryUrl"  value="<@full_path path='backstage/loginLog/?act=QUERY' />"  />
    <input type="hidden" id="deleteUrl"  value="<@full_path path='backstage/loginLog/?act=DELETE' />"  />
    <input type="hidden" id="saveUrl"  value="<@full_path path='backstage/loginLog/?act=SAVE' />"  />
    <input type="hidden" id="managerType"  value="${Session.manager.managerType!}"  />
    <input type="hidden" id="fullPath"  value="<@full_path path='' />"  />
       
       <div id="mask" class="mask"></div>
       <div class="container" style="width:100%;">
            
        <!--二次确认框  提示框 -->
        <#include "/views/backstage/common/common_confirm.ftl"/>
        
         <div class="row">
                <!--栏目路径-->
                        <div class="header">
                <h1 class="page-title">管理员登录日志</h1>
              </div>
              <ul class="breadcrumb">
                <li><a href="javascript:void(0);">用户管理</a> <span class="divider">/</span></li>
                <li class="active">管理员登录日志</li>
            </ul>
          </div>
          
          <div class="row search"  >
            <!--查询-->
            <div class="pull-right" >
                 <input type="text" class="form-control"  placeholder="搜索账号" data-bind="textinput:model.search.userName">
                 <button type="button" class="btn btn-default btn-sm" data-bind="click: $root.searchByCondition.bind($root)">
                   <span class="glyphicon glyphicon-search"></span> Search
                 </button>
            </div>
          </div>
            
            <!--列表数据s-->
            <div class="row"  >
                <div class="col-md-12" >
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th width="15%">管理员账号</th>
                                <th width="60%">备注</th>
                                <th width="10%">ip</th>
                                <th width="15%">添加时间</th>
                            </tr>
                        </thead>
                        <tbody data-bind="foreach: {data: model.dataList, as: 'item'}, visible: model.dataList().length" >
                            <tr data-bind="css : { evenTr: $index()%2==1 }">
                                <td data-bind="text: item.userName" ></td>
                                <td data-bind="text: item.remarks" ></td>
                                <td data-bind="text: item.ip" ></td>
                                <td data-bind="text: $root.fmt.date(item.createTime)"></td>
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
<script type="text/javascript" src="<@full_path path='js/backstage/login_log/manager/service.js'/>" ></script>   
<script type="text/javascript" src="<@full_path path='js/backstage/login_log/manager/list.js'/>" ></script>  
  </body>
</html>
