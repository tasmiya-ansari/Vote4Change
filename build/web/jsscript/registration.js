let username,pwd,cpwd,city,addresss,adhar,email,mobile,gender;//global coz initialization will be done by addUser() and validation by validateUser()

function addUser()
{
    username=$("#username").val();
    pwd=$("#password").val();
    cpwd=$("#cpassword").val();
    city=$("#city").val();
    address=$("#address").val();
    adhar=$("#adhar").val();
    email=$("#email").val();
    mobile=$("#mobile").val();
    gender=$('input[type="radio"][name="gender"]:checked').val();
    if (validateUser()===true)
    {
        if(pwd !==cpwd)
        {
            swal("Error!","Passwords do not match.","error");
            return;
        }
        else
        {
            if(checkEmail()===false)
                return;//return kr jao
            if(checkMobile()===false)
                    return;
            let data={username:username , password:pwd ,city:city , address:address , userid:adhar ,email:email,mobileno:mobile ,gender:gender};
            let xhr=$.post("RegistrationControllerServlet",data,processResponse);
            xhr.fail(handleError);
        }
    }
}
function processResponse(responseText,textStatus,xhr){
    let str=responseText.trim();
    
    if(str==="success")
    {
        swal("Success!","Registration done successfully. You can now login.","success");//agr setTimeOut nhi use krenge to swal ka alg thread chlta h or window.location ka alg...user error msg padh bhi nhi paya or redirect hogya...thus we wait for 3 sec
        setTimeout(redirectUser,3000);
    }    
    else if(str==="uap")
        swal("Error!","Sorry!Userid already present","error");
    else
        swal("Error!","Registration failed ! Try Again","error");
}
function handleError(xhr){
    swal("Error!","Problem in server communication:"+xhr.statusText,"error");
}

function validateUser()
{
    if(username===""||pwd===""|| cpwd==="" || city==="" || address==="" || adhar==="" || email==="" || mobile==="" )
    {
        swal("Error!","All fields are mandatory","error");
        return false;
    }
    return true;
       
}

function checkEmail(){
    let attheratepos=email.indexOf("@");
    let dotpos=email.indexOf(".");
    
    if(attheratepos<1 || dotpos<attheratepos+2 || dotpos+2>=email.length)
    {
        swal("Error!","Plese enter a valid email.","error");
        return false;
    }
    return true;
}

function checkMobile(){
    let arr=mobile,valid=false;
    let length=arr.length;
    if(length===10)
    {
        if(arr[0]>=7&& arr[0]<=9)
        {
            for(var i=1;i<arr.length;i++)
            {
                if(arr[i]>=0 && arr[0]<=9)
                    valid=true;
                    
            }
        }
            
    }
    else if(length===11)
    {
        if(arr[0]==0)
        {
            for(var i=1;i<arr.length;i++)
            {
                if(arr[i]>=0 && arr[i]<=9)
                    valid=true;
                else
                    valid=false;    
            }
        }
    }
    else if (length===12)
    {
        if(arr[0]==9 && arr[1]==1)
        {
            for(var i=1;i<arr.length;i++)
            {
                if(arr[i]>=0 && arr[0]<=9)
                    valid=true;  
            }
        }
    }
    else
        valid=false;
    if(valid==false)
    {
        swal("Error!","Plese enter a valid mobile number.","error");
    }   
    return valid;
}



function redirectUser()
{
    window.location.href="login.html";//is func se js ke through redirect krte h
}