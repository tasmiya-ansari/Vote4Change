function showusers()
{
    clearAll();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "showuser");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>User Details</h3>";
    newdiv.innerHTML = newdiv.innerHTML + "<br><span id='addresp'></span>";
    var addcand = $("#result")[0];
    addcand.appendChild(newdiv);//div fouran dikhega
    $("#showuser").hide();//in 2 lines se animation add krdiya
    $("#showuser").fadeIn("3500");
    $.post("ShowUsersControllerServlet", function (responseText) {
        $("#addresp").append(responseText.trim());
    });
}
function removeshowuser()
{
    var contdiv = document.getElementById("result");
    var formdiv = document.getElementById("showuser");
    if (formdiv !== null)
    {
        $("#showuser").fadeOut("3500");
        contdiv.removeChild(formdiv);
    }
}

function showremoveuser()
{
    clearAll();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "removeuserform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "220px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Remove User</h3>";
    newdiv.innerHTML = newdiv.innerHTML + "<div style='color:white;font-weight:bold;'>User Id:</div><div><select id='uid'></select></div>";
    newdiv.innerHTML = newdiv.innerHTML + "<br><span id=addresp></span>";
    var showcand = $("#result")[0];
    showcand.appendChild(newdiv);//div fouran dikhega
    $("#removeuserform").hide();//in 2 lines se animation add krdiya
    $("#removeuserform").fadeIn("3500");

    data = {data: "uid"};
    $.post("RemoveUserControllerServlet", data, function (responseText) {

        let result = JSON.parse(responseText);
        let cidList = result.userIdList;
        $("#uid").append(cidList);
    });
    $("#uid").change(function () {
        var selectedUid = $("#uid").val();

        if (selectedUid === " ") {
            swal("Error", "Please select a user id.", "error");
            return;
        }
        data = {data:"getdetails",id:selectedUid};
        $.post("RemoveUserControllerServlet", data, function (responseText) {
            clearText();
            let result = JSON.parse(responseText.trim());
            let details = result.details;
            $("#addresp").append(details);
            $("#addresp").html($("#addresp").html()+"<br><input type='button' value='Delete' onclick='deleteuser()'/>");
        });
    });
}
function deleteuser()
{
    var uid=$("#uid").val();
    data = {data:"deleteuid",uid:uid};
    $.post("RemoveUserControllerServlet", data, function (responseText) {
        str = responseText.trim();
        swal("Admin!", "User deleted "+str, "success").then((value) => {
        showremoveuser();
        });
    });
    
}
function removeremoveuser()
{
    var contdiv = document.getElementById("result");
    var formdiv = document.getElementById("removeuserform");
    if (formdiv !== null)
    {
        $("#removeuserform").fadeOut("3500");
        contdiv.removeChild(formdiv);
    }
}

function clearText()
{
    $("#addresp").html("");
}

function clearAll()
{
    removeshowuser();
    removeremoveuser();
}