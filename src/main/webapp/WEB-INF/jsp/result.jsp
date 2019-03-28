<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <title>EHR数据中间件</title>
</head>
<body>
EHR数据查重页面<br>
<form action="/ehrMidware/checkValues" method="POST">
    姓名全拼：<input type="text" value="${pinyinName}" name="pinyinName" >${pinyinNameFlag}<br>
    手机号码：<input type="text" value="${phoneNumber}" name="phoneNumber">${phoneNumberFlag}<br>
    阿里邮箱：<input type="text" value="${email}" name="email">${emailFlag}<br>
    <input type="submit">
</form>
<br><br><br><br><br><hr>
<a href="/ehrMidware/syncAllOrganization">全量同步组织架构</a><br>
<a href="/ehrMidware/syncInfluencedOrganization">增量同步组织架构</a><br>
<a href="/ehrMidware/syncAllStuff">全量同步人员</a><br>
<a href="/ehrMidware/syncInfluencedStuff">增量同步人员</a>
</body>
</html>
