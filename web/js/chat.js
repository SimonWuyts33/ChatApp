var statusSubmit = document.getElementById("status-submit");
statusSubmit.onclick = updateStatus;

var statusSubmitRequest = new XMLHttpRequest();

function updateStatus (){
    var statusInput = document.getElementById("status-input").value;
    statusSubmitRequest.open("POST", "Controller?action=UpdateStatus", true);
    statusSubmitRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    statusSubmitRequest.onreadystatechange = getStatus;
    statusSubmitRequest.send("status=" + encodeURIComponent(statusInput));
}

function getStatus () {
    if (statusSubmitRequest.status == 200) {
        if (statusSubmitRequest.readyState == 4) {
            var serverResponse = statusSubmitRequest.responseText;
            var statusDiv = document.getElementById("status");
            statusDiv.innerHTML=serverResponse;
            /*
            if (quoteParagraph == null) {
                quoteParagraph = document.createElement('p');
                quoteParagraph.id = "quoteText";
                var quoteText = document.createTextNode(quote);
                quoteParagraph.appendChild(quoteText);
                quoteDiv.appendChild(quoteParagraph);
            }
            else {
                var quoteText = document.createTextNode(quote);
                quoteParagraph.removeChild(quoteParagraph.childNodes[0]);
                quoteParagraph.appendChild(quoteText);
            }
            setTimeout(getNewQuote, 2000);*/
        }
    }
}

function updateErrors(){
    var errors = statusSubmitRequest.getAttribute("errors")

}