package ncu.im3069.demo.controller;

import java.io.PrintWriter;
import java.io.IOException;

import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import ncu.im3069.tools.JsonReader;

import ncu.im3069.demo.app.MemberHelper;
import ncu.im3069.demo.app.Member;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/api/login.do")

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberHelper mh = MemberHelper.getHelper();
	
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    /**
     * GET方法
     * 
     * @param request Servelet 為 HttpServletRequest 的 Request物件(前到後)
     * @param response Servlet 為 HttpServletResponse 的 Response物件（後到前）
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n Call GET");
		JsonReader jsr = new JsonReader(request);
		JSONObject jsob = new JSONObject();
		
		HttpSession session = request.getSession(false);
		if(session != null) {
			String Session_id = (String)session.getAttribute("member_id");
			String Session_name = (String)session.getAttribute("member_name");
			if(Session_name != "") {
				jsob.put("member_id", Session_id);
				jsob.put("member_name", Session_name);
				jsob.put("status", "Login");
				
			}else {
				jsob.put("status", "Not Login");
			}
			System.out.print(Session_id + ": " +Session_name);
		
		}else {
			System.out.print("此帳號不存在!");
			jsob.put("status", "Not Loign");
		}
		
		JSONObject res = new JSONObject();
		res.put("status", "200");
		res.put("message", "取得登入狀態");
		res.put("response", jsob);
		/*透過JsonReader物件回傳資訊到前端*/
		jsr.response(res, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JsonReader jsr = new JsonReader(request);
		JSONObject jsob = jsr.getObject();
		
		String member_account = jsob.getString("member_account");
		String hash_pwd = jsob.getString("hash_pwd");
		
		if(member_account == null) {
			member_account = "";
		}
		if(hash_pwd == null) {
			hash_pwd = "";
		}
		
		JSONObject rs = mh.getByEmail(member_account, hash_pwd);
		JSONObject rsp = new JSONObject();
		
		System.out.println(rs.get("data"));
		
		if(rs.getJSONArray("data").length() != 0) {
			System.out.println("登入成功!");
			
			System.out.println(rs.getJSONArray("data").get(0));
			String member_id=((JSONObject) rs.getJSONArray("data").get(0)).get("member_id").toString();
    		String member_name=((JSONObject) rs.getJSONArray("data").get(0)).get("member_name").toString();
    		int is_admin=(int)((JSONObject) rs.getJSONArray("data").get(0)).get("is_admin");
    		
    		System.out.println(member_name);
    		System.out.println(is_admin);
    		
    		HttpSession session_1 = request.getSession();
    		session_1.setAttribute("member_id", member_id);
            session_1.setAttribute("member_name", member_name);
            session_1.setAttribute("member_account", member_account);
            session_1.setAttribute("is_admin", is_admin);
            rsp.put("response", rs);
		}else {
			rsp.put("message", "Login_Fail");
    		System.out.println("此帳號不存在");
    	}
		
		HttpSession session = request.getSession(false);
        if (session != null) {
            String Session_id = (String) session.getAttribute("member_id");
            String Session_name = (String) session.getAttribute("member_name");
            String Session_email = (String) session.getAttribute("member_account");
            String Session_role = (String) session.getAttribute("is_admin");
	        rsp.put("message", "登入成功！");
            System.out.print(Session_id+"您好, " + Session_name + " 歡迎您來到個人資訊中心！ is_admin:"+Session_role+", member_account:"+Session_email);
        } else {
            System.out.print("請登入系統！");
            //request.getRequestDispatcher("login.html").include(request, response);
        }
        
        rsp.put("status", "200");
        /** 透過 JsonReader 物件回傳到前端（以 JSONObject 方式） */
        jsr.response(rsp, response);
        
        
		
	}


	

}
