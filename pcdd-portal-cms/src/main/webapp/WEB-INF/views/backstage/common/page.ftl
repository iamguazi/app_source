                <div class="row" style="margin-top:20px;margin-bottom:20px;" data-bind="visible: model.dataList().length">
                    <!-- 分页详情-->
					<div class="col-md-4 col-sm-4 col-xs-4 col-lg-4"  >
						<h5>总共<i id="totalCount" style="color:red" data-bind="text:model.page.totalCount"></i>条记录,当前<i style="color:red" data-bind="text:model.search.pageNo"></i>/<i style="color:red" data-bind="text:model.page.pageCount"></i>页,
							每页
							<select id="changePageSize">
					         <option value="10" selected="selected">10</option>
					         <option value="30" >30</option>
					         <option value="50" >50</option>
      		   				</select>
         				  显记录示
          			    </h5>
					</div>
					
					<!--页数列表-->
					<div class="col-md-5 col-sm-5 col-xs-5 col-lg-5 col-md-offset-1 col-sm-offset-1 col-xs-offset-1 col-lg-offset-1"  >
							<ul class="pagination center-block" style="margin:0;">
							<li id="firstPage" data-bind="click:firstPage">
								 <a href="javascript:void(0);">&lt;&lt;</a>
							</li>
							<li id="prevPage" class="disabled" >
								 <a href="javascript:void(0);">&lt;</a>
							</li>
							<li class="active" common_page="true" page_no="1">
								 <a href="javascript:void(0);">1</a>
							</li>
							<li common_page="true" page_no="2">
								 <a href="javascript:void(0);">2</a>
							</li>
							<li common_page="true" page_no="3">
								 <a href="javascript:void(0);">3</a>
							</li>
							<li common_page="true" page_no="4">
								 <a href="javascript:void(0);">4</a>
							</li>
							<li common_page="true" page_no="5">
								 <a href="javascript:void(0);">5</a>
							</li>
							<li id="nextPage">
								 <a href="javascript:void(0);">&gt;</a>
							</li>
							
							<li id="lastPage">
								 <a href="javascript:void(0);" >&gt;&gt;</a>
							</li>
						</ul>
					</div>
					
					<!--输入页码-->
					<div class="col-md-2 col-sm-2 col-xs-2 col-lg-2" >
						<div class="input-group pull-right" style="cursor:pointer;">
				         <input id="goToPageNo" type="text" class="form-control">
				         <span class="input-group-addon btn btn-primary" data-bind="click:goPage">Go</span>
				        </div>
					</div>
					
	           </div>	
