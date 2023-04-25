


$(document).ready(() => {
  const pages = 10;
  let page = 1;

  show(page);

  $(document).on('click', '.page-link', function () {
    const pageA = parseInt($(this).data('page1'));
    if (pageA !== page) {
      show(pageA);
    }
  });


  function show(pageA) {
    let id = 0;
    page = pageA;
    fetch(`/faq/findAll`, {
      method: 'GET',
    }).then((response) => {
      response.json().then(data => {
        let faqlist = document.getElementById("accordionExample");
        let context = "";


        for (let item of data) {
          context +=
            `
        <div class="accordion-item bg-transparent">
          <h2 class="accordion-header" id="headingOne${id}">
              <button class="accordion-button" type="button" data-bs-toggle="collapse"
                  data-bs-target="#collapseOne${id}" aria-expanded="true" aria-controls="collapseOne">
                  <tbody id="tbody">${item.faqname}</tbody>
              </button>
          </h2>

          <div id="collapseOne${id}" class="accordion-collapse collapse show" aria-labelledby="headingOne"
              data-bs-parent="#accordionExample">
              <div class="accordion-body">
                ${item.faqans}
              </div>
          </div>
        </div>
        `
          id++;
        }
        faqlist.innerHTML = context;


      })

    });
  }
});



//   function show(pageA) {
//     page = pageA;
//     fetch(`/faq/findAll`, {
//       method: 'GET',
//     }).then((response) => {
//       response.json().then(data => {
//         let faqlist = document.getElementById("accordionExample");
//         let context = "";


//         for (let item of data) {
//           context +=
//             `
//             <div class="accordion-item bg-transparent">
//               <h2 class="accordion-header" id="headingOne">
//                   <button class="accordion-button" type="button" data-bs-toggle="collapse"
//                       data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
//                       <tbody id="tbody">${item.faqname}</tbody>
//                   </button>
//               </h2>

//               <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne"
//                   data-bs-parent="#accordionExample">
//                   <div class="accordion-body">
//                     ${item.faqans}
//                   </div>
//               </div>
//             </div>
//             `
//         }
//         faqlist.innerHTML = context;




//       });
//     });

//   }
// });
const searchInput = document.querySelector('#search-input');
const searchBtn = document.querySelector('#search-btn');
const faqResult = document.querySelector('#searchResults');
let id = 0;

searchBtn.addEventListener('click', (e) => {
  e.preventDefault();
  // alert("click......")
  const faqtag = searchInput.value;


  fetch(`/faq/search?faqtag=${faqtag}`, { method: 'GET', })
    .then(response => response.json())
    .then(data => {


      console.log(data);
      let searchResults = ""; // 清空先前的搜索结果
      let faqResult = document.getElementById("accordionExample");


      for (let searchFaq of data) {
        console.log("start for loop")
        console.log(searchFaq.faqname);
        searchResults +=
          `
        <div class="accordion-item bg-transparent">
          <h2 class="accordion-header" id="headingOne">
              <button class="accordion-button" type="button" data-bs-toggle="collapse"
                  data-bs-target="#collapseOne${id}" aria-expanded="true" aria-controls="collapseOne">
                  <tbody id="tbody">${searchFaq.faqname}</tbody>
              </button>
          </h2>

          <div id="collapseOne${id}" class="accordion-collapse collapse show" aria-labelledby="headingOne"
              data-bs-parent="#accordionExample">
              <div class="accordion-body">
                ${searchFaq.faqans}
              </div>
          </div>
        </div>
        `
        id++;
      }
      faqResult.innerHTML = searchResults;
    })
    .catch(error => console.error(error));

});



 //         const bodyTable = data.map(obj => {

        //           const faqno = obj['faqno'];
        //           const faqnoa = `<div id="${faqno}" class="faqno">${faqno}</div>`;
        //           const faqname = obj['faqname'];
        //           const faqnamea = `<div id="${faqname}">${faqname} </div>`;
        //           const faqans = obj['faqans'];
        //           const faqansa = `<div id="${faqans}">${faqans} class="accordion-body"</div>`;
        //           //const faqtag = obj['faqtag'];
        //           //const faqtaga = `<td id="${faqtag}">${faqtag}</td>`;
        //           //const fixButton = `<td><button type="button" class="btn btn-warning m-2">修改</button></td>`;
        //           //const delButton = `<td><button type="button" class="btn btn-danger m-2">刪除</button></td>`;
        //           return `<div>${faqnoa}${faqnamea}${faqansa}</div>`;
        //         }).join('');
        //         // table.querySelector('tbody').innerHTML = bodyTable;

        //         const pagination = document.querySelector('.pagination');
        //         const totalPage = data.totalPages;
        //         const startPage = Math.max(1, pageA - 1);
        //         const endPage = Math.min(totalPage, pageA + 1);
        //         let pageButtons = '';
        //         for (let i = startPage; i <= endPage; i++) {

        //           pageButtons += `<li class="page-item ${i === pageA ? 'active' : ''}"><a class="page-link" href="#" data-page="${i}">${i}</a></li>`;
        //         }
        //         pagination.innerHTML = `<li class="page-item ${pageA === 1 ? 'disabled' : ''}">
        // <a class="page-link" href="#" data-page="${pageA - 1}" aria-label="Previous">
        // <span aria-hidden="true">&laquo;</span>
        // <span class="sr-only">Previous</span>
        // </a>
        // </li>
        // ${pageButtons}
        // <li class="page-item ${pageA === totalPage ? 'disabled' : ''}">
        // <a class="page-link" href="#" data-page="${pageA + 1}" aria-label="Next">
        // <span aria-hidden="true">&raquo;</span>
        // <span class="sr-only">Next</span>
        // </a>
        // </li>`;





