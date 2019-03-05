var webSocket;

document.getElementById("blog-submit").onclick = sendBlog;
window.onload = openSocket;
window.onunload = closeSocket;


function sendBlog(){
    var blog = {};
    blog.name = document.getElementById("name").value;
    blog.comment = document.getElementById("comment").value;
    blog.question = document.getElementById("question").value;
    blog.rating = document.getElementById("rating").value;
    webSocket.send(JSON.stringify(blog));
}

function openSocket() {
    webSocket = new WebSocket("ws://localhost:8080/Blog");
    webSocket.onopen = function (event) {
        console.log("Connection opened")
    };

    webSocket.onmessage = function (event) {
        var blog = JSON.parse(event.data);
        var div = document.getElementById(blog.question);
        var p = document.createElement("p");
        var name = document.createElement("strong");
        name.innerText = blog.name+ " ["+blog.rating+"]: ";
        p.appendChild(name);
        p.append(blog.comment);
        div.appendChild(p);
    };

    webSocket.onclose = function (event) {
        console.log("Connection closed");
    };
}

function closeSocket() {
    webSocket.close();
}