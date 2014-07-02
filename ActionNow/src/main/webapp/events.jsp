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
			<div id="event-all" style="display:none">
			<table id="events" class="display" cellspacing="0" width="100%">
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
			</div>
			<div id="event-none" style="display:none">
				<table border="1">
					<tr><td><span style="border: 1px">您尚未提交事件</span></td></tr>
				</table>
			</div>
	</BODY>
	<script type="text/javascript">
		$(function() {
			loadSubmitedEvent();
		});
	</script>
</HTML>