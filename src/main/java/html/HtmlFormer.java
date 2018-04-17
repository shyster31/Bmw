package html;

import entity.Bmw;
import entity.User;
import java.util.List;

public class HtmlFormer {

    public String header(String title, User user) {
        String out = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <title>" + title + "</title>\n"
                + "        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <div id='header' style='background-color: aquamarine; height: 80px' align='center'>\n"
                + "            <div>\n"
                + "                <table>\n"
                + "                    <tr>\n"
                + "                       <td>\n"
                + "                            <a href='/Pr4_bmw/main'><h1>BMW Motors Ukraine</h1></a>\n"
                + "                        </td>\n"
                + "                        <td>\n";
        if (user == null) {
            out += "                            <form action='/Pr4_bmw/login'>\n"
                    + "                                <input type='submit' value='Sign In'/>\n"
                    + "                            </form>\n";
        } else {
            out += "                <form action='/Pr4_bmw/profile'>\n"
                    + "                    <input type='submit' value='My Profile'/>\n"
                    + "                </form>\n"
                    + "                <a href='/Pr4_bmw/main?exit=true'>\n"
                    + "                    <input type='submit' value='Exit'/>\n"
                    + "                </a>\n"
                    + "                <form action='/Pr4_bmw/basket'>\n"
                    + "                    <input type='submit' value='My Basket'/>\n"
                    + "                </form>";
        }
        out += "                        </td>\n"
                + "                    </tr>\n"
                + "                </table>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "        <div id='search' style='background-color: lime; height: 21px' align='center'>\n"
                + "            <form action='/Pr4_bmw/main'>\n"
                + "                Query: <input name='q'/>\n"
                + "                <input type='submit' value='Search'/>\n"
                + "            </form>\n"
                + "        </div>\n"
                + "        <div id='menu' style='background-color: burlywood; height: 80px' align='center'>\n"
                + "            <table>\n"
                + "                <tr>\n"
                + "                    <td id='electro' style='width: 200px;' align='center'>\n"
                + "                        <h2>\n"
                + "                            <a href='/Pr4_bmw/main?cat=e'>\n"
                + "                                Electro car\n"
                + "                            </a>\n"
                + "                        </h2>\n"
                + "                    </td>\n"
                + "                    <td id='suv' style='width: 200px;' align='center'>\n"
                + "                        <h2>\n"
                + "                            <a href='/Pr4_bmw/main?cat=s'>\n"
                + "                                Offroad car\n"
                + "                            </a>\n"
                + "                        </h2>\n"
                + "                    </td>\n"
                + "                    <td id='car' style='width: 200px;' align='center'>\n"
                + "                        <h2>\n"
                + "                            <a href='/Pr4_bmw/main?cat=c'>\n"
                + "                                Car\n"
                + "                            </a>\n"
                + "                        </h2>\n"
                + "                    </td>\n"
                + "                </tr>\n"
                + "            </table>\n"
                + "        </div>";
        return out;
    }

    public String footer(int counter) {
        return "        <div id='footer' style='background-color: blueviolet; height: 30px;' align='center'>\n"
                + "            <h3>Copyright: Alex; On-site: " + counter + "</h3>\n"
                + "        </div>\n"
                + "    </body>\n"
                + "</html>";
    }

    public String mainContent(String header, String footer, List<Bmw> items) {
        String out = header
                + "        <div id='content' style='background-color: bisque;' align='center'>\n";
        for (Bmw item : items) {
            out += "            <div>\n"
                    + "                <a href='/Pr4_bmw/item?id=" + item.getId() + "'>\n"
                    + "                    <table>\n"
                    + "                        <tr>\n"
                    + "                            <td id='model'>\n"
                    + "                                <h1>" + item.getModel() + "</h1>\n"
                    + "                            </td>\n"
                    + "                            <td id='picture'>\n"
                    + "                                <img style='height: 150px' src='" + item.getPic() + "'/>\n"
                    + "                            </td>\n"
                    + "                            <td id='description'>\n"
                    + "                                <h3>" + item.getAbout() + "</h3>\n"
                    + "                            </td>\n"
                    + "                            <td id='price'>\n"
                    + "                                <h1>" + item.getPrice() + " UAH</h1>\n"
                    + "                            </td>\n"
                    + "                        </tr>\n"
                    + "                    </table>\n"
                    + "                </a>\n"
                    + "            </div><hr/>\n";
        }
        out += "        </div>"
                + footer;
        return out;
    }

