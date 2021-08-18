function addVote()
{
    var id=$('input[type="radio"][name="flat"]:checked').attr("id");
    data={candidateId:id};
    $.post("AddVoteControllerServlet",data,processresponse);
}
function processresponse(responseText){
    responseText=responseText.trim();
    if(responseText==="success")
    {
        swal("Success","Voting done!","success").then((value)=>{
            window.location="votingresponse.jsp";
        });      
    }
    else
    {
         swal("Failure","Voting failed!","error").then((value)=>{
            window.location="votingresponse.jsp";
        }); 
    }
}

