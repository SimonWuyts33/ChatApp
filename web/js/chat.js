//register function to button click event.
document.getElementById("status-submit").onclick = updateStatus;
document.getElementById("friend-submit").onclick = addFriend;
window.onload = getFriends;

const statusSubmitRequest = new XMLHttpRequest();
const updateFriendsRequest = new XMLHttpRequest();
const addFriendsRequest = new XMLHttpRequest();


function addFriend() {
    var friendInput = document.getElementById("friend-input");
    addFriendsRequest.open("POST", "Controller?action=AddFriend");
    addFriendsRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    addFriendsRequest.onreadystatechange = addFriendCallback;
    addFriendsRequest.send("newFriend=" + encodeURIComponent(friendInput.value));
    friendInput.value = "";
}

function addFriendCallback() {
    if (addFriendsRequest.status === 200) {
        if (addFriendsRequest.readyState === 4) {
            var serverResponse = JSON.parse(addFriendsRequest.responseText);
            updateErrors(serverResponse.errors);
        }
    }
}

function getFriends() {
    updateFriendsRequest.open("GET", "Controller?action=GetFriends");// true optional param , true asynch, false = synch (depricated)
    updateFriendsRequest.onreadystatechange = updateFriends;
    updateFriendsRequest.send();
}

function updateFriends() {
    if (updateFriendsRequest.status === 200) {
        if (updateFriendsRequest.readyState === 4) {

            var serverResponse = JSON.parse(updateFriendsRequest.responseText);
            //console.log(serverResponse);
            var friends = serverResponse.friends;
            if (friends !== undefined) {
                var table = document.getElementById("friend-table");
                table.innerText = "";
                Object.keys(friends).forEach(name => {
                    var tr = document.createElement("tr");
                    var td1 = document.createElement("td");
                    var td2 = document.createElement("td");
                    td1.innerText = name;
                    td1.addEventListener("click", openMessageTab);
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

function updateStatus() {
    var statusInput = document.getElementById("status-input");
    statusSubmitRequest.open("POST", "Controller?action=UpdateStatus");
    statusSubmitRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    statusSubmitRequest.onreadystatechange = getStatus;
    statusSubmitRequest.send("status=" + encodeURIComponent(statusInput.value));
    statusInput.value = "";
}

function getStatus() {
    if (statusSubmitRequest.status === 200) {
        if (statusSubmitRequest.readyState === 4) {
            var serverResponse = JSON.parse(statusSubmitRequest.responseText);
            var statusDiv = document.getElementById("status");
            statusDiv.innerHTML = serverResponse.status;
            updateErrors(serverResponse.errors)
        }
    }
}

function updateErrors(errors) {
    var ul = document.getElementById("error-list");
    var div = document.getElementById("error-field");
    ul.innerText = ""; //clear errorslist
    div.style.display = "none"; //hide errors

    if (errors === undefined) return; // No errors

    errors.forEach(error => {
        var li = document.createElement("li");
        li.innerText = error;
        ul.appendChild(li);
    });
    div.style.display = "block"; //show new errors
}


////JQuery, deelopdracht 3

const chatTabsContainer = $("#tabs");
const chatTabHeaderList = $("#tab-headers");

function openMessageTab(event) {
    const name = event.target.innerText;

    if (!$("#" + name).length) {
        createMessageTab(name)
        chatTabsContainer.tabs("option", "active", -1);
    }
}

function createMessageTab(name) {
    chatTabHeaderList.append(
        $("<li>").append(
            $("<a>")
                .attr("href", "#" + name)
                .text(name)
        ));

    chatTabsContainer.append(
        $("<div>")
            .attr("id", name)
            .text("test text")
    );
    chatTabsContainer.tabs("refresh");
}


// Initialize tabs
$(function () {
    chatTabsContainer.tabs({
        "heightStyle": "fill"
    });
});