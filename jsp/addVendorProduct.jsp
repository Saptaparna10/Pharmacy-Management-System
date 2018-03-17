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
<script>
function validate()
{
var vprod_id=document.abc.vprod_id.value;
var vprod_nm=document.abc.vprod_nm.value;
var vprod_ctgry=document.abc.vprod_ctgry.value;
var vprod_desc=document.abc.vprod_desc.value;
var vprod_price=document.abc.vprod_price.value;
var vprod_amt=document.abc.vprod_amt.value;
if(vprod_id.length==0 || vprod_nm.length==0 || vprod_ctgry.length==0 || vprod_desc.length==0 || vprod_price.length==0 || vprod_amt.length==0)
{
window.alert("ALL FIELDS ARE COMPULSORY");
return false;
}
if(vprod_id.length>5){
	 window.alert('PRODUCT ID IS TOO LARGE');
	    return false;
}
if (isNaN(vprod_price)) {
    window.alert('PRICE MUST BE A NUMBER');
    return false;
}
if (vprod_price <= 0) {
    window.alert('PRICE MUST BE A POSITIVE NUMBER');
    return false;
}
if (isNaN(vprod_amt)) {
    window.alert('AMOUNT MUST BE A NUMBER');
    return false;
}
if (vprod_amt <= 0) {
    window.alert('AMOUNT MUST BE A POSITIVE NUMBER');
    return false;
}
}
</script>
<div id="main_container">
  <div class="header">
    <div id="logo"><a href="#"><img src="images/logo.png" alt="" width="162" height="54" border="0" /></a></div>
    <div class="right_header">
      <div class="top_menu"> <a href="vendorLogout.do" class="login">log out</a> <a href="#" class="sign_up">report</a> </div>
      <div id="menu">
        <ul>
          <li><a href="vendorHome.jsp">Home</a></li>
         <li><a href="aboutUs.html">About Us</a></li>
          <li><a href="services.html">Services</a></li>
          <li><a href="contactUs.html">Contact Us</a></li>
        </ul>
      </div>
    </div>
  </div>
  <div id="middle_box">
    <div class="middle_box_content"><img src="images/banner_content.jpg" alt="" /><h3>${logname}</h3><h4>${message}</h4></div>
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
    <div class="box_icon"><img src="images/vendor_side_resized.jpg" width="250" height="350" /></div>
    <div class="box_content">
      <div class="box_title">
        <div class="title_icon"><img src="images/mini_icon2.gif" alt="" /></div>
        <h2><span class="dark_blue">Add product</span></h2></div>
           
    
      <div class="box_text_content"> 
        <div class="box_text">
       	 <form name="abc" method="post" action="addVendorProduct.do" onsubmit="return validate(this)">
			<table>
        	<tr>
                	
                    <td>Product id:</td>
                    <td><input type="text" name="vprod_id" /></td>
                </tr>
                <tr>
                	
                    <td>Name</td>
                    <td><input type="text" name="vprod_nm" /></td>
                </tr>
                <tr>
                	
                    <td>Category</td>
                    <td><input type="text" name="vprod_ctgry" /></td>
                </tr>
                <tr>
                	
                    <td>Description</td>
                    <td><textarea rows="2" cols="15" name="vprod_desc"></textarea></td>
                </tr>
                <tr>
                	
                    <td>Price</td>
                    <td><input type="text" name="vprod_price" /></td>
                </tr>
                <tr>
                	
                    <td>Quantity</td>
                    <td><input type="text" name="vprod_amt" /></td>
                </tr>
                
               
                </table>
                <h3 align="center"><input type="submit" value="Submit"/></h3>
                <h3 align="center">*All Fields are Compulsory</h3>
         </form>
        </div>
      </div>   
    </div>
    <div class="top_menu">
      <div class="top_menu"><img src="images/vendor_addproduct.jpg" width="300" height="350"  align="right" class="login" /></div></div>
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