    public String itemContent(String header, String footer, Bmw item, boolean isLogin) {
        String out = header
                + "        <div id='content' style='background-color: bisque;' align='center'>\n"
                + "            <div>\n"
                + "                <table>\n"
                + "                    <tr>\n"
                + "                        <td>\n"
                + "                            <h1>" + item.getModel() + "</h1>\n"
                + "                            <img style='height: 500px' src='" + item.getPic() + "'/>\n"
                + "                            <table>\n"
                + "                                <tr>\n"
                + "                                    <td><h1>" + item.getPrice() + " UAH</h1></td>\n";
        if (isLogin) {
            out += "                                    <td>\n"
                    + "                                        <a href='/Pr4_bmw/basket?id=" + item.getId() + "'>\n"
                    + "                                            <input type='submit' value='Add To Basket'/>\n"
                    + "                                        </a>\n"
                    + "                                    </td>\n";
        }
        out += "                                </tr>\n"
                + "                            </table>\n"
                + "                        </td>\n"
                + "                        <td style='width: 500px'>\n"
                + "                            <h3>" + item.getAbout() + "</h3>\n"
                + "                        </td>\n"
                + "                    </tr>\n"
                + "                </table>\n"
                + "            </div>\n"
                + "        </div>"
                + footer;
        return out;
    }

    public String loginContent(String header, String footer) {
        return header
                + "        <div id='content' style='background-color: bisque;' align='center'>\n"
                + "            <div>\n"
                + "                <form action='/Pr4_bmw/login' method='POST'>\n"
                + "                    Input login:  <input name='login'/><br/>\n"
                + "                    Input psss:   <input type='password' name='pass'/><br/>\n"
                + "                    <input type='submit' value='Sign In'/>\n"
                + "                </form>\n"
                + "                <form action='/Pr4_bmw/register'>\n"
                + "                    <input type='submit' value='Register'/>\n"
                + "                </form>\n"
                + "            </div>"
                + "        </div>"
                + footer;
    }

    public String registerContent(String header, String footer) {
        return header
                + "        <div id='content' style='background-color: bisque;' align='center'>\n"
                + "            <div>\n"
                + "                <form action='/Pr4_bmw/register' method='POST'>\n"
                + "                    Input login:  <input name='login'/><br/>\n"
                + "                    Input psss:   <input type='password' name='pass1'/><br/>\n"
                + "                    Confirm psss: <input type='password' name='pass2'/><br/>\n"
                + "                    <input type='submit' value='Register'/>\n"
                + "                </form>\n"
                + "            </div>"
                + "        </div>"
                + footer;
    }

    public String profileContent(String header, String footer, String userName) {
        return header
                + "        <div id='content' style='background-color: bisque;' align='center'>\n"
                + "            <div>\n"
                + "                <h1>Hello, " + userName + ". Change password:</h1>\n"
                + "                <form action='/Pr4_bmw/profile' method='POST'>\n"
                + "                    Input old pass: <input type='password' name='oldPass'/><br/>\n"
                + "                    Input new psss: <input type='password' name='pass1'/><br/>\n"
                + "                    Input again:    <input type='password' name='pass2'/><br/>\n"
                + "                    <input type='submit' value='Change'/>\n"
                + "                </form>\n"
                + "            </div>"
                + "        </div>"
                + footer;
    }

    public String basketContent(String header, String footer, List<Bmw> items) {
        String out = header
                + "        <div id='content' style='background-color: bisque;' align='center'>\n"
                + "            <a href='/Pr4_bmw/basket?id=0'>\n"
                + "                <input type='submit' value='Clear basket'/>\n"
                + "            </a>\n";
        for (Bmw item : items) {
            out += "            <div>\n"
                    + "                <a href='/Pr4_bmw/item?id=" + item.getId() + "'>\n"
                    + "                    <table>\n"
                    + "                        <tr>\n"
                    + "                            <td id='model'>\n"
                    + "                                <h1>" + item.getModel() + "</h1>\n"
                    + "                            </td>\n"
                    + "                            <td id='picture'>\n"
                    + "                                <img style='height: 150px' src='" + item.getPic() + "'/>\n"
                    + "                            </td>\n"
                    + "                            <td id='description'>\n"
                    + "                                <h3>" + item.getAbout() + "</h3>\n"
                    + "                            </td>\n"
                    + "                            <td id='price'>\n"
                    + "                                <h1>" + item.getPrice() + " UAH</h1>\n"
                    + "                            </td>"
                    + "                            <td>\n"
                    + "                            <a href='/Pr4_bmw/basket?id=-"+item.getId()+"'>\n"
                    + "                                <input type='submit' value='Remove From Basket'/>\n"
                    + "                            </a>\n"
                    + "                            </td>\n"
                    + "                        </tr>\n"
                    + "                    </table>\n"
                    + "                </a>\n"
                    + "            </div><hr/>\n";
        }
        out += "        </div>"
                + footer;
        return out;
    }
}
