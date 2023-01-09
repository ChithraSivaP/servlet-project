const uri = "http://localhost:9090/orderde";
let no = [];
let updateIndex = 0;
//insert function..................................................................
function insert() {
  const addNameTextbox = document.getElementById("id");
  const item ={
    id:document.getElementById("id").value, 
    ordername: document.getElementById("ordername").value,
    buyername: document.getElementById("buyername").value,
    res: document.getElementById("res").value,
    location: document.getElementById("location").value,
    date: document.getElementById("date").value,
    price: document.getElementById("price").value,

  };
  $.ajax({
    type: "POST",
    url: "http://localhost:9090/orderde",
    data: JSON.stringify(item),
    success: function (result) {
        alert("ordersucessful");
    },
    error: function (result) {
      alert("msg");
    },
  });
}





function display() {
   
  $.ajax({
      url: "http://localhost:9090/mycart",
      type: "GET",
      dataType: "json",
      success: function (data) {
          console.log(data);
  
      console.log(data[1]);


          let output1 = ''
     for (let objs in data[1]) {
              // set first row
              output1 = '<div class="d-flex">'
              // get data from first array of objects
              data[1].forEach(item => {
                  output1 += `
                  <div class="row">
                  <div class="col-md-4">
                  <div class="card mb-4 text-white bg-dark" style="width: 16rem;">
   <img class="card-img-top" src="//placeimg.com/290/180/any" alt="Card image cap" style = "width:16rem">
    <div class="card-body col-sm">
    
      <h5 class="card-title"> Location :${item.ordername}</h5>
      <p class="card-text">Customer name:${item.buyername}</p>
      
      
    </div>
    </div>
    </div>
  </div>`
              })
              // close the row
              output1 += '</div>'
          }
        main.insertAdjacentHTML('afterbegin', output1);





      
      },






      error: function (error) {
          console.log(`Error ${error}`);
      },
  });
}



//get all function......................................
function getItems() {
  $.ajax({
    url: "http://localhost:9090/mycart",
    type: "GET",
    dataType: "json",
    success: function (data) {
      // console.log(data);
      var datas = data[0];
      var datas1 = data[1];
      console.log(datas1);
      _displayItems(datas);
    },
    error: function (error) {
      console.log(`Error ${error}`);
    },
  });
}
 // display items.................................
    function _displayItems(datas) {
      const tBody = document.getElementById("no");
      const button = document.createElement("button");
      datas.forEach((item) => {
        let editButton = button.cloneNode(false);
        editButton.innerText = "Edit";
        editButton.setAttribute("onclick", `editItem(${item.id})`);
        let delButton = button.cloneNode(false);
        delButton.innerText = "Delete";
        delButton.setAttribute("onclick", `deleteItem(${item.id})`);
        let tr = tBody.insertRow();
        let td1 = tr.insertCell(0);
        let id = document.createTextNode(item.id);
        td1.appendChild(id);
    let td2 = tr.insertCell(1);
    let ordername = document.createTextNode(item.ordername);
    td2.appendChild(ordername);
    let td3 = tr.insertCell(2);
    let buyername = document.createTextNode(item.buyername);
    td3.appendChild(buyername);
    let td4 = tr.insertCell(3);
    let res = document.createTextNode(item.res);
    td4.appendChild(res);
    let td5 = tr.insertCell(4);
    let location = document.createTextNode(item.location);
    td5.appendChild(location);
    let td6 = tr.insertCell(5);
    let date = document.createTextNode(item.date);
    td6.appendChild(date);
    let td7 = tr.insertCell(6);
    let price = document.createTextNode(item.price);
    td7.appendChild(price);
  });
  no = datas;
}