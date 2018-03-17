<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Medical Clinic</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="style.css" media="screen" />

</head>
<body>
<script type="text/javascript">
function validate()
{
var id=document.abc.id.value;
var crd_no=document.abc.crd_no.value;
var bnk_pin=document.abc.bnk_pin.value;

if(id.length==0 || crd_no.length==0 || bnk_pin.length==0)
{
window.alert("ALL FIELDS ARE COMPULSORY");
return false;
}

}
</script>
<div id="main_container">
  <div class="header">
    <div id="logo"><a href="#"><img src="images/logo.png" alt="" width="162" height="54" border="0" /></a></div>
    <div class="right_header">
      <div class="top_menu"> <a href="adminLogout.do" class="login">log out</a> <a href="#" class="sign_up">report</a> </div>
      <div id="menu">
        <ul>
          <li><a href="adminHome.jsp">Home</a></li>
          <li><a href="aboutUs.html">About Us</a></li>
          <li><a href="services.html">Services</a></li>
          <li><a href="contactUs.html">Contact Us</a></li>
        </ul>
      </div>
    </div>
  </div>
  <div id="middle_box">
    <div class="middle_box_content"><img src="images/banner_content.jpg" alt="" /></div>
  </div>
   <div class="pattern_bg">
    <div class="pattern_box">
      <div class="pattern_box_icon"><img src="images/icon1.png" alt="" width="70" height="112" /></div>
      <div class="pattern_content">
        <h1>Company<span class="blue"> Vision</span></h1>
        <p class="pat">Jupitar's vision for the next phase of development is to 'Touch a Billion Lives'.</p>
      </div>
    </div>
    <div class="pattern_box">
      <div class="pattern_box_icon"><img src="images/icon2.png" alt="" width="70" height="112" /></div>
      <div class="pattern_content">
        <h1> Mission <span class="blue">Statement</span></h1>
        <p class="pat"> "Our mission is to bring healthcare of International standards within the reach of every individual. We are
committed to the achievement and maintenance of excellence in education, research and healthcare for the
benefit of humanity" </p>
      </div>
    </div>
  </div>
  <div id="main_content">
     <div class="box_icon"><img src="images/admin_logo_resized.jpg" width="250" height="300" /></div>
    <div class="box_content">
      <div class="box_title">
        <div class="title_icon"><img src="images/mini_icon2.gif" alt="" /></div>
        <h2><span class="dark_blue">Give Your Details</span></h2></div>
    <form name="abc" action="adminTansaction.do" method="post"  onsubmit="return validate(this)">     
      <div class="box_text_content"> 
        <div class="box_text">
        <table>
        	<tr>
                	
                    <td><b>ID</b></td>
                    <td><input type="text" name="id" value="${logname}"/></td>
                </tr>
                <tr>
                	
                    <td><b>Credit Card No.</b></td>
                    <td><input type="text" name="crd_no" /></td>
                </tr>
                <tr>
                	
                    <td><b>Pin</b></td>
                    <td><input type="password" name="bnk_pin" /></td>
                </tr>
                <tr>
                	
                    <td><b>Order ID</b></td>
                    <td><input type="text" name="aord_id" value ="${ord_id}" readonly="readonly" /></td>
                </tr>
                
                <tr>
                	
                    <td><b>Amount</b></td>
                    <td><input type="text" name="tot_amt" value ="${money}" readonly="readonly"/></td>
                </tr>
               
                </table>
               
        </div>
      </div>
  
      
    
       <h3 align="center"><input type="submit" name="btnLogin" value="Submit"/></h3>
      </form>
      
    </div>
    <div class="top_menu">
      <div class="top_menu"><img src="images/admincard.jpg" width="300" height="300"  align="right" class="login" /></div></div>
    <div class="clear"></div>
  </div>
  <div id="footer">
    <div class="copyright"> <img src="images/footer_logo.gif" alt="" /> </div>
    <div class="center_footer">&copy; Medical Clinic 2008. All Rights Reserved</div>
    <div style="clear:both;"></div>
    <div id="Downloaded"></div>

    
  </div>
</div>
</body>
</html>