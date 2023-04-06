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
        fetch(`/PODetail/findAll?page=${pageNum}&size=${pageSize}`, {
            method: 'GET',
        }).then((response) => {
            response.json().then(data => {
                const table = document.querySelector('#table');
                const bodyCells = data.content.map(obj => {
                    const detailno = obj['detailno'];
                    const detailnoCell = `<td id="${detailno}" class="cerno">${detailno}</td>`;
                    const pono = obj['pono'];
                    const ponoCell = `<td id="${pono}">${pono}</td>`;
                    const itemno = obj['itemno'];
                    const itemnoCell = `<td id="${itemno}">${itemno}</td>`;
                    const locno = obj['locno'];
                    const locnoCell = `<td id="${locno}">${locno}</td>`;
                    const iprice = obj['iprice'];
                    const ipriceCell = `<td id="${iprice}">$${iprice}</td>`;
                    const date = obj['date'];
                    const dateCell = `<td id="${date}">${date}</td>`;
                    return `<tr>${detailnoCell}${ponoCell}${itemnoCell}${locnoCell}${ipriceCell}${dateCell}</tr>`;
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
            });
        });
    }

});
function revertCell(cell, oldValue, input) {
    cell.innerText = oldValue;
    cell.removeChild(input);
}