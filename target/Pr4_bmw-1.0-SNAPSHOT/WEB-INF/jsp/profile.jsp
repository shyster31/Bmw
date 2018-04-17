<jsp:include page="header.jsp">
    <jsp:param name="user" value="${user}"></jsp:param>
    <jsp:param name="header" value="Change password"></jsp:param>
</jsp:include>
<div id='content' style='background-color: bisque;' align='center'>
    <div>
        <h1>Hello, ${user.login}. Change password:</h1>
        <form action='/Pr4_bmw/profile' method='POST'>
            Input old pass: <input type='password' name='oldPass'/><br/>
            Input new psss: <input type='password' name='pass1'/><br/>
            Input again:    <input type='password' name='pass2'/><br/>
            <input type='submit' value='Change'/>
        </form>
    </div>
</div>
<jsp:include page="footer.jsp">
    <jsp:param name="counter" value="${counter}"></jsp:param>
</jsp:include>