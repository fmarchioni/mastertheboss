package com.mastertheboss;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.annotation.Resource;

@WebServlet(value="/mail")
public class MailServlet extends HttpServlet
{
    @Resource(mappedName="java:jboss/mail/Default")
    private Session mailSession;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        {

            PrintWriter out=response.getWriter();
            try    {
                MimeMessage m = new MimeMessage(mailSession);
                Address from = new InternetAddress("test@mastertheboss.com");
                Address[] to = new InternetAddress[] {new InternetAddress("user@gmail.com") };

                m.setFrom(from);
                m.setRecipients(Message.RecipientType.TO, to);
                m.setSubject("JBoss AS 7 Mail");
                m.setSentDate(new java.util.Date());
                m.setContent("Mail sent from WildFly","text/plain");
                Transport.send(m);
                out.println("Mail sent!");
            }
            catch (javax.mail.MessagingException e)
            {
                e.printStackTrace();
                out.println("Error in Sending Mail: "+e);
            }
        }
    }
}