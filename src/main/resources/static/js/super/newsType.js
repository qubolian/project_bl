/*!
  * Bolg main JS.
 * 
 * @since: 1.0.0 2017/3/9
 * @author Way Lau <https://waylau.com>
 */
"use strict";
//# sourceURL=main.js
 
// DOM 加载完再执行
$(function() {

	var _pageSize; // 存储用于搜索

	// 根据用户名、页面索引、页面大小获取用户列表
	function getNewsTypetByMessageType(pageIndex, pageSize) {
		
		 $.ajax({ 
			 url: "/super/newsTypeList", 
			 contentType : 'application/json',
			 data:{
				 "async":true, 
				 "pageIndex":pageIndex,
				 "pageSize":pageSize,
				 "messageType":$("#searchName").val()
			 },
			 success: function(data){
				 $("#mainContainer").html(data);
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	}
	
	// 分页
	$.tbpage("#mainContainer", function (pageIndex, pageSize) {
		getNewsTypetByMessageType(pageIndex, pageSize);
		_pageSize = pageSize;
	});
   
	// 搜索
	$("#searchNameBtn").click(function() {
	
		getNewsTypetByMessageType(0, _pageSize);
	});
	
	
	// 获取添加信息的界面
	$("#addNewsType").click(function() {
		$.ajax({ 
			 url: "/super/addNewsType", 
			 success: function(data){
				
				 $("#formContainer").html(data);
		     },
		     error : function(data) {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	// 获取编辑信息的界面
	$("#rightContainer").on("click",".super-edit-newsType", function () { 
		$.ajax({ 
			 url: "/super/editNewsType/" + $(this).attr("newsTypeId"), 
			 success: function(data){
				 $("#formContainer").html(data);
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
		
	});
	
	
	
	// 提交变更后，清空表单
	$("#submitEdit").click(function() {
		$.ajax({ 
			 url: "/super/addNewsType", 
			 type: 'POST',
			 data:$('#newsTypeForm').serialize(),
			 success: function(data){
				 $('#newsTypeForm')[0].reset();  
				 
				 if (data.success) {
					 // 从新刷新主界面
					 getNewsTypetByMessageType(0, _pageSize);
				 } else {
					 toastr.error(data.message);
				 }

		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	// 删除信息内容
	$("#rightContainer").on("click",".super-delete-newsType", function () { 
		// 获取 CSRF Token 
		var csrfToken = $("meta[name='_csrf']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
		$.ajax({ 
			 url: "/super/newsType/" + $(this).attr("newsTypeId") , 
			 type: 'DELETE', 
			 beforeSend: function(request) {
                 request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token 
             },
			 success: function(data){
				 if (data.success) {
					 // 从新刷新主界面
					 getNewsTypetByMessageType(0, _pageSize);
				 } else {
					 toastr.error(data.message);
				 }
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
	
});