# Web-Server-Assignment
By : 
Yehezkiel Wiradhika - 5025201086  
Daniel Hermawan - 5025201087  
Aqil Ramadhan Hadiono - 5025201  
Immanuel Maruli Tua Pardede - 5025201166  
### Develop a web server that:
- Serves any file under the website root directory to the client. If the requested file is a text or HTML file, the browser will show the content. If it is a binary file (e.g. PDF, images, documents, etc) the browser will download it. (Hint: use the correct content-type for each file served)
- Shows a list of files and folders when the client requests a directory that does not have index.html inside it. The list must show the file/folder name, last modified date, and size. The name must also be clickable and make the user requests the file/folder when they click it. (Hint: send a temporary HTML string containing the necessary contents)
- Serves multiple websites like Nginx/Apache VirtualHost. The client must be able to access various domains handled by your web server. (Hint: modify /etc/hosts in Linux or C:\Windows\System32\drivers\etc\hosts in Windows to add your own domain names such that your browser can recognise them)
- Has a configuration file that allows us to configure the IP address and port bound by the webserver. The file must also include the root directory of each website handled by your web server.
- Keeps the connection open if the client requests it. (Hint: check the Connection HTTP header. Your web server will not be able to accept another client once the connection is still open, but that is okay)  
  
The web server will be tested using a real web browser (e.g., Chrome or Firefox)
