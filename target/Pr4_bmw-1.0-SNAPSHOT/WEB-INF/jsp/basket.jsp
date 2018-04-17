<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="user" value="${user}"></jsp:param>
    <jsp:param name="header" value="Basket"></jsp:param>
</jsp:include>

<div id='content' style='background-color: bisque;' align='center'>
    <div id='content' style='background-color: bisque;' align='center'>
        <a href='/Pr4_bmw/basket?id=0'>
            <input type='submit' value='Clear basket'/>
        </a>
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
                            <td>
                                <a href='/Pr4_bmw/basket?id=-${item.id}'>
                                    <input type='submit' value='Remove From Basket'/>
                                </a>
                            </td>
                        </tr>
                    </table>
                </a>
            </div>
        </c:forEach> 
    </div>

    <jsp:include page="footer.jsp">
        <jsp:param name="counter" value="${counter}"></jsp:param>
    </jsp:include>    