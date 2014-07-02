<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String openid = request.getParameter("openid");
%>
<HTML>
	<HEAD>
		<META lang="UTF-8" http-equiv=Content-Type content="text/html">
		<link rel="stylesheet" type="text/css" href="resources/DataTables/css/jquery.dataTables.css">
		<link rel="stylesheet" type="text/css" href="resources/DataTables/css/jquery.dataTables_themeroller.css">
		<script type="text/javascript" src="jquery-1.11.1.js"></script>
		<script type="text/javascript" src="resources/DataTables/js/jquery.dataTables.js"></script>
		<script type="text/javascript" src="my.js"></script>
	</HEAD>
	<BODY>
		<input id="openid" value = "<%=openid%>" style="display:none"/>
		<div>
			<span>
				所属类型
			</span>
			<div>
				<select id="category" onchange="loadType(this.value)">
					<option value="0">请选择</option>
					<option value="1">1</option>
					<option value="2">2</option>
				</select>
				<select id="type">
					<option value="0">请选择</option>
					<option value="11">11</option>
					<option value="12">12</option>
				</select>
			</div>
		</div>
		<div>
			<span>
				所在地区：
			</span>
			<div>
				<select id="province" onchange="loadCity(this.value)">
				</select>
				<select id="city" onchange="loadCounty(this.value)">
				</select>
				<select id="county">
				</select>
				<button type="button" onclick="loadEvent()">确定</button>
			</div>
		</div>
		<div id="eventsdiv" style="display: none;">
			<div id="event-all">
			<table id="events" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th>type</th>
						<th>businessCircle</th>
						<th>address</th>
						<th>time</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th>type</th>
						<th>businessCircle</th>
						<th>address</th>
						<th>time</th>
					</tr>
				</tfoot>
			</table>
			<table border="1">
			<tr><td><span style="border: 1px">该事件是否与系统内其他事件一致，是请点击取消按钮，取消上报，不是请填写详细街道地址，并点击提交按钮。</span></td></tr>
			</table>
			<br/>
			</div>
			<div id="event-none">
				<table border="1">
					<tr><td><span style="border: 1px">系统中不存在类似事件，请填写详细街道地址，并点击提交按钮。</span></td></tr>
				</table>
			</div>
			<input id="address"/>
			<button id="submit" onclick="submit()">submit</button>
			<button id="cancel" onclick="cancel()">cancel</button>
		</div>
	</BODY>
	<script type="text/javascript">
		$(function() {
			loadProvince();
			loadCategory();
		});
	</script>
</HTML>