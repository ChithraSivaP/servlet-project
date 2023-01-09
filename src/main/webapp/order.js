function getItems() {
    $('#tableid').DataTable( {
      "ajax": {
          "url": "http://localhost:9090/order",
          "dataSrc": ""
      },
      "columns": [
          { "data": "id" },
          { "data": "ordername" },
          { "data": "buyername" },
          { "data": "res" },
          { "data": "location" }, 
          { "data": "date" },
          { "data": "price" },
          { "data": "user_id"}
  
      ]
  } );
  }
  


  


function display() {
   
    $.ajax({
        url: "http://localhost:9090/new",
        type: "GET",
        dataType: "json",
        success: function (data) {
            console.log(data);
    
        console.log(data[1]);

 
            let output = ''
            let output1 = ''
            let output2 = ''
         

            // loop over the two nested arrays
            for (let objs in data[0]) {
                // set first row
                output = '<div class="d-flex">'
                // get data from first array of objects
                data[0].forEach(item => {
                    output += `
                   
                    <div class="row">
                    <div class="col-md-4">
                    <div class="card mb-4 text-white bg-dark" style="width: 16rem;">
     <img class="card-img-top" src="//placeimg.com/290/180/any" alt="Card image cap" style = "width:16rem">
      <div class="card-body col-sm">
      
        <h5 class="card-title">${item.location}</h5>
        <p class="card-text">${item.res}</p>
        
      </div>
      </div>
      </div>
    </div>`
                })
                // close the row
                output += '</div>'
            }

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
      
        <h5 class="card-title"> Location :${item.location}</h5>
        <p class="card-text">Customer name:${item.buyername}</p>
        
        
      </div>
      </div>
      </div>
    </div>`
                })
                // close the row
                output1 += '</div>'
            }

            for (let objs in data[2]) {
                // set first row
                output2 = '<div class="d-flex">'
                // get data from first array of objects
                data[2].forEach(item => {
                    output2 += `
                    <div class="row">
                    <div class="col-md-4">
                    <div class="card mb-4 text-white bg-dark" style="width: 16rem;">
     <img class="card-img-top" src="//placeimg.com/290/180/any" alt="Card image cap" style = "width:16rem">
      <div class="card-body col-sm">
      
        <h5 class="card-title"> Location :${item}</h5>
        
        
      </div>
      </div>
      </div>
    </div>`
                })
                // close the row
                output2 += '</div>'
            }
           

            main.insertAdjacentHTML('afterbegin', output);
            main.insertAdjacentHTML('afterbegin', output1);
            main.insertAdjacentHTML('afterbegin', output2);
        

        
        },






        error: function (error) {
            console.log(`Error ${error}`);
        },
    });
}