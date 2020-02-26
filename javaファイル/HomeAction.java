package com.internousdev.rose.action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.rose.dao.MCategoryDAO;
import com.internousdev.rose.dto.MCategoryDTO;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;

	public String execute() throws SQLException {

		String result = SUCCESS;

		//ログインフラグ保持していない場合、未ログインとしてログインフラグを保持する
		if (!session.containsKey("loginFlg")) {
			session.put("loginFlg", false);
		}

		// 仮ユーザーID保持していない場合 かつ 未ログインの場合、仮ユーザーIDを作成
		boolean loginFlg = Boolean.valueOf(session.get("loginFlg").toString());
		if (!session.containsKey("proUserId") && loginFlg == false) {

			// 仮ユーザーID(16桁ランダムな数字)を作成し、セッションに格納
			String proUserId = getRamdomValue();
			session.put("proUserId", proUserId);
		}

		//カテゴリ保持していない場合、m_categoryのDBから、カテゴリID カテゴリ名を取得したcategoryListをセッションに保持
		if (!session.containsKey("categoryList")) {
			MCategoryDAO dao = new MCategoryDAO();
			List<MCategoryDTO> categoryList = dao.selectCategoryList();
			session.put("categoryList", categoryList);
		}
		return result;
	}

	/*ランダムな16桁の数字(String型)で返すメソッド
	 * @return ランダムな16桁の整数(String型)
	 * */
	public String getRamdomValue() {
		String randomNumber = "";
		double d;
		int i = 1;

		while (i <= 16) {
			d = Math.random() * 10;
			randomNumber = randomNumber + ((int) d);
			i++;
		}
		return randomNumber;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
