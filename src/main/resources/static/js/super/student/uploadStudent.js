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
		
		var formData = new FormData($( "#uploadStudentForm" )[0]);
		$.ajax({ 
			 url: "/super/uploadStudent", 
			 type: 'POST',
			 data:formData,
			 cache: false,  
	         processData: false,  
	         contentType: false,
			 success: function(data){
				 $('#uploadStudentForm')[0].reset();  
				 
				 if (data.success) {
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