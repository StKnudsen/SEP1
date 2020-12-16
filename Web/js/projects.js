/*
 *  Her henter vi xml filen der er genereret fra vores data
 */
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

/*
 *  Her hiver vi den data fra XML filen ud, og indsætter
 *  i en tabel, til visning på hjemmesiden.
 */
function xmlFunction(xml) {
  var parser = new DOMParser();
  var xmlDoc = parser.parseFromString(xml, "text/xml");

  // HTML Table genereres og værdierne i headeren sættes ind
  var table = "<thead class='thead-dark'><tr>";
  table += "<th>Titel</th><th>Kunde</th><th>Link</th></tr>";
  table += "</tr></thead><tbody>";

// Her findes de data i XML dokumentet der skal sendes præsenteres på hjemmesiden
  var projectList = xmlDoc.getElementsByTagName("projectList");
  for (i = 0; i < projectList.length; i++) {
    var titles = projectList[i].getElementsByTagName("title")[0].childNodes[0].nodeValue;
    var customers = projectList[i].getElementsByTagName("name")[projectList[i].getElementsByTagName("name").length - 1].childNodes[0].nodeValue;

    // Body på HTML Table genereres udfra de data fra XML dokumentet
    table += "<tr><td>" + titles + "</td><td>" + customers + "</td><td><a href='projekt.html?projekt=" + i + "'>Link</a></td></tr>";
  }
  table +="</tbody>"
  // Den nu genereret tabel indsættes her på hjemmesiden
  document.getElementById("projects").innerHTML = table;
}

// Start det ovenstående
loadXML();