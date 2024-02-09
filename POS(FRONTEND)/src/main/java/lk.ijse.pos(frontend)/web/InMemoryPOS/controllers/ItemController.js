const modaltwo = new bootstrap.Modal(document.querySelector("#staticBackdropitem"), {});

let checkitemtextfieldAR = [false,false,false,false];

$('#addnewitmbtn').click(function(){
  clearTextfield();
  console.log("addnewbtn");
  document.querySelector('#itemCode').disabled = false;
  allitmtxtFielddisable();
});

$('#itemsavebtn').click(function(){
    var ItemINFORMATION = {
        code : $('#itemCode').val(),
        name : $('#itemName').val(),
        qty : $('#itemQuantity').val(),
        price : $('#itemPrices').val()
    }

    console.log(JSON.stringify(ItemINFORMATION));
    $.ajax({
        url:'http://localhost:8080/website/item',
        method:'POST',
        data:JSON.stringify(ItemINFORMATION),
        contentType: 'application/json',

        success: function(resp){
            $('#itmtable td').parent().remove();
            GetAllITMData();
        },
        error:function(resp){}
    });
});

//This Function For View All Item
$('#itmviewallbtn').on('click',function(){
    $('#itmtable td').parent().remove();
    GetAllITMData();
 });

 //This Function For Update Item
 $('#itemupdatebtn').click(function(){
     var ItemINFORMATION = {
         code : $('#itemCode').val(),
         name : $('#itemName').val(),
         qty : $('#itemQuantity').val(),
         price : $('#itemPrices').val()
     }

     console.log(JSON.stringify(ItemINFORMATION));
     $.ajax({
         url:'http://localhost:8080/website/item',
         method:'PUT',
         data:JSON.stringify(ItemINFORMATION),
         contentType: 'application/json',

         success: function(resp){
             $('#itmtable td').parent().remove();
             GetAllITMData();
         },
         error:function(resp){}
     });
 });

 //This Function For Delete Item
 $('#itmdeletebtn').click(function(){
     var DeleteItemINFORMATION = {
         code : $('#itmCODfield').val(),
         name : "Dummy",
         qty : 1,
         price : 1
     }

     $.ajax({
         url:'http://localhost:8080/website/item',
         method:'DELETE',
         data:JSON.stringify(DeleteItemINFORMATION),
         contentType: 'application/json',

         success: function(resp){
             $('#itmtable td').parent().remove();
             GetAllITMData();
             $('#itmCODfield').val('');
         },
         error:function(resp){}
     });
 });

 $('#itemCode').keyup(function(){
  if(testValid(/^I00-\d{3,}$/,$('#itemCode').val(),'#itemCode',document.querySelector('#itemName'))){
    checkitemtextfieldAR[0] = 'true';
  }else{
    checkitemtextfieldAR[0] = 'false';
  }
  console.log("cusID");
});

$('#itemName').keyup(function(){
  if(testValid(/^[A-Za-z ]{5,}$/,$('#itemName').val(),'#itemName',document.querySelector('#itemPrices'))){
    checkitemtextfieldAR[1] = 'true';
  }else{
    checkitemtextfieldAR[1] = 'false';
  }
});

$('#itemPrices').keyup(function(){
  if(testValid(/^[0-9]{2,}([.][0-9]{2})?$/,$('#itemPrices').val(),'#itemPrices',document.querySelector('#itemQuantity'))){
    checkitemtextfieldAR[2] = 'true';
  }else{
    checkitemtextfieldAR[2] = 'false';
  }
});

