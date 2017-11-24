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
	var avatarApi;
	
	// 获取编辑用户头像的界面
	$(".blog-content-container").on("click",".blog-edit-avatar", function () { 
		avatarApi = "/u/"+$(this).attr("userName")+"/avatar";
		$.ajax({ 
			 url: avatarApi, 
			 success: function(data){
				 $("#avatarFormContainer").html(data);
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	$('#userForm').submit(function(){
		if($("#confirmPassword").val() != $("#newPassword").val()){
			toastr.error("两次密码输入不一致，请重试！");
			return false;
		}
		return true;
	});
	
	$(":checkbox").change(function () {
		if($(this).is(':checked')){
			$("#CP").show();
		}else{
			$("#CP").hide();
		}
	});
	
	$("#submitEditAvatar").on("click", function () { 
		var avatar = $("#avatarImg")[0].src.split("/"); 
		if(avatar[3]=='u'){
			toastr.error("请选择头像！");
			return false;
		}else{
			// 获取 CSRF Token 
			var csrfToken = $("meta[name='_csrf']").attr("content");
			var csrfHeader = $("meta[name='_csrf_header']").attr("content");
			$.ajax({ 
				 url: "/uploadimg/saveAvatar", 
				 type: 'POST',
				 beforeSend: function(request) {
	                 request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token 
	             },
				 data:{
					 "avatar":avatar[3]
				 },
				 success: function(data){
					 
					 if (data.success) {
						 $(".blog-avatar").attr("src", $("#avatarImg")[0].src);
					 } else {
						 toastr.error(data.message);
					 }
			     },
			     error : function() {
			    	 toastr.error("error!");
			     }
			 });
		}
	});
	
	
	
});