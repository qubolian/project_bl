/*!
 * blog.html 页面脚本.
 * 
 * @since: 1.0.0 2017-03-26
 * @author Way Lau <https://waylau.com>
 */
"use strict";
//# sourceURL=blog.js

// DOM 加载完再执行
$(function() {
    $('#cropinput').change(function(){
    	
    	var formData = new FormData($( "#avatarform" )[0]);
    	var img = $("#cropImg > img");
		$.ajax({ 
			 url: "/uploadimg/upload", 
			 type: 'POST',
			 data:formData,
			 cache: false,  
	         processData: false,  
	         contentType: false,
			 success: function(data){
				 
				 if (data.success) {
					 img.attr("src", data.body);
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