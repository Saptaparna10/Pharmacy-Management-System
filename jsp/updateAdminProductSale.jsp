<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
var prod_nm=document.abc.prod_nm.value;
var prod_ctgry=document.abc.prod_ctgry.value;
var prod_desc=document.abc.prod_desc.value;
var prod_price=document.abc.prod_price.value;
var prod_amt=document.abc.prod_amt.value;
if(prod_nm.length==0 || prod_ctgry.length==0 || prod_desc.length==0 || prod_price.length==0 || prod_amt.length==0)
{
window.alert("ALL FIELDS ARE COMPULSORY");
return false;
}
if (isNaN(prod_price)) {
    window.alert('PRICE MUST BE A NUMBER');
    return false;
}
if (prod_price <= 0) {
    window.alert('PRICE MUST BE A POSITIVE NUMBER');
    return false;
}
if (isNaN(prod_amt)) {
    window.alert('AMOUNT MUST BE A NUMBER');
    return false;
}
if (prod_amt <= 0) {
    window.alert('AMOUNT MUST BE A POSITIVE NUMBER');
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
    <div class="box_icon"><img src="images/admin_logo_resized.jpg" /></div>
    <div class="box_content">
      <div class="box_title">
        <div class="title_icon"><img src="images/mini_icon2.gif" alt="" /></div>
        <h2><span class="dark_blue">Update Product</span></h2>
      </div>
      <form name="abc" method="post" action="updateAdminSaleProduct.do" onsubmit="return validate(this)">
      <jsp:useBean id="adminProductSale" class="com.AdminProduct" scope="request"/>
      <div class="box_text_content"> 
        <div class="box_text">
        <table><tr><td><img src="images/checked.gif" alt="" class="box_icon" /></td><td>Id:</td><td><input type="text" size="15" name="prod_id" readonly="readonly" value="<jsp:getProperty property="prod_id" name="adminProductSale"/>"/></td></tr>
      
      
       <tr><td><img src="images/checked.gif" alt="" class="box_icon" /></td><td>Name:</td><td><input type="text" size="15" name="prod_nm" value="<jsp:getProperty property="prod_nm" name="adminProductSale"/>"/></td></tr>
     
      
       <tr><td><img src="images/checked.gif" alt="" class="box_icon" /></td><td>Category:</td><td><input type="text" size="15" name="prod_ctgry" value="<jsp:getProperty property="prod_ctgry" name="adminProductSale"/>"/></td></tr>
     
      
       <tr><td><img src="images/checked.gif" alt="" class="box_icon" /></td><td>Description:</td><td><textarea rows="2" cols="12" name="prod_desc"><jsp:getProperty property="prod_desc" name="adminProductSale"/></textarea></td></tr>
   
      
        <tr><td><img src="images/checked.gif" alt="" class="box_icon" /></td><td>Price:</td><td><input type="text" size="15" name="prod_price" value="<jsp:getProperty property="prod_price" name="adminProductSale"/>"/></td></tr>
  
      
        <tr><td><img src="images/checked.gif" alt="" class="box_icon" /></td><td>Amount:</td><td><input type="text" size="15" name="prod_amt" value="<jsp:getProperty property="prod_amt" name="adminProductSale"/>"/></td></tr>
     
      
       
       </table>
       </br>
       <h3 align="center"><input type="submit" align="middle" value="Update" name="update"/></h3>
        </div>
      </div>
      </form>
    </div>
    <div class="top_menu">
    <div class="top_menu"><img src="images/updateadminproduct.jpg" width="300" height="300"  align="right" class="login" /> </div> </div>
    
    <div class="clear"></div>
  </div>
  <div id="footer">
    <div class="copyright"> <img src="images/footer_logo.gif" alt="" /> </div>
    <div class="center_footer">&copy; Medical Clinic 2008. All Rights Reserved</div>
    <div style="clear:both;"></div>

    
  </div>
</div>
</body>
</html>