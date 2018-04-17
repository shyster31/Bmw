<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="user" value="${user}"></jsp:param>
    <jsp:param name="header" value="Registration"></jsp:param>
</jsp:include>
<div id='content' style='background-color: bisque;' align='center'>
    <div>
        <form action='/Pr4_bmw/registration' method='POST'>
            Input login:  <input name='login'/><br/>
            Input psss:   <input type='password' name='pass1'/><br/>
            Confirm psss: <input type='password' name='pass2'/><br/>
            <input type='submit' value='Register'/>
        </form>
    </div>
</div>
<jsp:include page="footer.jsp">
    <jsp:param name="counter" value="${counter}"></jsp:param>
</jsp:include> 
