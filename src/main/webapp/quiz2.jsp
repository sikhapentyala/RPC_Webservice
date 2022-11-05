<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MCDA WebService Demo | TCSS  559  Service  Inc.</title>
</head>
<body>

<div>
<b> Please upload a file </b>
<p> The file should represent the matrix of items, their attributes and their weights. Please indicate if an attribute is beneficiary or not.
The file should be in a JSON format <a href="/sample.json">(sample attached)</a> </p>

<form name="uploadMatrixForm"  action="./quiz2" method="post" enctype="multipart/form-data"> 

<input type="file" name="fileName">
<br>
<input type="submit" value="Upload">

Or Use a demo file

<input type="submit" value="Demo">

</form>

</div>
</body>
</html>