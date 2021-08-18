
function redirectadministratorpage()
{
    swal("Admin!", "Redirecting To Admin Actions Page!", "success").then(value => {
        window.location = "adminactions.jsp";
    });
}
function redirectvotingpage()
{
    swal("Admin!", "Redirecting To Voting Controller Page!", "success").then(value => {
        window.location = "VoterControllerServlet";
    });
}
function manageuser()
{
    swal("Admin!", "Redirecting To User Management Page!", "success").then(value => {
        window.location = "manageuser.jsp";
    });
}
function managecandidate()
{
    swal("Admin!", "Redirecting To Candidate Management Page!", "success").then(value => {
        window.location = "managecandidate.jsp";
    });
}
function showaddcandidateform()
{
    clearAll();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "candidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Add New Candidate</h3>";
//enctype indicates ki form ke sath srf text nhi file bhi h
    newdiv.innerHTML = newdiv.innerHTML + "<form method='POST' enctype='multipart/form-data' id='fileUploadForm'>\n\
<table><tr><th>Candidate Id:</th><td><input type='text' id='cid'></td></tr>\n\
<tr><th>User Id:</th><td><select id='uid'></select></td></tr>\n\
<tr><th>Candidate Name:</th><td><input type='text' id='cname'></td></tr>\n\
<tr><th>City:</th><td><select id='city'></select></td></tr>\n\
<tr><th>Party:</th><td><input type='text' id='party'></td></tr>\n\
<tr><td colspan='2'><input type='file' id='img' name='files' value='Select Image'></td></tr>\n\
<tr><th><input type='button' value='Add Candidate' onclick='addcandidate()' id='addcnd'></th>\n\
<th><input type='reset' value='Clear' onclick='clearText()'></th></tr></table></form>";
    newdiv.innerHTML = newdiv.innerHTML + "<br><span id='addresp'></span>";
    var addcand = $("#result")[0];
    addcand.appendChild(newdiv);//div fouran dikhega
    $("#candidateform").hide();//in 2 lines se animation add krdiya
    $("#candidateform").fadeIn("3500");

    data = {id: "getid"};//ye id wali key isliye bhej rhe h qk form load hote hi servlet call krega or id mangwaega or yhi form tb bhi 
    //servlet call krega jb candidate name or city bulwaenge ye id servlet end pr pta krne ke liye h ki kb kya dena h
    //agr esa nhi krte to fr 2 servlet bnane padte ek se candidate id or dusre se name and city mangwate
    //ye ek servlet se do kaam krwane ke liye h

    $.post("AddCandidateControllerServlet", data, function (responseText) {
        var result = JSON.parse(responseText.trim());
        $("#cid").val(result.cid);
        $('#cid').prop("disabled", true)
        $("#uid").empty();
        $("#uid").append(result.uid);

        $("#uid").change(function ()
        {
            var selectedUid = $("#uid").val();

            if (selectedUid === " ")
            {
                swal("Error", "Please select a user id.", "error");
                return;
            }
            data = {id:"getcity",uid: $("#uid").val()};
            $.post("AddCandidateControllerServlet", data, function (responseText)
            {
                let details = JSON.parse(responseText);
                let city = details.city;
                let username = details.username;

                $("#cname").val(username);
                $("#city").empty();//empty isliye qk phle user ki city list me hi dusri list aa jaegi
                $("#city").append(city);//hm nhi chahte ki usename bhi chnage ho ab isliye disabled
                $("#cname").prop("disabled", true);

            });

        });

    });
}


