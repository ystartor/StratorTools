package com.ystartor.security;

public class StringFound {

    public static String s = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
            "<head>\n" +
            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\" />\n" +
            "<title>��¼�����˲鹫�������Ϣϵͳ</title>\n" +
            "<style type=\"text/css\">\n" +
            "<!--\n" +
            "-->\n" +
            "</style>\n" +
            "<link href=\"/picp/image/css.css;jsessionid=0000HP4CQeeNsKtiPaSIHWzRTGi:16pltg5ae\" rel=\"stylesheet\" type=\"text/css\" />\n" +
            "<style type=\"text/css\">\n" +
            "<!--\n" +
            "a:link {\n" +
            "\tcolor: #002E51;\n" +
            "\ttext-decoration: none;\n" +
            "}\n" +
            "a:visited {\n" +
            "\ttext-decoration: none;\n" +
            "\tcolor: #002E51;\n" +
            "}\n" +
            "a:hover {\n" +
            "\ttext-decoration: none;\n" +
            "\tcolor: #B0DEFF;\n" +
            "}\n" +
            "a:active {\n" +
            "\ttext-decoration: none;\n" +
            "\tcolor: #002E51;\n" +
            "}\n" +
            "a {\n" +
            "\tfont-size: 12px;\n" +
            "\tfont-weight: bold;\n" +
            "}\n" +
            ".STYLE2 {font-size: 14px}\n" +
            "body {\n" +
            "\tbackground-color: #01325A;\n" +
            "}\n" +
            "#Layer1 {\tposition:absolute;\n" +
            "\tleft:435px;\n" +
            "\ttop:153px;\n" +
            "\twidth:159px;\n" +
            "\theight:77px;\n" +
            "\tz-index:1;\n" +
            "}\n" +
            "#Layer2 {\tposition:absolute;\n" +
            "\tleft:586px;\n" +
            "\ttop:162px;\n" +
            "\twid                                                                                                                                                  \n" +
            "th:94px;\n" +
            "\theight:51px;\n" +
            "\tz-index:2;\n" +
            "}\n" +
            "-->\n" +
            "</style>\n" +
            "<script language=\"javascript\" src=\"/picp/js/verifyfunction.js;jsessionid=0000HP4CQeeNsKtiPaSIHWzRTGi:16pltg5ae\"></script>\n" +
            "<script language=\"javascript\" src=\"/picp/js/core.js;jsessionid=0000HP4CQeeNsKtiPaSIHWzRTGi:16pltg5ae\"></script>\t\n" +
            "<script language=\"javascript\" src=\"/picp/js/jsbn.js;jsessionid=0000HP4CQeeNsKtiPaSIHWzRTGi:16pltg5ae\"></script>\n" +
            "<script language=\"javascript\" src=\"/picp/js/jsbn2.js;jsessionid=0000HP4CQeeNsKtiPaSIHWzRTGi:16pltg5ae\"></script>\n" +
            "<script language=\"javascript\" src=\"/picp/js/sm3.js;jsessionid=0000HP4CQeeNsKtiPaSIHWzRTGi:16pltg5ae\"></script>\n" +
            "<script>\n" +
            "function keyLogin(){\n" +
            "\tvar password = document.getElementsByName(\"piUsersDto.password\")[0];\n" +
            "\tpassword.value = hex_sm3(password.value);\n" +
            "\tdocument.forms[0].submit();\n" +
            "}\n" +
            "</script>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "<form action=\"SecurityAction.do?method=login\" method=\"post\">\n" +
            "<table width=\"1001\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
            "  <tr>\n" +
            "    <td height=\"70\" background=\"/picp/image/log\n" +
            "o.jpg;jsessionid=0000HP4CQeeNsKtiPaSIHWzRTGi:16pltg5ae\"></td>\n" +
            "  </tr>\n" +
            "  <tr>\n" +
            "    <td height=\"30\" background=\"/picp/image/menu_bar.jpg;jsessionid=0000HP4CQeeNsKtiPaSIHWzRTGi:16pltg5ae\">&nbsp;&nbsp;<span class=\"STYLE2\">  �û������ڣ� �����µ�¼</span></td>\n" +
            "  </tr>\n" +
            "  <tr>\n" +
            "    <td height=\"467\" background=\"/picp/image/log.jpg;jsessionid=0000HP4CQeeNsKtiPaSIHWzRTGi:16pltg5ae\"><div id=\"Layer1\">\n" +
            "      <input name=\"piUsers                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        \n" +
            "Dto.usercode\" type=\"text\" class=\"log\" maxlength=\"10\"/>\n" +
            "      <br />\n" +
            "      <br />\n" +
            "      <input name=\"piUsersDto.password\" type=\"password\" class=\"log\"  maxlength=\"8\"/>\n" +
            "      <input type=\"button\" name=\"Submit\" value=\"\" style=\"width:1px;height:1px\" onclick=\"keyLogin();\">\n" +
            "    </div>\n" +
            "    <div id=\"Layer2\"><img src=\"/picp/image/logbutton.jpg;jsessionid=0000HP4CQeeNsKtiPaSIHWzRTGi:16pltg5ae\" name = \"login\" width=\"95\" height=\"52\" border=\"0\" onclick=\"keyLogin();\"/></div></td>\n" +
            "  </tr>\n" +
            "</table>\n" +
            "</form>\n" +
            "</body>\n" +
            "</html>";

    public static void main(String[] args) {
//        System.out.println(s);
        String s2 = s.substring(0, s.indexOf("jsessionid"));
        String s3 = s.substring(s2.length(), s.length());
        String s4 = s3.substring(0, s3.indexOf("\""));
        System.out.println(s4);
    }

}
