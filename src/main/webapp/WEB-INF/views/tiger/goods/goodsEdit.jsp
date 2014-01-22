<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<tr>
	<td align="right" class="bg02">
		<label>名称 <span class="req">*</span></label>
	</td>
	<td colspan="5">
		<input name="title" type="text" maxlengtd="256" class="full bian" value="${pojo.title}"/>
	</td>
</tr>
<tr>
	<td align="right" class="bg02">
		<label>说明 <span class="req">*</span></label>
	</td>
	<td colspan="5">
		<input name="content" type="text" maxlengtd="512" class="full bian" value="${pojo.content}"/>
	</td>
</tr>
<tr>
	<td width="100" align="right" class="bg02">
		<label>折扣价 <span class="req">*</span></label>
	</td>
	<td class="small">
		<input name="salePrice" type="text" class="small bian" value="${pojo.salePrice}"/>
	</td>
	<td width="100" align="right" class="bg02">
		<label>市场价 <span class="req">*</span></label>
	</td>
	<td class="small">
		<input name="marketPrice" type="text" class="small bian" value="${pojo.marketPrice}"/>
	</td>
	<td width="100" align="right" class="bg02">
		<label>优惠券折扣 <span class="req">*</span></label>
	</td>
	<td class="small">
		<input name="couponDiscount" type="text" class="small bian" value="${pojo.couponDiscount}"/>
	</td>
</tr>
<tr>
	<td align="right" class="bg02">
		<label>图标 <span class="req">*</span></label>
	</td>
	<td colspan="5">
	</td>
</tr>
<tr>
	<td align="right" class="bg02">
		<label>图片 <span class="req">*</span></label>
	</td>
	<td colspan="5">
	</td>
</tr>
<tr>
	<td colspan="6" align="center">
		<input type="submit" class="btnClass" style="cursor: pointer;" value="提交" />
		&nbsp;&nbsp;
		<input type="button" class="btnClass" style="cursor: pointer;" value="返回" onclick="back('/tiger/goods/list.shtml?modulePath=${modulePath }')"/>
	</td>
</tr>