$('#itemQuantity').keyup(function(){
  if(testValid(/^(100|[1-9]\d{0,1})$/,$('#itemQuantity').val(),'#itemQuantity','')){
    checkitemtextfieldAR[3] = 'true';
  }else{
    checkitemtextfieldAR[3] = 'false';
  }
});

 //This Function For Set Click Action For Table Row, When Click They Row Get The Data From they Row And Set Data To The Save Form Field 
 function setitmClickeventForTable(){
    $('#itmtable tr').click(function () {
      
      let itmcode = $(this).children().eq(0).text();
      let itmname = $(this).children().eq(1).text();
      let itmprice = $(this).children().eq(2).text();
      let itmqty = $(this).children().eq(3).text();

      $('#itemCode').val(itmcode);
      $('#itemName').val(itmname);
      $('#itemQuantity').val(itmqty);
      $('#itemPrices').val(itmprice);
      
      document.querySelector('#itemCode').disabled = true;

      showitmSaveForm();
    });
  }

 //This Function For Get All Data
function GetAllITMData() {
    $.ajax({
        url:'http://localhost:8080/website/item',
        method:'GET',
        dataType: 'json',

        success: function(resp){
            console.log(resp)
            for (var i in resp) {
                var CODE = resp[i].code;
                var NAME = resp[i].name;
                var QTY = resp[i].qty;
                var PRICE = resp[i].price;

                let newItem = Object.assign({},Item);
                newItem.itmcode = CODE;
                newItem.itmname = NAME;
                newItem.itmprice = PRICE;
                newItem.itmqty = QTY;
                item.push(newItem);
                console.log(item.length)

                datarowitm(CODE, NAME, PRICE, QTY);
            }
        },
        error: function (xhr, status, error) {

        }
    });
}

  //This Function For All Data Add Add To The Table
  function datarowitm(itmcode,itmname,itmqty,itmprice){

    let row = `<tr>
                <td>${itmcode}</td>
                <td>${itmname}</td>
                <td>${itmprice}</td>
                <td>${itmqty}</td>
              </tr>`;

    $("#itmtable").append(row);
    setitmClickeventForTable();
  }

  function showitmSaveForm(){
    modaltwo.show();
  }

  function updateItem(CODE){
    console.log("ok");
    brI:for(i in item){
        if(CODE===item[i].itmcode){
            item[i].itmcode = $('#itemCode').val();
            item[i].itmname = $('#itemName').val();
            item[i].itmprice = $('#itemPrices').val();
            item[i].itmqty = $('#itemQuantity').val();

          break brI;
        }
        console.log(item);
      }
      $('#itmtable td').parent().remove();
      getAllDataitm();
  }

  //This Function For Delete Item From Array
  function deleteItem(CODE){
    let newArray = [];

    if(checkitemCODE(CODE)){
      for(i in item){
        if(CODE===item[i].itmcode){
        }else{
          newArray.push(item[i]);
        }
      }
      item = newArray;
      alert("Successfully Item Deleted");
      console.log(item);
    }else{
      alert("Something Wrong, Please check & enter correct Item CODE !");
    }
  }

  //This Function For Check the Input CODE Alredy Exicts OR Not
  function checkitemCODE(CODE){
    for(i in item){
      if(CODE===item[i].itmcode){
        return true;
      }else{
        return false;
      }
    }
  }

  //This Function For Before Save Some Data Check They Item CODE Alredy Exicts Or Not
  function checkCode(CODE){
    for(i in item){
      if(CODE===item[i].itmcode){
        return true;
      }
    }
  }

  //This Function For When Click The Add new Item Button , Clear All TextField
  function clearTextfield(){
    $('#itemCode').val('');
    $('#itemName').val('');
    $('#itemQuantity').val('');
    $('#itemPrices').val('');
  }

  function allitmtxtFielddisable(){
    $('#itemCode').css('border', '2px solid red');
    document.querySelector('#itemName').disabled = true;
    document.querySelector('#itemQuantity').disabled = true;
    document.querySelector('#itemPrices').disabled = true;
  }

  function itmFieldArrcheck(){
    if(checkitemtextfieldAR.length===4){
      for(i in checkitemtextfieldAR){
        if(checkitemtextfieldAR[i]==='true'){
        }else{
          return false;
        }
      }
    }else{
      return false;
    }
  }