function addcandidate()
{
    var party=$("#party").val();
    if(party===""||$("#img").val()==="")
    {
        swal("Error!","Party/symbol cannot be empty.","error");
        return;
    }
    data={id:"checkfor party",party:party,city:$("#city").val()};
    $.post("AddCandidateControllerServlet", data, function (responseText)
            {
                let result = responseText.trim();
                if(result==="failure")
                {
                    swal("Error!","Candidate with this party and city alredy present.","error");
                    return;
                }  
                var form = $("#fileUploadForm")[0];
                var data = new FormData(form);//img val se nhi milegi
                //iske liye js ke pas class form data  h wo bolti h agr use form obj'dede to wo esa obj return kregi jo us form ki image rakhega
                //form ke andar jitna binary data h use us var me dal degi
                //is line se img copy hokr ja rhi h us variable me jiska naam h data..jitne binary dta hote sb usme chle jate

                //json imge nhi send krta agr krna h to image ko text me convert krna h using lib Base64 encoding and decoding
                var cid = $("#cid").val();
                var cname = $("#cname").val();
                var city = $("#city").val();
                var party = $("#party").val();
                var uid = $("#uid").val();
                data.append("cid", cid);
                data.append("uid", uid);
                data.append("cname", cname);
                data.append("party", party);
                data.append("city", city);

                $.ajax({
                    type: "POST",
                    enctype: 'multipart/form-data',
                    url: "AddNewCandidateControllerServlet",
                    data: data,
                    processData: false, //process data false mtlb processing server end pr hogi hm nhi kr rhe
                    contentType: false,
                    cache: false,
                    timeout: 600000, // timeout isliye qk bht sara data ja rha h esa ho skta h server end pr time lge
                    success: function (data) {
                        str = data;
                        swal("Admin!", "Candidate added " + str, "success").then((value) => {
                            showaddcandidateform();
                        });
                    },
                    error: function (e) {
                        swal("Admin!", e, "error");
                    }
                });
            });
}
function clearText()
{
    $("#addresp").html("");
}
function removeshowaddcandidateForm()
{
    var contdiv = document.getElementById("result");
    var formdiv = document.getElementById("candidateform");
    if (formdiv !== null)
    {
        $("#candidateform").fadeOut("3500");
        contdiv.removeChild(formdiv);
    }
}

function showcandidate()
{
    clearAll();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "showcandidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "220px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Show Candidate</h3>";
//enctype indicates ki form ke sath srf text nhi file bhi h
    newdiv.innerHTML = newdiv.innerHTML + "<div style='color:white;font-weight:bold;'>Candidate Id:</div><div><select id='cid'></select></div>";
    newdiv.innerHTML = newdiv.innerHTML + "<br><span id=addresp></span>";
    var showcand = $("#result")[0];
    showcand.appendChild(newdiv);//div fouran dikhega
    $("#showcandidateform").hide();//in 2 lines se animation add krdiya
    $("#showcandidateform").fadeIn("3500");

    data = {data: "cid"};
    $.post("ShowCandidateControllerServlet", data, function (responseText) {

        let result = JSON.parse(responseText);
        let cidList = result.cidList;
        $("#cid").append(cidList);
    });
    $("#cid").change(function () {
        var selectedCid = $("#cid").val();

        if (selectedCid === " ") {
            swal("Error", "Please select a candidate id.", "error");
            return;
        }
        data = {data: selectedCid};
        $.post("ShowCandidateControllerServlet", data, function (responseText) {
            clearText();
            let result = JSON.parse(responseText.trim());
            let details = result.details;
            $("#addresp").append(details);
        });

    });
}

function removeshowcandidateform()
{
    var contdiv = document.getElementById("result");
    var formdiv = document.getElementById("showcandidateform");
    if (formdiv !== null)
    {
        $("#showcandidateform").fadeOut("3500");
        contdiv.removeChild(formdiv);
    }
}

