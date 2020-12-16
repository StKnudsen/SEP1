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

function xmlFunction(xml) {
  var parser = new DOMParser();
  var xmlDoc = parser.parseFromString(xml, "text/xml");
  var projectList = xmlDoc.getElementsByTagName("projectList");
  var projectTitle = projectList[project].getElementsByTagName("title")[0].childNodes[0].nodeValue;
  document.getElementById("projectTitle").innerHTML = projectTitle;
  // console.log("Current Project Number = " + project);

  var table = "<thead class='thead-dark'>"

  table += "<tr><th>Requirement titel</th><th>Type</th><th>Deadline</th><th>Status</th></tr></thead>";

  var requirementList = projectList[project].getElementsByTagName("requirementList");
  for (i = 0; i < requirementList.length; i++) {
    var requirement = requirementList[i]; // Sætter requirement til at være et requirementList[i], som indeholder alle requirements
    var requirementTitle = requirement.getElementsByTagName("title")[requirementList[i].getElementsByTagName("title").length - 1].innerHTML; // Sætter requirementTitle til den sidste title der findes. Da alle Tasks kommer før og også har en title, så skal de springes over først
    var requirementType = requirement.getElementsByTagName("type")[requirementList[i].getElementsByTagName("type").length - 1].innerHTML;
    //var requirementDeadline = requirement.getElementsByTagName("deadline")[requirementList[i].getElementsByTagName("deadline").length - 1].innerHTML;
    var requirementStatus = requirement.getElementsByTagName("status")[requirementList[i].getElementsByTagName("status").length - 1].innerHTML; // Sætter requirementStatus til den sidste status der findes. samme grund som ovenfor
    // console.log(requirementStatus);
    table += "<tbody><tr><td>" + requirementTitle + "</td>"
    table += "<td>" + requirementType + "</td>"
    table += "<td>Deadline fra XML... Hvor?</td>"
    table += "<td>" + requirementStatus + "</td></tr></tbody>" // Sætter Status ind i table



    // console.log(projectList[project].getElementsByTagName("title")[i + projectList[project].getElementsByTagName("taskList").length - 1]);
    // console.log(projectList[project].getElementsByTagName("requirementList")[i].childNodes);
    // console.log(projectList[project].getElementsByTagName("title")[i].childNodes[0].nodeValue);
    //for (i = 0; i < projectList[project].getElementsByTagName("requirementList")[i].childNodes.title)

    // var titles = projectList[project].getElementsByTagName("title")[i + projectList[project].getElementsByTagName("taskList").length - 1];
    // var titles = projectList[project].getElementsByTagName("requirementList");

    // table += "<tr><td>" + titles + "</td><td><a href='projekt.html?projekt=" + i + "'>Link</a></td></tr>";
  }
  document.getElementById("requirementTable").innerHTML = table;

}

var searchParams = new URLSearchParams(window.location.search);
var project = searchParams.get("projekt");

loadXML();

function lastSibling(node) {
  var tempObj = node.parentNode.lastChild;
  while (tempObj.nodeType != 1 && tempObj.previousSibling != null) {
    tempObj = tempObj.previousSibling;
  }
  return (tempObj.nodeType == 1) ? tempObj : false;
}