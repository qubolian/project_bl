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
	
    CKEDITOR.replace( 'mark',{ height: '400px', width: 'auto' } );
	
	// 提交变更后，清空表单
	$("#update").on("click",function(){
		
		  var editor_data = CKEDITOR.instances.mark.getData();
		  
		$.ajax({ 
			 url: "/super/missionEdit", 
			 data:{
				 "mark":editor_data
			 },
			 success: function(data){
				
				 
				 if (data.success) {
					 // 更新内容
					 toastr.info("更新成功");
					 //getMission();
				 } else {
					 toastr.error("更新失败");
					 getMission();
				 }

		     },
		     error : function() {
		    	 toastr.error("更新失败!");
		    	 getMission();
		     }
		 });
	});
	
	
	$("#reload").on("click",function(){
		getMission();
	});
	
	function getMission() {
		
		 $.ajax({ 
			 url: "/super/missionList", 
			
			 contentType : 'application/json',
			 
			 data:{
				// "mark":$("#mark").val()
			 },
			 success: function(data){
				 
				 CKEDITOR.instances.mark.setData(data.body.mark);
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	}
	
});