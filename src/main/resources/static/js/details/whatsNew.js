/*!
  * Bolg main JS.
 * 
 * @since: 1.0.0 2017/3/9
 * @author Way Lau <https://waylau.com>
 */
"use strict";
//# sourceURL=main.js
function more() {
	
	 $.ajax({ 
		 url: "/details/whatsNewList", 
		 contentType : 'application/json',
		 data:{
			 "async":true
		 },
		 success: function(data){
			 
			 $("#rightContainer").html(data);
	     },
	     error : function() {
	    	 toastr.error("error!");
	     }
	 });
}

function teaherDetail(id) {
	
	 $.ajax({ 
		 url: "/details/teaherDetail", 
		 contentType : 'application/json',
		 data:{
			 "id":id
		 },
		 success: function(data){
			 
			 $("#rightContainer").html(data);
	     },
	     error : function() {
	    	 toastr.error("error!");
	     }
	 });
}

// DOM 加载完再执行
$(function() {
	var _pageSize; // 存储用于搜索
	

	
	// 根据公告标题、页面索引、页面大小获取用户列表
	function getWhatsNewList(pageIndex, pageSize) {
		
		 $.ajax({ 
			 url: "/details/whatsNewList", 
			 contentType : 'application/json',
			 data:{
				 "async":true, 
				 "pageIndex":pageIndex,
				 "pageSize":pageSize
			 },
			 success: function(data){
				 
				 $("#rightContainer").html(data);
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	}
	
	// 分页
	$.tbpage("#mainContainer", function (pageIndex, pageSize) {
		getWhatsNewList(pageIndex, pageSize);
		_pageSize = pageSize;
	});
	

	
});