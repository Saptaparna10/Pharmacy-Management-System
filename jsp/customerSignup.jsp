<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Medical Clinic</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="style.css" media="screen" />
</head>
<body>
<script language="JavaScript">
function validate()
{
var pass=document.abc.pass.value;
var repass=document.abc.repass.value;
var name=document.abc.name.value;
var address=document.abc.address.value;
var phno=document.abc.phno.value;
var email=document.abc.email.value;
var atpos=email.indexOf("@");
var dotpos=email.lastIndexOf(".");

if(pass.length==0 || repass.length==0 || name.length==0 || address.length==0 || phno.length==0 || email.length==0)
{
window.alert("ALL FIELDS ARE COMPULSORY");
return false;
}
if (pass!=repass) {
    window.alert('PASSWORDS NOT MATCHED');
    return false;
}

if (isNaN(phno)) {
    window.alert('PHONE MUST BE A NUMBER');
    return false;
}

if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length)
{
alert("Not a valid e-mail address");
return false;
}
}
</script>
<div id="main_container">
  <div class="header">
    <div id="logo"><a href="#"><img src="images/logo.png" alt="" width="162" height="54" border="0" /></a></div>
    <div class="right_header">
      <div class="top_menu"></div>
      <div id="menu">
        <ul>
          <li><a  href="customerLogin.jsp">Home</a></li>
          <li><a href="aboutUs.html">About Us</a></li>
          <li><a href="services.html">Services</a></li>
          <li><a href="contactUs.html">Contact Us</a></li>
        </ul>
      </div>
    </div>
  </div>
  <div id="middle_box">
    <div class="middle_box_content"><img src="images/banner_content.jpg" alt="" /><h3>&nbsp;</h3> </div>
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
    <div class="box_icon"><img src="images/customer_logo.jpg" width="250" height="600" /></div>
    <form name="abc" action="customerSignup.do" method="post" onsubmit="return validate(this)">
    <div class="box_content">
	      <div class="box_title">
	      
	        <div class="title_icon"><img src="images/mini_icon2.gif" alt="" /></div>
	        <h3>Profile Update </h3>
	      </div>
	       <jsp:useBean id="custo" class="com.CustomerDetails" scope="request"/>
	      <div class="box_text_content"> <img src="images/checked.gif" alt="" class="box_icon" />
		        <div class="box_text">
		          <p>Username </p>
		          <p>&nbsp;&nbsp;
		            <input type="text" name="cust" value="${logname}" readonly="readonly"/>
		          </p>
		        </div>
	      </div>
	      <div class="box_text_content"> 
	      
	        <p><img src="images/checked.gif" alt="" class="box_icon" />
	        </p>
		        <div class="box_text">
		          <p>Password          </p>
		          <p> 
		            &nbsp;&nbsp;
		            <input type="password" name="pass" />
		          </p>
		        </div>
	      </div>
	      <div class="box_text_content"> 
	      
	        <p><img src="images/checked.gif" alt="" class="box_icon" />
	        </p>
		        <div class="box_text">
		          <p>Retype Password          </p>
		          <p> 
		            &nbsp;&nbsp;
		            <input type="password" name="repass" />
		          </p>
		        </div>
	      </div>
	      <div class="box_text_content"> 
	      
	        <p><img src="images/checked.gif" alt="" class="box_icon" />
	        </p>
		        <div class="box_text">
		          <p>Name          </p>
		          <p> 
		            &nbsp;&nbsp;
		            <input type="text" name="name" value ="<jsp:getProperty property="usr_nm" name="custo"/>"/>
		          </p>
		        </div>
	      </div>
	      <div class="box_text_content"> 
	      
	        <p><img src="images/checked.gif" alt="" class="box_icon" />
	        </p>
		        <div class="box_text">
		          <p>Address          </p>
		          <p> 
		            &nbsp;&nbsp;
		            <input type="text" name="address" value="<jsp:getProperty property="usr_add" name="custo"/>"/>
		          </p>
		        </div>
	      </div>
	      <div class="box_text_content"> 
	      
	        <p><img src="images/checked.gif" alt="" class="box_icon" />
	        </p>
		        <div class="box_text">
		          <p>Contact Number          </p>
		          <p> 
		            &nbsp;&nbsp;
		            <input type="text" name="phno" value="<jsp:getProperty property="usr_phn" name="custo"/>" />
		          </p>
		        </div>
	      </div>
	       <div class="box_text_content"> 
	      
	        <p><img src="images/checked.gif" alt="" class="box_icon" />
	        </p>
		        <div class="box_text">
		          <p>E-mail Id          </p>
		          <p> 
		            &nbsp;&nbsp;
		            <input type="text" name="email" value="<jsp:getProperty property="usr_mail" name="custo"/>"/>
		          </p>
		        </div>
	      </div>

      <h3 align="center"><input type="submit" name="btnLogin" value="Submit"/></h3>
      <h3 align="center">*All Fields are Compulsory</h3>
    </div>
    
    </form>
    <div class="top_menu">
      <div class="top_menu"><img src="images/customersignup.jpg" width="300" height="600"  align="right" class="login" /></div></div>
    <div class="clear"></div>
  </div>
  <div id="footer">
    <div class="copyright"> <img src="images/footer_logo.gif" alt="" /> </div>
    <div class="center_footer">&copy; Medical Clinic 2008. All Rights Reserved</a></div>
    <div style="clear:both;"></div>
    <div id="Downloaded"></div>

    
  </div>
</div>

</body>
</html>