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