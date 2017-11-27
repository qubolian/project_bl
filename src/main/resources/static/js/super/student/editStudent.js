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
	
	
	
	// 根据年级、页面索引、页面大小获取组队方法列表
	function getStudentByName(pageIndex, pageSize) {
		
		 $.ajax({ 
			 url: "/super/studentList", 
			 contentType : 'application/json',
			 data:{
				 "async":true, 
				 "pageIndex":pageIndex,
				 "pageSize":pageSize,
				 "name":$("#searchName").val()
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
		getStudentByName(pageIndex, pageSize);
		_pageSize = pageSize;
	});
   

	// 提交变更后，清空表单
	$("#submitEdit").on("click",function(){
		$.ajax({ 
			 url: "/super/addStudent", 
			 type: 'POST',
			 data:$('#studentForm').serialize(),
			 success: function(data){
				 $('#studentForm')[0].reset();  
				 
				 if (data.success) {
					 // 从新刷新主界面
					 getStudentByName(0, _pageSize);
				 } else {
					 toastr.error(data.message);
				 }
		     },
		     error: function() {
		    	 toastr.error("error!");
		     }
		 });
	});

});