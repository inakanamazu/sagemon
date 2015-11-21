<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 以下、User情報をセッションに持たせるためのテスト用コード --%>
<%@ page import="model.User" %>
<%
User u = new User();
u.setFamilyname("test");
u.setUid(1);
session.setAttribute("User", u);
%>
<%-- ShoppingCartのテスト用Seesionを発行 --%>
<%@page import="model.ShoppingCart,model.Product,java.util.*"%>
<%
List<Product> pl = new ArrayList<Product>();
ShoppingCart sp = new ShoppingCart();
for(int i=1;i<10;i++){
	Product p = new Product();
	p.setPid(i);
	p.setQty(i);
	pl.add(p);
}
sp.setProductsCart(pl);

session.setAttribute("ShoppingCart",sp);
%>


<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ここにタイトルを挿入</title>
</head>
<body>
<jsp:include page="header.jsp" />
<form method="post" action="SelectAddressServlet">
<input type="submit" value="送信">
</form>

</body>
</html>
