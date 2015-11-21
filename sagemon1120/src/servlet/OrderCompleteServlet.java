package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Address;
import model.OrderBasic;
import model.OrderCompleteLogic;
import model.OrderDetail;
import model.ShoppingCart;
import model.User;
/**
 * Servlet implementation class OrderCompleteServlet
 */
@WebServlet("/OrderCompleteServlet")
public class OrderCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderCompleteServlet() {
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
		//パラメータ群の取得
		request.setCharacterEncoding("UTF-8");
    RequestDispatcher dispatcher;

    HttpSession session = request.getSession();
    OrderCompleteLogic lo = new OrderCompleteLogic();
    Address a=null;
    String buf = "";
    String errMsg="";
    int payid=-1;
    int aid = -1;
    int oid = -1;

    ShoppingCart sp = (ShoppingCart)(session.getAttribute("ShoppingCart"));

    if(sp==null){
    	errMsg +="ショッピングカートの中身がありません。\n";
    	//TODO とりあえず住所入力画面へ
    	request.setAttribute("ErrMsg", errMsg);
  		dispatcher = request.getRequestDispatcher("/SelectAddressServlet");
      dispatcher.forward(request, response);

    }else{
    	lo.debugsp(sp);
    }

    //決済方法の入力チェック→わざわざ引数で渡すのも面倒なので、インラインで
    buf=request.getParameter("payid");
    if(buf==null){
    	errMsg+="決済方法が入力されていません。\n";
    }else if(buf.equals("")){
    	errMsg+="決済方法が入力されていません。\n";
    }else{
    	payid = Integer.parseInt(buf);
    }

    //ユーザ情報はセッションから
		User u = (User)(session.getAttribute("User"));
		System.out.println("OCS_uid:"+u.getUid());
		//送付先情報はaidの有無で分岐
		buf="";
    buf = (String)(request.getParameter("aid"));
    System.out.println("buf:"+buf+ (buf==null) +(buf.equals("")));
    boolean f =(buf==null);
    if(!f) f = (buf.equals(""));
		if(f){
			//フォームで送られたデータからAddressを作成
			a=new Address();
			a.setUid(u.getUid());
			a.setZip3((String)(request.getParameter("zip3")));
			a.setZip4((String)(request.getParameter("zip4")));
			a.setPref((String)(request.getParameter("pref")));
			a.setCity((String)(request.getParameter("city")));
			a.setAddress1((String)(request.getParameter("address1")));
			a.setAddress2((String)(request.getParameter("address2")));
			a.setName((String)(request.getParameter("name")));
			a.setAname((String)(request.getParameter("aname")));
			a.setTel((String)(request.getParameter("tel")));
			a.setMobile((String)(request.getParameter("mobile")));

			//入力チェック
			errMsg += lo.AddressCheck(a);
			if(!errMsg.isEmpty()){
				//エラーメッセージが入っている→入力エラーがあるので入力画面に戻る
				request.setAttribute("ErrMsg",errMsg.replaceAll("\\n", "<br>"));
				//編集中のデータもrequestScopeにつけてもどす
				//sessionScopeとかぶらないようにEditAddressとつける
				request.setAttribute("EditAddress",a);
				RequestDispatcher rd = request.getRequestDispatcher("SelectAddressServlet");
				rd.forward(request, response);
				return;
			}
		}else{
				aid = Integer.parseInt(buf);
				System.out.println("aid:"+aid);
		}
		//ここからトランザクション開始→別のLogicで書いたほうがよいような気がする
	 f = true;
	 System.out.println("chk:"+(a==null) +":"+(a!=null));
		if(a!=null){
			//addressのInsert→LastIDを取得
			try {
				aid = lo.getLastaid(a);
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				f=false;
			}
		}
		//OrderBasic にInsert用の変数を格納
    OrderBasic ob = new OrderBasic();
    //dateはDAO側で登録するのであえてここで登録する必要はない
    System.out.println("ob登録時のaid:"+aid);
		ob.setAid(aid);
		ob.setOpayment(payid);
		ob.setUid(u.getUid());
		try {
			oid = lo.getLastoid(ob);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			f=false;
		}
		//ショッピングカートの情報をorderdetailに変換
		List<OrderDetail>od = lo.sp2od(sp, oid);
		if(f){
			try{
				f=lo.setod(od);
			}catch(Exception e){
				e.printStackTrace();
				f=false;
			}
		}
		String uri="";
		if(f){
	    uri="/WEB-INF/jsp/orderComplete.jsp";
		}else{
			errMsg +="★なんか更新に失敗した…\n";
	    uri="/SelectAddressServlet";
	    request.setAttribute("ErrMsg", errMsg);

		}
		dispatcher = request.getRequestDispatcher(uri);
    dispatcher.forward(request, response);
    return;

	}
}

