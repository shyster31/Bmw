<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>${param.header}</title>
        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    </head>
    <body>
        <div id='header' style='background-color: aquamarine; height: 80px' align='center'>
            <div>
                <table>
                    <tr>
                        <td>
                            <a href='/Pr4_bmw/main'><h1>BMW Motors Ukraine</h1></a>
                        </td>
                        <td>
                            <c:if test="${user == null}">
                                <form action='/Pr4_bmw/login' method="GET">
                                    <input type='submit' value='Sign In'/>
                                </form>
                            </c:if>
                            <c:if test="${user != null}">
                                <form action='/Pr4_bmw/profile'>
                                    <input type='submit' value='My Profile'/>
                                </form></td><td>
                                <a href='/Pr4_bmw/main?exit=true'>
                                    <input type='submit' value='Exit'/>
                                </a></td><td>
                                <form action='/Pr4_bmw/basket'>
                                    <input type='submit' value='My Basket'/>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div id='search' style='background-color: lime; height: 21px' align='center'>
    <form action='/Pr4_bmw/main'>
        Query: <input name='q'/>
        <input type='submit' value='Search'/>
    </form>
</div>
<div id='menu' style='background-color: burlywood; height: 80px' align='center'>
    <table>
        <tr>
            <td id='electro' style='width: 200px;' align='center'>
                <h2>
                    <a href='/Pr4_bmw/main?cat=e'>
                        Electro car
                    </a>
                </h2>
            </td>
            <td id='suv' style='width: 200px;' align='center'>
                <h2>
                    <a href='/Pr4_bmw/main?cat=s'>
                        Offroad car
                    </a>
                </h2>
            </td>
            <td id='car' style='width: 200px;' align='center'>
                <h2>
                    <a href='/Pr4_bmw/main?cat=c'>
                        Car
                    </a>
                </h2>
            </td>
        </tr>
    </table>
</div> 