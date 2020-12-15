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
  var x = xmlDoc.getElementsByTagName("projectList");
  var projectTitle = x[project].getElementsByTagName("title")[0].childNodes[0].nodeValue;
  document.getElementById("projectTitle").innerHTML = projectTitle;



  var table = "<tr><th>Requirement titel</th><th>Type</th><th>Deadline</th><th>Status</th></tr>";

    console.log(x[project].getElementsByTagName("requirementList"));  // første vi kan se

  for (i = 0; i < x[project].getElementsByTagName("requirementList").length; i++) {

    console.log(x[project].getElementsByTagName("title")[i + x[project].getElementsByTagName("taskList").length - 1] );
    //console.log(x[project].getElementsByTagName("requirementList")[i].childNodes);
    //console.log(x[project].getElementsByTagName("title")[i].childNodes[0].nodeValue);
    //for (i = 0; i < x[project].getElementsByTagName("requirementList")[i].childNodes.title)

    var titles = x[project].getElementsByTagName("title")[i + x[project].getElementsByTagName("taskList").length - 1];
    //var titles = x[project].getElementsByTagName("requirementList");

    table += "<tr><td>" + titles + "</td><td><a href='projekt.html?projekt=" + i + "'>Link</a></td></tr>";
  }
  document.getElementById("requirementTable").innerHTML = table;


}

var searchParams = new URLSearchParams(window.location.search);
var project = searchParams.get("projekt");

loadXML();

function lastSibling(node){
    var tempObj=node.parentNode.lastChild;
    while(tempObj.nodeType!=1 && tempObj.previousSibling!=null){
    tempObj=tempObj.previousSibling;
    }
    return (tempObj.nodeType==1)?tempObj:false;
}