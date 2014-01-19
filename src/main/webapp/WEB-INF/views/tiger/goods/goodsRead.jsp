<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<tr>
	<td align="right" class="bg02">
		<label>名称</label>
	</td>
	<td colspan="5">
		${pojo.title}
	</td>
</tr>
<tr>
	<td align="right" class="bg02">
		<label>说明</label>
	</td>
	<td colspan="5">
		${pojo.content}
	</td>
</tr>
<tr>
	<td width="100" align="right" class="bg02">
		<label>折扣价</label>
	</td>
	<td class="small">
		${pojo.salePrice}
	</td>
	<td width="100" align="right" class="bg02">
		<label>市场价</label>
	</td>
	<td class="small">
		${pojo.marketPrice}
	</td>
	<td width="100" align="right" class="bg02">
		<label>优惠券折扣</label>
	</td>
	<td class="small">
		${pojo.couponDiscount}
	</td>
</tr>
<tr>
	<td align="right" class="bg02">
		<label>图标</label>
	</td>
	<td colspan="5">
	</td>
</tr>
<tr>
	<td align="right" class="bg02">
		<label>图片</label>
	</td>
	<td colspan="5">
	</td>
</tr>
<tr>
	<td colspan="6" align="center">
		<input type="button" class="btnClass" style="cursor: pointer;" value="返回" onclick="back('/tiger/goods/list.shtml?modulePath=${modulePath }')"/>
	</td>
</tr>