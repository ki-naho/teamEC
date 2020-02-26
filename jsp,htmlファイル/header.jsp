<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="s" uri="/struts-tags"%>
<header>

	<div class="header-menu">
		<ul>
			<li id="header-title">ROSE</li>

			<!-- カテゴリ情報がsessionにあるとき、商品検索機能部を表示 -->

					<s:form action="SearchItemAction">
						<s:if test='#session.categoryList!=null'>
							<li><s:select name="categoryId" list="#session.categoryList" listValue="categoryName" listKey="categoryId" class="cs-div" id="categoryId"/></li>
						</s:if>
						<li><s:textfield name="searchItem" class="search" placeholder="検索ワード"/></li>
						<li><s:submit value="商品検索" class="submit_btn" /></li>
					</s:form>

			<!-- ログインボタン (未ログイン時) -->
			<!-- ログイン画面に遷移 -->
			<s:if test='#session.loginFlg==false'>
				<li>
					<s:form action="GoLoginAction">
						<s:submit class="submit_btn" value="ログイン"/>
					</s:form>
				</li>
			</s:if>
			<s:else>
			<!-- ログアウトボタン  (ログイン時）-->
			<!-- ログアウト処理を行う -->
				<li>
					<s:form action="LogoutAction">
						<s:submit class="submit_btn" value="ログアウト"/>
					</s:form>
				</li>
			</s:else>

			<!-- カートボタン (常時)-->
			<!-- カート画面に遷移 -->
			<li>
				<s:form action="CartAction">
					<s:submit class="submit_btn" value="カート"/>
				</s:form>
			</li>

			<!-- マイページボタン (ログイン時)-->
			<!-- マイページ画面に遷移 -->
			<s:if test='#session.loginFlg==true'>
				<li>
					<s:form action="MyPageAction">
						<s:submit class="submit_btn" value="マイページ"/>
					</s:form>
				</li>
			</s:if>

		</ul>
	</div>
</header>
