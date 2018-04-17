<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="user" value="${user}"/>
    <jsp:param name="header" value="BMW MOTORS"/>
</jsp:include>
       
<div id='content' style='background-color: bisque;' align='center'>
    <c:forEach items="${items}" var="item">
        <div>
            <a href='/Pr4_bmw/item?id=${item.id}'>
                <table>
                    <tr>
                        <td id='model'>
                            <h1>${item.model}</h1>
                        </td>
                        <td id='picture'>
                            <img style='height: 150px' src='${item.pic}'/>
                        </td>
                        <td id='description'>
                            <h3>${item.about}</h3>
                        </td>
                        <td id='price'>
                            <h1>${item.price} UAH</h1>
                        </td>
                    </tr>
                </table>
            </a>
        </div><hr/>  
    </c:forEach>
</div>        
<jsp:include page="footer.jsp">
    <jsp:param name="counter" value="${counter}"></jsp:param>
</jsp:include>    
