//register function to button click event.
document.getElementById("status-submit").onclick = updateStatus;
window.onload = getFriends;

var statusSubmitRequest = new XMLHttpRequest();
var updateFriendsRequest = new XMLHttpRequest();


function getFriends(){
    updateFriendsRequest.open("GET", "Controller?action=GetFriends");// true optional param , true asynch, false = synch (depricated)
    updateFriendsRequest.onreadystatechange = updateFriends;
    updateFriendsRequest.send();
}

function updateFriends () {
    if (updateFriendsRequest.status == 200) {
        if (updateFriendsRequest.readyState == 4) {

            var serverResponse = JSON.parse(updateFriendsRequest.responseText);
            var friends = serverResponse.friends;
            var table = document.getElementById("friend-table");
            table.innerText = "";
            Object.keys(friends).forEach(name =>{
                $("<tr>").append($("<td>").html(name)).append($("<td>").html(friends[name])).appendTo(table);
            })




            //updateErrors(serverResponse.errors) //wipes errors every 5 sec...
            setTimeout(getFriends, 5000)
        }
    }
}

function updateStatus (){
    var statusInput = document.getElementById("status-input").value;
    statusSubmitRequest.open("POST", "Controller?action=UpdateStatus");
    statusSubmitRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    statusSubmitRequest.onreadystatechange = getStatus;
    statusSubmitRequest.send("status=" + encodeURIComponent(statusInput));
}

function getStatus () {
    if (statusSubmitRequest.status == 200) {
        if (statusSubmitRequest.readyState == 4) {
            var serverResponse = JSON.parse(statusSubmitRequest.responseText);
            var statusDiv = document.getElementById("status");
            statusDiv.innerHTML=serverResponse.status;
            updateErrors(serverResponse.errors)
        }
    }
}

function updateErrors(errors){
    var ul = document.getElementById("error-list");
    var div = document.getElementById("error-field");
    ul.innerText = ""; //clear errorslist
    div.style.display = "none"; //hide errors

    if(errors === undefined) return; // No errors

    errors.forEach( error =>{
        var li = document.createElement("li");
        li.innerText = error;
        ul.appendChild(li);
    });
    div.style.display = "block"; //show new errors
}
