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
	
	// 提交变更后，清空表单
	$("#submitEdit").on("click",function(){
		if($("#upperLimit").val() > $("#lowerLimit").val()){
			$.ajax({ 
				 url: "/director/addCourse", 
				 type: 'POST',
				 data:$('#courseForm').serialize(),
				 success: function(data){
					 
					 $('#courseForm')[0].reset();  
					 
					 if (data.success) {
						 // 从新刷新主界面
						 getCourseByName(0, _pageSize);
					 } else {
						 toastr.error(data.message);
					 }
	
			     },
			     error : function() {
			    	 toastr.error("error!");
			     }
			 });
		}else{
			toastr.error("上限不能低于下限");
		}
	});

	
	/*// 删除信息内容
	$("#rightContainer").on("click",".director-delete-course", function () { 
		// 获取 CSRF Token 
		var csrfToken = $("meta[name='_csrf']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
		$.ajax({ 
			 url: "/director/course/" + $(this).attr("courseId") , 
			 type: 'DELETE', 
			 beforeSend: function(request) {
                 request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token 
             },
			 success: function(data){
				 if (data.success) {
					 // 从新刷新主界面
					 getCourseByName(0, _pageSize);
				 } else {
					 toastr.error(data.message);
				 }
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
	*/
	
});