package com.ystartor.security;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EndodeString {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String sd = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "   \n" +
                "\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\" />\n" +
                "<title>���ʺ˶Խ��</title>\n" +
                "<link href=\"/picp/image/css.css;jsessionid=0000GgaC7KsUaW7jkubnnpbjKNH:16pltg5ae\" rel=\"stylesheet\" type=\"text/css\" />\n" +
                "<style type=\"text/css\">\n" +
                "<!--\n" +
                "body {\n" +
                "\tbackground-color: #FFFFFF;\n" +
                "\tbackground-image: url(/picp/image/content_table_bg.jpg;jsessionid=0000GgaC7KsUaW7jkubnnpbjKNH:16pltg5ae);\n" +
                "\tbackground-repeat: repeat-y;\n" +
                "}\n" +
                "-->\n" +
                "</style>\n" +
                "</head>\n" +
                "<script>\n" +
                "function prints()\n" +
                "{\n" +
                "\twindow.print();\n" +
                "}\n" +
                "function persubmit()\n" +
                "{\t\n" +
                "\tdocument.SingleInqueryResultForm.action=\"SingleInqueryResultAction.do?method=feedbackOpen\";\n" +
                "\tdocument.SingleInqueryResultForm.submit();\n" +
                "}     \n" +
                "\n" +
                "</script>\n" +
                "<body>\n" +
                "<form name=\"SingleInqueryResultForm\" method=\"post\" action=\"/picp/SingleInqueryResultAction.do;jsessionid=0000GgaC7KsUaW7jkubnnpbjKNH:16pltg5ae?method=feedbackOpen\">\n" +
                "<table width=\"840                                                                   \n" +
                "\" height=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "  <input type=\"hidden\" name=\"checkdate\" value=\"20200818\">\n" +
                "  <input type=\"hidden\" name=\"usercode\" value=\"003YYB\">\n" +
                "  <tr>\n" +
                "    <td width=\"8\" height=\"48\" background=\"/picp/image/content_table_TL.jpg;jsessionid=0000GgaC7KsUaW7jkubnnpbjKNH:16pltg5ae\"></td>\n" +
                "    <td background=\"/picp/image/content_table_TM.jpg;jsessionid=0000GgaC7KsUaW7jkubnnpbjKNH:16pltg5ae\"><table width=\"100%\" height=\"48\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "      <tr>\n" +
                "        <td width=\"360\" background=\"/picp/image/content_table_bar_L.jpg;jsessionid=0000GgaC7KsUaW7jkubnnpbjKNH:16pltg5ae\"><img src=\"/picp/image/sys_arrow1.jpg;jsessionid=0000GgaC7KsUaW7jkubnnpbjKNH:16pltg5ae\" width=\"15\" height=\"15\" />&nbsp;�����˲�&nbsp;<img src=\"/picp/image/sys_arrow2.jpg;jsessionid=0000GgaC7KsUaW7jkubnnpbjKNH:16pltg5ae\" width=\"15\" height=\"15\" />&nbsp;���ʺ˶Խ��</td>\n" +
                "        <td width=\"194\"></td>\n" +
                "        <td width=\"270\" background=\"/picp/image/content_table_bar_R.jpg;jsessionid=0000GgaC7Ks\n" +
                "UaW7jkubnnpbjKNH:16pltg5ae\"></td>\n" +
                "      </tr>\n" +
                "    </table></td>\n" +
                "    <td width=\"8\" background=\"/picp/image/content_table_TR.jpg;jsessionid=0000GgaC7KsUaW7jkubnnpbjKNH:16pltg5ae\"></td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td background=\"/picp/image/content_table_line_L.jpg;jsessionid=0000GgaC7KsUaW7jkubnnpbjKNH:16pltg5ae\"></td>\n" +
                "    <td style=\"FILTER: progid:DXImageTransform.Microsoft.Gradient(gradientType=0,startColorStr=#FFFFFF,end                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        \n" +
                "ColorStr=#B4FFFF);\"><br />\n" +
                "      <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "        <tr>\n" +
                "          <td width=\"10\">&nbsp;</td>\n" +
                "          <td>\n" +
                "            <div align=\"center\">\n" +
                "              <table width=\"600\" height=\"320\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                <tr>\n" +
                "                  <td height=\"40\" class=\"dotline\"><span class=\"text_blue2\">���ʺ˶Խ��</span></td>\n" +
                "                </tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\n" +
                "\n" +
                "\n" +
                "                  <td height=\"40\" class=\"dotline\">\n" +
                "                    <div align=\"left\">\n" +
                "                      <input class=\"button\" type=\"button\" name=\"Submit\" value=\"��  ӡ\" onclick=\"prints();\"/>\n" +
                "                      <input class=\"button\" type=\"button\" name=\"Submit2\" value=\"���Ϊ\" onclick=\"location.href='SingleInquireAction.do?method=WriteExcel'\" />\n" +
                "                      <input name=\"Submit23\" type=\"button\" class=\"button\" value=\"��  ��\" onclick=\"persubmit();\"/>\n" +
                "                      <input name=\"Submit22\" type=\"button\" class=\"button\" value=\"��  ��\" oncli\n" +
                "ck=\"location.href='SingleInquireAction.do?method=siOpen'\" />\n" +
                "\t\t\t\t\t  <!-- <input name=\"Submit22\" type=\"button\" class=\"button\" value=\"��  ��\" onclick=\"history.back();\" />   -->\n" +
                "                    </div></td>\n" +
                "                </tr>\n" +
                "                \n" +
                "                \n" +
                "                  \n" +
                "\n" +
                "                <tr>\n" +
                "                  <td height=\"10\" class=\"dotline\">&nbsp;</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           \n" +
                "               <td height=\"30\"><div align=\"center\">\n" +
                "                    <table id=\"outtab00\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"tbcolor\">\n" +
                "                      <tr>\n" +
                "                        <td width=\"151\" bgcolor=\"FFFFD0\" class=\"text_tablehead_b_d\">\u052D¼������</td>\n" +
                "                        <td width=\"227\" bgcolor=\"FFFFD0\" class=\"text_list\"><div align=\"left\"><input type=\"hidden\" name=\"certname\" value=\"����\">����</div></td>\n" +
                "                        <td width=\"220\" rowspan=\"4\" class=\"text_list\"><br />\n" +
                "                          <!--<img src=\"/picp/image/pig2.png;jsessionid=0000GgaC7KsUaW7jkubnnpbjKNH:16pltg5ae\" width=\"100\" height=\"130\" />-->\n" +
                "                          <image border=0 src=\"photoservlet1?Index=0\">\n" +
                "                          <br/>                                           \n" +
                "                       </td>\n" +
                "                      </tr>\n" +
                "                      <tr>\n" +
                "                        <td class=\"text_tablehead_b_d\">\u052D¼�����֤����</td>\n" +
                "                        <td class\n" +
                "=\"text_list\"><div align=\"left\"><input type=\"hidden\" name=\"certno\" value=\"370725199301182822\">370725199301182822</div></td>\n" +
                "                      \t\n" +
                "                      </tr>\n" +
                "                      <tr>\n" +
                "                        <td height=\"*\" bgcolor=\"#FFFFD0\" class=\"text_tablehead_b_d\">�˶Խ��</td>\n" +
                "                        <td bgcolor=\"#FFFFD0\" class=\"text_list\"><div align=\"left\"><input type=\"hidden\" name=\"checkresul                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        \n" +
                "t\" value=\"00\">����������һ������Ƭ����</div></td>\n" +
                "                      \t\n" +
                "                      </tr>\n" +
                "                      <tr>\n" +
                "                        <td class=\"text_tablehead_b_d\">ǩ������</td>\n" +
                "                        <td class=\"text_list\"><div align=\"left\">�����ݲ����غ˲���</div></td>\n" +
                "                      \t\n" +
                "                      </tr>\n" +
                "                    </table>  \n" +
                "                          \n" +
                "                                 \n" +
                "               <tr>\n" +
                "                  <td height=\"10\" class=\"dotline\">&nbsp;</td>\n" +
                "                </tr>\n" +
                "              </table>\n" +
                "            </div></td>\n" +
                "        </tr>\n" +
                "    </table></td>\n" +
                "    <td background=\"/picp/image/content_table_line_R.jpg;jsessionid=0000GgaC7KsUaW7jkubnnpbjKNH:16pltg5ae\"></td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>\n" +
                "                                                                                                                                                                                                                                 \n";
        sd = sd.replaceAll(" ", "");
        System.out.println(sd);
        sd = sd.replaceAll("\n","");
        System.out.println(sd);
//        int i = sd.indexOf("name=\"checkresult\"");
//        System.out.println(i);

        System.out.println("--------------------------------------------------");
        String respContent = "<!DOCTYPEhtmlPUBLIC\"-//W3C//DTDXHTML1.0Transitional//EN\"\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><htmlxmlns=\"http://www.w3.org/1999/xhtml\"><head><metahttp-equiv=\"Content-Type\"content=\"text/html;charset=gb2312\"/><title>���ʺ˶Խ��</title><linkhref=\"/picp/image/css.css;jsessionid=0000GgaC7KsUaW7jkubnnpbjKNH:16pltg5ae\"rel=\"stylesheet\"type=\"text/css\"/><styletype=\"text/css\"><!--body{\tbackground-color:#FFFFFF;\tbackground-image:url(/picp/image/content_table_bg.jpg;jsessionid=0000GgaC7KsUaW7jkubnnpbjKNH:16pltg5ae);\tbackground-repeat:repeat-y;}--></style></head><script>functionprints(){\twindow.print();}functionpersubmit(){\t\tdocument.SingleInqueryResultForm.action=\"SingleInqueryResultAction.do?method=feedbackOpen\";\tdocument.SingleInqueryResultForm.submit();}</script><body><formname=\"SingleInqueryResultForm\"method=\"post\"action=\"/picp/SingleInqueryResultAction.do;jsessionid=0000GgaC7KsUaW7jkubnnpbjKNH:16pltg5ae?method=feedbackOpen\"><tablewidth=\"840\"height=\"100%\"border=\"0\"cellpadding=\"0\"cellspacing=\"0\"><inputtype=\"hidden\"name=\"checkdate\"value=\"20200818\"><inputtype=\"hidden\"name=\"usercode\"value=\"003YYB\"><tr><tdwidth=\"8\"height=\"48\"background=\"/picp/image/content_table_TL.jpg;jsessionid=0000GgaC7KsUaW7jkubnnpbjKNH:16pltg5ae\"></td><tdbackground=\"/picp/image/content_table_TM.jpg;jsessionid=0000GgaC7KsUaW7jkubnnpbjKNH:16pltg5ae\"><tablewidth=\"100%\"height=\"48\"border=\"0\"cellpadding=\"0\"cellspacing=\"0\"><tr><tdwidth=\"360\"background=\"/picp/image/content_table_bar_L.jpg;jsessionid=0000GgaC7KsUaW7jkubnnpbjKNH:16pltg5ae\"><imgsrc=\"/picp/image/sys_arrow1.jpg;jsessionid=0000GgaC7KsUaW7jkubnnpbjKNH:16pltg5ae\"width=\"15\"height=\"15\"/>&nbsp;�����˲�&nbsp;<imgsrc=\"/picp/image/sys_arrow2.jpg;jsessionid=0000GgaC7KsUaW7jkubnnpbjKNH:16pltg5ae\"width=\"15\"height=\"15\"/>&nbsp;���ʺ˶Խ��</td><tdwidth=\"194\"></td><tdwidth=\"270\"background=\"/picp/image/content_table_bar_R.jpg;jsessionid=0000GgaC7KsUaW7jkubnnpbjKNH:16pltg5ae\"></td></tr></table></td><tdwidth=\"8\"background=\"/picp/image/content_table_TR.jpg;jsessionid=0000GgaC7KsUaW7jkubnnpbjKNH:16pltg5ae\"></td></tr><tr><tdbackground=\"/picp/image/content_table_line_L.jpg;jsessionid=0000GgaC7KsUaW7jkubnnpbjKNH:16pltg5ae\"></td><tdstyle=\"FILTER:progid:DXImageTransform.Microsoft.Gradient(gradientType=0,startColorStr=#FFFFFF,endColorStr=#B4FFFF);\"><br/><tablewidth=\"100%\"border=\"0\"cellspacing=\"0\"cellpadding=\"0\"><tr><tdwidth=\"10\">&nbsp;</td><td><divalign=\"center\"><tablewidth=\"600\"height=\"320\"border=\"0\"cellpadding=\"0\"cellspacing=\"0\"><tr><tdheight=\"40\"class=\"dotline\"><spanclass=\"text_blue2\">���ʺ˶Խ��</span></td></tr>\t\t\t\t<tr><tdheight=\"40\"class=\"dotline\"><divalign=\"left\"><inputclass=\"button\"type=\"button\"name=\"Submit\"value=\"��ӡ\"onclick=\"prints();\"/><inputclass=\"button\"type=\"button\"name=\"Submit2\"value=\"���Ϊ\"onclick=\"location.href='SingleInquireAction.do?method=WriteExcel'\"/><inputname=\"Submit23\"type=\"button\"class=\"button\"value=\"����\"onclick=\"persubmit();\"/><inputname=\"Submit22\"type=\"button\"class=\"button\"value=\"����\"onclick=\"location.href='SingleInquireAction.do?method=siOpen'\"/>\t\t\t\t\t<!--<inputname=\"Submit22\"type=\"button\"class=\"button\"value=\"����\"onclick=\"history.back();\"/>--></div></td></tr><tr><tdheight=\"10\"class=\"dotline\">&nbsp;</td></tr><tr><tdheight=\"30\"><divalign=\"center\"><tableid=\"outtab00\"width=\"100%\"border=\"0\"cellpadding=\"0\"cellspacing=\"0\"class=\"tbcolor\"><tr><tdwidth=\"151\"bgcolor=\"FFFFD0\"class=\"text_tablehead_b_d\">\u052D¼������</td><tdwidth=\"227\"bgcolor=\"FFFFD0\"class=\"text_list\"><divalign=\"left\"><inputtype=\"hidden\"name=\"certname\"value=\"����\">����</div></td><tdwidth=\"220\"rowspan=\"4\"class=\"text_list\"><br/><!--<imgsrc=\"/picp/image/pig2.png;jsessionid=0000GgaC7KsUaW7jkubnnpbjKNH:16pltg5ae\"width=\"100\"height=\"130\"/>--><imageborder=0src=\"photoservlet1?Index=0\"><br/></td></tr><tr><tdclass=\"text_tablehead_b_d\">\u052D¼�����֤����</td><tdclass=\"text_list\"><divalign=\"left\"><inputtype=\"hidden\"name=\"certno\"value=\"370725199301182822\">370725199301182822</div></td>\t</tr><tr><tdheight=\"*\"bgcolor=\"#FFFFD0\"class=\"text_tablehead_b_d\">�˶Խ��</td><tdbgcolor=\"#FFFFD0\"class=\"text_list\"><divalign=\"left\"><inputtype=\"hidden\"name=\"checkresult\"value=\"00\">����������һ������Ƭ����</div></td>\t</tr><tr><tdclass=\"text_tablehead_b_d\">ǩ������</td><tdclass=\"text_list\"><divalign=\"left\">�����ݲ����غ˲���</div></td>\t</tr></table><tr><tdheight=\"10\"class=\"dotline\">&nbsp;</td></tr></table></div></td></tr></table></td><tdbackground=\"/picp/image/content_table_line_R.jpg;jsessionid=0000GgaC7KsUaW7jkubnnpbjKNH:16pltg5ae\"></td></tr></table></form></body></html>";
        int i = respContent.indexOf("name=\"checkresult\"");
        if (i < 0){
            System.out.println("-1");
//            return "-1";
        }
        String s1 = respContent.substring(i, respContent.length());
        int j = s1.indexOf("value");
        if (j < 0){
            System.out.println("-1");
        }
        int k = s1.indexOf(">");
        if (k < 0){
            System.out.println("-1");
        }
        String s2 = s1.substring(j, k);
        System.out.println(s2);
        int m = s2.indexOf("=");
        if (m < 0){
            System.out.println("-1");
        }
        String s3 = s2.substring(m + 2, m + 4);
        System.out.println(s3);

//        String plain = "吴琼";
//        System.out.println(plain);
//        plain = URLEncoder.encode(plain, "GBK");
//        System.out.println(plain);
//        String ss = new String(plain.getBytes("UTF-8"),"GBK");
//        System.out.println(ss);
//        System.out.println(plain);
//        byte[] bytes = plain.getBytes("UTF-8");
//        byte[] gbks = new String(bytes, "UTF-8").getBytes("GBK");
//        String s = new String(gbks, "GBK");
//        System.out.println(s);
//        StringBuilder sb = new StringBuilder().append("jiji=1212").append(s);
//        System.out.println(sb);
//        byte[] gbks1 = s.getBytes("GBK");
//        String s2 = new String(gbks1, "GBK");
//        System.out.println(s2);
    }

    @Test
    public void testData(){
        
    }


}
