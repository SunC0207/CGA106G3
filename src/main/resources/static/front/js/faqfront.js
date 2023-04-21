const searchInput = document.querySelector('#searchInput');
const searchBtn = document.querySelector('#searchBtn');
const resultTable = document.querySelector('#resultTable');

searchBtn.addEventListener('click', () => {
  const faqtag = searchInput.value;
  if (!faqtag) return;

  fetch(`/faq/faqSearch?faqtag=${faqtag}`)
    .then(response => response.json())
    .then(data => {
      resultTable.innerHTML = '';
      data.forEach(search => {
        const tr = document.createElement('tr');
        const tdTitle = document.createElement('td');
        tdTitle.textContent = search.title;
        tr.appendChild(tdTitle);
        const tdContent = document.createElement('td');
        tdContent.textContent = search.content;
        tr.appendChild(tdContent);
        resultTable.appendChild(tr);
      });
    });
});


$(document).ready(() => {
const pages = 10;
let page = 1;

show(page);

$(document).on('click','.page-link', function(){
const pageA = parseInt($(this).data('page1'));
if(pageA !== page){
show(pageA);
}
} );






function show(pageA){
page = pageA;
fetch(`/faq/findAll`, {
method: 'GET',
}).then((response) => {
response.json().then(data => {
const table = document.querySelector('#table');



const bodyTable = data.map(obj => {

const faqno = obj['faqno'];
const faqnoa = `<td id="${faqno}" class="faqno">${faqno}</td>`;
const faqname = obj['faqname'];
const faqnamea = `<td id="${faqname}">${faqname}</td>`;
const faqans = obj['faqans'];
const faqansa = `<td id="${faqans}">${faqans}</td>`;
//const faqtag = obj['faqtag'];
//const faqtaga = `<td id="${faqtag}">${faqtag}</td>`;
//const fixButton = `<td><button type="button" class="btn btn-warning m-2">修改</button></td>`;
//const delButton = `<td><button type="button" class="btn btn-danger m-2">刪除</button></td>`;
return `<tr>${faqnoa}${faqnamea}${faqansa}</tr>`;
}).join('');
table.querySelector('tbody').innerHTML = bodyTable;

const pagination = document.querySelector('.pagination');
const totalPage = data.totalPages;
const startPage = Math.max(1,pageA - 1);
const endPage = Math.min(totalPage, pageA + 1);
let pageButtons = '';
for(let i = startPage; i <= endPage; i++){

pageButtons += `<li class="page-item ${i === pageA ? 'active' : ''}"><a class="page-link" href="#" data-page="${i}">${i}</a></li>`;
}
pagination.innerHTML =`<li class="page-item ${pageA === 1 ? 'disabled' : ''}">
<a class="page-link" href="#" data-page="${pageA - 1}" aria-label="Previous">
<span aria-hidden="true">&laquo;</span>
<span class="sr-only">Previous</span>
</a>
</li>
${pageButtons}
<li class="page-item ${pageA === totalPage ? 'disabled' : ''}">
<a class="page-link" href="#" data-page="${pageA + 1}" aria-label="Next">
<span aria-hidden="true">&raquo;</span>
<span class="sr-only">Next</span>
</a>
</li>`;



});
});

}
});
