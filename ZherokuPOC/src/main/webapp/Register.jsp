<%@ page language="java" contentType="text/html; charset=UTF-8"  
 pageEncoding="UTF-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>Farmers | Heroku POC</title>  
</head>  
<body>  
 <center>  
  <div style="color: teal; font-size: 30px">Farmers |  
   Registration Form</div>  
  <form method="post" action="TestServlet">  
   <table>  
    <tr>  
     <td>Name :</td>  
     <td><input type="text" name="name" />  
     </td>  
    </tr>  
    <tr>  
     <td>Gender :</td>  
     <td><input type="radio" name="gender" value="male">Male  
  
      <input type="radio" name="gender" value="female">Female</td>  
    </tr>  
    <tr>  
     <td>Phone :</td>  
     <td><input type="text" name="phone" />  
     </td>  
    </tr>  
    <tr>  
     <td>Email :</td>  
     <td><input type="text" name="email" />  
     </td>  
    </tr>  
    <tr>  
     <td>Work Location :</td>  
     <td><select name="location">  
       <option value="Annex-2">Annex-2</option>  
       <option value="Simi Valley">Simi Valley</option>  
       <option value="Woodland Hills">Woodland Hills</option>      
     </select>  
     </td>  
    </tr>  
    <tr>  
     <td> </td>  
     <td><input type="submit" value="Save">  
     </td>  
    </tr>  
   </table>  
  </form>  
 </center>  
</body>  
</html>  