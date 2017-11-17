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
	
});