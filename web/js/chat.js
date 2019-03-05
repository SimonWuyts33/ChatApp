//register function to button click event.
document.getElementById("status-submit").onclick = updateStatus;
document.getElementById("friend-submit").onclick = addFriend;
window.onload = getFriends;

var statusSubmitRequest = new XMLHttpRequest();
var updateFriendsRequest = new XMLHttpRequest();
var addFriendsRequest = new XMLHttpRequest();

function addFriend() {
    var friendInput = document.getElementById("friend-input").value;
    addFriendsRequest.open("POST", "Controller?action=AddFriend");
    addFriendsRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    addFriendsRequest.onreadystatechange = addFriendCallback;
    addFriendsRequest.send("newFriend=" + encodeURIComponent(friendInput));
}
function addFriendCallback () {
    if (addFriendsRequest.status == 200) {
        if (addFriendsRequest.readyState == 4) {
            var serverResponse = JSON.parse(addFriendsRequest.responseText);
            updateErrors(serverResponse.errors);
        }
    }
}

function getFriends(){
    updateFriendsRequest.open("GET", "Controller?action=GetFriends");// true optional param , true asynch, false = synch (depricated)
    updateFriendsRequest.onreadystatechange = updateFriends;
    updateFriendsRequest.send();
}

function updateFriends () {
    if (updateFriendsRequest.status == 200) {
        if (updateFriendsRequest.readyState == 4) {

            var serverResponse = JSON.parse(updateFriendsRequest.responseText);
            //console.log(serverResponse);
            var friends = serverResponse.friends;
            if( friends !== undefined){
                var table = document.getElementById("friend-table");
                table.innerText = "";
                Object.keys(friends).forEach(name =>{
                    var tr = document.createElement("tr");
                    var td1 = document.createElement("td");
                    var td2 = document.createElement("td");
                    td1.innerText = name;
                    td2.innerText = friends[name];
                    tr.appendChild(td1);
                    tr.appendChild(td2);
                    table.appendChild(tr);
                });
            }

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
