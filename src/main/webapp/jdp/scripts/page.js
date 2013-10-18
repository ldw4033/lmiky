function turnPage(pageNo) {
	$("#page_currentPage").val(pageNo);
	$("#mainForm").submit();
}

function reloadPage() {
	$("#mainForm").submit();
}