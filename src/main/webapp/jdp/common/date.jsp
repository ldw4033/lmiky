<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script language="javascript" type="text/javascript" src="${scriptPlugin }/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function buildBeginDate(obj) {
		var value = $(obj).val();
		if(value != '') {
			value += ' 00:00:00';
			$(obj).val(value);
		}
	}
	
	function buildEndDate(obj) {
		var value = $(obj).val();
		if(value != '') {
			value += ' 23:59:59';
			$(obj).val(value);
		}
	}
</script>