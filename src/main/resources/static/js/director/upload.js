/*!
 * Avatar JS.
 * 
 * @since: 1.0.0 2017/4/6
 * @author Way Lau <https://waylau.com>
 */
"use strict";
//# sourceURL=main.js
 
// DOM 加载完再执行
$(function() {
	// 提交变更后，清空表单
	$("#submit").on("click",function(){
		var formData = new FormData($( "#uploadForm" )[0]);
		
			$.ajax({ 
				 url: "/director/upload", 
				 type: 'POST',
				 data:formData,
				 cache: false,  
		         processData: false,  
		         contentType: false,
				 success: function(data){
					 $('#uploadForm')[0].reset();  
					 
					 if (data.success) {
						 toastr.success("上传成功！");
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