package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Address;
import model.Payment;
import model.SelectAddressLogic;
import model.User;
/**
 * Servlet implementation class SelectAddressServlet
 */
@WebServlet("/SelectAddressServlet")
public class SelectAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectAddressServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");
    HttpSession session = request.getSession();
		User u = (User)(session.getAttribute("User"));
    //以下、テスト用
//    User u = new User();
//    u.setUid(1);

		List<Address> ls =new ArrayList<Address>();
		List<Payment> ls2 = new ArrayList<Payment>();
		SelectAddressLogic lo = new SelectAddressLogic();
		try {
			ls = lo.getAddress(u.getUid());
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		if(ls.size()>0){
			session.setAttribute("Address", ls);
		}
		try{
			ls2 = lo.getPayment();
		}catch(Exception e){
			e.printStackTrace();
		}
		for(int i=0;i<ls2.size();i++) System.out.println("ls格納後:"+(ls2.get(i)).getPayid()+(ls2.get(i)).getPayname());
		session.setAttribute("Payment", ls2);
    RequestDispatcher dispatcher =
        request.getRequestDispatcher
            ("/WEB-INF/jsp/selectAddress.jsp");
    dispatcher.forward(request, response);



	}

}
