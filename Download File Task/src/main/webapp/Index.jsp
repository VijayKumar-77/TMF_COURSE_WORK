<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Download Files</title>
</head>

<body>

    <form action="http://localhost:8080/DownloadFiles/IndexServlet2" method="post">
    
        <!-- input type="radio" id="file1" name="file" value="TXT/File1.txt">
        <label for="File1.txt">File 1</label><br>
        
        <input type="radio" id="file2" name="file" value="TXT/File2.txt">
        <label for="File2.txt">File 2</label><br>
        
        <input type="radio" id="file3" name="file" value="TXT/File3.txt">
        <label for="File3.txt">File 3</label><br>
        
        <input type="radio" id="file4" name="file" value="TXT/File4.txt">
        <label for="File4.txt">File 4</label><br>
         -->
         
        <a href ="IndexServlet2?filename=File1.txt" class ="btn btn-sm btn-primary">Download</a>
        <!-- input type="submit" value="Download" -->
    </form>
   
    
</body>
</html>