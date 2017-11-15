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
	
	$('.form_date1').datetimepicker({
	    language:  'zh-CN',
	   
	    weekStart: 1,
	    todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
	
	}).on('changeDate', function (e) {  
	    $('.form_date2').datetimepicker('setStartDate', $('#startTime').val()); 
	}); 
	
	$('.form_date2').datetimepicker({
	    language:  'zh-CN',
	    
	    weekStart: 1,
	    todayBtn:  1,
	    autoclose: 1,
	    todayHighlight: 1,
	    startView: 2,
	    minView: 2,
	    forceParse: 0
	}).on('changeDate', function (e) {  
	    $('.form_date1').datetimepicker('setEndDate', $('#endTime').val()); 
	});
	

});