var xhr = new XMLHttpRequest();
xhr.onload = function () {
    showData(this)
};

xhr.open('GET', 'SEP1/projects.xml', false);
var projects = XMLDocument.grtE

function showData(xml) {
    txt = "<table class='projects'>";
    for (i = 0; i < listLength; i++) {
        txt += "<tr><td class='projects'>" + x[i].childNodes[0].nodeValue + "</td></tr>";
    }
    txt += "</table>";
    document.getElementById("id").innerHTML = txt;
}
