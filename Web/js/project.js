/*
 *  Her henter vi xml filen der er genereret fra vores data
 */
function loadXML() {
  var xhttp = new XMLHttpRequest();

  xhttp.open("GET", '../projects.xml', true);
  xhttp.send();
  xhttp.onreadystatechange = function () {
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

  // Titlen på projektet tages her fra xml dokumentet og indsættes som projekt titel på hjemmesiden
  projectList = xmlDoc.getElementsByTagName("projectList");
  projectTitle = projectList[project].getElementsByTagName("title")[0].childNodes[0].nodeValue;
  document.getElementById("projectTitle").innerHTML = projectTitle;

  // HTML Table genereres og værdierne i headeren sættes ind
  var table = "<thead class='thead-dark'><tr>"
  table += "<th>Requirement titel</th><th>Type</th><th>Deadline</th><th>Status</th>";
  table += "</tr></thead><tbody>";

  // Her findes de data i XML dokumentet der skal sendes præsenteres på hjemmesiden
  var requirementList = projectList[project].getElementsByTagName("requirementList");
  for (i = 0; i < requirementList.length; i++) {
    var requirement = requirementList[i]; // Sætter requirement til at være et requirementList[i], som indeholder alle requirements
    var requirementTitle = requirement.getElementsByTagName("title")[requirementList[i].getElementsByTagName("title").length - 1].innerHTML; // Sætter requirementTitle til den sidste title der findes. Da alle Tasks kommer før og også har en title, så skal de springes over først
    var requirementType = requirement.getElementsByTagName("type")[requirementList[i].getElementsByTagName("type").length - 1].innerHTML;
    //var requirementDeadline = requirement.getElementsByTagName("deadline")[requirementList[i].getElementsByTagName("deadline").length - 1].innerHTML;
    var requirementStatus = requirement.getElementsByTagName("status")[requirementList[i].getElementsByTagName("status").length - 1].innerHTML; // Sætter requirementStatus til den sidste status der findes. samme grund som ovenfor

    // Body på HTML Table genereres udfra de data fra XML dokumentet
    table += "<tr><td>" + requirementTitle + "</td>";
    table += "<td>" + requirementType + "</td>";
    table += "<td>Deadline fra XML... Hvor?</td>";
    table += "<td>" + requirementStatus + "</td></tr>";
  }
  table += "</tbody>";
  //  Den nu genereret tabel indsættes her på hjemmesiden
  document.getElementById("requirementTable").innerHTML = table;
}

/*
 *  Her fanges hvilken parameter der er sendt med til hjemmesidens projekt side
 *    /projekt.html/?projekt=<value>
 *  Denne parameter værdi, bruges til at få data fra det korrekte projekt :)
 */
var searchParams = new URLSearchParams(window.location.search);
var project = searchParams.get("projekt");

// Start det ovenstående
loadXML();
