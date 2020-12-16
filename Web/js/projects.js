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
  var table = "<thead class='thead-dark'><tr>";
  table += "<th>Titel</th><th>Kunde</th><th>Link</th></tr>";
  table += "</tr></thead><tbody>";

  var x = xmlDoc.getElementsByTagName("projectList");
  for (i = 0; i < x.length; i++) {
    var titles = x[i].getElementsByTagName("title")[0].childNodes[0].nodeValue;
    var customers = x[i].getElementsByTagName("name")[x[i].getElementsByTagName("name").length - 1].childNodes[0].nodeValue;
    table += "<tr><td>" + titles + "</td><td>" + customers + "</td><td><a href='projekt.html?projekt=" + i + "'>Link</a></td></tr>";
  }
  table +="</tbody>"
  document.getElementById("projects").innerHTML = table;
}
loadXML();