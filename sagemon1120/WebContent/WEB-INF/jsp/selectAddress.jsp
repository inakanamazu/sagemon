<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 以下、User情報をセッションに持たせるためのテスト用コード --%>
<%@ page import="model.User" %>
<%
User u = new User();
u.setFamilyname("test");
u.setUid(1);
session.setAttribute("User", u);
%>


<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>送付先の確認</title>
</head>
<body>
<jsp:include page="/header.jsp" />

<form method="post" action ="OrderCompleteServlet">
<table border="1"><tr><td valign="top">
	<label for="aid">■過去の送付履歴から検索</label><br>
	<select name="aid">
	<option value="" selected>新たに送付先を入力する→</option>
	<c:forEach items="${Address }" var="vo">
	<option value="<c:out value="${vo.aid }" />">
	<c:out value="${vo.aname }" />
	<c:out value="${vo.pref }" />&nbsp;
	<c:out value="${vo.city }" />&nbsp;
	<c:out value="${vo.address1 }" />&nbsp;
	<c:out value="${vo.name }" />
	</option>
	</c:forEach>

	</select>
	</td><td>
	■新たに送付先を入力する<br>
	<c:if test="${!empty ErrMsg }">
	<p>${ErrMsg }</p>
	</c:if>
	<label>◎郵便番号　：<input type="number" name="zip3" value="${EditAddress.zip3 }">－
	                   <input type="number" name="zip4" value="${EditAddress.zip4 }"></label><br>
	<label>◎都道府県　：<input type="text" name="pref" value="${EditAddress.pref }"></label><br>
	<label>◎市区群町村：<input type="text" name="city" value="${EditAddress.city }"></label><br>
	<label>◎番地まで　：<input type="text" name="address1" value="${EditAddress.address1 }"></label><br>
	<label>○建物名など：<input type="text" name="address2" value="${EditAddress.address2 }"></label><br>
	<label>※電話番号　：<input type="text" name="tel" value="${EditAddress.tel }"></label><br>
	<label>※携帯電話　：<input type="text" name="mobile" value="${EditAddress.mobile }"></label><br>
	<label>◎受取人氏名：<input type="text" name="name" value="${EditAddress.name }"></label><br>
	<label>○送付先ラベル名(管理用)：<input type="text" name="aname" value="${EditAddress.aname }"></label>
	</td></tr>
</table>
■支払い方法の選択<br>
<c:forEach items="${Payment }" var="vo2">
	<label>
		<c:out value="${vo2.payname }" />
		<input type="radio" name="payid" value="<c:out value="${vo2.payid }" />">
	</label><br>
</c:forEach>

<a href="ShopingCartServlet">カートに戻る</a>|
<input type="submit" value="注文を確定させる">
</form>
</body>
</html>
