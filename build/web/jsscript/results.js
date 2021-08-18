function resultbycandidates()
{
    data={id:"getresultbycid"};
     $.post("ElectionResultControllerServlet", data, function (responseText) {
        swal("Result fetched!", "Success", "success");
        $("#result").html(responseText.trim());
    });
}

function resultbyparty()
{
    data={id:"getresultbycity"};
    $.post("ElectionResultControllerServlet", data, function (responseText) {
        swal("Result fetched!", "Success", "success");
        $("#result").html(responseText.trim());
    });
}

function resultbygender()
{
    data={id:"getresultbygender"};
     $.post("ElectionResultControllerServlet", data, function (responseText) {
        swal("Result fetched!", "Success", "success");
        $("#result").html(responseText.trim());
    });
}

