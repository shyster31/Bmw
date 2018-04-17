<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="user" value="${user}"></jsp:param>
    <jsp:param name="header" value="${item.model}"></jsp:param>
</jsp:include>
<div id='content' style='background-color: bisque;' align='center'>
    <div>
        <table>
            <tr>
                <td>
                    <h1>${item.model}</h1>
                    <img style='height: 500px' src='${item.pic}'/>
                    <table>
                        <tr>
                            <td><h1> ${item.price} UAH</h1></td>
                            <c:if test="${isLogin}">
                                <td>
                                    <a href='/Pr4_bmw/basket?id=${item.id}'>
                                        <input type='submit' value='Add To Basket'/>
                                    </a>
                                </td>
                            </c:if>
                        </tr>
                    </table>
                </td>
                <td style='width: 500px'>
                    <h3>${item.about}</h3>
                </td>
            </tr>
        </table>
    </div>
</div>
<jsp:include page="footer.jsp">
    <jsp:param name="counter" value="${counter}"></jsp:param>
</jsp:include> 