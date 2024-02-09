let cusstate = document.querySelector('#cusstate');
let itmsatate = document.querySelector('#inputitmState');
document.querySelector('#orderidfield').disabled = true;

GetAllITMData();
GetAllData();

let Arr = [];

let bool = false;

cusstate.style.borderm = '2px solid red'

$('#orderidfield').val(genaratenewOID());

function AddCustomerOptoins(){
    document.getElementById("cusstate").innerHTML = "";
    for (i in customerAr) {
        const option = document.createElement("option");
        option.text = customerAr[i].cusid;

        cusstate.appendChild(option);
    }
}

function AddItemOptions(){
    for (i in item) {
        const option = document.createElement("option");
        option.text = item[i].itmcode;
        console.log("itemstate",item[i].itmcode)

        itmsatate.appendChild(option);
    }
}

cusstate.addEventListener("change", function() {
    if(cusstate.value !== ''){
        for(i in customerAr){
            if(cusstate.value === customerAr[i].cusid){
                $('#cusIDfield').val(customerAr[i].cusid);
                $('#cusNameField').val(customerAr[i].name);
                $('#CusSalaryField').val(customerAr[i].cussalry);
                $('#CusNumberField').val(customerAr[i].cusnomber);
            }
        }
    }
});

itmsatate.addEventListener("change", function() {
    console.log(itmsatate.value !== '');
    if(itmsatate.value !== ''){
        for(i in item){
            if(itmsatate.value === item[i].itmcode){
                $('#itIdField').val(item[i].itmcode);
                $('#itemNamefield').val(item[i].itmname);
                $('#itemPriceField').val(item[i].itmprice);
                $('#qtyOnhandFieitm').val(item[i].itmqty);
            }
        }
    }
});

$('#orderQuantityField').keyup(function(){
    $('#orderQuantityField').css('border','2px solid red');
    for(i in item){
        if($('#itIdField').val()===item[i].itmcode){
            if(parseInt($('#orderQuantityField').val()) <= parseInt(item[i].itmqty)){
                $('#orderQuantityField').css('border','2px solid green');
                bool = true;
            }else{
                $('#orderQuantityField').css('border','2px solid red');
                bool = false;
            }
        }
    }
});

$('#additembtnO').click(function(){
    if(bool){
        let ODetails = Object.assign({},orderDetails);

        ODetails.oid = $('#orderidfield').val();
        ODetails.code = $('#itIdField').val();
        ODetails.unitPrice = $('#itemPriceField').val();
        ODetails.qty = $('#orderQuantityField').val()

        Arr.push(ODetails);
        console.log(Arr);
        $('#orderitmtable td').parent().remove();
        getAllOrderData();
    }else{
        $('#orderQuantityField').css('border','2px solid red');
        $('#orderQuantityField').focus();
    }
});

$('#purchObtnO').click(function(){
    let neworder = Object.assign({},order);

    var OrderINFORMATION = {
        orderId : $('#orderidfield').val(),
        date : $('#datefielsO').val(),
        customerId : $('#cusIDfield').val(),
        orderDetailsId : $('#orderidfield').val(),
        itemName : $('#itemNamefield').val(),
        qty : $('#orderQuantityField').val(),
        unitPrice : $('#itemPriceField').val(),
        itemId : $('#itIdField').val()
    }

    console.log(OrderINFORMATION)
    $.ajax({
        url:'http://localhost:8080/website/order',
        method:'POST',
        data:JSON.stringify(OrderINFORMATION),
        contentType: 'application/json',

        success: function(resp){
            //$('#itmtable td').parent().remove();
            //GetAllITMData();
        },
        error:function(resp){}
    });

    Arr = [];
    $('#orderitmtable td').parent().remove();
    clearAllfieldinorderForm();
    $('#orderidfield').val(genaratenewOID());
    alert('Successfully Order Placement !');
});

//This Function For Get All Data From Array
function getAllOrderData(){    
    for(i in Arr){
      let inam;
      for(j in item){
        if(Arr[i].code===item[j].itmcode){
            inam = item[j].itmname;
        }
      }
      console.log(Arr[i]);
      let code = Arr[i].code;
      let unitPrice = Arr[i].unitPrice;
      let qty = Arr[i].qty;

      ortabledatarow(code,inam,unitPrice,qty);
    }
  }

  //This Function For All Data Add Add To The Table
  function ortabledatarow(code,inam,unitPrice,qty){

    let row = `<tr>
                <td>${code}</td>
                <td>${inam}</td>
                <td>${unitPrice}</td>
                <td>${qty}</td>
              </tr>`;

    $("#orderitmtable").append(row);
  }

  function clearAllfieldinorderForm(){
    $('#cusIDfield').val('');
    $('#cusNameField').val('');
    $('#CusSalaryField').val('');
    $('#CusNumberField').val('');
    
    $('#itIdField').val('');
    $('#itemNamefield').val('');
    $('#itemPriceField').val('');
    $('#qtyOnhandFieitm').val('');
  }

  function genaratenewOID(){
    let sentence = orderDB[orderDB.length-1].oid;

    let regex = /(\w+)$/;
    let match = regex.exec(sentence);

    let newOID = ('OID-00'+`${parseFloat('001')+parseFloat(match)}`);
    return newOID;
  }