function loadXML() {
  var xhttp = new XMLHttpRequest();

  xhttp.open("GET", '../projects.xml', true);
  xhttp.send();
  xhttp.onreadystatechange = function() {
    if (this.readyState === 4 && this.status === 200) {
      xmlFunction(this.response);
    }
  };

}

function xmlFunction(xml) {
  var parser = new DOMParser();
  var xmlDoc = parser.parseFromString(xml, "text/xml");
  var table = "<tr><th>Titel</th></tr>";
  var x = xmlDoc.getElementsByTagName("projectList");
  for (i = 0; i < x.length; i++) {
    var titles = x[i].getElementsByTagName(
      "title")[0].childNodes[0].nodeValue;
    table += "<tr><td>" + titles + "</td></tr>";
  }
  document.getElementById("projects").innerHTML = table;
}
loadXML();