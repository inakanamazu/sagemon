//-------------------------------------------------
//2015/11/17 MOMONO
//-------------------------------------------------
package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDAO;

import model.Product;
import model.ShoppingCart;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

    //カート呼び出しいいいいいい
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();

		//セッションスコープ"shoppingcartがない場合"
		//shoppingCartインスタンスを生成しスコープに格納
		if(session.getAttribute("shoppingCart") == null){

			ShoppingCart ShoppingCart = new ShoppingCart();
			session.setAttribute("ShoppingCart", ShoppingCart);


		}

		request.setAttribute("cid", "0");

		//ShoppingCart.jspにフォワード
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ShoppingCart.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	HttpSession session = request.getSession();

	//コンソールでのセッション確認用
	System.out.println("session確認:"+session);

	//セッションスコープ"shoppingcartがない場合"
	//shoppingCartインスタンスを生成しスコープに格納
	if(session.getAttribute("ShoppingCart") == null){

		ShoppingCart ShoppingCart = new ShoppingCart();
		session.setAttribute("ShoppingCart", ShoppingCart);
	}

	//shoppingCartインスタンスに現セッションのShoppingCartの内容をセット
	ShoppingCart ShoppingCart = (ShoppingCart) session.getAttribute("ShoppingCart");

	//カート確認
	System.out.println("カート中身："+ ShoppingCart);

	request.setCharacterEncoding("UTF-8");

	int pid = Integer.parseInt(request.getParameter("pid"));

	//「買い物を続ける」用のカテゴリID判定
	int cid = Integer.parseInt(request.getParameter("cid"));

	//peoductのインスタンス
	Product product = new Product();

	//DB接続用のインスタンスDAO
	CartDAO dao = new CartDAO();

	try {
		product = dao.select(pid);

	}catch(Exception e){

		throw new ServletException(e);

	}finally{

		dao.closeConnection();

	}

	String action = request.getParameter("action");
	String url = null;

	//商品追加が選択された場合(商品詳細ページから)

	if("add".equals(action)){

		int qty = Integer.parseInt(request.getParameter("qty"));

		ShoppingCart.addProduct(product,qty);

		url = "/WEB-INF/jsp/ShoppingCart.jsp";

	}
	//request.setAttribute("tmp_c_id", tmp_c_id);
			request.setAttribute("cid", cid);

			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
	}
}