function showupdatecandidateform()
{
    clearAll();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "updatecandidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Update Candidate</h3>";
    newdiv.innerHTML = newdiv.innerHTML + "<div style='color:white;font-weight:bold;'>Candidate Id:</div><div><select id='cid'></select></div>";
    newdiv.innerHTML = newdiv.innerHTML + "<br><span id=addresp></span>";
    var showcand = $("#result")[0];
    showcand.appendChild(newdiv);//div fouran dikhega
    $("#showcandidateform").hide();//in 2 lines se animation add krdiya
    $("#showcandidateform").fadeIn("3500");

    data = {data: "cid"};
    $.post("UpdateCandidateControllerServlet", data, function (responseText) {

        let result = JSON.parse(responseText);
        let cidList = result.cidList;
        $("#cid").append(cidList);
    });
    $("#cid").change(function () {
        var selectedCid = $("#cid").val();
        if (selectedCid === " ") {
            swal("Error", "Please select a candidate id.", "error");
            return;
        }
        data = {data: selectedCid};
        $.post("UpdateCandidateControllerServlet", data, function (responseText) {
            clearText();
            let result = JSON.parse(responseText.trim());
            let details = result.details;
            $("#addresp").append(details);
        });

    });
}
function updatecandidate() 
{
    if ($("#city").val() === "" || $("#party").val() === "" || $("#file").val() === "")
        swal("Unable to update!", "Please fill all fields.", "error");
    else
    {
        var form = $("#fileUploadForm")[0];
        var data = new FormData(form);
        var cid = $("#cid").val();
        var cname = $("#cname").val();
        var city = $("#city").val();
        var party = $("#party").val();
        var uid = $("#uid").val();
        data.append("cid", cid);
        data.append("uid", uid);
        data.append("cname", cname);
        data.append("party", party);
        data.append("city", city);

        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "UpdateNewCandidateControllerServlet",
            data: data,
            processData: false, //process data false mtlb processing server end pr hogi hm nhi kr rhe
            contentType: false,
            cache: false,
            timeout: 600000, // timeout isliye qk bht sara data ja rha h esa ho skta h server end pr time lge
            success: function (data) {
                str = data;
                swal("Admin!", "Candidate updated "+str, "success").then((value) => {
                    showupdatecandidateform();
                });
            },
            error: function (e) {
                swal("Admin!", e, "error");
            }
        });
    }
}
function removeshowupdatecandidateform()
{
    var contdiv = document.getElementById("result");
    var formdiv = document.getElementById("updatecandidateform");
    if (formdiv !== null)
    {
        $("#updatecandidateform").fadeOut("3500");
        contdiv.removeChild(formdiv);
    }
}

function clearAll()
{ 
    removeshowaddcandidateForm();
    removeshowcandidateform();
    removeshowupdatecandidateform();
    removeremovecandidateform();    
}

function showremovecandidate()
{
    clearAll();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "removecandidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "220px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Remove Candidate</h3>";
    newdiv.innerHTML = newdiv.innerHTML + "<div style='color:white;font-weight:bold;'>Candidate Id:</div><div><select id='cid'></select></div>";
    newdiv.innerHTML = newdiv.innerHTML + "<br><span id=addresp></span>";
    var showcand = $("#result")[0];
    showcand.appendChild(newdiv);//div fouran dikhega
    $("#removecandidateform").hide();//in 2 lines se animation add krdiya
    $("#removecandidateform").fadeIn("3500");

    data = {data: "cid"};
    $.post("ShowCandidateControllerServlet", data, function (responseText) {

        let result = JSON.parse(responseText);
        let cidList = result.cidList;
        $("#cid").append(cidList);
    });
    $("#cid").change(function () {
        var selectedCid = $("#cid").val();

        if (selectedCid === " ") {
            swal("Error", "Please select a candidate id.", "error");
            return;
        }
        data = {data: selectedCid};
        $.post("ShowCandidateControllerServlet", data, function (responseText) {
            clearText();
            let result = JSON.parse(responseText.trim());
            let details = result.details;
            $("#addresp").append(details);
            $("#addresp").html($("#addresp").html()+"<br><input type='button' value='Delete' onclick='deletecandidate()'/>");
        });

    });
}
function deletecandidate()
{
    var cid=$("#cid").val();
    data = {data:"cid",cid:cid};
    $.post("RemoveCandidateControllerServlet", data, function (responseText) {
        str = responseText.trim();
        swal("Admin!", "Candidate deleted "+str, "success").then((value) => {
        showremovecandidate();
        });
    });
}
function removeremovecandidateform()
{
    var contdiv = document.getElementById("result");
    var formdiv = document.getElementById("removecandidateform");
    if (formdiv !== null)
    {
        $("#removecandidateform").fadeOut("3500");
        contdiv.removeChild(formdiv);
    }
}

function electionresult() {
    swal("Admin!", "Redirecting To Results Page!", "success").then(value => {
        window.location = "results.jsp";
    });  
}