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
	function doUpload() {  
	     var formData = new FormData($( "#uploadForm" )[0]);  
	     $.ajax({  
	          url: '/upload/testuploadimg' ,  
	          type: 'POST',  
	          data: formData,  
	          async: false,  
	          cache: false,  
	          contentType: false,  
	          processData: false,  
	          success: function (returndata) {  
	              alert(returndata);  
	          },  
	          error: function (returndata) {  
	              alert(returndata);  
	          }  
	     });  
	}  
	
});