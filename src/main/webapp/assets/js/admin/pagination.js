var pagination = {
		/** 配置 */ 
		page : function(cfg){
			this.config = {
				items : cfg.items, //分页数据
				itemsContainerId : cfg.itemsContainerId, //分页数据显示区域
				itemsViewFormat : cfg.itemsViewFormat, //数据显示格式
				total : cfg.total, //总记录数
				pageSize : cfg.pageSize, //每页数据大小
				pageNo : cfg.pageNo, //当前页码
				paginationBarId : cfg.itemsContainerId + "_pgbar", //分页导航条显示区域
				pageQueryFunName : cfg.pageQueryFunName //分页数据查询函数名称
			};
			return this;
		},
		
		/** 显示分页数据 */ 
		show:function(){
			this.clear();
			//数据区域
		    var $itemsViewContainer = $("#" + this.config.itemsContainerId);
		    
		    //没有数据
		    if(!this.config.items || this.config.items.length <= 0){
		    	return;
		    }
		    
		    //显示下一页数据
		    var itemsViewFormat = this.config.itemsViewFormat;
		    this.config.items.forEach(function(item, index) {
		        var content =  itemsViewFormat(item);
		        $itemsViewContainer.append($(content));
		    });
		    this.showPageBar();
		},
		
		/** 显示分页导航条 */ 
		showPageBar:function(){
            //总记录数
			var total = this.config.total;
            //总页数
			var pageCount = parseInt((total - 1) / this.config.pageSize) + 1;
			//当前页码
			var pageNo = this.config.pageNo > pageCount ? pageCount : this.config.pageNo;
			//上一页页码
			var prevPageNo = pageNo - 1 <= 0 ? 1 : pageNo - 1;
			//下一页页码
			var nextPageNo = pageNo + 1 >= pageCount ? pageCount : pageNo + 1;
			
			//分页导航条中最多可以显示的页码数（奇数，以使当前页码左右两则的页码数相同）
			var pages = 9;
			//分页导航条中当前页码左则页码数
			var leftOfPageNo = parseInt(pages / 2);
			//分页导航条中当前页码右则页码数（减1，以使当前页码左右两则的页码数相同）
			var rightOfPageNo = pages - leftOfPageNo - 1;
			//每次查询后分页导航条中起始页码
			var beginPageNo = pageNo - leftOfPageNo;
			//每次查询后分页导航条中结束页码
			var endPageNo = pageNo + rightOfPageNo;
			
			if(pageCount <= pages){
				//总页数小于分页导航条中可显示的页码数时
				beginPageNo = 1;
				endPageNo = pageCount;
			}else{ 
				//总页数大于分页导航条中可显示的页码数时
				if(beginPageNo <= 0 ){
					//起始页码小于零，从1开始
					beginPageNo = 1;
				}
				 
				if(endPageNo > pageCount){
					//结束页码不能大于总页数，
					endPageNo = pageCount;
				}
			}

			//分页信息
			var paginationBarInfoWrapper = $("<div class='col-sm-5'>" +
				              "<div class='dataTables_info' aria-live='polite' role='status'>" + total + "条记录，共" + pageCount + "页，当前第"+ pageNo +"/" + pageCount + "页"+"</div>" +
				           "</div>");
			
			//分页导航条
			var paginationBarButtonsWrapper = $("<div class='col-sm-7'>" +
				               "<div class='dataTables_paginate paging_simple_numbers'>" +
				                 "<ul name='paginationBarButtons' class='pagination'></ul>" +
				               "</div>" +
				            "</div>");
			var paginationBarButtons = paginationBarButtonsWrapper.find("ul[name='paginationBarButtons']");
			
			//上一页按钮
			var prevPageBtn = $("<li class='disabled'><span class='glyphicon glyphicon-menu-left' aria-hidden='true'></span></li>");
			//如果还有上一页
			if(pageNo - 1 > 0){				
				prevPageBtn = $("<li><a href='javascript:" + this.config.pageQueryFunName + "(" + prevPageNo + ")' aria-label='Previous'><span class='glyphicon glyphicon-menu-left' aria-hidden='true'></span></a></li>");
			}
            paginationBarButtons.append(prevPageBtn);
			
			//中间页按钮
			for(var index = beginPageNo;index <= endPageNo; index++){
				//active,高亮显示当前页
				var active = index == pageNo ? "active" : "";
                paginationBarButtons.append($("<li class='paginate_button " + active + "'><a href='javascript:" + this.config.pageQueryFunName + "(" + index + ")' >" + index + "</a></li>"));
			}
			
			//下一页按钮
			var nextPageBtn = $("<li class='paginate_button disabled'><span class='glyphicon glyphicon-menu-right' aria-hidden='true'></span></li>");
			//如果还有下一页
			if(pageNo + 1 <= pageCount){
				nextPageBtn = $("<li><a href='javascript:" + this.config.pageQueryFunName + "(" + nextPageNo + ")' aria-label='Next'><span class='glyphicon glyphicon-menu-right' aria-hidden='true'></span></a></li>");
			}
            paginationBarButtons.append(nextPageBtn);
			
			//分页导航
			var paginationBarWrapper = $("<div id='" + this.config.paginationBarId + "' class='row'></div>")
            paginationBarWrapper.append(paginationBarInfoWrapper);
            paginationBarWrapper.append(paginationBarButtonsWrapper);
			//显示分页导航
            var itemsContainerWrapperId = $("#" + this.config.itemsContainerId).parent().attr("id") + "_wrapper";
            $("#" + itemsContainerWrapperId).append(paginationBarWrapper);
			 
		},
		
		clear : function(){
			 //清除数据
			 $("#" + this.config.itemsContainerId).html("");
			 //清除分页导航
			 $("#" + this.config.paginationBarId).remove();
		}
}