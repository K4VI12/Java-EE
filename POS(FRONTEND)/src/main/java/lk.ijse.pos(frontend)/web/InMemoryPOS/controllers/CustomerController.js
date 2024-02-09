        
        //Save Form Text Field ID
        const savebtn = document.querySelector("#customersavebtn");
        const cusnam = document.querySelector("#cusName");
        const customerid = document.querySelector("#cusId");
        const address = document.querySelector("#cusAddress");
        const Salary = document.querySelector("#cusSalary");

        const customerarr = new Array();
        const table = document.querySelector("#cusdatatable");
        const viewallbtn  =  document.querySelector("#viewallbtn");

        const modal = new bootstrap.Modal(document.querySelector("#cusstaticBackdrop"), {});

        let checkcustomertextfieldAR = [false,false,false,false];

        //This Function For Add New Button
        $('#addnewbtn').click(function(){
          customerid.disabled = false;
          clearcustomerTextfield();
          alltxtFielddisable();
        });

        //This Function For When Click The Save Button Save All Customer Details
        savebtn.addEventListener("click",function(){
            var CustomerINFORMATION = {
                id : $('#cusId').val(),
                name : $('#cusName').val(),
                address : $('#cusAddress').val(),
                salary : $('#cusSalary').val()
            }

            console.log(JSON.stringify(CustomerINFORMATION));
            $.ajax({
                url:'http://localhost:8080/website/customer',
                method:'POST',
                data:JSON.stringify(CustomerINFORMATION),
                contentType: 'application/json',

                success: function(resp){
                    alert('Customer Saved Successfully!')
                    $('#cusdatatable td').parent().remove();
                    GetAllData();
                },
                error:function(resp){
                    alert('This CUstomer ID Already Exists!')
                }
            });
        });

        //This Function For View All Customer
        viewallbtn.addEventListener("click",function(){
            $('#cusdatatable td').parent().remove();
            GetAllData();
        });

        //This Function For Delete Button Action
        $('#deletebtn').click(function(){
            var CustomerINFORMATION = {
                id : $('#cusinputfield').val(),
                name : "Dummy",
                address : "Dummy",
                salary : 1
            }

            $.ajax({
                url:'http://localhost:8080/website/customer',
                method:'DELETE',
                data:JSON.stringify(CustomerINFORMATION),
                contentType: 'application/json',

                success: function(resp){
                    $('#cusdatatable td').parent().remove();
                    GetAllData();
                    $('#cusinputfield').val('');
                },
                error:function(resp){}
            });
        });

        //This Function For Update Customer Button Action
        $('#customerupdatebtn').click(function(){
            var CustomerINFORMATION = {
                id : $('#cusId').val(),
                name : $('#cusName').val(),
                address : $('#cusAddress').val(),
                salary : $('#cusSalary').val()
            }

            $.ajax({
                url:'http://localhost:8080/website/customer',
                method:'PUT',
                data:JSON.stringify(CustomerINFORMATION),
                contentType: 'application/json',

                success: function(resp){
                    $('#cusdatatable td').parent().remove();
                    GetAllData();
                },
                error:function(resp){}
            });
        });

        $('#cusId').keyup(function(){
          if(testValid(/^C00-\d{3,}$/,$('#cusId').val(),'#cusId',cusnam)){
            checkcustomertextfieldAR[0] = 'true';
          }else{
            checkcustomertextfieldAR[0] = 'false';
          }
          console.log("cusID");
        });

        $('#cusName').keyup(function(){
          if(testValid(/^[A-Za-z ]{5,}$/,$('#cusName').val(),'#cusName',address)){
            checkcustomertextfieldAR[1] = 'true';
          }else{
            checkcustomertextfieldAR[1] = 'false';
          }
        });

        $('#cusAddress').keyup(function(){
          if(testValid(/^[A-Za-z ]{5,}$/,$('#cusAddress').val(),'#cusAddress',Salary)){
            checkcustomertextfieldAR[2] = 'true';
          }else{
            checkcustomertextfieldAR[2] = 'false';
          }
        });

        $('#cusSalary').keyup(function(){
          if(testValid(/^[0-9]{2,}([.][0-9]{2})?$/,$('#cusSalary').val(),'#cusSalary','')){
            checkcustomertextfieldAR[3] = 'true';
          }else{
            checkcustomertextfieldAR[3] = 'false';
          }
        });

        //This Function For When Click Some Row Show The Update Form With They Row Value
        function showSaveForm(){
          modal.show();
        }

        //This Function For Set Click Action For Table Row, When Click They Row Get The Data From they Row And Set Data To The Save Form Field 
        function setClickeventForTable(){
          $('#cusdatatable tr').click(function () {
            
            let id = $(this).children().eq(0).text();
            let name = $(this).children().eq(1).text();
            let addres = $(this).children().eq(2).text();
            let salary = $(this).children().eq(3).text();
    
            console.log(address,salary);
            customerid.value = id;
            cusnam.value = name;
            address.value = addres;
            Salary.value = salary;
            
            customerid.disabled = true;

            showSaveForm();
          });
        }

        function GetAllData() {
            $.ajax({
                url:'http://localhost:8080/website/customer',
                method:'GET',
                dataType: 'json',

                success: function(resp){
                    console.log(resp)
                    for (var i in resp) {
                        var ID = resp[i].id;
                        var NAME = resp[i].name;
                        var ADDRESS = resp[i].address;
                        var SALARY = resp[i].salary;
                        console.log(ID);

                        let newCustomer = Object.assign({},Customer);
                        newCustomer.name = NAME;
                        newCustomer.cusid = ID;
                        newCustomer.cusnomber = ADDRESS;
                        newCustomer.cussalry = SALARY;
                        customerAr.push(newCustomer);

                        datarow(ID, NAME, ADDRESS, SALARY);
                    }
                },
                error:function(resp){}
            });
        }

        //This Function For All Data Add Add To The Table
        function datarow(id,nam,address,sal){

          let row = `<tr>
                      <td>${id}</td>
                      <td>${nam}</td>
                      <td>${address}</td>
                      <td>${sal}</td>
                    </tr>`;

          $("#cusdatatable").append(row);
          setClickeventForTable();
        }

        //This Function For Delete Customer From Array
        function deleteCustomer(ID){
          let newArray = [];

          if(checkID(ID)){
            for(i in customerAr){
              if(ID===customerAr[i].cusid){
              }else{
                newArray.push(customerAr[i]);
              }
            }
            customerAr = newArray;
            alert("Successfully Customer Deleted");
            console.log(customerAr);
          }else{
            alert("Something Wrong, Please check & enter correct ID !");
          }
        }

        //This Function For Check the Input ID Alredy Exicts OR Not
        function checkID(ID){
          for(i in customerAr){
            if(ID===customerAr[i].cusid){
              return true;
            }else{
              return false;
            }
          }
        }

        //This Function For Update Customer When Click the Update Button
        function updateCustomer(ID){
          br:for(i in customerAr){
            if(ID===customerAr[i].cusid){
              customerAr[i].cusid = customerid.value;
              customerAr[i].name = cusnam.value;
              customerAr[i].cusnomber = address.value;
              customerAr[i].cussalry = Salary.value;

              break br;
            }
            console.log(customerAr);
          }
          $('#cusdatatable td').parent().remove();
          //getAllData();
        }

        //This Function For When Click The Add new Customer Button , Clear All TextField
        function clearcustomerTextfield(){
          $('#cusId').val('');
          $('#cusName').val('');
          $('#cusAddress').val('');
          $('#cusSalary').val('');
        }

        //This Function For Before Save Some Data Check They Customer ID Alredy Exicts Or Not
        function checkcusID(ID){
          for(i in customerAr){
            if(ID===customerAr[i].cusid){
              return true;
            }
          }
        }

        function testValid(pattern,data,Field,nextField){
          var regexPattern = pattern;
      
          if(regexPattern.test(data)){
            $(Field).css('border', '1px solid gray');
            if(nextField!==''){
              nextField.disabled = false;
              nextField.style.border = '2px solid red';
            }
            return true;
          }else{
            $(Field).css('border', '2px solid red');
            return false;
          }
        }

        function alltxtFielddisable(){
          $('#cusId').css('border', '2px solid red');
          cusnam.disabled = true;
          address.disabled = true;
          Salary.disabled = true;
        }

        function customerFieldArrcheck(){
          if(checkcustomertextfieldAR.length===4){
            for(i in checkcustomertextfieldAR){
              if(checkcustomertextfieldAR[i]==='true'){
              }else{
                return false;
              }
            }
          }else{
            return false;
          }
        }
