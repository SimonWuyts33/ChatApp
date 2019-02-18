var statusSubmit = document.getElementById("status-submit");
statusSubmit.onclick = updateStatus;

var statusSubmitRequest = new XMLHttpRequest();

function updateStatus (){
    var statusInput = document.getElementById("status-input").value;
    statusSubmitRequest.open("POST", "Controller?action=UpdateStatus", true);
    statusSubmitRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    statusSubmitRequest.send("status=" + encodeURIComponent(statusInput));
}