<jsp:include page="header.jsp">
    <jsp:param name="user" value="${user}"></jsp:param>
    <jsp:param name="header" value="Sign in Page"></jsp:param>
</jsp:include>
<div id='content' style='background-color: bisque;' align='center'>
    <div>
        <form action='/Pr4_bmw/login' method='POST'>
            Input login:  <input name='login'/><br/>
            Input psss:   <input type='password' name='pass'/><br/>
            <input type='submit' value='Sign In'/>
        </form>
        <form action='/Pr4_bmw/registration'>
            <input type='submit' value='Register'/>
        </form>
    </div>
</div>
<jsp:include page="footer.jsp">
    <jsp:param name="counter" value="${counter}"></jsp:param>
</jsp:include> 