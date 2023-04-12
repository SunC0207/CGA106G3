$(document).ready(() => {
    const pageSize = 5;
    let currentPage = 1;

    // 初始化時顯示第一頁
    showTable(currentPage);

    // 監聽分頁按鈕
    $(document).on('click', '.page-link', function () {
        const pageNum = parseInt($(this).data('page'));
        if (pageNum !== currentPage) {
            showTable(pageNum);
        }
    });

    // 顯示表格
    function showTable(pageNum) {
        currentPage = pageNum;
        fetch(`/ceremony/findAll?page=${pageNum}&size=${pageSize}`, {
            method: 'GET',
        }).then((response) => {
            response.json().then(data => {
                const table = document.querySelector('#table');
                const bodyCells = data.content.map(obj => {
                    const cerNo = obj['cerNo'];
                    const cerNoCell = `<td id="${cerNo}" class="cerNo">${cerNo}</td>`;
                    const cerName = obj['cerName'];
                    const cerNameCell = `<td id="${cerName}">${cerName}</td>`;
                    const relNo = obj['relNo'];
                    const relNoCell = `<td id="${relNo}">${relNo}</td>`;
                    return `<tr>${cerNoCell}${cerNameCell}${relNoCell}</tr>`;
                }).join('');
                table.querySelector('tbody').innerHTML = bodyCells;

                // 更新分頁按鈕
                const pagination = document.querySelector('.pagination');
                const totalPage = data.totalPages;
                const startPage = Math.max(1, pageNum - 1);
                const endPage = Math.min(totalPage, pageNum + 1);
                let pageButtons = '';
                for (let i = startPage; i <= endPage; i++) {
                    pageButtons += `<li class="page-item ${i === pageNum ? 'active' : ''}"><a class="page-link" href="#" data-page="${i}">${i}</a></li>`;
                }
                pagination.innerHTML = `
                    <li class="page-item ${pageNum === 1 ? 'disabled' : ''}">
                        <a class="page-link" href="#" data-page="${pageNum - 1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only">Previous</span>
                        </a>
                    </li>
                    ${pageButtons}
                    <li class="page-item ${pageNum === totalPage ? 'disabled' : ''}">
                        <a class="page-link" href="#" data-page="${pageNum + 1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">Next</span>
                        </a>
                    </li>
                `;
                updatebtn();
            });
        });
    }

});
function revertCell(cell, oldValue, input) {
    cell.innerText = oldValue;
    cell.removeChild(input);
}






document.addEventListener('DOMContentLoaded', function () {
    const navreligion = document.querySelector('#religion');
    navreligion.addEventListener('click', function (event) {
        event.preventDefault();
        console.log('click event triggered!');
        fetch(`rel/findAll`, {
            method: 'GET'
        }).then(response => {
            response.json().then(data => {
                const div = document.querySelector('#religion-div');
                div.innerHTML = '';
                data.forEach(rel => {
                    const body = `<a href="" class="dropdown-item">${rel.relName}</a>`;
                    div.innerHTML += body;
                });
            })
        })
    });
});