<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- HEADER --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="login"><p>
<c:choose>
<c:when test="${empty User.familyname }">
<%-- 未ログインのときの表示項目 --%>
ようこそゲストさん｜<a href="LoginServlet">ログイン</a>
</c:when>
<c:when test="${!empty User.familyname }">
<%-- ログイン時の表示項目 --%>
ようこそ${User.familyname }&nbsp;${User.firstname }さん｜
<a href="LogoutServlet">ログアウト</a>
</c:when>
</c:choose>
</p></div>

<div class="cart"><p>
<c:choose>
<c:when test="${fn:length(ShopingCart)>0}">
<%-- ショッピングカートにモノが入っている場合 --%>
ショッピングカートに商品が入っています。
<a href="ConfirmServlet">購入確認画面へ</a>
</c:when>
</c:choose>
</p></div>
<nav>
<ul style="display:inline;">
<li style="display:inline;"><a href="index.jsp">トップページ</a></li>
<li style="display:inline;"><a href="ProductListServlet">商品一覧</a>
<li style="display:inline;"><a href="#" onclick="alert('工事中');">さげもんとは</a></li>
<li style="display:inline;"><a href="#" onclick="alert('工事中');">お問い合わせ</a></li>
</ul>
</nav>
<%-- /HEADER --%>
