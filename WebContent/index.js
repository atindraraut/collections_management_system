const modal_container = document.getElementById("modal_container");
const closeadd = document.getElementById("closeadd");
const openadd = document.getElementById("openadd");
const edit_modal_container=document.getElementById("edit_modal_container");
const openedit = document.getElementById("openedit");
const closeedit = document.getElementById("closeedit");
const delete_modal_container= document.getElementById("delete_modal_container");
const closedelete = document.getElementById("closedelete");
const opendelete = document.getElementById("opendelete");
const addcancel = document.getElementById("addcancel");
const previousbutton = document.getElementById("previousbutton");
const nextbutton = document.getElementById("nextbutton");
const submitadd = document.getElementById("submitadd");
const clearaddcontents =document.getElementById("clearaddcontents");
const editcancel =document.getElementById("editcancel");
const editsubmit = document.getElementById("editsubmit");
const reset_edit_data = document.getElementById("reset_edit_data");
const canceldelete = document.getElementById("canceldelete");
const save_delete = document.getElementById("save_delete");


let editdelete = [];
let myarray;
var start =0;
var end=13;
var newarray = [];
let trimmeddata;
let trimmeddata1;


//let jsondata = fetch("http://localhost:8080/H2HBABBA1820/fetch").then(Response=>{return Response.json}).then(json=>{console.log(json)})





openadd.addEventListener("click",()=>{
    modal_container.classList.add("show");
});

closeadd.addEventListener("click",()=>{
    modal_container.classList.remove("show");
});
addcancel.addEventListener("click",()=>{
    modal_container.classList.remove("show");
});
openedit.addEventListener("click",()=>{
    edit_modal_container.classList.add("show");
});

closeedit.addEventListener("click",()=>{
    edit_modal_container.classList.remove("show");
});

opendelete.addEventListener("click",()=>{
    delete_modal_container.classList.add("show");
});

closedelete.addEventListener("click",()=>{
    delete_modal_container.classList.remove("show");
});

editcancel.addEventListener("click",()=>{
    edit_modal_container.classList.remove("show");
});

canceldelete.addEventListener("click",()=>{
	delete_modal_container.classList.remove("show");
    
});



save_delete.addEventListener("click",()=>{
	for(let k = 0;k<editdelete.length;k++){
		fetch(`http://localhost:8080/H2HBABBA1820/delete?FIELD1=${editdelete[k]}`,
				{
					method:'POST'
				}		
				);
	}
	delete_modal_container.classList.remove("show");
	fetchtabledata();

    
});



clearaddcontents.addEventListener("click",()=>{

	document.getElementById("addcustomer_name").value='';
	document.getElementById("addcustomer_no").value='';
	document.getElementById("addinvoice_no").value='';
	document.getElementById("addinvoice_amount").value='';
	document.getElementById("add_duedate").value='';
	document.getElementById("addnotes").value='';
	
});

reset_edit_data.addEventListener("click",()=>{
	document.getElementById("editinvoice_amount").value='';
	document.getElementById("editnotes").value='';

});

submitadd.addEventListener("click",()=>{
	const addcustomer_name = document.getElementById("addcustomer_name").value;
	const addcustomer_no = document.getElementById("addcustomer_no").value;
	const addinvoice_no = document.getElementById("addinvoice_no").value;
	const addinvoice_amount = document.getElementById("addinvoice_amount").value;
	const add_duedate = document.getElementById("add_duedate").value;
	const addnotes = document.getElementById("addnotes").value;
	fetch(`http://localhost:8080/H2HBABBA1820/add?cust_number=${addcustomer_no}&name_customer=${addcustomer_name}&invoice_id=${addinvoice_no}&total_open_amount=${addinvoice_amount}&due_in_date=${add_duedate}&notes=${addnotes}`,
	{
		method:'POST'
	}		
	);
    modal_container.classList.remove("show");
    fetchtabledata();

	//console.log(`http://localhost:8080/H2HBABBA1820/add?cust_number=${addcustomer_no}&name_customer=${addcustomer_name}&invoice_id=${addinvoice_no}&total_open_amount=${addinvoice_amount}&due_in_date=${add_duedate}&notes=${addnotes}`)
});

editsubmit.addEventListener("click",()=>{
	const editinvoice_amount = document.getElementById("editinvoice_amount").value;
	const editnotes = document.getElementById("editnotes").value;
	fetch(`http://localhost:8080/H2HBABBA1820/edit?total_open_amount=${editinvoice_amount}&notes=${editnotes}&FIELD1=${editdelete[0]}`,
			{
				method:'POST'
			}		
			);
    edit_modal_container.classList.remove("show");
    fetchtabledata();

});

