let userid,pwd;
function connectUser(){
    userid=$("#username").val();
    pwd=$("#password").val();
    
    if(validateUser()===false)
    {
        swal("Access Denied!","Please enter userid/password.","error");
        return;
    }
    let data={userid:userid,pwd:pwd};
    let xhr=$.post("LoginControllerServlet",data,processResponse);
    xhr.fail(handleError);
}

function processResponse(responseText){
    if(responseText.trim()==="error")
    {
        swal("Login Denied!","Please enter userid/password.","error");
        return;
    }
    //we now have 2 possibilities ya to show exception.jsp sse response aaega ya to loginresponse.jsp se
    else if(responseText.trim().indexOf("jsessionid")!==-1)
    {
        //hm setTimeout nhi use krenge qk hm chahte h jb user ok pr click kre tb hi kch ho...thus we make use of promise
        //swal ek obj return krta..es6 me khas tor pr react angular ke liye class ka support diya gya h
        //promise ek class h built in is ke pas then method hota hye bolta h jb tk mere upar wali line khtm nhi hota me run nhi krunga
        //it takes 2 func as arg.. first fun if upar wali line me kch error nhi h to first fun chlega else fun 2 chlega
        //but hmari swal line me kch error nhi aaega thus we can drop fun2...fun1 is parameterized(true) fun(false)
        
        let pr=swal("Success","Login Accepted!","success");
        pr.then((value)=>{                             
            window.location=responseText.trim();       
        }); 
        //pr.then(f1,f2){}     f1{value} f2{}
        //or in short we can write it as swal("Success","Login Accepted!","success").then((value)=>{  window.location=responseText.trim(); });
    }
    else
    {
        swal("Access Denied!","Some problem occured "+responseText,"error");
    }
}

function handleError(xhr)
{
    swal("Error!","Problem in server communication:"+xhr.statusText,"error");
}

function validateUser()
{
    if(userid==="" || pwd==="")
        return false;
    return true;
}