function showfirstpage(){
	console.log(start,end)
	console.log("shoefirstpage",newarray.length)
	if(newarray.length > 0){
		trimmeddata1 = newarray.slice(start,end);
	}else{
		trimmeddata1 = myarray.slice(start,end);
	}
	console.log(trimmeddata1);
	showTabledata(trimmeddata1);
}

nextbutton.addEventListener("click",()=>{
	start +=13;
	end +=13;
	console.log(start)
	console.log(end)
	console.log(newarray.length)
	if(newarray.length > 0){
		trimmeddata = newarray.slice(start,end);
	}else{
		trimmeddata = myarray.slice(start,end);
	}
	
	console.log(trimmeddata);
	showTabledata(trimmeddata);

});

previousbutton.addEventListener("click",()=>{
	start -=13;
	end -=13;
	console.log(start)
	console.log(end)
	if(newarray.length > 0){
		trimmeddata = newarray.slice(start,end);
	}else{
		trimmeddata = myarray.slice(start,end);
	};
	console.log(trimmeddata);
	showTabledata(trimmeddata);
	
	//showTabledata(myarray,start,end);

});



function showTabledata(data){

			var col = [];
			for (var i = 0; i < data.length; i++) {
			    for (var key in data[i]) {
			        if (col.indexOf(key) === -1) {
			            col.push(key);
			        }
			    }
			}
			
			var table = document.createElement("table");
	
			
			 // ADD JSON DATA TO THE TABLE AS ROWS.
	        for (var i = 0; i < data.length; i++) {

	            tr = table.insertRow(-1);

//	            for (var j = 0; j < col.length; j++) {
//	                var tabCell = tr.insertCell(-1);
//	                tabCell.innerHTML = data[i][col[j]];  data[i].FIELD1
//	            }
	            tr.insertCell().innerHTML = `<input type="checkbox" id="CHECKRO"  onclick="foo(${data[i].FIELD1})" name="SelectAll" >`;
	            tr.insertCell().innerHTML = data[i].name_customer;
	            tr.insertCell().innerHTML = data[i].cust_number;
	            tr.insertCell().innerHTML = data[i].invoice_id;
	            tr.insertCell().innerHTML = data[i].total_open_amount;
	            tr.insertCell().innerHTML = data[i].due_in_date;
	            tr.insertCell().innerHTML = data[i].predicted_clear_date;
	            tr.insertCell().innerHTML = 'Null Null NUll....';
	            
	        }

	        // FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
	        var divContainer = document.getElementById("showData");
	        divContainer.innerHTML = "";
	        divContainer.appendChild(table);
	    
				
	
 }

function foo(field1){
	console.log("click");
	console.log(field1);
	editdelete.push(field1);
	activatebutton()
	
}

function activatebutton(){
	if(editdelete.length == 0){
		console.log("now add button is activated");
		openadd.disabled = false;
		openadd.style.border = "1px solid #14AFF1";
		openedit.disabled = true;
		openedit.style.border = "1px solid #97A1A9";
		opendelete.disabled = true;
		opendelete.style.border = "1px solid #97A1A9";
	}else if(editdelete.length ==1){
		console.log("add desabled edit and delete enabled");
		openadd.disabled = true;
		openadd.style.border = "1px solid #97A1A9";
		openedit.disabled = false;
		openedit.style.border = "1px  solid #14AFF1";
		opendelete.disabled = false;
		opendelete.style.border = "1px  solid #14AFF1";
	}else{
		console.log("add edit disabled and delete enabled")
		openadd.disabled = true;
		openadd.style.border = "1px  solid #97A1A9";
		openedit.disabled = true;
		openedit.style.border = "1px  solid #97A1A9";
		opendelete.disabled = false;
		opendelete.style.border = "1px  solid #14AFF1";
	}
}

//this the search box feature
		$('#search-bar').on('keyup',function(){
			var valueofinput = $(this).val()
			console.log('value',valueofinput)
			newarray = searchtable(valueofinput,myarray)
			console.log("newarray",newarray)
			let trimmeddata2 = newarray.slice(start,end);
			showTabledata(trimmeddata2);
		})



function searchtable(value,prearray){
	let finalarray = [];
	
	for(let i=0;i<prearray.length;i++){
		let invoicen = prearray[i].invoice_id;
		if(invoicen.includes(value)){
			finalarray.push(prearray[i])
		}
		
	}
	
	return finalarray;
}

const fetchtabledata = ()=>{
	fetch('http://localhost:8080/H2HBABBA1820/fetch')
	.then(response=>{
		return response.json()
	})
	.then(jsonResult=>{
		
		myarray = jsonResult;
		console.log(myarray);
		showfirstpage();
		activatebutton();
		

		//console.log(jsonResult);	
	})
	.catch(error=>{
		console.log(error)
	})
}



//starts with the start of programme

(
	function(){
		
		fetchtabledata()
	}	
)()




